package com.djordjeratkovic.gymaholic.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "workout_type")
public class WorkoutType {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "type")
    private String type;

    public WorkoutType(int id, @NonNull String type) {
        this.id = id;
        this.type = type;
    }

    @Ignore
    public WorkoutType(@NonNull String type) {
        this.id = 0;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }
}
