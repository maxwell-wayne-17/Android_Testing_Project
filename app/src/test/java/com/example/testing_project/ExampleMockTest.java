package com.example.testing_project;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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

@RunWith(MockitoJUnitRunner.class)
public class ExampleMockTest {

    private static final String FAKE_STRING = "HELLO WORLD";
    private static final String FAKE_PREF_STR = "FAKE PREF STR";
    private static final String FAKE_WRONG_STR = "this string is definitely wrong";
    private static final int FAKE_PREF_NUM = 123;

    private ClassUnderTest workingTestClass;
    private ClassUnderTest brokenTestClass;

    @Mock
    SharedPreferences mockSharedPref;

    @Mock
    SharedPreferences mockBrokenSharedPref;

    @Mock
    SharedPreferences.Editor mockEditor;

    @Mock
    SharedPreferences.Editor mockBrokenEditor;

    @Mock
    Context mockContext;

    @Mock
    Context mockBrokenContext;

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

    @Test
    public void readStringFromWorkingContext_LocalizedString(){

        // ...when the string is returned from the object under test...
        String result = workingTestClass.getHelloWorldString();

        // ...then the result should be the expected one.
        assertThat("Checking that we get the hello world string from context",
                result, is(equalTo(FAKE_STRING)));
    }

    // Should fail
    @Test
    public void readStringFromBrokenContext_LocalizedString(){

        // ...when the string is returned from the object under test...
        String result = brokenTestClass.getHelloWorldString();

        // ...then the result should be the expected one.
        assertThat("This is meant to fail, as we should not get hello world string but" +
                        " the wrong string instead",
                result, is(equalTo(FAKE_STRING)));
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
