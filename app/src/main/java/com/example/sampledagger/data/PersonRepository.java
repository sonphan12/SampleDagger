package com.example.sampledagger.data;

import androidx.lifecycle.LiveData;

import com.example.sampledagger.ui.Person;

import java.util.List;

import io.reactivex.Completable;

public interface PersonRepository {

    Completable fetchPersons();

    LiveData<List<Person>> getPersonsLiveData();

    Completable insertPersons(List<Person> personList);
}
