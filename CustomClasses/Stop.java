package com.example.ebulgarianrailway.CustomClasses;

import android.os.Parcel;
import android.os.Parcelable;

public class Stop implements Parcelable {
    private String name;
    private String arrival;
    private String departure;

    protected Stop(Parcel in) {
        name = in.readString();
        arrival = in.readString();
        departure = in.readString();
    }

    public Stop()
    {}

    public static final Creator<Stop> CREATOR = new Creator<Stop>() {
        @Override
        public Stop createFromParcel(Parcel in) {
            return new Stop(in);
        }

        @Override
        public Stop[] newArray(int size) {
            return new Stop[size];
        }
    };

    public void setName(String name) {
        this.name = name;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getName() {
        return name;
    }

    public String getArrival() {
        return arrival;
    }

    public String getDeparture() {
        return departure;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(arrival);
        parcel.writeString(departure);
    }
}
