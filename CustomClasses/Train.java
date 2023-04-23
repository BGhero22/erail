package com.example.ebulgarianrailway.CustomClasses;

import android.os.Parcel;
import android.os.Parcelable;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Train implements Parcelable {
    private String from;
    private String to;
    private String duration;
    private String number;
    private String isDirect;
    private String departure;
    private String arrival;
    private ArrayList<Stop> stops;

    public ArrayList<Stop> getStops() {
        return stops;
    }

    public void setStops(ArrayList<Stop> stops) {
        this.stops = stops;
    }

    public Train()
    {

    }

    public  Train(Train t)
    {
        this.setFrom(t.getFrom());
        this.setTo(t.getTo());
        this.setDuration(t.getDuration());
        this.setNumber(t.getNumber());
        this.setIsDirect(t.getIsDirect());
        this.setDeparture(t.getDeparture());
        this.setArrival(t.getArrival());
        this.setStops(t.getStops());
    }


    protected Train(Parcel in) {
        from = in.readString();
        to = in.readString();
        duration = in.readString();
        number = in.readString();
        isDirect = in.readString();
        departure = in.readString();
        arrival = in.readString();
        stops = in.createTypedArrayList(Stop.CREATOR);
    }

    public static final Creator<Train> CREATOR = new Creator<Train>() {
        @Override
        public Train createFromParcel(Parcel in) {
            return new Train(in);
        }

        @Override
        public Train[] newArray(int size) {
            return new Train[size];
        }
    };

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setIsDirect(String isDirect) {
        this.isDirect = isDirect;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDuration() {
        return duration;
    }

    public String getNumber() {
        return number;
    }

    public String getIsDirect() {
        return isDirect;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }


    public static ArrayList<Train> GetTrainFromElement(Elements elements)
    {
        ArrayList<Train> trains = new ArrayList<>();
        for (int i= 0;i<=elements.size()-1;i++) {
            Train train = new Train();
            Element element =  elements.get(i);
            train.stops= new ArrayList<>();
            Elements stops = element.getElementsByClass("col-12 col-md-4 text-uppercase truncate");
            Elements times = element.getElementsByClass("col-3 col-md-1 text-center");
            int counter = 0;
            for (Element stop:stops)
            {
                Stop stopFinal = new Stop();
                stopFinal.setName(stop.text().replaceAll("[0-9]","").trim());
                stopFinal.setArrival(times.get(counter).text());
                stopFinal.setDeparture(times.get(counter+1).text());
                train.stops.add(stopFinal);
                counter+=2;
            }
            train.setFrom(train.stops.get(0).getName());
            train.setTo(train.stops.get(train.stops.size()-1).getName());

            String roadtime = element.getElementsByClass("schedule-time").first().text();
            String[] roadtimes = roadtime.split("-");
            train.setDeparture(roadtimes[0]);
            train.setArrival(roadtimes[1]);

            train.setDuration(element.getElementsByClass("col-6 col-md-2").first().text());
            train.setNumber(element.getElementsByClass("train-number").first().text());
            train.setIsDirect(element.select("small").first().text());

            trains.add(train);
        }
        return trains;
    }

    public static String GetFullName(ArrayList<Stop> stops)
    {
        String trainName = "";
        for (Stop stop:stops)
        {
            trainName= trainName.concat(stop.getName());
            trainName = trainName.concat("-");
        }
        return trainName.substring(0,trainName.length()-1);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(from);
        parcel.writeString(to);
        parcel.writeString(duration);
        parcel.writeString(number);
        parcel.writeString(isDirect);
        parcel.writeString(departure);
        parcel.writeString(arrival);
        parcel.writeTypedList(stops);
    }
}
