package com.sayed.sayedsimplealarm.utilities;

import android.os.Build;
import android.os.Bundle;
import android.text.format.Time;
import android.widget.CheckBox;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

final public class ViewUtils {

   private ViewUtils () throws IllegalAccessException {
      throw new IllegalAccessException(" this class should not be instantiated");
   }

   @RequiresApi(api = Build.VERSION_CODES.M)
   public static int getTimePickerInMinutes (TimePicker timePicker) {
    return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            ?timePicker.getMinute()
            :timePicker.getCurrentMinute();
   }

   @RequiresApi(api = Build.VERSION_CODES.M)
   public static int getTimePickerInHour (TimePicker timePicker) {
       return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
               ? timePicker.getHour()
               : timePicker.getCurrentHour() ;
   }

   public static List<CheckBox> addAllchekcBoxes (CheckBox ... boxes){
      List<CheckBox> tList = new ArrayList<>();
      for (CheckBox box:boxes) {
         tList.add(box);
      }
      return tList ;
   }
}
