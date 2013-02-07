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

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/**
 * Class for the initialization of the preferences.
 */

public class PreferencesActivity extends PreferenceActivity {

    // Strings for the preferences' names and default values.
    private static final String OPTION_ANIMATION_SPEED = "ANIMATION_SPEED_VARIABLE";
    private static final String OPTION_ANIMATION_SPEED_DEFAULT = "3";
    private static final String OPTION_COLOR_CODE = "COLOR_CODE_VARIABLE";
    private static final String OPTION_COLOR_CODE_DEFAULT = "0";

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
    
    /**
     * Getter for the animation speed number selected in the preferences menu.
     * "5" is the slowest speed option, while "1" is the fastest.
     * 
     * @param context - Context of the Life activity.
     * @return The integer as a String representing the speed selected.
     */
    public static String getAnimationSpeed(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).
            getString(OPTION_ANIMATION_SPEED, OPTION_ANIMATION_SPEED_DEFAULT);
    }
    
    /**
     * Getter for the color code selected in the preferences menu.
     * This code can be 'decoded' and read as a colorization theme.
     * 
     * @param context - Context of the Life activity.
     * @return The String containing the integer that represents the color 
     *          code type selected.
     */
    public static String getColorCode(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).
            getString(OPTION_COLOR_CODE, OPTION_COLOR_CODE_DEFAULT);
    }
}