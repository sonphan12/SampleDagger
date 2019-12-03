package com.example.sampledagger.data.remote;

import com.example.sampledagger.data.remote.responses.PersonResultResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface PersonService {
    @GET("/api/?results=25&inc=name,picture")
    Observable<PersonResultResponse> getPersons();
}
