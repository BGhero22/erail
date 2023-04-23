package com.example.ebulgarianrailway.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebulgarianrailway.CustomClasses.DBTrain;
import com.example.ebulgarianrailway.FavouritesActivity;
import com.example.ebulgarianrailway.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static com.example.ebulgarianrailway.Helpers.DbHelperNew.MakeReadable;
import static com.example.ebulgarianrailway.Helpers.JsoupHelper.ScrapeData;
import static com.example.ebulgarianrailway.MainActivity._HTML;
import static com.example.ebulgarianrailway.MainActivity.recyclerMain;

public class RecyclerAdapterMain extends RecyclerView.Adapter<RecyclerAdapterMain.MyViewHolder> {

    private static ArrayList<DBTrain> trainList;
    private final Activity activity;
    public RecyclerAdapterMain(ArrayList<DBTrain> trainList, Activity activity)
    {
        RecyclerAdapterMain.trainList = trainList;
        this.activity = activity;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ConstraintLayout layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.ConstraintRecyclerMain);
            name = itemView.findViewById(R.id.textViewNameDB);
        }
    }

    public static void TemperList(String name, String from, String to)
    {
        DBTrain train = new DBTrain();
        train.setName(name);
        train.setFrom(from);
        train.setTo(to);
        trainList.add(train);
        recyclerMain.getAdapter().notifyItemInserted(trainList.size()-1);
    }

    public static void TemperList(Integer id,Integer position)
    {
        trainList.remove(DBTrain.GetByID(trainList,id));
        recyclerMain.getAdapter().notifyItemRemoved(position);
    }

    public static void TemperList(Integer id,String name, String from, String to,Integer postion)
    {
        trainList.get(DBTrain.GetByID(trainList,id)).setName(name);
        trainList.get(DBTrain.GetByID(trainList,id)).setFrom(from);
        trainList.get(DBTrain.GetByID(trainList,id)).setTo(to);
        recyclerMain.getAdapter().notifyItemChanged(postion);
    }


    @NonNull
    @Override
    public RecyclerAdapterMain.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_main, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DBTrain currentTrain = trainList.get(position);
        holder.name.setText(currentTrain.getName());
        holder.layout.setOnClickListener(view -> {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDateTime now = LocalDateTime.now();
            ScrapeData(activity,MakeReadable(currentTrain.getFrom())
                    ,MakeReadable(currentTrain.getTo()),
                    _HTML,
                    dtf.format(now));
        });
        holder.layout.setOnLongClickListener(view -> {
            Intent intent = new Intent(activity, FavouritesActivity.class);
            intent.putExtra("Train",currentTrain);
            intent.putExtra("Position",position);
            activity.startActivity(intent);
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return trainList.size();
    }
}


