package com.example.testing_project;

import android.content.Context;
import android.util.AttributeSet;

import androidx.preference.EditTextPreference;

/**
 * By default, EditTextPreference will save input as a string, even if inputType="decimal" is declared
 * This class is used in preferences.xml to ensure preference value is saved as an int opposed to string
 * https://stackoverflow.com/questions/3721358/preferenceactivity-save-value-as-integer/3755608#3755608
 */
public class IntEditTextPreference extends EditTextPreference {
    public IntEditTextPreference(Context context) {
        super(context);
    }

    public IntEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IntEditTextPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected String getPersistedString(String defaultReturnValue) {
        return String.valueOf(getPersistedInt(-1));
    }

    @Override
    protected boolean persistString(String value) {
        return persistInt(Integer.valueOf(value));
    }
}
