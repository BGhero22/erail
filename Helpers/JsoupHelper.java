package com.example.ebulgarianrailway.Helpers;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.example.ebulgarianrailway.CustomClasses.CustomDialog;
import com.example.ebulgarianrailway.CustomClasses.Train;
import com.example.ebulgarianrailway.TrainsActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.ebulgarianrailway.CustomClasses.Train.GetTrainFromElement;

public class JsoupHelper {
    public static void ScrapeData(Activity activity, String from, String to, String html,String date)
    {
        CustomDialog alert = new CustomDialog(activity);
        Thread t1 = new Thread(() -> {
           ArrayList<Train> list = ConnectJsoup(from.toLowerCase(),to.toLowerCase(),html,date);
           if (list == null)
           {
               activity.runOnUiThread(() -> {
                   alert.dismisDialog();
                   Toast.makeText(activity.getApplicationContext(), "Липсва интернет", Toast.LENGTH_SHORT).show();
               });
           }
           else {
               ArrayList<Train> listAranged = Rearange(list);
               Intent intent = new Intent(activity, TrainsActivity.class);
               intent.putParcelableArrayListExtra("List",listAranged);
               alert.dismisDialog();
               activity.startActivity(intent);
           }

        });

        alert.startLoadingDialog();
        t1.start();
    }

    private static ArrayList<Train> ConnectJsoup(String from, String to, String html,String date)
    {
        Document doc = null;
        try {
            String stops = from.concat("/");
            stops = stops.concat(to);
            stops = stops.concat("/").concat(date);
            doc = Jsoup.connect(html.concat(stops)).get();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        if (doc != null)
        {
            Elements elements = doc.getElementsByClass("card mb-4 ");
            elements.add(doc.getElementsByClass("card mb-4  fastest ").first());
            return GetTrainFromElement(elements);
        }
        return null;
    }

    public static ArrayList<Train> Rearange(ArrayList<Train> trains)
    {
        int check=0;
        ArrayList<Train> result =new ArrayList<>(trains);
        while (check<trains.size()-2)
        {
        for (int i = 1;i<result.size();i++)
        {
            if (i==1)
            {
                check = 0;
            }
                String[] second = trains.get(i).getDeparture().trim().split(":");
                String[] first = trains.get(i-1).getDeparture().trim().split(":");
                if (Integer.parseInt(second[0])<Integer.parseInt(first[0]))
                {
                    Train temp = new Train(result.get(i-1));
                    result.set(i-1,new Train(result.get(i)));
                    result.set(i,new Train(temp));
                }
                else if(Integer.parseInt(second[0])==Integer.parseInt(first[0]))
                {
                    if (Integer.parseInt(second[1])<Integer.parseInt(first[1]))
                    {
                        Train temp = new Train(result.get(i-1));
                        result.set(i-1,new Train(result.get(i)));
                        result.set(i,new Train(temp));
                    }
                    else{
                        check++;
                    }
                }
                else
                {
                    check++;
                }
            }
        }
        return result;
    }


}
