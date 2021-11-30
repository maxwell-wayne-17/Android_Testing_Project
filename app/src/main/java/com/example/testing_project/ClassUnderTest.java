package com.example.testing_project;

import android.content.Context;

public class ClassUnderTest {

    private Context ctx;

    public ClassUnderTest(Context ctx){ this.ctx = ctx; }

    public String getHelloWorldString(){
        return ctx.getString(R.string.hello_world);
    }

}
