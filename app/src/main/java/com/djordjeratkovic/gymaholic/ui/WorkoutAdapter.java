package com.djordjeratkovic.gymaholic.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.djordjeratkovic.gymaholic.R;
import com.djordjeratkovic.gymaholic.model.Workout;
import com.djordjeratkovic.gymaholic.model.WorkoutViewModel;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> {

    private Context context;
    private List<Workout> workoutList;
    private WorkoutViewModel workoutViewModel;

    public WorkoutAdapter(Context context, List<Workout> workoutList) {
        this.context = context;
        this.workoutList = workoutList;
    }

    @NonNull
    @Override
    public WorkoutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.workout_card, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutAdapter.ViewHolder holder, int position) {
        Workout workout = workoutList.get(position);

        holder.workoutName.setText(workout.getName());
        holder.sets.setText(String.valueOf(workout.getSet()));
        holder.startingWeight.setText(String.valueOf(workout.getStartingWeight()) + " " + context.getString(R.string.kg));
        holder.expectedTime.setText(String.valueOf(workout.getExpectedTime()) + " " + context.getString(R.string.min));

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteDialog deleteDialog = new DeleteDialog(workout.getId(), 2);
                FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                deleteDialog.show(fragmentManager, "tag");
            }
        });
        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewWorkoutDialog newWorkoutDialog = new NewWorkoutDialog(workout.getWorkoutType(), workout);
                FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                newWorkoutDialog.show(fragmentManager, "tag");
            }
        });

        setMoveButtons(holder, workout);

        switch (position % 16) {
            case 0:
                holder.workoutIcon.setBackgroundResource(R.drawable.ic_1);
                break;
            case 1:
                holder.workoutIcon.setBackgroundResource(R.drawable.ic_13);
                break;
            case 2:
                holder.workoutIcon.setBackgroundResource(R.drawable.ic_3);
                break;
            case 3:
                holder.workoutIcon.setBackgroundResource(R.drawable.ic_4);
                break;
            case 4:
                holder.workoutIcon.setBackgroundResource(R.drawable.ic_5);
                break;
            case 5:
                holder.workoutIcon.setBackgroundResource(R.drawable.ic_6);
                break;
            case 6:
                holder.workoutIcon.setBackgroundResource(R.drawable.ic_7);
                break;
            case 7:
                holder.workoutIcon.setBackgroundResource(R.drawable.ic_8);
                break;
            case 8:
                holder.workoutIcon.setBackgroundResource(R.drawable.ic_15);
                break;
            case 9:
                holder.workoutIcon.setBackgroundResource(R.drawable.ic_10);
                break;
            case 10:
                holder.workoutIcon.setBackgroundResource(R.drawable.ic_16);
                break;
            case 11:
                holder.workoutIcon.setBackgroundResource(R.drawable.ic_12);
                break;
            case 12:
                holder.workoutIcon.setBackgroundResource(R.drawable.ic_2);
                break;
            case 13:
                holder.workoutIcon.setBackgroundResource(R.drawable.ic_14);
                break;
            case 14:
                holder.workoutIcon.setBackgroundResource(R.drawable.ic_9);
                break;
            case 15:
                holder.workoutIcon.setBackgroundResource(R.drawable.ic_11);
                break;
        }
    }

    private void setMoveButtons(WorkoutAdapter.ViewHolder holder, Workout workout) {
        workoutViewModel = ViewModelProviders.of((FragmentActivity) context).get(WorkoutViewModel.class);

        if (workoutList.isEmpty()) {
            holder.moveDown.setVisibility(View.INVISIBLE);
            holder.moveUp.setVisibility(View.INVISIBLE);
        } else {
            if (workout.getId() != workoutList.get(0).getId()) {
                holder.moveUp.setVisibility(View.VISIBLE);
                holder.moveUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int tempOrder = workout.getOrder();
                        int tempId = 0;
                        for (Workout workout1 : workoutList) {
                            if (workout1.getOrder() == tempOrder - 1) {
                                tempId = workout1.getId();
                            }
                        }
                        workoutViewModel.updateWorkoutOrder(workout.getId(), tempOrder - 1);
                        workoutViewModel.updateWorkoutOrder(tempId, tempOrder);
                    }
                });
            } else {
                holder.moveUp.setVisibility(View.INVISIBLE);
            }
            if (workout.getId() != workoutList.get(workoutList.size() - 1).getId()) {
                holder.moveDown.setVisibility(View.VISIBLE);
                holder.moveDown.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int tempOrder = workout.getOrder();
                        int tempId = 0;
                        for (Workout workout1 : workoutList) {
                            if (workout1.getOrder() == tempOrder + 1) {
                                tempId = workout1.getId();
                            }
                        }
                        workoutViewModel.updateWorkoutOrder(workout.getId(), tempOrder + 1);
                        workoutViewModel.updateWorkoutOrder(tempId, tempOrder);
                    }
                });
            } else {
                holder.moveDown.setVisibility(View.INVISIBLE);
            }
        }

    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageButton moveUp, moveDown, deleteBtn, editBtn;
        private TextView sets, startingWeight, expectedTime, workoutName;
        private CardView cardView;

        private ImageView workoutIcon;

        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            context = ctx;

            moveUp = itemView.findViewById(R.id.moveUp);
            moveDown = itemView.findViewById(R.id.moveDown);

            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            editBtn = itemView.findViewById(R.id.editBtn);

            sets = itemView.findViewById(R.id.sets);
            startingWeight = itemView.findViewById(R.id.startingWeight);
            expectedTime = itemView.findViewById(R.id.expectedTime);
            workoutName = itemView.findViewById(R.id.workoutName);

            cardView = itemView.findViewById(R.id.cardView);

            workoutIcon = itemView.findViewById(R.id.workoutIcon);
        }
    }
}
