package com.example.sampledagger.data.cache;

import com.example.sampledagger.ui.Person;

import java.util.List;

public interface PersonCache {
    void setPersons(List<Person> persons);
    List<Person> getPersons();
    void invalidate();
}
