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
package com.cezerilab.openjazarilibrary.utils.unit_test;

import com.cezerilab.openjazarilibrary.core.CMatrix;
import com.cezerilab.openjazarilibrary.utils.NoisePerlin;

/**
 *
 * @author BAP1
 */
public class TestNoisePerlin {
    public static void main(String[] args) {
        System.out.println("noise = " + NoisePerlin.noise(1.2,3.4,4.5));
	double x = 0.05447734756785486781d;
	double y = 0.04923141242323442d;
        NoisePerlin np=new NoisePerlin();
        double[][] d=new double[600][600];
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[0].length; j++) {
                d[i][j]=NoisePerlin.noise(i*x, j*y,x);
            }
        }
        CMatrix cm = CMatrix.getInstance(d)
                .map(0, 255)
                .imshow("Perlin Noise")
                ;
    }
}
