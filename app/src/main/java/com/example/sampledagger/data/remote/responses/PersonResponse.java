package com.example.sampledagger.data.remote.responses;

import com.google.gson.annotations.SerializedName;

public class PersonResponse {
    @SerializedName("name")
    private final NameResponse mName;

    @SerializedName("picture")
    private final PictureResponse mAvatar;

    public PersonResponse(NameResponse name, PictureResponse avatar) {
        mName = name;
        mAvatar = avatar;
    }

    public NameResponse getName() {
        return mName;
    }

    public PictureResponse getAvatar() {
        return mAvatar;
    }
}
