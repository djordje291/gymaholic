package com.djordjeratkovic.gymaholic.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.djordjeratkovic.gymaholic.model.Workout;
import com.djordjeratkovic.gymaholic.model.WorkoutType;

@Database(entities = {Workout.class, WorkoutType.class}, version = 1)
public abstract class WorkoutRoomDatabase extends RoomDatabase {

    public static volatile WorkoutRoomDatabase INSTANCE;

    public abstract WorkoutDao workoutDao();

    public static WorkoutRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WorkoutRoomDatabase.class) {
                if (INSTANCE == null) {
                    //create our db
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WorkoutRoomDatabase.class, "workout_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    //da popunis db na pocetku, ne treba ti sem za testiranje
//    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
//
//        @Override
//        public void onOpen(@NonNull SupportSQLiteDatabase db) {
//            super.onOpen(db);
//            new PopulateDbAsync(INSTANCE).execute();
//        }
//    };
//
//    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
//
//        private final WorkoutDao workoutDao;
//
//        public PopulateDbAsync(WorkoutRoomDatabase db) {
//
//            workoutDao = db.workoutDao();
//
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
////            workoutDao.deleteAllWorkoutTypes();
////
////            WorkoutType wt = new WorkoutType("Legs");
////            workoutDao.insertWorkoutType(wt);
////            wt = new WorkoutType("Hips");
////            workoutDao.insertWorkoutType(wt);
//            return null;
//        }
//    }




}
