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
package com.cezerilab.openjazarilibrary.javafx.unit_test;

import com.github.javafx.charts.zooming.ZoomManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MiniTest extends Application {

    public static void main(final String[] args) {
	launch(args);
    }

    @Override
    public void start(final Stage stage) {
	stage.setTitle("Line Chart Sample");
	// defining the axes
	final NumberAxis xAxis = new NumberAxis();
	final NumberAxis yAxis = new NumberAxis();
	xAxis.setAutoRanging(true);
	xAxis.setForceZeroInRange(false);
	yAxis.setAutoRanging(true);
	yAxis.setForceZeroInRange(false);
	xAxis.setLabel("Number of Month");
	// creating the chart
	final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

	lineChart.setTitle("Stock Monitoring, 2010");
	// defining a series
	final XYChart.Series series = new XYChart.Series();
	series.setName("My portfolio");
	// populating the series with data
	series.getData().add(new XYChart.Data(1, 23));
	series.getData().add(new XYChart.Data(2, 14));
	series.getData().add(new XYChart.Data(3, 15));
	series.getData().add(new XYChart.Data(4, 24));
	series.getData().add(new XYChart.Data(5, 34));
	series.getData().add(new XYChart.Data(6, 36));
	series.getData().add(new XYChart.Data(7, 22));
	series.getData().add(new XYChart.Data(8, 45));
	series.getData().add(new XYChart.Data(9, 43));
	series.getData().add(new XYChart.Data(10, 17));
	series.getData().add(new XYChart.Data(11, 29));
	series.getData().add(new XYChart.Data(12, 25));

	// DO NOT ADD DATA TO CHART
	// bc.getData().addAll(series1, series2, series3);
	final StackPane pane = new StackPane();
	pane.getChildren().add(lineChart);
	final Scene scene = new Scene(pane, 500, 400);
	new ZoomManager(pane, lineChart, series);
	stage.setScene(scene);
	stage.show();
    }
}