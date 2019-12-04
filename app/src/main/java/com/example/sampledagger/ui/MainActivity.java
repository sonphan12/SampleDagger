package com.example.sampledagger.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampledagger.MyApplication;
import com.example.sampledagger.R;
import com.example.sampledagger.data.PersonRepository;
import com.example.sampledagger.di.AppComponent;
import com.example.sampledagger.di.ApplicationModule;
import com.example.sampledagger.di.DaggerAppComponent;
import com.example.sampledagger.di.NetworkModule;
import com.example.sampledagger.di.StorageModule;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Inject
    PersonRepository mRepository;

    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private RecyclerView mRvPersons;
    private PersonAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList();

        injectComponent();

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

    private void injectComponent() {
        AppComponent appComponent = MyApplication.getInstance().getAppComponent();
        if (appComponent != null) {
            appComponent.inject(this);
        }
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
