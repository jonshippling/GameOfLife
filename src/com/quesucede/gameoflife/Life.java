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
import android.util.Log;

/**
 * Class for the logic behind the Game of Life simulation.
 */
public class Life {

	// Instance variables for the game board.
	private int dp;
	private int width;
	private int height;  
	private int cellSize;
	
	// The two-dimensional array housing the alive and dead cells
	private int[][] _lifeGrid;

	// Context of the application
	private Context _context;

	/**
	 * Constructor for the Life class, initializes the size of each cell, the
	 * proper width and height of the board, and the 2D cell array.
	 */
	public Life(Context context, int h, int w, int dpi) {
		this._context = context;
		this.dp = dpi/160;
		/* Calculates the proper cell size depending on dpi of the game board,
		 * must be casted to an int to avoid potential errors. */
		this.cellSize = (int) ((dp)*8);
		this.width = w/cellSize;
		this.height = h/cellSize;
		this._lifeGrid = new int[h/cellSize][w/cellSize];
		
		initializeGrid();
	}
	
	/**
	 * @return the two dimensional cell array
	 */
	public int[][] getGrid() {
		return _lifeGrid;
	}

	/**
	 * @return the height of the game board
	 */
	public int getHeight(){
		return height;
	}
	
	/**
	 * @return the width of the game board
	 */
	public int getWidth(){
		return width;
	}

	/**
	 * @return the size of the cells
	 */
	public int getCellSize(){
		return cellSize;
	}
	
	/**
	 * @return the density independent pixel value
	 */
	public int getDp(){
		return dp;
	}

	/**
	 * Defines an arbitrary Life pattern
	 */
	public void initializeGrid() {
		resetGrid(_lifeGrid);

		_lifeGrid[8][(width / 2) - 1] = 1;
		_lifeGrid[8][(width / 2) + 1] = 1;
		_lifeGrid[9][(width / 2) - 1] = 1;
		_lifeGrid[9][(width / 2) + 1] = 1;
		_lifeGrid[10][(width / 2) - 1] = 1;
		_lifeGrid[10][(width / 2)] = 1;
		_lifeGrid[10][(width / 2) + 1] = 1;
	}

	/**
	 * Sets min, max, and spawn speed variables according to user settings and 
	 * creates the next generation of cells depending on previously set rules.
	 */
	public void generateNextGeneration() {
		int neighbors;
		int minimum = Integer.parseInt(VariablesActivity
				.getMinimumVariable(this._context));
		int maximum = Integer.parseInt(VariablesActivity
				.getMaximumVariable(this._context));
		int spawn = Integer.parseInt(VariablesActivity
				.getSpawnVariable(this._context));

		int[][] nextGenerationLifeGrid = new int[height][width];

		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				neighbors = calculateNeighbors(h, w);

				if (_lifeGrid[h][w] != 0) {
					if ((neighbors >= minimum) && (neighbors <= maximum)) {
						nextGenerationLifeGrid[h][w] = neighbors;
					}
				} else {
					if (neighbors == spawn) {
						nextGenerationLifeGrid[h][w] = spawn;
					}
				}
			}
		}
		copyGrid(nextGenerationLifeGrid, _lifeGrid);
	}

	/**
	 * Resets all cells to dead.
	 * 
	 * @param grid - the cell array to be reset.
	 */
	private void resetGrid(int[][] grid) {
		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				grid[h][w] = 0;
			}
		}
	}

	/**
	 * Calculates the neighbors of every living cell.
	 * 
	 * @param y - height of game board.
	 * @param x - width of game board.
	 * @return the neighbors of the cell.
	 */
	public int calculateNeighbors(int y, int x) {
		int total = (_lifeGrid[y][x] != 0) ? -1 : 0;
		for (int h = -1; h <= +1; h++) {
			for (int w = -1; w <= +1; w++) {
				if (_lifeGrid[(height + (y + h)) % height][(width + (x + w))
				                                           % width] != 0) {
					total++;
				}
			}
		}
		return total;
	}

	/**
	 * Creates a copy of the grid.
	 * 
	 * @param source - Original grid to be copied.
	 * @param destination - New grid to be copied into.
	 */
	private void copyGrid(int[][] source, int[][] destination) {
		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				destination[h][w] = source[h][w];
			}
		}
	}

	/**
	 * Fetches and animation speed integer from the VariablesActivity.
	 * 
	 * @return String of the theme name selected.
	 */
	public int getAnimationValue(){
		return Integer.parseInt(PreferencesActivity
				.getAnimationSpeed(_context));
	}

	/**
	 * Fetches and decodes the color code from the VariablesActivity.
	 * 
	 * @return String of the theme name selected.
	 */
	public int getColorValue(){
		return Integer.parseInt(PreferencesActivity
				.getColorCode(_context));

	}
}