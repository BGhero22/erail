package com.example.ebulgarianrailway;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ebulgarianrailway.Adapters.RecyclerAdapterMain;
import com.example.ebulgarianrailway.CustomClasses.DBTrain;
import com.example.ebulgarianrailway.Helpers.DbHelperNew;

import java.util.ArrayList;

import static com.example.ebulgarianrailway.Helpers.ApiHelper.GetApiResults;
import static com.example.ebulgarianrailway.Helpers.DbHelperNew.Checker;
import static com.example.ebulgarianrailway.Helpers.DbHelperNew.corrects;
import static com.example.ebulgarianrailway.Helpers.JsoupHelper.ScrapeData;


public class MainActivity extends AppCompatActivity {

    public static String _HTML ="https://razpisanie.bdz.bg/bg/";
    public static RecyclerView recyclerMain;
    private EditText from_field;
    private EditText to_field;
    private String chosenDate ="";
    DbHelperNew helper = new DbHelperNew(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper.open();
        Button covidB = findViewById(R.id.buttonMainCovid);
        covidB.setOnClickListener(this::OnClick);
        Button addFav = findViewById(R.id.addFavouriteButton);
        addFav.setOnClickListener(this::OnClick);
        recyclerMain = findViewById(R.id.recyclerViewMain);
        Button searchButton =  findViewById(R.id.buttonSearch);
        from_field =  findViewById(R.id.FieldFrom);
        to_field = findViewById(R.id.FieldTo);
        CalendarView date = findViewById(R.id.editTextTextDateMain);
        long now = System.currentTimeMillis() - 1000;
        date.setMinDate(now);
        date.setMaxDate(now+(1000*60*60*24*20));


        date.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String  curDate = String.valueOf(dayOfMonth);
            String  Year = String.valueOf(year);
            String  Month = String.valueOf(month+1);

            chosenDate = "";
            chosenDate = chosenDate.concat(curDate.concat(".").concat(Month).concat(".").concat(Year));
        });


        searchButton.setOnClickListener(this::OnClick);

        ArrayList<DBTrain> list = new ArrayList<>();
        try {
            list = helper.getAll();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        recyclerMain.setAdapter(new RecyclerAdapterMain(list,this));
        recyclerMain.setLayoutManager(new GridLayoutManager(this.getApplicationContext(),1));

    }

    @SuppressLint("NonConstantResourceId")
    public void OnClick(View v)
    {

        switch (v.getId()) {
            case R.id.buttonSearch:
                Checker(from_field.getText().toString(),to_field.getText().toString(),
                        ()->ScrapeData(this,corrects.get(0),
                                corrects.get(1),_HTML,chosenDate),
                        ()-> Toast.makeText(this, "Грешно въведени гари", Toast.LENGTH_SHORT).show());

                break;
            case  R.id.addFavouriteButton:
                Intent intent = new Intent(this,FavouritesActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonMainCovid:
                GetApiResults(this);
                break;
        }
    }



}

