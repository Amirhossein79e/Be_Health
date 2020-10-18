package com.amirhosseinemadi.behealth.model.dataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.amirhosseinemadi.behealth.model.entity.StepModel;

@Database(entities = StepModel.class, version = 1)
public abstract class DataBase extends RoomDatabase {

    public abstract Dao dao();

}
