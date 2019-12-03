package com.example.sampledagger.data;

import com.example.sampledagger.ui.Person;

import java.util.List;

import io.reactivex.Observable;

public interface PersonDataSource {
    Observable<List<Person>> getPersons();
}
