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
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * Class for the Grid View, provides the graphics for the Grid Activity and
 * paints the cells where and when appropriate onto the background.
 * 
 */
public class GridView extends View {
    
	// Whether or not the activity is running.
    public static final int PAUSE = 0;
    public static final int RUNNING = 1;
    
    // Animation speed presets.
    private static final long speed_very_slow = 1000;
    private static final long speed_slow = 500;
    private static final long speed_normal = 250;
    private static final long speed_fast = 150;
    private static final long speed_very_fast = 25;
    
    // Colorization theme names.
    public static final String theme_off = "Off";
    public static final String theme_android = "Android";
    public static final String theme_spectrum = "Spectrum";
    public static final String theme_error = "Error";
    
    // Object of the Life class.
    private Life _life;
    
    // Info about the device.
	private Display _display;
	private DisplayMetrics _displayMetrics = new DisplayMetrics();

    // The current color theme.
    private String color;
    
    // The current animation speed.
    private long _moveDelay = speed_normal; 
    
    //Refresh Handler
    private RefreshHandler _redrawHandler = new RefreshHandler();

    /**
     * Class that handles the redrawing of the board.
     * 
     * @author Brett Kromkamp
     */
    class RefreshHandler extends Handler {

    	/** 
    	 * Calls the update() and invalidate() functions of Gridview.
    	 */
        @Override
        public void handleMessage(Message message) {
            GridView.this.update();
            GridView.this.invalidate();
        }

        /** 
         * Removes the current message in the queue and sends another
         * message after a delay.
         */
        public void sleep(long delayMillis) {
            this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
        }
    };

    /**
     * Constructor for the GridView class, initializes the view and creates a 
     * new Life object.
     * 
     * @param context - the context of the application
     * @param attrs - set of attributes pulled from the xml files
     */
    public GridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        // Accesses information regarding the application's current device.
		WindowManager wm = (WindowManager) 
				context.getSystemService(Context.WINDOW_SERVICE);
        _display = wm.getDefaultDisplay();
        _display.getMetrics(_displayMetrics);
        
        // Creates the Life object, passing in the context, height, width, and
        // DPI of the device.
        _life = new Life(context,getH(),getW(),_displayMetrics.densityDpi);

