package com.example.sampledagger.data.remote.responses;

import com.google.gson.annotations.SerializedName;

public class PictureResponse {
    @SerializedName("medium")
    private String mAvatar;

    public PictureResponse(String avatar) {
        mAvatar = avatar;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        mAvatar = avatar;
    }
}
