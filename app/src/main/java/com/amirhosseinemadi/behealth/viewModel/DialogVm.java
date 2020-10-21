package com.amirhosseinemadi.behealth.viewModel;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import com.amirhosseinemadi.behealth.common.Application;
import com.amirhosseinemadi.behealth.common.PrefManager;

public class DialogVm extends BaseObservable {

    public String weight = "";
    public String height = "";
    public boolean maleChecked = false;
    public boolean femaleChecked = false;
    private PrefManager prefManager;
    private Dialog dialog;

    public DialogVm(Dialog dialog)
    {
        this.dialog = dialog;
      prefManager = Application.dComponent.prefManager();
    }


    public void submitClick()
    {
        if ((maleChecked || femaleChecked)&& !weight.isEmpty() && !height.isEmpty())
        {
            if (maleChecked)
            {
                prefManager.setGender("male");
            }else
                {
                    prefManager.setGender("female");
                }

            if (Integer.parseInt(height)!=0 && Integer.parseInt(weight)!=0)
            {
                prefManager.setWeight(Integer.parseInt(weight));
                prefManager.setHeight(Integer.parseInt(height));
                dialog.cancel();
            }

        }else
        {
            //TODO SnackBar
        }
    }

}
