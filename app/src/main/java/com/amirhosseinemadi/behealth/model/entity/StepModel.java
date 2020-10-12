package com.amirhosseinemadi.behealth.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "step")
public class StepModel {

    @ColumnInfo(name = "id",typeAffinity = 3)
    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "count",typeAffinity = 3)
    int count;

    @ColumnInfo(name = "minute",typeAffinity = 3)
    int minute;

    @ColumnInfo(name = "distance",typeAffinity = 3)
    int distance;

    @ColumnInfo(name = "date",typeAffinity = 3)
    long date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