        initGridView();
    }
    
    /**
     * @return height in pixels of the device.
     */
    public int getH(){
    	Point size = new Point();
    	_display.getSize(size);
    	return size.y;
    }
    
    /**
     * @return width in pixels of the device.
     */
    public int getW(){
    	Point size = new Point();
    	_display.getSize(size);
    	return size.x;
    }
    
    /**
     * Either calls update if the mode bit is 1 or does nothing if the mode
     * bit is 0.
     * 
     * @param mode - either PAUSE(0) or RUNNING(1)
     */
    public void setMode(int mode) {
        if (mode == RUNNING) {
            update();
            return;
        }
        if (mode == PAUSE) {
            // TODO: implement.
        }
    }
    
    /**
     * Sets the parameter cell at location (y,x) in the grid to the proper 
     * color, based on the its number of neighbors and the theme selected.
     * 
     * @param cell - Paint object to set the color of.
     * @param y - Y-value of location in the grid.
     * @param x - X-value of location in the grid.
     */
    private void setCellColor(Paint cell, int y, int x){
        int neighbors = _life.calculateNeighbors(y, x);
        if (neighbors > 8 || neighbors < 0){
            /*  Default cases in the themes will still allow the cell to be 
             * shown, despite it having an invalid number of neighbors.*/
            Log.e("setColor","Cell at (" + y + "," + x+ ") has " + neighbors +
                    " neighbors.");
        }
        if (color.equals(theme_spectrum)){
            /*  The Spectrum theme is a rainbow gradient of 8 values 
             *  from blue to red. */
            switch(neighbors){
            case 0:
                cell.setColor(getResources().getColor(R.color.cc_spectrum0));
                break;
            case 1: 
                cell.setColor(getResources().getColor(R.color.cc_spectrum1));
                break;
            case 2:
                cell.setColor(getResources().getColor(R.color.cc_spectrum2));
                break;
            case 3:
                cell.setColor(getResources().getColor(R.color.cc_spectrum3));
                break;
            case 4:
                cell.setColor(getResources().getColor(R.color.cc_spectrum4));
                break;
            case 5:
                cell.setColor(getResources().getColor(R.color.cc_spectrum5));
                break;
            case 6:
                cell.setColor(getResources().getColor(R.color.cc_spectrum6));
                break;
            case 7:
                cell.setColor(getResources().getColor(R.color.cc_spectrum7));
                break;
            default:
                cell.setColor(getResources().getColor(R.color.cc_spectrum8));
                break;
            }
        }
        else if (color.equals(theme_android)){
            /*  The Android theme is a 3 value gradient from pale 
             *  blue to android green. */
            switch(neighbors){
            case 0: case 1:
                cell.setColor(getResources().getColor(R.color.cc_android0));
                break;
            case 2: case 3: 
                cell.setColor(getResources().getColor(R.color.cc_android1));
                break;
            default: 
                // A case of 4-8 neighbors gets the dominant theme color.
                cell.setColor(getResources().getColor(R.color.cc_android2));
                break;
            }
        }
        else if (color.equals(theme_off)){
            // When the theme is 'off', paint the cell black.
            cell.setColor(getResources().getColor(R.color.cc_off));
        }
        else {
            // Any other case would be an error.
            cell.setColor(getResources().getColor(R.color.cc_error));
        }

    }    

    /**
     * Creates a Paint background object and draws on Paint cell objects
     * depending on their value in the grid.
     */
    @Override
    protected void onDraw(Canvas canvas) {
        Paint background = new Paint();
        background.setColor(getResources().getColor(R.color.background));

        Paint cell = new Paint();
        
        // draw background
        canvas.drawRect(0, 0, getWidth(), getHeight(), background);

        // draw cells
        for (int h = 0; h < _life.getHeight(); h++) {
            for (int w = 0; w < _life.getWidth(); w++) {
                if (_life.getGrid()[h][w] != 0) {
                	// Set the color of the cell depending on the algorithm and
                	// settings.
                    setCellColor(cell, h, w);
                    canvas.drawRect(
                        w * _life.getCellSize(), 
                        h * _life.getCellSize(), 
                        (w * _life.getCellSize()) + (_life.getCellSize() -1),
                        (h * _life.getCellSize()) + (_life.getCellSize() -1),
                        cell);
                }
            }
        }
    }
    
    /**
     * Sets the animation speed and color of the next generation and calls for
     * the Life object to generate the next generation.
     */
    private void update() {
        setAnimationSpeed(_life.getAnimationValue());
        setColor(_life.getColorValue());
        _life.generateNextGeneration();
        _redrawHandler.sleep(_moveDelay);
    }
    
    /**
     * Changes color scheme of the cells depending on user settings.
     * 
     * @param colorCode - integer representation of color scheme.
     */
    private void setColor(int colorCode){
        switch (colorCode){
        case 0:
            color = theme_off;
            break;
        case 1:
            color = theme_android;
            break;
        case 2:
            color = theme_spectrum;
            break;
        default:
            Log.e("getColorCoding", "Color code " + colorCode + 
                    " is not an acceptable value.");
            color = theme_error;
            break;
        }
    }
    
    /**
     * Changes animation speed of the cells depending on user settings.
     * 
     * @param speedVar - integer representation of the speed.
     */
    private void setAnimationSpeed(int speedVar){
        switch (speedVar){
        case 5:
            _moveDelay = speed_very_slow;
            break;
        case 4:
            _moveDelay = speed_slow;
            break;
        case 3:
            _moveDelay = speed_normal;
            break;
        case 2:
            _moveDelay = speed_fast;
            break;
        case 1:
            _moveDelay = speed_very_fast;
            break;
        default:
            Log.e("update", "Speed varibale " +  speedVar + 
                    " is not a vaild speed value.");
            break;
        }
    }
    
    /**
     * Initializes the Grid View.
     */
    private void initGridView() {
        setFocusable(true);
    }
    
    /**
     * @return Life object.
     */
    public Life getLife(){
    	return _life;
    }
    
    /**
     * Determines if the cell at the passed-in coordinates is alive or not.
     * 
     * @param x - the x coordinate.
     * @param y - the y coordinate.
     * @return true if the cell is alive at that point.
     */
    public boolean isAlive(int x, int y){
    	/* Floor division is used to return an integer from the float by 
    	 * integer division and returns the correct "coordinate" in the 
    	 * two-dimensional cell array.
    	 */
    	int xCoor = (int) Math.floor(x/_life.getCellSize());
    	int yCoor = (int) Math.floor(y/_life.getCellSize());    	
    	return _life.getGrid()[yCoor][xCoor] != 0;
    }
    
    /**
     * Changes the clicked cell to the opposite state it was in prior, such as
     * alive to dead or dead to alive.  An offset must be used to accurately 
     * decide where the mouse pointer clicks.
     * 
     * @param x - the x coordinate in pixels to be checked.
     * @param y - the y coordinate in pixels to be checked.
     */
    public void flipCellLife(int x, int y){
    	/* 
    	 * Move the y coordinate up the amount of pixels the top of the screen
    	 * takes up, so the location of the click is accurate.
    	 */
    	y = y - (75/_life.getDp());
    	
    	//Check if the coordinate in the two-dimensional array is alive or not.
    	if(isAlive(x,y)){
    		_life.getGrid()[(int) (y/_life.getCellSize())]
    				[(int) x/_life.getCellSize()] = 0;
    	} else {
    		_life.getGrid()[(int) (y/_life.getCellSize())]
    				[(int) x/_life.getCellSize()] = 1;
    		
    	}
    	
    }
}