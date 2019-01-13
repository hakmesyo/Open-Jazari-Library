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

import javafx.geometry.Point2D;

public class HorizontalWall extends Wall {
	
	private final double startX ;
	private final double endX ;
	private final double y ;
	
	public HorizontalWall(double startX, double endX, double y) {
		this.startX = Math.min(startX, endX);
		this.endX = Math.max(startX, endX);
		this.y = y ;
	}

	@Override
	public double getStartX() {
		return startX ;
	}

	@Override
	public double getEndX() {
		return endX ;
	}

	@Override
	public double getStartY() {
		return y ;
	}

	@Override
	public double getEndY() {
		return y ;
	}

	@Override
	public Point2D getIntersectionFrom(Point2D origin, double angle) {
		
		// if line is horizontal, there is no intersection:
		if (angle % 180 == 0) return null ;
		
		// if line is pointing away from wall, there is no intersection:
		if ( ((int) angle / 180) % 2 == 0 /* downward (+ve y) */ && y < origin.getY()) return null ;
		if ( ((int) angle / 180) % 2 == 1 /* upward (-ve y) */ && y > origin.getY()) return null ;
		
		// find x-coordinate of intersection:
		double slope = Math.tan(Math.toRadians(angle)) ;
		double x = origin.getX() + (y - origin.getY()) / slope ;
		
		// if x-coordinate is beyond ends of wall, there is no intersection:
		if (x < startX || x > endX) return null ;
		
		// return intersecting point:
		return new Point2D(x, y);
	}

	@Override
	public boolean isHorizontal() {
		return true;
	}

	@Override
	public boolean isVertical() {
		return false;
	}

}
