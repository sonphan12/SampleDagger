package com.example.sampledagger.data.remote.responses;

import com.google.gson.annotations.SerializedName;

public class NameResponse {
    @SerializedName("title")
    private String mTitle;

    @SerializedName("first")
    private String mFirst;

    @SerializedName("last")
    private String mLast;

    public NameResponse(String title, String first, String last) {
        mTitle = title;
        mFirst = first;
        mLast = last;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getFirst() {
        return mFirst;
    }

    public String getLast() {
        return mLast;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setFirst(String first) {
        mFirst = first;
    }

    public void setLast(String last) {
        mLast = last;
    }
}
