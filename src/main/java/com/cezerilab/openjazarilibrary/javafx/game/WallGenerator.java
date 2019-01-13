/*
 * The MIT License
 *
 * Copyright 2019 BAP1.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.cezerilab.openjazarilibrary.javafx.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WallGenerator {

	private final int widthInCells ;
	private final int heightInCells ;
	
	/*
	 * Class for generating a random set of walls. The walls must be non-intersecting (except endpoints),
	 * must bound the entire area with a perimeter, must be connected to each other, 
	 * and must leave the interior of the area connected.
	 * 
	 * The area is regarded as a grid of cells, with the walls following the boundaries of cells.
	 * 
	 * The current strategy is to first draw the perimeter walls. Vertices of the cells are marked as "filled" if 
	 * they are contained in an existing wall. Filled vertices are considered as "extensible" in each of four 
	 * directions if they have two or more non-filled vertices in that direction. The amount by which they can
	 * be extended is defined as the number of contiguous adjacent extensible vertices in that direction.
	 * 
	 * A vertex with non-zero extensibility in some direction is picked at random. A direction in which it has
	 * non-zero extensibility is then picked at random, and an amount by which to extend is picked at random in that direction.
	 * A wall is then added. This is repeated until the specified number of walls exists, or there is no option for 
	 * further extensibility.
	 * 
	 */
	

	public WallGenerator(int widthInCells, int heightInCells) {
		this.widthInCells = widthInCells;
		this.heightInCells = heightInCells;
	}
	
	public List<Wall> generateWalls(int numWalls, double cellWidth, double cellHeight) {
		
		// TODO: rewrite this so the relationship between the code and the algorithm is clearer.
		// TODO: only extend perpendicular to existing walls.
		
		boolean[][] filled = new boolean[widthInCells+1][heightInCells+1];
		
		List<IntWall> walls = new ArrayList<>();
		Random rng = new Random();
		
		
		List<IntWall> boundary = createBoundaryWalls();
		boundary.forEach(w -> markFilled(w, filled));
		walls.addAll(boundary);
		
		Map<CellLocation, Integer> minHorizExtension = new HashMap<>();
		Map<CellLocation, Integer> maxHorizExtension = new HashMap<>();
		Map<CellLocation, Integer> minVertExtension = new HashMap<>();
		Map<CellLocation, Integer> maxVertExtension = new HashMap<>();
		
		computeExtensionsAvailable(minHorizExtension, maxHorizExtension, minVertExtension, maxVertExtension, filled) ;
		
		Set<CellLocation> extensibleCells = new HashSet<>();
		extensibleCells.addAll(minHorizExtension.keySet());
		extensibleCells.addAll(maxHorizExtension.keySet());
		extensibleCells.addAll(minVertExtension.keySet());
		extensibleCells.addAll(maxVertExtension.keySet());
		
		while (extensibleCells.size() > 0 && walls.size() < numWalls) {
						
			CellLocation extendFrom = new ArrayList<>(extensibleCells).get(rng.nextInt(extensibleCells.size()));
			List<Map<CellLocation,Integer>> availableDirections = 
					Stream.of(minHorizExtension, maxHorizExtension, minVertExtension, maxVertExtension)
					.filter(m -> m.containsKey(extendFrom))
					.collect(Collectors.toList());
			Map<CellLocation, Integer> direction = availableDirections.get(rng.nextInt(availableDirections.size()));
			int maxDist = direction.get(extendFrom);
			int dist = rng.nextInt(Math.abs(maxDist))+1;
			if (direction == minHorizExtension) {
				IntWall newWall = makeWall(new CellLocation(extendFrom.x - dist, extendFrom.y), extendFrom, filled);
				walls.add(newWall);
			}
			if (direction == maxHorizExtension) {
				IntWall newWall = makeWall(extendFrom, new CellLocation(extendFrom.x + dist, extendFrom.y), filled);
				walls.add(newWall);
			}
			if (direction == minVertExtension) {
				IntWall newWall = makeWall(new CellLocation(extendFrom.x, extendFrom.y - dist), extendFrom, filled);
				walls.add(newWall);
			}
			if (direction == maxVertExtension) {
				IntWall newWall = makeWall(extendFrom, new CellLocation(extendFrom.x, extendFrom.y + dist), filled);
				walls.add(newWall);
			}
			
			computeExtensionsAvailable(minHorizExtension, maxHorizExtension, minVertExtension, maxVertExtension, filled) ;
			
			extensibleCells.clear();
			extensibleCells.addAll(minHorizExtension.keySet());
			extensibleCells.addAll(maxHorizExtension.keySet());
			extensibleCells.addAll(minVertExtension.keySet());
			extensibleCells.addAll(maxVertExtension.keySet());
		}
		
		
		return walls.stream().map(w -> w.toWall(cellWidth, cellHeight)).collect(Collectors.toList());
	}
	

	
	private IntWall makeWall(CellLocation start, CellLocation end, boolean[][] filled) {
		IntWall wall = new IntWall(start, end);
		markFilled(wall, filled);
		return wall ;
	}
	
	private void computeExtensionsAvailable(
			Map<CellLocation, Integer> minHorizExtension, 
			Map<CellLocation, Integer> maxHorizExtension,
			Map<CellLocation, Integer> minVertExtension,
			Map<CellLocation, Integer> maxVertExtension,
			boolean[][] filled) {
		
		Stream.of(minHorizExtension, maxHorizExtension, minVertExtension, maxVertExtension)
			.forEach(Map::clear);
		
		for (int x = 0 ; x < widthInCells ; x++) {
			for (int y = 0 ; y < widthInCells ; y++) {
				int count ;
				if (filled[x][y]) {
					count = 0 ;
					for (int testX = x-1 ; testX > 0 && !filled[testX][y] && ! filled[testX-1][y] ; testX--) {
						count-- ;
					}
					if (count < 0) {
						minHorizExtension.put(new CellLocation(x,y), count);
					}
					count = 0 ;
					for (int testX = x+1 ; testX < widthInCells && !filled[testX][y] && ! filled[testX+1][y] ; testX++) {
						count++ ;
					}
					if (count > 0) {
						maxHorizExtension.put(new CellLocation(x,y), count);
					}
					count = 0 ;
					for (int testY = y-1 ; testY > 0 && !filled[x][testY] && ! filled[x][testY-1]; testY--) {
						count-- ;
					}
					if (count < 0) {
						minVertExtension.put(new CellLocation(x,y), count);
					}
					count = 0 ;
					for (int testY = y+1 ; testY < heightInCells && !filled[x][testY] && ! filled[x][testY+1] ; testY++) {
						count++ ;
					}
					if (count > 0) {
						maxVertExtension.put(new CellLocation(x,y), count);
					}
					
				}

			}
		}
		
	}
	
	private List<IntWall> createBoundaryWalls() {
		return Arrays.asList(
				new IntWall(new CellLocation(0,0), new CellLocation(widthInCells, 0)),
				new IntWall(new CellLocation(widthInCells, 0), new CellLocation(widthInCells, heightInCells)),
				new IntWall(new CellLocation(0,0), new CellLocation(0, heightInCells)),
				new IntWall(new CellLocation(0, heightInCells), new CellLocation(widthInCells, heightInCells))
		);
	}
	
	private void markFilled(IntWall wall, boolean[][] vertices) {
		if (wall.isHorizontal()) {
			for (int x = wall.start.x ; x <= wall.end.x ; x++) {
				vertices[x][wall.start.y] = true ;
			}
		}
		if (wall.isVertical()) {
			for (int y = wall.start.y ; y <= wall.end.y ; y++) {
				vertices[wall.start.x][y] = true ;
			}
		}
	}
	
	private static class IntWall {
		private final CellLocation start ;
		private final CellLocation end ;
		public IntWall(CellLocation start, CellLocation end) {
			
			this.start = start;
			this.end = end;
		}
		
		Wall toWall(double cellWidth, double cellHeight) {
			if (isVertical()) {
				return new VerticalWall(start.x * cellWidth, start.y * cellHeight, end.y * cellHeight);
			}
			if (isHorizontal()) {
				return new HorizontalWall(start.x * cellWidth, end.x * cellWidth, start.y * cellHeight);
			}
			throw new IllegalStateException("Wall is neither horizontal nor vertical: "+this);
		}

		private boolean isHorizontal() {
			return start.y == end.y;
		}

		private boolean isVertical() {
			return start.x == end.x;
		}
		
		@Override
		public String toString() {
			return String.format("%s -> %s", start, end);
		}
	}
	
	private static class CellLocation {
		
		private final int x ;
		private final int y ;

		public CellLocation(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public String toString() {
			return String.format("[%d, %d]", x, y);
		}
	}
}
