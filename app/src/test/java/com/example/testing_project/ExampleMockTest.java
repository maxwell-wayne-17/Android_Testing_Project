package com.example.testing_project;

import android.content.Context;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExampleMockTest {

    private static final String FAKE_STRING = "HELLO WORLD";

    // Research the benefits of this
    @Mock
    Context mockContext;

    @Test
    public void readStringFromContext_LocalizedString(){
        // Given a mocked Context injected into the object under test...
        when(mockContext.getString(R.string.hello_world))
                .thenReturn(FAKE_STRING);
        ClassUnderTest myObjectUnderTest = new ClassUnderTest(mockContext);

        // ...when the string is returned from the object under test...
        String result = myObjectUnderTest.getHelloWorldString();

        // ...then the result should be the expected one.
        assertEquals(result, FAKE_STRING);
    }
}
