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

public class VerticalWall extends Wall {
	
	private final double x ;
	private final double startY ;
	private final double endY ;
	
	

	public VerticalWall(double x, double startY, double endY) {
		this.x = x;
		this.startY = Math.min(startY, endY);
		this.endY = Math.max(startY, endY);
	}

	@Override
	public double getStartX() {
		return x ;
	}

	@Override
	public double getEndX() {
		return x ;
	}

	@Override
	public double getStartY() {
		return startY;
	}

	@Override
	public double getEndY() {
		return endY;
	}

	@Override
	public Point2D getIntersectionFrom(Point2D origin, double angle) {
		
		// if line is vertical, there is no intersection:
		if ((angle + 90) % 180 == 0) /* vertical */ return null; 
		
		// if line is pointing away from wall, there is no intersection:
		if ((int)(angle+90) / 180 % 2 == 0 /* rightwards */ && x < origin.getX()) return null ;
		if ((int)(angle+90) / 180 % 2 == 1 /* leftwards */ && x > origin.getX()) return null ;
		
		// find y-coordinate of intersection:
		double slope = Math.tan(Math.toRadians(angle)) ;
		double y = origin.getY() + slope * (x - origin.getX());
		
		// if y-coordinate is beyond ends of wall, there is no intersection:
		if (y < startY || y > endY) return null ;
		
		// return intersecting point:
		return new Point2D(x, y);
	}

	@Override
	public boolean isHorizontal() {
		return false;
	}

	@Override
	public boolean isVertical() {
		return true;
	}

}
