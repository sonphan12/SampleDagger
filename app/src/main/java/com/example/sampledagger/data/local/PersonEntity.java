package com.example.sampledagger.data.local;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "person")
public final class PersonEntity {
    @PrimaryKey(autoGenerate = true)
    private int mId;

    @ColumnInfo(name = "name")
    private final String mName;

    @ColumnInfo(name = "avatarUrl")
    private final String mAvatarUrl;

    public PersonEntity(String name, String avatarUrl) {
        mName = name;
        mAvatarUrl = avatarUrl;
    }

    public String getName() {
        return mName;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }
}
