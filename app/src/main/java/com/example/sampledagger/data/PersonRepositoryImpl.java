package com.example.sampledagger.data;

import androidx.lifecycle.LiveData;

import com.example.sampledagger.data.cache.PersonCache;
import com.example.sampledagger.data.local.PersonLocalDataSource;
import com.example.sampledagger.ui.Person;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public final class PersonRepositoryImpl implements PersonRepository {

    private final PersonDataSource mRemoteDataSource;

    private final PersonLocalDataSource mLocalDataSource;

    private final PersonCache mCache;

    public PersonRepositoryImpl(PersonDataSource remoteDataSource,
                                PersonLocalDataSource localDataSource, PersonCache cache) {
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
        mCache = cache;
    }

    @Override
    public Completable fetchPersons() {
        return Completable.fromObservable(
                mRemoteDataSource
                        .getPersons()
                        .flatMap(new Function<List<Person>, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(List<Person> people) throws Exception {
                                return mLocalDataSource.insertPersons(people).toObservable();
                            }
                        })
        );
    }

    @Override
    public LiveData<List<Person>> getPersonsLiveData() {
        return mLocalDataSource.getPersonLiveData();
    }

    @Override
    public Completable insertPersons(List<Person> personList) {
        return mLocalDataSource.insertPersons(personList);
    }
}
