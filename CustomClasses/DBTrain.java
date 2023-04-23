package com.example.ebulgarianrailway.CustomClasses;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import static com.example.ebulgarianrailway.Helpers.DbHelperNew.FROM;
import static com.example.ebulgarianrailway.Helpers.DbHelperNew.NAME;
import static com.example.ebulgarianrailway.Helpers.DbHelperNew.ROW_ID;
import static com.example.ebulgarianrailway.Helpers.DbHelperNew.TO;

public class DBTrain implements Parcelable {
    private Integer ID;
    private String Name;
    private String From;
    private String To;

    protected DBTrain(Parcel in) {
        if (in.readByte() == 0) {
            ID = null;
        } else {
            ID = in.readInt();
        }
        Name = in.readString();
        From = in.readString();
        To = in.readString();
    }

    public  DBTrain()
    {

    }
    public static int GetByID(ArrayList<DBTrain> list,int id)
    {
        ArrayList<DBTrain> result = new ArrayList<>();
        list.forEach(train->{if (train.ID==id) result.add(train);
        });
        return list.indexOf(result.get(0));
    }

    public static final Creator<DBTrain> CREATOR = new Creator<DBTrain>() {
        @Override
        public DBTrain createFromParcel(Parcel in) {
            return new DBTrain(in);
        }

        @Override
        public DBTrain[] newArray(int size) {
            return new DBTrain[size];
        }
    };

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setFrom(String from) {
        From = from;
    }

    public void setTo(String to) {
        To = to;
    }

    public Integer getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getFrom() {
        return From;
    }

    public String getTo() {
        return To;
    }

    public static DBTrain GetTrainFromCursor(Cursor cursor)
    {
        DBTrain train = new DBTrain();
        train.setID(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(ROW_ID))));
        train.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)));
        train.setFrom(cursor.getString(cursor.getColumnIndexOrThrow(FROM)));
        train.setTo(cursor.getString(cursor.getColumnIndexOrThrow(TO)));
        return train;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (ID == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(ID);
        }
        parcel.writeString(Name);
        parcel.writeString(From);
        parcel.writeString(To);
    }

}
