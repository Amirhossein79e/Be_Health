package com.amirhosseinemadi.behealth.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "step")
public class StepModel {

    @ColumnInfo(name = "id",typeAffinity = 3)
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "count",typeAffinity = 3)
    private int count;

    @ColumnInfo(name = "minute",typeAffinity = 3)
    private int minute;

    @ColumnInfo(name = "distance",typeAffinity = 3)
    private int distance;

    @ColumnInfo(name = "date",typeAffinity = 3)
    private long date;

}
