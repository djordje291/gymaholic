package com.djordjeratkovic.gymaholic.util;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.djordjeratkovic.gymaholic.data.WorkoutDao;
import com.djordjeratkovic.gymaholic.data.WorkoutRoomDatabase;
import com.djordjeratkovic.gymaholic.model.Workout;
import com.djordjeratkovic.gymaholic.model.WorkoutType;

import java.util.List;

public class WorkoutRepository {

    private WorkoutDao workoutDao;
    private LiveData<List<Workout>> allWorkouts;
    private LiveData<List<WorkoutType>> allWorkoutTypes;

    public WorkoutRepository(Application application) {
        WorkoutRoomDatabase db = WorkoutRoomDatabase.getDatabase(application);
        workoutDao = db.workoutDao();
        allWorkoutTypes = workoutDao.getAllWorkoutTypes();
    }

    public LiveData<List<Workout>> getAllWorkouts(int type) {
        return workoutDao.getAllWorkouts(type);
    }

    public LiveData<List<WorkoutType>> getAllWorkoutTypes() {
        return allWorkoutTypes;
    }

    public void insertWorkout(Workout workout) {
        new InsertWorkoutAsyncTask(workoutDao).execute(workout);
    }

    public void insertWorkoutType(WorkoutType workoutType) {
        new InsertWorkoutTypeAsyncTask(workoutDao).execute(workoutType);
    }

    public void deleteWorkoutType(int type) {
        new DeleteWorkoutTypeAsyncTask(workoutDao, type).execute();
    }

    public void deleteWorkout(int id) {
        new DeleteWorkoutAsyncTask(workoutDao, id).execute();
    }

    public void updateWorkout(Workout workout) {
        new UpdateWorkoutAsyncTask(workoutDao, workout).execute();
    }

    public void updateWorkoutOrder(int id, int order) {
        new UpdateWorkoutOrderAsyncTask(workoutDao, id, order).execute();
    }

    //classa za insert workout na background thread
    private class InsertWorkoutAsyncTask extends AsyncTask<Workout, Void, Void> {

        private WorkoutDao asyncTaskDao;

        public InsertWorkoutAsyncTask(WorkoutDao dao) {
            asyncTaskDao = dao;

        }

        @Override
        protected Void doInBackground(Workout... params) {
            asyncTaskDao.insertWorkout(params[0]);
            return null;
        }
    }

    private class InsertWorkoutTypeAsyncTask extends AsyncTask<WorkoutType, Void, Void> {

        private WorkoutDao asyncTaskDao;

        public InsertWorkoutTypeAsyncTask(WorkoutDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(WorkoutType... workoutTypes) {
            asyncTaskDao.insertWorkoutType(workoutTypes[0]);
            return null;
        }
    }

    private class DeleteWorkoutTypeAsyncTask extends AsyncTask<Void, Void, Void> {

            private WorkoutDao asyncDao;
            private int type;

            public DeleteWorkoutTypeAsyncTask(WorkoutDao dao, int i) {
                asyncDao = dao;
                type = i;
            }

        @Override
        protected Void doInBackground(Void... voids) {
                asyncDao.deleteAWorkoutType(type);
            return null;
        }
    }

    private class DeleteWorkoutAsyncTask extends AsyncTask<Void, Void, Void>  {

        private WorkoutDao asyncDao;
        private int type;

        public DeleteWorkoutAsyncTask(WorkoutDao asyncDao, int type) {
            this.asyncDao = asyncDao;
            this.type = type;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            asyncDao.deleteAWorkout(type);
            return null;
        }
    }

    private class UpdateWorkoutAsyncTask extends AsyncTask<Void , Void, Void> {

        private WorkoutDao asyncDao;
        private Workout workout;

        public UpdateWorkoutAsyncTask(WorkoutDao asyncDao, Workout workout) {
            this.asyncDao = asyncDao;
            this.workout = workout;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            asyncDao.updateWorkout(workout);
            return null;
        }
    }

    private class UpdateWorkoutOrderAsyncTask extends AsyncTask<Void, Void, Void> {

        private WorkoutDao asyncDao;
        private int id;
        private int order;


        public UpdateWorkoutOrderAsyncTask(WorkoutDao asyncDao,int id, int order) {
            this.asyncDao = asyncDao;
            this.id = id;
            this.order = order;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            asyncDao.updateWorkoutOrder(id, order);
            return null;
        }
    }

}
