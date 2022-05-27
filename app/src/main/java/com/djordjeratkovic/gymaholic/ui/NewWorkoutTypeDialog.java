package com.djordjeratkovic.gymaholic.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.djordjeratkovic.gymaholic.R;
import com.djordjeratkovic.gymaholic.model.WorkoutType;
import com.djordjeratkovic.gymaholic.model.WorkoutViewModel;

import java.util.Objects;

public class NewWorkoutTypeDialog extends DialogFragment {

    private EditText newWorkoutType;

    private WorkoutViewModel workoutViewModel;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_workout_type_dialog, null);
        newWorkoutType = view.findViewById(R.id.newWorkoutType);

        newWorkoutType.requestFocus();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!newWorkoutType.getText().toString().trim().isEmpty()) {

                          // moze sve
                            workoutViewModel = ViewModelProviders.of(getActivity()).get(WorkoutViewModel.class);
                            WorkoutType workoutType = new WorkoutType(newWorkoutType.getText().toString().trim());
                            workoutViewModel.insertWorkoutType(workoutType);

                        } else {
                            Toast.makeText(getActivity(), "Fill in the field", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Objects.requireNonNull(NewWorkoutTypeDialog.this.getDialog()).cancel();
            }
        });

        return builder.create();
    }

}
