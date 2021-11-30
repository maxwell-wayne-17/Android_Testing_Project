package com.example.testing_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private TestObject testObj;
    private ClassUnderTest prefHelper;

    private FloatingActionButton addButton;
    private FloatingActionButton decButton;

    private TextView tv;
    private TextView numDisplay;

    private SharedPreferences myPreferences;
    private SharedPreferences.OnSharedPreferenceChangeListener listener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set widgets
        addButton = findViewById(R.id.addButton);
        decButton = findViewById(R.id.minusButton);
        tv = findViewById(R.id.textDisplay);
        numDisplay = findViewById(R.id.numDisplay);

        // Set TestObject
        testObj = new TestObject();

        // Set up toolbar with no title
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // Set up preferences
        if (myPreferences == null){
            myPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        }
        // Set up preference helper test object
        prefHelper = new ClassUnderTest(this.getApplicationContext());
        if (listener == null){
            listener = (sharedPreferences, key) -> {
                setPrefValues();
            };
        }
        else{
            setPrefValues();
        }
        myPreferences.registerOnSharedPreferenceChangeListener(listener);


    }

    private void setPrefValues(){
        testObj.setStr(prefHelper.getSharedPrefString(myPreferences));
        testObj.setNum(prefHelper.getSharedPrefNum(myPreferences));
        tv.setText(testObj.getStr());
        numDisplay.setText( Integer.toString(testObj.getNum()) );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Log.d("MAIN_ACT", "launching settings");
            Intent myIntent = new Intent(this, SettingsActivity.class);
            startActivity(myIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void doIncrement(View view) {
        testObj.increment();
        prefHelper.saveNumPref(testObj.getNum(), myPreferences);
        numDisplay.setText( Integer.toString(testObj.getNum()) );
    }

    public void doDecrement(View view) {
        testObj.decrement();
        prefHelper.saveNumPref(testObj.getNum(), myPreferences);
        numDisplay.setText( Integer.toString(testObj.getNum()) );
    }
}