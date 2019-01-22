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
import com.cezerilab.openjazarilibrary.factory.FactoryUtils;
import com.cezerilab.openjazarilibrary.utils.NoiseOpenSimplex;

/**
 *
 * @author BAP1
 */
public class TestNoiseOpenSimplex {
    public static void main(String[] args) {
	double x = 0.005447734756785486781d;
	double y = 0.004923141242323442d;
        long t=FactoryUtils.tic();
        NoiseOpenSimplex nos=new NoiseOpenSimplex(t);
        double[][] d=new double[600][600];
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[0].length; j++) {
                d[i][j]=nos.eval(i*0.01, j*0.01);
            }
        }
        CMatrix cm = CMatrix.getInstance(d)
                .map(0, 255)
                .imshow("Open Simplex Noise")
                ;
        CMatrix cm1 = CMatrix.getInstance()
                .rand(60, 60, 0, 255)
                .filterGaussian(3)
                .imshow()
                ;
    }
}
