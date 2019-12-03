package com.example.sampledagger.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(PersonEntity personEntity);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertList(List<PersonEntity> personEntityList);

    @Query("SELECT * from person ORDER BY name ASC")
    LiveData<List<PersonEntity>> getPersonsLiveData();

    @Query("SELECT * from person ORDER BY name ASC")
    List<PersonEntity> getPersons();
}
