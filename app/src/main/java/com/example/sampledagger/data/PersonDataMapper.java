package com.example.sampledagger.data;

import com.example.sampledagger.data.local.PersonEntity;
import com.example.sampledagger.data.remote.responses.PersonResponse;
import com.example.sampledagger.data.remote.responses.PersonResultResponse;
import com.example.sampledagger.ui.Person;

import java.util.ArrayList;
import java.util.List;

public final class PersonDataMapper {

    private PersonDataMapper() {
    }

    public static PersonEntity toEntity(Person person) {
        if (person == null) {
            return new PersonEntity("", "");
        }
        return new PersonEntity(person.getName(), person.getAvatarUrl());
    }

    public static Person fromEntity(PersonEntity entity) {
        if (entity == null) {
            return new Person("", "");
        }
        return new Person(entity.getName(), entity.getAvatarUrl());
    }

    public static List<Person> fromEntities(List<PersonEntity> entities) {
        final List<Person> result = new ArrayList<>();
        if (entities == null) {
            return result;
        }
        for (PersonEntity entity : entities) {
            result.add(fromEntity(entity));
        }
        return result;
    }

    public static List<PersonEntity> toEntities(List<Person> personList) {
        final List<PersonEntity> result = new ArrayList<>();
        if (personList == null) {
            return result;
        }
        for (Person person : personList) {
            result.add(toEntity(person));
        }
        return result;
    }

    public static List<Person> fromResponse(PersonResultResponse resultResponse) {
        final List<Person> result = new ArrayList<>();
        if (resultResponse == null) {
            return result;
        }
        for (PersonResponse personResponse : resultResponse.getResults()) {
            Person person = new Person(
                    personResponse.getName().getFirst() + " " + personResponse.getName().getLast(),
                    personResponse.getAvatar().getAvatar()
            );
            result.add(person);
        }
        return result;
    }
}
