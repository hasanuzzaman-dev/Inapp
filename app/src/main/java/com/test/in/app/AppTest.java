package com.test.in.app;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by android on 12/13/2017.
 */

public class AppTest {

    public Context context;
    public AppTest(Context context){
        this.context = context;
    }

    public void saveApi(String data){
        SharedPreferences.Editor editor = context.getSharedPreferences("in_app", MODE_PRIVATE).edit();
        editor.putString("in" , data);
        editor.commit();
    }

    public String getApiPreferance(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("in_app", MODE_PRIVATE);
        String value = sharedPreferences.getString("in", "0");
        return value;
    }
}
