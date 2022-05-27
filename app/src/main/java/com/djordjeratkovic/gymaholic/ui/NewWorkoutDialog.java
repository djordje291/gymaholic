package com.djordjeratkovic.gymaholic.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.djordjeratkovic.gymaholic.R;
import com.djordjeratkovic.gymaholic.model.Workout;
import com.djordjeratkovic.gymaholic.model.WorkoutType;
import com.djordjeratkovic.gymaholic.model.WorkoutViewModel;

import java.util.Objects;

public class NewWorkoutDialog extends DialogFragment {

    private WorkoutViewModel workoutViewModel;

    private EditText name, sets, weight, time;

    private  int type;
    private Workout wt;
    private int order;

    public NewWorkoutDialog(int type, Workout wt) {
        this.type = type;
        this.wt = wt;
    }

    public NewWorkoutDialog(int type, int order) {
        this.type = type;
        this.order = order;
        wt = null;
    }
    //TODO: poboljsaj dijaloge sve

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_workout_dialog, null);

        //findViewById
        name = view.findViewById(R.id.workoutNameDialog);
        sets = view.findViewById(R.id.setsDialog);
        weight = view.findViewById(R.id.startingWeightDialog);
        time = view.findViewById(R.id.expectedTimeDialog);

        name.requestFocus();

        if (wt == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setView(view)
                    .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (!TextUtils.isEmpty(name.getText().toString().trim()) && !TextUtils.isEmpty(sets.getText().toString().trim())
                                    && !TextUtils.isEmpty(weight.getText().toString().trim()) && !TextUtils.isEmpty(time.getText().toString().trim())) {
                                // moze sve
                                workoutViewModel = ViewModelProviders.of(getActivity()).get(WorkoutViewModel.class);
                                Workout workout = new Workout(name.getText().toString().trim(), Integer.parseInt(sets.getText().toString().trim()),
                                        Integer.parseInt(weight.getText().toString().trim()), Integer.parseInt(time.getText().toString().trim()), type, order);
                                workoutViewModel.insertWorkout(workout);

                            } else {
                                Toast.makeText(getActivity(), "Fill in all the fields", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Objects.requireNonNull(NewWorkoutDialog.this.getDialog()).cancel();
                }
            });

            return builder.create();
        } else {
            name.setText(wt.getName());
            sets.setText(String.valueOf(wt.getSet()));
            weight.setText(String.valueOf(wt.getStartingWeight()));
            time.setText(String.valueOf(wt.getExpectedTime()));

            int id = wt.getId();

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setView(view)
                    .setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (!TextUtils.isEmpty(name.getText().toString().trim()) && !TextUtils.isEmpty(sets.getText().toString().trim())
                                    && !TextUtils.isEmpty(weight.getText().toString().trim()) && !TextUtils.isEmpty(time.getText().toString().trim())) {
                                workoutViewModel = ViewModelProviders.of(getActivity()).get(WorkoutViewModel.class);
                                wt.setName(name.getText().toString().trim());
                                wt.setSet(Integer.parseInt(sets.getText().toString().trim()));
                                wt.setStartingWeight(Integer.parseInt(weight.getText().toString().trim()));
                                wt.setExpectedTime(Integer.parseInt(time.getText().toString().trim()));
                                workoutViewModel.updateWorkout(wt);
                            } else {
                                Toast.makeText(getActivity(), "Fill in all the fields", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Objects.requireNonNull(NewWorkoutDialog.this.getDialog()).cancel();
                }
            });

            return builder.create();
        }
    }
}
