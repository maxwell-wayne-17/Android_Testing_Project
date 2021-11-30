package com.example.testing_project;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

// Allow assertThat
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

// NOTE: Roboelectric not compatible with targetSdkVersion > 29
import androidx.test.core.app.ApplicationProvider;

@RunWith(MockitoJUnitRunner.class)
public class ExampleMockTest {

    private static final String FAKE_STRING = "HELLO WORLD";
    private static final String FAKE_PREF_STR = "FAKE PREF STR";
    private static final String FAKE_WRONG_STR = "this string is definitely wrong";
    private static final int FAKE_PREF_NUM = 123;

    private ClassUnderTest workingTestClass;
    private ClassUnderTest brokenTestClass;

    // Roboelectric artifact Context
    // "Robolectric executes real Android framework code and fakes of native framework code
    // on your local JVM or on a real device."
    //private Context roboelectricCtx = ApplicationProvider.getApplicationContext();

    // Mockito fields
    @Mock
    Context mockContext;

    @Mock
    Context mockBrokenContext;

    @Mock
    SharedPreferences mockSharedPref;

    @Mock
    SharedPreferences mockBrokenSharedPref;

    @Mock
    SharedPreferences.Editor mockEditor;

    @Mock
    SharedPreferences.Editor mockBrokenEditor;


    private ClassUnderTest createMockSharedPreferenceAndContext(){
        // Mocking reading the SharedPreferences as if mockSharedPref is written correctly
            // When calling getInt, return FAKE_PREF_NUM
        when(mockSharedPref.getInt( eq(ClassUnderTest.KEY_NUM), anyInt()) )
                .thenReturn(FAKE_PREF_NUM);
            // WHen calling getString, return FAKE_PREF_STR
        when(mockSharedPref.getString( eq(ClassUnderTest.KEY_STR), anyString()) )
                .thenReturn(FAKE_PREF_STR);

        // Mock successful commit
        when(mockEditor.commit()).thenReturn(true);

        // Return the MockEditor when requesting editor
        when(mockSharedPref.edit()).thenReturn(mockEditor);

        // Given a mocked Context injected into the object under test...
        // ** Basically tells the code to return FAKE_STRING when ctx.getString(R.string.hello_world) is called
        when(mockContext.getString(R.string.hello_world))
                .thenReturn(FAKE_STRING);

        return new ClassUnderTest(mockContext, mockSharedPref);
    }

    private ClassUnderTest createBrokenSharedPreferenceAndContext(){
        // Mock a commit that fails
        when(mockBrokenEditor.commit()).thenReturn(false);

        // Return the broken mock editor when requesting editor
        when(mockBrokenSharedPref.edit()).thenReturn(mockBrokenEditor);

        // Given a mocked Context injected into the object under test...
        // ** Basically tells the code to return FAKE_WRONG_STR when ctx.getString(R.string.hello_world) is called
        when(mockBrokenContext.getString(R.string.hello_world))
                .thenReturn(FAKE_WRONG_STR);

        return new ClassUnderTest(mockBrokenContext, mockBrokenSharedPref);

    }

    @Before
    public void initMocks(){
        // Create ClassUnderTest with working SharedPreferences and Context
        workingTestClass = createMockSharedPreferenceAndContext();

        // Create ClassUnderTest with broken SharedPreferences and Context
        brokenTestClass = createBrokenSharedPreferenceAndContext();
    }

    // For roboelectric context, more realistic since real context is actually
    // using the getString() call, opposed to a mocked getString() call
//    @Test
//    public void readStringFromContext_LocalizedString() {
//        // Given a Context object retrieved from Robolectric...
//        ClassUnderTest myObjectUnderTest = new ClassUnderTest(roboelectricCtx, mockSharedPref);
//
//        // ...when the string is returned from the object under test...
//        String result = myObjectUnderTest.getHelloWorldString();
//
//        // ...then the result should be the expected one.
//        assertThat(result).isEqualTo(FAKE_STRING);
//    }

    @Test
    public void readStringFromWorkingContext_LocalizedString(){

        // ...when the string is returned from the object under test...
        String result = workingTestClass.getHelloWorldString();

        // ...then the result should be the expected one.
        assertThat("Checking that we get the hello world string from context",
                result, is(equalTo(FAKE_STRING)));
    }

    @Test
    public void readStringFromBrokenContext_LocalizedString(){

        // ...when the string is returned from the object under test...
        String result = brokenTestClass.getHelloWorldString();

        // ...then the result should be the expected one.
        assertThat("This is meant to fail, as we should not get hello world string but" +
                        " the wrong string instead",
                result, is(not(FAKE_STRING)));
    }

    @Test
    public void saveAndReadValidPreferences(){
        // Save info to SharedPreferences
        boolean success = workingTestClass.savePreferences(FAKE_PREF_NUM, FAKE_PREF_STR);

        assertThat("Checking that savePreferences returns true", success, is(true));

        // Read num info from SharedPreferences
        int actualNum = workingTestClass.getSharedPrefNum();
        assertThat("Checking that FAKE_PREF_NUM persisted and read correctly",
                actualNum, is( equalTo(FAKE_PREF_NUM) ));

        // Read string info from SharedPreferences
        String actualStr = workingTestClass.getSharedPrefString();
        assertThat("Checking that FAKE_PREF_STR persisted and read correctly",
                actualStr, is( equalTo(FAKE_PREF_STR) ));
    }

    // Should fail
    @Test
    public void saveAndReadBrokenPreferences(){
        // Save info to SharedPreferences
        boolean success = brokenTestClass.savePreferences(FAKE_PREF_NUM, FAKE_PREF_STR);

        assertThat("Broken sharedPreferences should return false", success, is(false));

        // Read num info from SharedPreferences
        int actualNum = brokenTestClass.getSharedPrefNum();
        assertThat("FAKE_PREF_NUM should not be persisted or read correctly",
                actualNum, is( not(FAKE_PREF_NUM) ));

        // Read string info from SharedPreferences
        String actualStr = brokenTestClass.getSharedPrefString();
        assertThat("FAKE_PREF_STR should not b persisted or read correctly",
                actualStr, is( not(FAKE_PREF_STR) ));
    }


}
