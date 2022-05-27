package com.djordjeratkovic.gymaholic.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.djordjeratkovic.gymaholic.MainActivity;
import com.djordjeratkovic.gymaholic.R;
import com.djordjeratkovic.gymaholic.model.WorkoutType;
import com.djordjeratkovic.gymaholic.model.WorkoutViewModel;

import java.util.Objects;

public class DeleteDialog extends DialogFragment {

    private int type;
    private int i;

    private WorkoutViewModel workoutViewModel;


    public DeleteDialog(int type, int i) {
        this.type = type;
        this.i = i;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        workoutViewModel = ViewModelProviders.of(this).get(WorkoutViewModel.class);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Are you sure you want to delete this?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (i == 1) {
                            workoutViewModel.deleteWorkoutType(type);
                        } if (i == 2) {
                            workoutViewModel.deleteWorkout(type);
                        }
                        Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Objects.requireNonNull(DeleteDialog.this.getDialog()).cancel();
            }
        });

        return builder.create();
    }
}
