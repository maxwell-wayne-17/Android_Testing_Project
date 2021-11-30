package com.example.testing_project;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    ///// Everything above this was added by default with empty project /////

    ///// Test equals method /////
    @Test
    public void test_testObjectDefault_equalsTrue(){
        TestObject one = new TestObject();
        TestObject two = new TestObject();

        boolean expected = true;
        // Test objects equal themselves and each other
        boolean actual = one.equals(two) && two.equals(one) && one.equals(one) && two.equals(two);

        assertEquals(expected, actual);
    }

    @Test
    public void test_testObjectExplicit_equalsTrue(){
        TestObject one = new TestObject(10, "test", true);
        TestObject two = new TestObject(10, "test", true);

        // Test objects equal themselves and each other
        boolean actual = one.equals(two) && two.equals(one) && one.equals(one) && two.equals(two);

        assertTrue(actual);
    }

    @Test
    public void test_testObjectNum_equalsFalse(){
        TestObject one = new TestObject(0, "test", true);
        TestObject two = new TestObject(10, "test", true);

        // Test objects do not equal each other
        boolean actual = one.equals(two) || two.equals(one);

        assertFalse(actual);
    }

    @Test
    public void test_testObjectStr_equalsFalse(){
        TestObject one = new TestObject(0, "one", true);
        TestObject two = new TestObject(0, "two", true);

        // Test objects do not equal each other
        boolean actual = one.equals(two) || two.equals(one);

        assertFalse(actual);
    }

    @Test
    public void test_testObjectBool_equalsFalse(){
        TestObject one = new TestObject(0, "test", true);
        TestObject two = new TestObject(0, "test", false);

        // Test objects do not equal each other
        boolean actual = one.equals(two) || two.equals(one);

        assertFalse(actual);
    }

    //// Test inc/dec methods /////
    @Test
    public void test_testObject_default_constructor(){

        TestObject explicit = new TestObject(0, "Default", false);
        TestObject def = new TestObject();


    }
}