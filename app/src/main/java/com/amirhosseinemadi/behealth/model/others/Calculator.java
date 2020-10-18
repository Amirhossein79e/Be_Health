package com.amirhosseinemadi.behealth.model.others;

import java.text.DecimalFormat;


public class Calculator {


    public String calculateBmi(int height, float weight)
    {
        float mHeight = height/100f;
        float bmi = weight/(mHeight*mHeight);
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        return decimalFormat.format(bmi);
    }


    public float calculateStride(String gender,int height)
    {
        float stride = 0f;
        switch (gender)
        {
            case "male":
                stride = height*0.415f;
                break;

            case "female":
                stride = height*0.413f;
                break;
        }
        return stride;
    }


    public float calculateDistance(float steps,float stride)
    {
        float distance = steps*stride/100;
        return distance;
    }

}
