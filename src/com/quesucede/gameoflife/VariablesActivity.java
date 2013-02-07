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

public class VariablesActivity extends PreferenceActivity {

    // Strings for the behavior variables' names and default values.
    private static final String OPTION_MINIMUM = "UNDERPOPULATION_VARIABLE";
    private static final String OPTION_MINIMUM_DEFAULT = "2";
    private static final String OPTION_MAXIMUM = "OVERPOPULATION_VARIABLE";
    private static final String OPTION_MAXIMUM_DEFAULT = "3";
    private static final String OPTION_SPAWN = "SPAWN_VARIABLE";
    private static final String OPTION_SPAWN_DEFAULT = "3";

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.variables);
    }
    
    /**
     * Getter for the minimum variable selected in the cell behavior menu.
     * 
     * @param context - Context of the Life activity.
     * @return The integer as a String for the minimum variable.
     */
    public static String getMinimumVariable(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).
            getString(OPTION_MINIMUM, OPTION_MINIMUM_DEFAULT);
    }
    
    /**
     * Getter for the maximum variable selected in the cell behavior menu.
     * 
     * @param context - Context of the Life activity.
     * @return The integer as a String for the maximum variable.
     */
    public static String getMaximumVariable(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).
            getString(OPTION_MAXIMUM, OPTION_MAXIMUM_DEFAULT);
    }
    
    /**
     * Getter for the spawn variable selected in the cell behavior menu.
     * 
     * @param context - Context of the Life activity.
     * @return The integer as a String for the spawn number.
     */
    public static String getSpawnVariable(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).
            getString(OPTION_SPAWN, OPTION_SPAWN_DEFAULT);
    }
    
}