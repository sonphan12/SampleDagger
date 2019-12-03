package com.example.sampledagger.data.local;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.sampledagger.data.PersonDataMapper;
import com.example.sampledagger.ui.Person;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.functions.Action;

public class PersonLocalDataSourceImpl implements PersonLocalDataSource {
    private final PersonDao mPersonDao;

    public PersonLocalDataSourceImpl(PersonDao personDao) {
        mPersonDao = personDao;
    }

    @Override
    public Completable insertPersons(final List<Person> personList) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                mPersonDao.insertList(PersonDataMapper.toEntities(personList));
            }
        });
    }

    @Override
    public Observable<List<Person>> getPersons() {
        return Observable.just(PersonDataMapper.fromEntities(mPersonDao.getPersons()));
    }

    @Override
    public LiveData<List<Person>> getPersonLiveData() {
        return Transformations.map(mPersonDao.getPersonsLiveData(), new Function<List<PersonEntity>, List<Person>>() {
            @Override
            public List<Person> apply(List<PersonEntity> entities) {
                return PersonDataMapper.fromEntities(entities);
            }
        });
    }
}
