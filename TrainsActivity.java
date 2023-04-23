package com.example.ebulgarianrailway;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.ebulgarianrailway.Adapters.RecyclerAdapter;
import com.example.ebulgarianrailway.CustomClasses.Train;

import java.util.ArrayList;

public class TrainsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trains);

        RecyclerView recycler = findViewById(R.id.RecyclerViewElement);
        Intent intent = getIntent();
        ArrayList<Train> list = intent.getParcelableArrayListExtra("List");

        if (recycler != null) {
            recycler.setAdapter(new RecyclerAdapter(list,this));
            recycler.setLayoutManager(new GridLayoutManager(this.getApplicationContext(),1));
        }
    }
}