package com.djordjeratkovic.gymaholic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.util.StringUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.djordjeratkovic.gymaholic.model.Workout;
import com.djordjeratkovic.gymaholic.model.WorkoutType;
import com.djordjeratkovic.gymaholic.model.WorkoutViewModel;
import com.djordjeratkovic.gymaholic.ui.NewWorkoutDialog;
import com.djordjeratkovic.gymaholic.ui.NewWorkoutTypeDialog;
import com.djordjeratkovic.gymaholic.ui.WorkoutAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class WorkoutActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private TextView emptyWorkout;

    private int type;

    private List<Workout> workoutsList = new ArrayList<>();

    private WorkoutViewModel workoutViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);
        emptyWorkout = findViewById(R.id.emptyWorkout);

        emptyWorkout.setVisibility(View.INVISIBLE);

        type = getIntent().getIntExtra("typeId", 0);

        workoutViewModel = ViewModelProviders.of(this).get(WorkoutViewModel.class);

        workoutViewModel.getAllWorkouts(type).observe(WorkoutActivity.this, new Observer<List<Workout>>() {
            @Override
            public void onChanged(List<Workout> workouts) {
                if (!workouts.isEmpty()) {
                    if (!workoutsList.isEmpty()) {
                        workoutsList.clear();
                    }

                    emptyWorkout.setVisibility(View.INVISIBLE);

                    workoutsList.addAll(workouts);
                    setTitle();
                    LinearLayoutManager layoutManager = new LinearLayoutManager(WorkoutActivity.this);
                    recyclerView.setLayoutManager(layoutManager);

                    WorkoutAdapter workoutAdapter = new WorkoutAdapter(WorkoutActivity.this, workoutsList);
                    recyclerView.setAdapter(workoutAdapter);
                    workoutAdapter.notifyDataSetChanged();
                } else {
                    workoutsList.clear();
                    setTitle();
                    if (recyclerView.getAdapter() != null) {
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }
                    emptyWorkout.setVisibility(View.VISIBLE);
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int order = workoutsList.size() + 1;
                NewWorkoutDialog dialog = new NewWorkoutDialog(type, order);
                dialog.show(getSupportFragmentManager(), "tag");
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.workout_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.shareBtn) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            StringBuilder mainText = new StringBuilder();
            List<Workout> list = new ArrayList<>(workoutsList);
            Collections.reverse(list);
            for (Workout workout : list) {
                String text = "Name of the workout: " + workout.getName() + "\n" +
                        "Number of sets: " + String.valueOf(workout.getSet()) + "\n" +
                        "Starting weight: " + String.valueOf(workout.getStartingWeight()) + "kg \n" +
                        "Expected time: " + String.valueOf(workout.getExpectedTime()) + "min \n\n";
                mainText.append(text);
                sendIntent.putExtra(Intent.EXTRA_TEXT, mainText.toString());
            }
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void setTitle() {
        String tempTitle =getIntent().getStringExtra("typeName");
        String title = tempTitle.substring(0,1).toUpperCase() + tempTitle.substring(1).toLowerCase();
        int time = 0;

        for (Workout workout : workoutsList) {
            time += workout.getExpectedTime();
        }
        if (time != 0) {
            setTitle(title + " ( " + String.valueOf(time) + " min )");
        } else {
            setTitle(title);
        }
    }
}