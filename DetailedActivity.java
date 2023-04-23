package com.example.ebulgarianrailway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ebulgarianrailway.CustomClasses.Stop;
import com.example.ebulgarianrailway.CustomClasses.Train;

import java.util.ArrayList;

import static com.example.ebulgarianrailway.CustomClasses.Train.GetFullName;

public class DetailedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        Intent intent = getIntent();
        Train train = intent.getExtras().getParcelable("Train");
        TextView name = findViewById(R.id.textViewNameDetailed);
        TextView type = findViewById(R.id.textViewTypeTrainDetailed);
        TextView number = findViewById(R.id.textViewNumberTrainDetailed);
        TextView duration = findViewById(R.id.textViewDurationDetailed);
        TextView stop1 = findViewById(R.id.textViewStop1);
        TextView stop2 = findViewById(R.id.textViewStop2);
        TextView stop3 = findViewById(R.id.textViewStop3);
        TextView stop4 = findViewById(R.id.textViewStop4);
        TextView begin1 = findViewById(R.id.textViewBegin1);
        TextView begin2 = findViewById(R.id.textViewBegin2);
        TextView begin3 = findViewById(R.id.textViewBegin3);
        TextView begin4 = findViewById(R.id.textViewBegin4);
        TextView end1 = findViewById(R.id.textViewEnd1);
        TextView end2 = findViewById(R.id.textViewEnd2);
        TextView end3 = findViewById(R.id.textViewEnd3);
        TextView end4 = findViewById(R.id.textViewEnd4);
        TextView dash3 = findViewById(R.id.textViewSlash3);
        TextView dash4 = findViewById(R.id.textViewSlash4);

        ArrayList<Stop> stops = train.getStops();
        name.setText(GetFullName(stops));
        type.setText(train.getIsDirect());
        number.setText(train.getNumber());
        duration.setText(train.getDuration());

        stop1.setText(stops.get(0).getName());
        stop2.setText(stops.get(1).getName());
        begin1.setText(stops.get(0).getArrival());
        begin2.setText(stops.get(1).getArrival());
        end1.setText(stops.get(0).getDeparture());
        end2.setText(stops.get(1).getDeparture());

        switch (stops.size())
        {
            case 2:
                stop3.setVisibility(TextView.INVISIBLE);
                begin3.setVisibility(TextView.INVISIBLE);
                end3.setVisibility(TextView.INVISIBLE);
                dash3.setVisibility(TextView.INVISIBLE);
                stop4.setVisibility(TextView.INVISIBLE);
                begin4.setVisibility(TextView.INVISIBLE);
                end4.setVisibility(TextView.INVISIBLE);
                dash4.setVisibility(TextView.INVISIBLE);
                break;
            case 3 :
                stop3.setText(stops.get(2).getName());
                begin3.setText(stops.get(2).getArrival());
                end3.setText(stops.get(2).getDeparture());
                stop4.setVisibility(TextView.INVISIBLE);
                begin4.setVisibility(TextView.INVISIBLE);
                end4.setVisibility(TextView.INVISIBLE);
                dash4.setVisibility(TextView.INVISIBLE);
                break;
            case 4:
                stop3.setText(stops.get(2).getName());
                begin3.setText(stops.get(2).getArrival());
                end3.setText(stops.get(2).getDeparture());
                stop4.setText(stops.get(3).getName());
                begin4.setText(stops.get(3).getArrival());
                end4.setText(stops.get(3).getDeparture());
                break;
        }
    }
}