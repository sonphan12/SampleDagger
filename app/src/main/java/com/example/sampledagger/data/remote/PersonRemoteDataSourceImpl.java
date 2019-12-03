package com.example.sampledagger.data.remote;

import com.example.sampledagger.data.PersonDataMapper;
import com.example.sampledagger.data.PersonDataSource;
import com.example.sampledagger.data.remote.responses.PersonResultResponse;
import com.example.sampledagger.ui.Person;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class PersonRemoteDataSourceImpl implements PersonDataSource {

    private final PersonService mService;

    public PersonRemoteDataSourceImpl(PersonService service) {
        mService = service;
    }

    @Override
    public Observable<List<Person>> getPersons() {
        return mService.getPersons()
                .map(new Function<PersonResultResponse, List<Person>>() {
                    @Override
                    public List<Person> apply(PersonResultResponse personResultResponse) throws Exception {
                        return PersonDataMapper.fromResponse(personResultResponse);
                    }
                });
    }
}
