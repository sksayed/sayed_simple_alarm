package com.sayed.sayedsimplealarm.data;

import android.util.SparseBooleanArray;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

class DaysConverter {
    @TypeConverter
    public static SparseBooleanArray fromString ( String value) {
       Type arrayType = new TypeToken<SparseBooleanArray>() {}.getType();
       return new Gson().fromJson(value , arrayType);
    }
    @TypeConverter
    public static String toString (SparseBooleanArray array) {
        Gson gson = new Gson();
        return gson.toJson(array);

    }
}
