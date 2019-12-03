package com.example.sampledagger.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.sampledagger.R;
import com.example.sampledagger.data.PersonDataSource;
import com.example.sampledagger.data.PersonRepository;
import com.example.sampledagger.data.PersonRepositoryImpl;
import com.example.sampledagger.data.cache.PersonCache;
import com.example.sampledagger.data.cache.PersonCacheImpl;
import com.example.sampledagger.data.local.AppDatabase;
import com.example.sampledagger.data.local.PersonLocalDataSource;
import com.example.sampledagger.data.local.PersonLocalDataSourceImpl;
import com.example.sampledagger.data.remote.PersonRemoteDataSourceImpl;
import com.example.sampledagger.data.remote.PersonService;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private PersonRepository mRepository;

    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private RecyclerView mRvPersons;
    private PersonAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList();

        PersonLocalDataSource localDataSource =
                new PersonLocalDataSourceImpl(AppDatabase.getDatabase(getApplicationContext()).personDao());
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        PersonService service = retrofit.create(PersonService.class);

        PersonDataSource remoteDataSource = new PersonRemoteDataSourceImpl(service);
        PersonCache cache = new PersonCacheImpl();
        mRepository = new PersonRepositoryImpl(remoteDataSource, localDataSource, cache);

        mCompositeDisposable.add(
                mRepository.fetchPersons()
                        .subscribeOn(Schedulers.io())
                        .subscribe()
        );

        mRepository.getPersonsLiveData().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(List<Person> people) {
                mAdapter.setData(people);
            }
        });
    }

    private void initList() {
        mRvPersons = findViewById(R.id.rvPersons);
        mAdapter = new PersonAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRvPersons.setLayoutManager(layoutManager);
        mRvPersons.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.dispose();
    }
}
