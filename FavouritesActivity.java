package com.example.ebulgarianrailway;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ebulgarianrailway.CustomClasses.DBTrain;
import com.example.ebulgarianrailway.Helpers.DbHelperNew;

import static com.example.ebulgarianrailway.Helpers.DbHelperNew.Checker;
import static com.example.ebulgarianrailway.Helpers.DbHelperNew.corrects;
import static com.example.ebulgarianrailway.Adapters.RecyclerAdapterMain.TemperList;

public class FavouritesActivity extends AppCompatActivity {
    private final DbHelperNew dbhelper = new DbHelperNew(this);
    private TextView name;
    private TextView from;
    private TextView to;
    private int position;
    private DBTrain train;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        dbhelper.open();
        name = findViewById(R.id.editTextNameFav);
        from = findViewById(R.id.editTextFromFav);
        to = findViewById(R.id.editTextToFav);
        Button add = findViewById(R.id.buttonAddFav);
        add.setOnClickListener(this::OnClick);
        Button update = findViewById(R.id.buttonUpdateFav);
        update.setOnClickListener(this::OnClick);
        Button delete = findViewById(R.id.buttonDeleteFav);
        delete.setOnClickListener(this::OnClick);
        Intent intent = getIntent();
        if (intent.getExtras()!=null)
        {
         add.setVisibility(Button.INVISIBLE);
         train = intent.getExtras().getParcelable("Train");
         name.setText(train.getName());
         from.setText(train.getFrom());
         to.setText(train.getTo());
         position = intent.getExtras().getInt("Position");
        }
        else
        {
            update.setVisibility(Button.INVISIBLE);
            delete.setVisibility(Button.INVISIBLE);
        }
    }

    @SuppressLint("NonConstantResourceId")
    public void OnClick(View v)
    {

        switch (v.getId()) {
            case R.id.buttonAddFav:

                Checker(from.getText().toString(),to.getText().toString(),
                        ()-> {
                            int success = dbhelper.Insert(name.getText().toString(),corrects.get(0), corrects.get(1));
                            if (success <= -1) {
                                Toast.makeText(this, "Добавянето бе неуспешно", Toast.LENGTH_SHORT).show();
                            } else {
                                TemperList(name.getText().toString(), from.getText().toString(), to.getText().toString());
                                this.finish();
                            }
                        }
                        ,()->
                        Toast.makeText(this, "Грешно въведени гари", Toast.LENGTH_SHORT).show());
               break;
            case  R.id.buttonUpdateFav:
                Checker(from.getText().toString(),to.getText().toString(),
                        ()->{
                            boolean successU = dbhelper.Update(train.getID(),name.getText().toString(),corrects.get(0),
                                    corrects.get(0));
                            if (!successU)
                            {
                                Toast.makeText(this, "Промяната бе неуспешна", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                TemperList(train.getID(),name.getText().toString(),from.getText().toString(),to.getText().toString(),position);
                                this.finish();
                            }
                        }
                        ,()-> Toast.makeText(this, "Грешно въведени гари", Toast.LENGTH_SHORT).show());

                break;
            case  R.id.buttonDeleteFav:
                boolean successD = dbhelper.Delete(train.getID());
                if (!successD)
                {
                    Toast.makeText(this, "Изтриването бе неуспешно", Toast.LENGTH_SHORT).show();
                }
                else{
                    TemperList(train.getID(),position);
                    this.finish();
                }
                break;
        }
    }
}