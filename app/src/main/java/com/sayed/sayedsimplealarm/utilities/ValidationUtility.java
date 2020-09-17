package com.sayed.sayedsimplealarm.utilities;

import android.widget.CheckBox;

import java.util.List;

public final class ValidationUtility  {

    public static boolean isAllCheckBoxesFalse (List<CheckBox> checkBoxes) {
        boolean result = false;
        int size = checkBoxes.size() ;
        int iterator = 0;
        while (iterator < size){
            if( checkBoxes.get(iterator).isChecked()) {
                result = true ;
                break;
            }
            iterator++;
        }
        return result ;
    }
}
