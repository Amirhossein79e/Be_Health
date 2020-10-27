package com.amirhosseinemadi.behealth.viewModel;

import android.app.Dialog;

import androidx.databinding.BaseObservable;

import com.amirhosseinemadi.behealth.callback.DialogVmCallback;
import com.amirhosseinemadi.behealth.common.Application;
import com.amirhosseinemadi.behealth.common.PrefManager;

public class DialogVm extends BaseObservable {

    public String weight = "";
    public String height = "";
    public String age = "";
    public boolean maleChecked = false;
    public boolean femaleChecked = false;
    private PrefManager prefManager;
    private Dialog dialog;
    private DialogVmCallback dialogVmCallback;

    public DialogVm(Dialog dialog, DialogVmCallback dialogVmCallback)
    {
        this.dialog = dialog;
        this.dialogVmCallback = dialogVmCallback;
        prefManager = Application.dComponent.prefManager();

    }


    public void submitClick()
    {
        if ((maleChecked || femaleChecked)&& !weight.isEmpty() && !height.isEmpty()&& !age.isEmpty())
        {
            if (Integer.parseInt(height)!=0 && Integer.parseInt(weight)!=0 && Integer.parseInt(age)!=0)
            {
            if (maleChecked)
            {
                prefManager.setGender("male");
                prefManager.setStride(Application.dComponent.calculator().calculateStride("male",Integer.parseInt(height)));
                prefManager.setBmr(Application.dComponent.calculator().calculateBmr("male",Integer.parseInt(weight),Integer.parseInt(height),Integer.parseInt(age)));
            }else
                {
                    prefManager.setGender("female");
                    prefManager.setStride(Application.dComponent.calculator().calculateStride("female",Integer.parseInt(height)));
                    prefManager.setBmr(Application.dComponent.calculator().calculateBmr("female",Integer.parseInt(weight),Integer.parseInt(height),Integer.parseInt(age)));
                }

                prefManager.setWeight(Integer.parseInt(weight));
                prefManager.setHeight(Integer.parseInt(height));
                prefManager.setAge(Integer.parseInt(age));
                prefManager.setTarget(6000);

                dialogVmCallback.onCorrect();
                dialog.cancel();

            }else
            {
                dialogVmCallback.onIncorrect();
            }

        }else
        {
            dialogVmCallback.onIncorrect();
        }
    }

}
