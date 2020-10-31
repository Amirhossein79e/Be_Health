package com.amirhosseinemadi.behealth.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "step")
public class StepModel {

    @ColumnInfo(name = "id",typeAffinity = 3)
    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "step",typeAffinity = 3)
    int step;

    @ColumnInfo(name = "time",typeAffinity = 3)
    int time;

    @ColumnInfo(name = "distance",typeAffinity = 4)
    float distance;

    @ColumnInfo(name = "date",typeAffinity = 3)
    long date;

    @ColumnInfo(name = "calorie",typeAffinity = 3)
    int calories;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
