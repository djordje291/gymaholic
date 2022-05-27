package com.djordjeratkovic.gymaholic.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.sql.Time;

@Entity(tableName = "workout",
        foreignKeys = {@ForeignKey(entity = WorkoutType.class,
                parentColumns = "id",
                childColumns = "workout_type",
                onDelete = ForeignKey.CASCADE)})
public class Workout {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "set")
    private int set;

    @ColumnInfo(name = "starting_weight")
    private int startingWeight;

    @ColumnInfo(name = "expected_time")
    private int expectedTime;

    @ColumnInfo(name = "workout_type")
    private int workoutType;

    @ColumnInfo(name = "order")
    private int order;

    public Workout(int id, String name, int set, int startingWeight, int expectedTime, int workoutType, int order) {
        this.id = id;
        this.name = name;
        this.set = set;
        this.startingWeight = startingWeight;
        this.expectedTime = expectedTime;
        this.workoutType = workoutType;
        this.order = order;
    }

    @Ignore
    public Workout(String name, int set, int startingWeight, int expectedTime, int workoutType, int order) {
        this.id = 0;
        this.name = name;
        this.set = set;
        this.startingWeight = startingWeight;
        this.expectedTime = expectedTime;
        this.workoutType = workoutType;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }

    public int getStartingWeight() {
        return startingWeight;
    }

    public void setStartingWeight(int startingWeight) {
        this.startingWeight = startingWeight;
    }

    public int getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(int expectedTime) {
        this.expectedTime = expectedTime;
    }

    public int getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(int workoutType) {
        this.workoutType = workoutType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
