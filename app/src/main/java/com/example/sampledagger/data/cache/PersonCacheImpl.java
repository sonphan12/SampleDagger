package com.example.sampledagger.data.cache;

import androidx.annotation.Nullable;

import com.example.sampledagger.ui.Person;

import java.util.List;

public class PersonCacheImpl implements PersonCache {
    private List<Person> data = null;
    @Override
    public void setPersons(List<Person> persons) {
        data = persons;
    }

    @Override
    @Nullable
    public List<Person> getPersons() {
        return data;
    }

    @Override
    public void invalidate() {
        data = null;
    }
}
