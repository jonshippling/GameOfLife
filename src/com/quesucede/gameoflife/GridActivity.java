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
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;

/**
 * Class for the Grid Activity, or the New Game button.  It holds the
 * simulation itself.
 * 
 */
public class GridActivity extends Activity {

    private GridView _gridView;

    /**
     * Called when the activity is first created. 
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid);
        
        _gridView = (GridView)findViewById(R.id.grid_view); 
        _gridView.setMode(GridView.RUNNING);
    }
    
    /**
     * Called when the menu button is pressed and creates the menu through
     * a MenuInflator.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Starts the appropriate PreferencesActivity from the menu.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                startActivity(new Intent(this, PreferencesActivity.class));
                return true;
        }
        return false;
    }
    
    /**
     * Reads touch(or mouse input) and spawns the appropriate cells.
     */
    public boolean onTouchEvent(MotionEvent event){
		_gridView.flipCellLife((int) event.getX(),(int) event.getY());
    	return true;
    }
    
    /**
     *  Pauses the view of the Grid Activity 
     */
    @Override
    protected void onPause() {
        super.onPause();
        _gridView.setMode(GridView.PAUSE);
    }
}