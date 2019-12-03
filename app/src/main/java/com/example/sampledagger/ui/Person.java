package com.example.sampledagger.ui;

public class Person {
    private final String mName;
    private final String mAvatarUrl;

    public Person(String name, String avatarUrl) {
        mName = name;
        mAvatarUrl = avatarUrl;
    }

    public String getName() {
        return mName;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }
}
