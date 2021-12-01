package com.example.testing_project;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

// MUST TURN ANIMATIONS OFF IN EMULATOR OR TEST DEVICE
// Settings -> Accessibility -> toggle "Remove Animations"
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleUiTest {

    private static final String TAG = "FINDME_UI_TEST";

    private int beforeNumDisplayed;

    /**
     * Sort of a hacky way to get current text in a text view
     * Not necessarily recommended and usually not required,
     * I need this since the result depends on what was previous in the text view
     * https://stackoverflow.com/questions/23381459/how-to-get-text-from-textview-using-espresso
     **/
    private String getText(final Matcher<View> matcher) {
        final String[] stringHolder = { null };
        onView(matcher).perform(new ViewAction() {

            @Override
            public org.hamcrest.Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription() {
                return "Getting text from a TextView";
            }

            @Override
            public void perform(UiController uiController, View view) {
                TextView tv = (TextView)view; // Save, because of check in getConstraints()
                stringHolder[0] = tv.getText().toString();
            }
        });
        return stringHolder[0];
    }

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void initNumDisplayed(){
        // Get original num displayed in text view
        beforeNumDisplayed = Integer.parseInt(
                getText( withId(R.id.numDisplay) )
        );
    }


    @Test
    public void testIncrementButton(){

        Log.d(TAG, "beforeNumDisplayed = " + beforeNumDisplayed);
        // Expected value in text view after increment
        String numAfter = Integer.toString(beforeNumDisplayed + 1);


        // Simulate click increment button
        onView(withId(R.id.addButton))
                .perform(click());
        Log.d(TAG, "num after click = " + getText( withId(R.id.numDisplay)) );

        // Compare new value in text view
        onView(withId(R.id.numDisplay))
                .check(matches(ViewMatchers.withText(numAfter)));

    }

    @Test
    public void testDecrementButton(){

        // Expected value in text view after increment
        String numAfter = Integer.toString(beforeNumDisplayed - 1);

        // Simulate click decrement button
        onView(withId(R.id.minusButton))
                .perform(click());

        // Compare new value in text view
        onView(withId(R.id.numDisplay))
                .check(matches(ViewMatchers.withText(numAfter)));

    }

}
