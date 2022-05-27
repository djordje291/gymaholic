package com.djordjeratkovic.gymaholic.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.djordjeratkovic.gymaholic.util.WorkoutRepository;

import java.util.List;

public class WorkoutViewModel extends AndroidViewModel {

    private WorkoutRepository workoutRepository;
    private LiveData<List<Workout>> allWorkouts;
    private LiveData<List<WorkoutType>> allWorkoutTypes;

    public WorkoutViewModel(@NonNull Application application) {
        super(application);
        workoutRepository = new WorkoutRepository(application);
        allWorkoutTypes = workoutRepository.getAllWorkoutTypes();

    }

    public LiveData<List<Workout>> getAllWorkouts(int type) {
        return workoutRepository.getAllWorkouts(type);
    }
    public LiveData<List<WorkoutType>> getAllWorkoutTypes() {
        return allWorkoutTypes;
    }

    public void insertWorkout(Workout workout) {
        workoutRepository.insertWorkout(workout);
    }

    public void insertWorkoutType(WorkoutType workoutType) {
        workoutRepository.insertWorkoutType(workoutType);
    }

    public void deleteWorkoutType(int type) {
        workoutRepository.deleteWorkoutType(type);
    }

    public void deleteWorkout(int id) {
        workoutRepository.deleteWorkout(id);
    }

    public void updateWorkout(Workout workout) {
        workoutRepository.updateWorkout(workout);
    }

    public void updateWorkoutOrder(int id, int order) {
        workoutRepository.updateWorkoutOrder(id, order);
    }
}
