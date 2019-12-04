package com.example.sampledagger.di;

import android.content.Context;

import com.example.sampledagger.data.PersonDataSource;
import com.example.sampledagger.data.PersonRepository;
import com.example.sampledagger.data.PersonRepositoryImpl;
import com.example.sampledagger.data.cache.PersonCache;
import com.example.sampledagger.data.local.AppDatabase;
import com.example.sampledagger.data.local.PersonDao;
import com.example.sampledagger.data.local.PersonLocalDataSource;
import com.example.sampledagger.data.local.PersonLocalDataSourceImpl;
import com.example.sampledagger.data.remote.PersonRemoteDataSourceImpl;
import com.example.sampledagger.data.remote.PersonService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class StorageModule {
    @Provides
    @Singleton
    public PersonService provideService(Retrofit retrofit) {
        return retrofit.create(PersonService.class);
    }

    @Provides
    @Singleton
    @Named("remote_data_source")
    public PersonDataSource provideRemoteDataSource(PersonService service) {
        return new PersonRemoteDataSourceImpl(service);
    }

    @Provides
    @Singleton
    public PersonDao providePersonDao(Context context) {
        return AppDatabase.getDatabase(context).personDao();
    }

    @Provides
    @Singleton
    @Named("local_data_source")
    public PersonLocalDataSource provideLocalDataSource(PersonDao personDao) {
        return new PersonLocalDataSourceImpl(personDao);
    }

    @Provides
    @Singleton
    public PersonRepository provideRepository(
            @Named("remote_data_source") PersonDataSource remoteDataSource,
            @Named("local_data_source") PersonLocalDataSource localDataSource,
            PersonCache cache
    ) {
        return new PersonRepositoryImpl(remoteDataSource, localDataSource, cache);
    }
}
