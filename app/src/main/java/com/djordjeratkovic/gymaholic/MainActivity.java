package com.djordjeratkovic.gymaholic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.djordjeratkovic.gymaholic.model.Workout;
import com.djordjeratkovic.gymaholic.model.WorkoutType;
import com.djordjeratkovic.gymaholic.model.WorkoutViewModel;
import com.djordjeratkovic.gymaholic.ui.NewWorkoutDialog;
import com.djordjeratkovic.gymaholic.ui.NewWorkoutTypeDialog;
import com.djordjeratkovic.gymaholic.ui.WorkoutTypeAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMain;
    private FloatingActionButton fab;
    private TextView emptyGreet;

    private WorkoutViewModel workoutViewModel;

    private List<WorkoutType> workoutTypesList = new ArrayList<>();

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_try);

        recyclerViewMain = findViewById(R.id.recyclerViewMain);
        fab = findViewById(R.id.fabMain);
        emptyGreet = findViewById(R.id.emptyGreet);

        emptyGreet.setVisibility(View.INVISIBLE);


        workoutViewModel = ViewModelProviders.of(this).get(WorkoutViewModel.class);

        workoutViewModel.getAllWorkoutTypes().observe(MainActivity.this, new Observer<List<WorkoutType>>() {
            @Override
            public void onChanged(List<WorkoutType> workoutTypes) {
                if (!workoutTypes.isEmpty()) {
                    if (!workoutTypesList.isEmpty()) {
                        workoutTypesList.clear();
                    }

                    workoutTypesList.addAll(workoutTypes);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                    recyclerViewMain.setLayoutManager(layoutManager);

                    WorkoutTypeAdapter workoutTypeAdapter = new WorkoutTypeAdapter(MainActivity.this, workoutTypesList);
                    recyclerViewMain.setAdapter(workoutTypeAdapter);
                    workoutTypeAdapter.notifyDataSetChanged();

                    emptyGreet.setVisibility(View.INVISIBLE);
                } else {
                    workoutTypesList.clear();
                    if (recyclerViewMain.getAdapter() != null) {
                        recyclerViewMain.getAdapter().notifyDataSetChanged();
                    }
                    emptyGreet.setVisibility(View.VISIBLE);
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewWorkoutTypeDialog dialog = new NewWorkoutTypeDialog();
                dialog.show(getSupportFragmentManager(), "tag");
            }
        });

    }


}