package com.example.testing_project;

import java.util.Objects;

/**
 * Simple class that essentailly just holds some data used to demonstrate basic java JUnit4 tests in
 * ExampleUnitTest
 */
public class TestObject {

    // Constants
    private static int DEFAULT_NUM = 0;
    private static String DEFAULT_STRING = "Default";

    private int testNum;
    private String testString;
    private boolean testBool;

    // Default constructor
    public TestObject(){
        testNum = DEFAULT_NUM;
        testString = DEFAULT_STRING;
        testBool = false;
    }

    public TestObject(int num, String str, boolean bool){
        testNum = num;
        testString = str;
        testBool = bool;
    }

    public void increment(){ testNum++; }

    public void decrement(){ testNum--; }

    public int getNum(){ return testNum; }

    public void setNum(int num){ testNum = num; }

    public void setStr(String str){ testString = str; }

    public String getStr(){ return testString; }

    public void switchBool(){ testBool = !testBool; }

    public boolean getBool(){ return testBool; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestObject that = (TestObject) o;
        return testNum == that.testNum && testBool == that.testBool && Objects.equals(testString, that.testString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testNum, testString, testBool);
    }
}
