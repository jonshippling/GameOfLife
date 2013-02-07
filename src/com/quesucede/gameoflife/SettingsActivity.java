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

public class SettingsActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        
        View aboutButton = findViewById(R.id.variables_button);
        aboutButton.setOnClickListener(this);
        
        View settingsButton = findViewById(R.id.preferences_button);
        settingsButton.setOnClickListener(this);
    }
    
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.variables_button:
            Intent variablesIntent = new Intent(this, VariablesActivity.class);
            startActivity(variablesIntent);
            break;
        case R.id.preferences_button:
            Intent preferencesIntent = new Intent(this, PreferencesActivity.class);
            startActivity(preferencesIntent);
            break;            
        }
    }
}