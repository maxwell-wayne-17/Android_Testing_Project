package com.example.testing_project;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Shared preferences helper class used to demonstrate testing with mock objects in ExampleMockTest
 */
public class ClassUnderTest {

    private Context ctx;

    // Keys for values in SharedPreferences
    static final String KEY_NUM = "key_num";
    static final int DEFAULT_NUM = -1;
    static final String KEY_STR = "key_str";
    static final String DEFAULT_STR = "Hello World!";

    public ClassUnderTest(Context ctx){
        this.ctx = ctx;
    }

    // Gets string from resources in context
    public String getHelloWorldString(){
        return ctx.getString(R.string.hello_world);
    }

    public boolean savePreferences(int num, String str, SharedPreferences mySharedPref){
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putInt(KEY_NUM, num);
        editor.putString(KEY_STR, str);
        return editor.commit();
    }

    public boolean saveNumPref(int num, SharedPreferences mySharedPref){
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putInt(KEY_NUM, num);
        return editor.commit();
    }

    public boolean saveStrPref(String str, SharedPreferences mySharedPref){
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putString(KEY_STR, str);
        return editor.commit();
    }

    public int getSharedPrefNum(SharedPreferences mySharedPref){
        return mySharedPref.getInt(KEY_NUM, DEFAULT_NUM);
    }

    public String getSharedPrefString(SharedPreferences mySharedPref){
        return mySharedPref.getString(KEY_STR, DEFAULT_STR);
    }

}
