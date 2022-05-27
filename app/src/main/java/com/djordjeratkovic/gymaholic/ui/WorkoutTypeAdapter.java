package com.djordjeratkovic.gymaholic.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.djordjeratkovic.gymaholic.MainActivity;
import com.djordjeratkovic.gymaholic.R;
import com.djordjeratkovic.gymaholic.WorkoutActivity;
import com.djordjeratkovic.gymaholic.model.WorkoutType;

import java.util.List;

public class WorkoutTypeAdapter extends RecyclerView.Adapter<WorkoutTypeAdapter.ViewHolder> {

    private Context context;
    private List<WorkoutType> workoutTypeList;

    public WorkoutTypeAdapter(Context context, List<WorkoutType> workoutTypeList) {
        this.context = context;
        this.workoutTypeList = workoutTypeList;
    }

    @NonNull
    @Override
    public WorkoutTypeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.workout_type_card, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutTypeAdapter.ViewHolder holder, int position) {
            WorkoutType workoutType = workoutTypeList.get(position);
            switch (position % 7) {
                case 0:
                    holder.imageViewType.setBackgroundResource(R.drawable.image1);
                    break;
                case 1:
                    holder.imageViewType.setBackgroundResource(R.drawable.image2);
                    break;
                case 2:
                    holder.imageViewType.setBackgroundResource(R.drawable.image3);
                    break;
                case 3:
                    holder.imageViewType.setBackgroundResource(R.drawable.image4);
                    break;
                case 4:
                    holder.imageViewType.setBackgroundResource(R.drawable.image5);
                    break;
                case 5:
                    holder.imageViewType.setBackgroundResource(R.drawable.image6);
                    break;
                case 6:
                    holder.imageViewType.setBackgroundResource(R.drawable.image7);
                    break;
            }

            holder.mainWorkoutType.setText(workoutType.getType());

            holder.imageViewType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, WorkoutActivity.class);
                    intent.putExtra("typeName", workoutType.getType());
                    intent.putExtra("typeId", workoutType.getId());
                    context.startActivity(intent);
                }
            });

            holder.deleteMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DeleteDialog deleteDialog = new DeleteDialog(workoutType.getId(), 1);
                    FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                    deleteDialog.show(fragmentManager, "tag");
                }
            });

    }

    @Override
    public int getItemCount() {
        return workoutTypeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewType;
        private TextView mainWorkoutType;
        private CardView cardView;
        private ConstraintLayout constraintLayout;
        private ImageButton deleteMain;

        public ViewHolder(@NonNull View itemView, Context cnt) {
            super(itemView);
            context = cnt;

            imageViewType = itemView.findViewById(R.id.imageViewType);
            mainWorkoutType = itemView.findViewById(R.id.mainWorkoutType);
            cardView = itemView.findViewById(R.id.cardViewMain);
            constraintLayout = itemView.findViewById(R.id.constraintLayoutMain);
            deleteMain = itemView.findViewById(R.id.deleteMain);

        }
    }
}
