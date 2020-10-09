package com.amirhosseinemadi.behealth.model.room;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Insert;
import androidx.room.Query;

import com.amirhosseinemadi.behealth.model.entity.StepModel;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Insert
    void insertStep(StepModel stepModel);

    @Query("select * from step limit 30 offset :myOffset")
    MutableLiveData<List<StepModel>> selectStep(int myOffset);

}
