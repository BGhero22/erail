package com.example.ebulgarianrailway.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebulgarianrailway.CustomClasses.Train;
import com.example.ebulgarianrailway.DetailedActivity;
import com.example.ebulgarianrailway.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private final ArrayList<Train> trainList;
    private final Context context;
    public RecyclerAdapter(ArrayList<Train> trainList,Context context)
    {
     this.trainList = trainList;
     this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView type;
        TextView time;
        TextView numb;
        TextView duration;
        ConstraintLayout layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.RecyclerItemConstraint);
            name = itemView.findViewById(R.id.TextTrainName);
            type = itemView.findViewById(R.id.TextTypeTrain);
            time = itemView.findViewById(R.id.TextTime);
            numb = itemView.findViewById(R.id.TextTrainNumb);
            duration = itemView.findViewById(R.id.TextDuration);
        }
    }


    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Train currentTrain = trainList.get(position);
        holder.name.setText(Train.GetFullName(currentTrain.getStops()));
        holder.duration.setText(currentTrain.getDuration());
        holder.type.setText(currentTrain.getIsDirect());
        holder.time.setText(currentTrain.getDeparture().concat("-").concat(currentTrain.getArrival()));
        holder.numb.setText(currentTrain.getNumber());
        holder.layout.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailedActivity.class);
            intent.putExtra("Train",currentTrain);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return trainList.size();
    }
}
