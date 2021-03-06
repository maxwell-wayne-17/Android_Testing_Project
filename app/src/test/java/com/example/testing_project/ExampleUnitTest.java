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

    private final static int DEFAULT_NUM = 0;
    private final static int TEST_NUM = 123;
    private final static String DEFAULT_STRING = "DEFAULT";

    ///////// Test equals method /////////
    @Test
    public void test_testObjectDefault_equalsTrue(){
        TestObject one = new TestObject();
        TestObject two = new TestObject();

        // Test objects equal themselves and each other
        boolean actual = one.equals(two) && two.equals(one) && one.equals(one) && two.equals(two);

        assertTrue(actual);
        //assertEquals(one, two); //Will also work
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

    //////// Test getters and setters /////////

    /// Test num getters and setters ///
    @Test
    public void test_testObject_getNum(){
        TestObject test = new TestObject(DEFAULT_NUM, DEFAULT_STRING, false);

        int expected = 0;
        int actual = test.getNum();

        assertEquals(expected, actual);
    }

    @Test
    public void test_testObject_setNum(){
        TestObject test = new TestObject(DEFAULT_NUM, DEFAULT_STRING, false);

        test.setNum(TEST_NUM);
        int actual = test.getNum();

        assertEquals(TEST_NUM, actual);
    }

    @Test
    public void test_testObject_incNum(){
        TestObject test = new TestObject(DEFAULT_NUM, DEFAULT_STRING, false);

        int expected = 1;
        test.increment();
        int actual = test.getNum();

        assertEquals(expected, actual);
    }

    @Test
    public void test_testObject_decNum(){
        TestObject test = new TestObject(DEFAULT_NUM, DEFAULT_STRING, false);

        int expected = -1;
        test.decrement();
        int actual = test.getNum();

        assertEquals(expected, actual);
    }

    /// Test string getters and setters
    @Test
    public void test_testObject_getStr(){
        TestObject test = new TestObject(DEFAULT_NUM, DEFAULT_STRING, false);

        String actual = test.getStr();

        assertEquals(DEFAULT_STRING, actual);
    }

    @Test
    public void test_testObject_setStr(){
        TestObject test = new TestObject(DEFAULT_NUM, DEFAULT_STRING, false);

        String expected = "New";
        test.setStr("New");
        String actual = test.getStr();

        assertEquals(expected, actual);
    }

    /// Test bool getters and setters ///
    @Test
    public void test_testObject_getBoolFalse(){
        TestObject test = new TestObject(DEFAULT_NUM, DEFAULT_STRING, false);

        assertFalse(test.getBool());
    }

    @Test
    public void test_testObject_getBoolTrue(){
        TestObject test = new TestObject(DEFAULT_NUM, DEFAULT_STRING, true);

        assertTrue(test.getBool());
    }

    @Test
    public void test_testObject_switchBoolTrue(){
        TestObject test = new TestObject(DEFAULT_NUM, DEFAULT_STRING, false);
        test.switchBool();
        assertTrue(test.getBool());
    }

    @Test
    public void test_testObject_switchBoolFalse(){
        TestObject test = new TestObject(DEFAULT_NUM, DEFAULT_STRING, true);
        test.switchBool();
        assertFalse(test.getBool());
    }





}