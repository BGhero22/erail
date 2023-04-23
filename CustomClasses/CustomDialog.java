package com.example.ebulgarianrailway.CustomClasses;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.ebulgarianrailway.R;

import java.util.HashMap;

public class CustomDialog {
    private final Activity activity;
    private AlertDialog alertDialog;
    private Dialog dialog;
        public CustomDialog(Activity activity)
        {
            this.activity= activity;
        }

        @SuppressLint("InflateParams")
        public void startLoadingDialog()
        {
            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(activity);
            LayoutInflater inflater = activity.getLayoutInflater();
            builder.setView(inflater.inflate(R.layout.progres_layout,null));
            builder.setCancelable(false);


            alertDialog = builder.create();
            alertDialog.show();
        }


    public void startCovidDialog(HashMap<String, String> map)
    {
        activity.runOnUiThread(() -> {
            dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.layout_covid);

            TextView casesNew = (TextView) dialog.findViewById(R.id.textViewCasesNewValue);
            casesNew.setText(map.get("new_cases"));
            TextView casesAll = (TextView) dialog.findViewById(R.id.textViewCasesAllValue);
            casesAll.setText(map.get("active_cases"));
            TextView deathsNew = (TextView) dialog.findViewById(R.id.textViewDeathNewValue);
            deathsNew.setText(map.get("new_deaths"));
            TextView deathsAll = (TextView) dialog.findViewById(R.id.textViewDeathsAllValue);
            deathsAll.setText(map.get("total_deaths"));
            dialog.show();
        });
    }


        public void dismisDialog()
        {
            alertDialog.dismiss();
        }

}
