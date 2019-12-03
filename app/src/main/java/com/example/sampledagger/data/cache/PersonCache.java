package com.example.sampledagger.data.cache;

import androidx.annotation.Nullable;

import com.example.sampledagger.ui.Person;

import java.util.List;

import javax.inject.Inject;

public class PersonCache {
    private List<Person> data = null;

    @Inject
    public PersonCache(){
    }

    public void setPersons(List<Person> persons) {
        data = persons;
    }

    @Nullable
    public List<Person> getPersons() {
        return data;
    }

    public void invalidate() {
        data = null;
    }
}
