package com.example.sampledagger.data.local;

import androidx.lifecycle.LiveData;

import com.example.sampledagger.data.PersonDataSource;
import com.example.sampledagger.ui.Person;

import java.util.List;

import io.reactivex.Completable;

public interface PersonLocalDataSource extends PersonDataSource {
    Completable insertPersons(List<Person> personList);

    LiveData<List<Person>> getPersonLiveData();
}
