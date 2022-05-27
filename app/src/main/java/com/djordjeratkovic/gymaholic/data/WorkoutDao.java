package com.djordjeratkovic.gymaholic.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.djordjeratkovic.gymaholic.model.Workout;
import com.djordjeratkovic.gymaholic.model.WorkoutType;

import java.util.List;

@Dao
public interface WorkoutDao {
    //CRUD

    @Insert
    void insertWorkout(Workout workout);

    @Insert
    void insertWorkoutType(WorkoutType workoutType);

    @Query("DELETE FROM workout")
    void deleteAllWorkout();

    @Query("DELETE FROM workout_type")
    void deleteAllWorkoutTypes();

    @Query("DELETE FROM workout WHERE id = :id")
    int deleteAWorkout(int id);

    @Query("DELETE FROM workout_type WHERE id = :id")
    int deleteAWorkoutType(int id);

    @Query("SELECT * FROM workout WHERE workout_type = :type  ORDER BY `order`  ASC")
    LiveData<List<Workout>> getAllWorkouts(int type);

    @Query("SELECT * FROM workout_type ORDER BY id ASC")
    LiveData<List<WorkoutType>> getAllWorkoutTypes();

    @Update
    void updateWorkout(Workout... workouts);

    @Query("UPDATE workout SET `order` = :order WHERE id = :id")
    int updateWorkoutOrder(int id, int order);

}
