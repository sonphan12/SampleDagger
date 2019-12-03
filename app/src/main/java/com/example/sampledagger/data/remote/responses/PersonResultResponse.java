package com.example.sampledagger.data.remote.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonResultResponse {

    @SerializedName("results")
    private List<PersonResponse> mResults = null;

    public List<PersonResponse> getResults() {
        return mResults;
    }

    public void setResults(List<PersonResponse> results) {
        this.mResults = results;
    }
}
