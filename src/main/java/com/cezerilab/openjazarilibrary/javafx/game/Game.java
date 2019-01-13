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
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;



public class Game extends Application {

    private static final int LINES_IN_TRAJECTORY = 4;
    private final int widthInCells = 5;
    private final int heightInCells = 5;

    private final double cellSize = 120;

    private final List<Wall> walls = new ArrayList<>();
    private Circle tank;

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        pane.setMinSize(widthInCells * cellSize, heightInCells * cellSize);
        StackPane view = new StackPane(pane);
        view.setPadding(new Insets(10));

        tank = new Circle(widthInCells * cellSize / 2, heightInCells * cellSize / 2, 10, Color.BLUE);
        pane.getChildren().add(tank);

        Button button = new Button("Generate walls");

        WallGenerator wallGenerator = new WallGenerator(widthInCells, heightInCells);

        button.setOnAction(e -> {

            for (Wall w : walls) {
                pane.getChildren().remove(w.asLine());
            }

            walls.clear();
            walls.addAll(wallGenerator.generateWalls(10, cellSize, cellSize));

            for (Wall w : walls) {
                pane.getChildren().add(w.asLine());
            }
        });

        BorderPane root = new BorderPane(view);
        BorderPane.setAlignment(button, Pos.CENTER);
        BorderPane.setMargin(button, new Insets(5));
        root.setBottom(button);

        Scene scene = new Scene(root);

        BooleanProperty up = new SimpleBooleanProperty();
        BooleanProperty down = new SimpleBooleanProperty();
        BooleanProperty left = new SimpleBooleanProperty();
        BooleanProperty right = new SimpleBooleanProperty();

        List<Line> fireTrajectory = new ArrayList<>();

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP:
                    up.set(true);
                    break;
                case DOWN:
                    down.set(true);
                    break;
                case LEFT:
                    left.set(true);
                    break;
                case RIGHT:
                    right.set(true);
                    break;
                case ENTER:
                    // fire...
                    pane.getChildren().removeAll(fireTrajectory);
                    fireTrajectory.clear();
                    fireTrajectory.addAll(getTrajectory(new Point2D(tank.getCenterX(), tank.getCenterY())));
                    pane.getChildren().addAll(fireTrajectory);
                    break;
                default:
                    break;

            }

        });

        scene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case UP:
                    up.set(false);
                    break;
                case DOWN:
                    down.set(false);
                    break;
                case LEFT:
                    left.set(false);
                    break;
                case RIGHT:
                    right.set(false);
                    break;
                default:
                    break;
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate;

            public void handle(long timeStamp) {
                double elapsedSeconds = (timeStamp - lastUpdate) / 1_000_000_000.0;
                lastUpdate = timeStamp;
                if (elapsedSeconds > 1) {
                    return;
                }

                double deltaX = 0, deltaY = 0;
                double delta = 100 * elapsedSeconds;
                if (up.get()) {
                    deltaY -= delta;
                }
                if (down.get()) {
                    deltaY += delta;
                }
                if (left.get()) {
                    deltaX -= delta;
                }
                if (right.get()) {
                    deltaX += delta;
                }

                tank.setCenterX(clamp(tank.getCenterX() + deltaX, 0, cellSize * widthInCells));
                tank.setCenterY(clamp(tank.getCenterY() + deltaY, 0, cellSize * heightInCells));
            }
        };
        timer.start();

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private List<Line> getTrajectory(Point2D start) {

        List<Line> trajectory = new ArrayList<>();

        double angle = Math.random() * 360;

        Intersection lastIntersection = new Intersection(null, start);

        for (int i = 0; i < LINES_IN_TRAJECTORY; i++) {
            Intersection intersection = findNearestIntersection(angle, lastIntersection);

            Line l = new Line(lastIntersection.intersectingPoint.getX(), lastIntersection.intersectingPoint.getY(),
                    intersection.intersectingPoint.getX(), intersection.intersectingPoint.getY());

            l.setStroke(Color.RED);
            trajectory.add(l);
            if (intersection.wall.isVertical()) {
                angle = 180 - angle;
            }
            if (intersection.wall.isHorizontal()) {
                angle = 360 - angle;
            }
            if (angle < 0) {
                angle += 360;
            }
            lastIntersection = intersection;
        }

        return trajectory;
    }

    private Intersection findNearestIntersection(double angle, Intersection lastIntersection) {
        Intersection intersection = null;
        double minDist = Double.MAX_VALUE;
        for (Wall w : walls) {
            if (w == lastIntersection.wall) {
                continue;
            }
            Point2D wallIntersection = w.getIntersectionFrom(lastIntersection.intersectingPoint, angle);
            if (wallIntersection != null) {
                double dist = lastIntersection.intersectingPoint.distance(wallIntersection);
                if (dist < minDist) {
                    intersection = new Intersection(w, wallIntersection);
                    minDist = dist;
                }
            }
        }
        return intersection;
    }

    private double clamp(double value, double min, double max) {
        return Math.min(max, Math.max(min, value));
    }

    private static class Intersection {

        private final Wall wall;
        private final Point2D intersectingPoint;

        public Intersection(Wall wall, Point2D intersectingPoint) {
            super();
            this.wall = wall;
            this.intersectingPoint = intersectingPoint;
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
