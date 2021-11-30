package com.example.testing_project;

import android.content.Context;
import android.content.SharedPreferences;

public class ClassUnderTest {

    private Context ctx;

    // Keys for values in SharedPreferences
    static final String KEY_NUM = "key_num";
    static final int DEFAULT_NUM = -1;
    static final String KEY_STR = "key_str";
    static final String DEFAULT_STR = "Default Str";

    // Injected SharedPreferences
    private final SharedPreferences mySharedPref;

    public ClassUnderTest(Context ctx, SharedPreferences sp){
        this.ctx = ctx;
        mySharedPref = sp;
    }

    public String getHelloWorldString(){
        return ctx.getString(R.string.hello_world);
    }

    public boolean savePreferences(int num, String str){
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putInt(KEY_NUM, num);
        editor.putString(KEY_STR, str);
        return editor.commit();
    }

    public int getSharedPrefNum(){
        return mySharedPref.getInt(KEY_NUM, DEFAULT_NUM);
    }

    public String getSharedPrefString(){
        return mySharedPref.getString(KEY_STR, DEFAULT_STR);
    }

}
