/*
 * Copyright (C) 2012 The Android Open Source Project 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */

/**
 * This application is based off of the tutorial for Conway's Game of Life 
 * by Brett Kromkamp available at: 
 * http://www.quesucede.com/page/show/id/conway_game_of_life_android
 *
 * @author  Peter Sevich (pas2363)
 * @author  Jonathon Shippling (jjs5471)
 */

package com.quesucede.gameoflife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * Class for the MainActivity or main screen of the program.
 */
public class MainActivity extends Activity implements OnClickListener {
    /** 
     * Called when the activity is first created. 
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // click-handler for New Game button.
        View newButton = findViewById(R.id.new_button);
        newButton.setOnClickListener(this);
        
        // click-handler for About button.
        View aboutButton = findViewById(R.id.about_button);
        aboutButton.setOnClickListener(this);
        
        // click-handler for Settings button.
        View settingsButton = findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(this);
        
        // click-handler for Exit button.
        View exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);
        
    }

    /**
     * Defines actions for an onClick event.
     */
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.new_button:
        	// Starts a new game.
            Intent gridIntent = new Intent(this, GridActivity.class);
            startActivity(gridIntent);
            break;
        case R.id.settings_button:
        	// Starts the settings activity.
        	Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
        	break;
        case R.id.about_button:
        	// Starts the about activity.
            Intent i = new Intent(this, AboutActivity.class);
            startActivity(i);
            break;
        case R.id.exit_button:
        	// Begins exit process.
        	finish();
        	break;
        }
    }
    
    // Destroys the threads of the application, is called on exit
    protected void onDestroy(){
    	super.onDestroy();
    	android.os.Process.killProcess(android.os.Process.myPid());
    }
}