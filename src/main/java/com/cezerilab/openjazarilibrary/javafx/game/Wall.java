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
import javafx.scene.shape.Line;

public abstract class Wall {
	
	public abstract double getStartX() ;
	public abstract double getEndX() ;
	public abstract double getStartY() ;
	public abstract double getEndY() ;
	
	public abstract boolean isHorizontal() ;
	public abstract boolean isVertical() ;
	
	public abstract Point2D getIntersectionFrom(Point2D origin, double angle) ;
	
	private Line line ;
	
	public Line asLine() {
		if (line == null) {
			line = new Line(getStartX(), getStartY(), getEndX(), getEndY());
		}
		return line ;
	}
	
	@Override
	public String toString() {
		return String.format("[%.2f, %.2f] -> [%.2f, %.2f]", getStartX(), getStartY(), getEndX(), getEndY());
	}
}