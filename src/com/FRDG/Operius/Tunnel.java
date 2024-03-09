package com.FRDG.Operius;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Tunnel {
    private static final double CENTER_X = 500;
    private static final double CENTER_Y = 285;
    private static final double START_RADIUS = 450;
    private static final double SCALING_FACTOR = 0.7;
    private static final int NUM_SEGMENTS = 15;
    private static final double ANIMATION_DURATION = 35; // in seconds
    private static final double SCALE_FACTOR = 2;

    public static void createTunnel(Pane pane) {
        Canvas canvas = new Canvas(1000, 600);
        pane.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.web("#FFFAD7"));
        gc.setLineWidth(1.8);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(ANIMATION_DURATION * NUM_SEGMENTS), canvas);
        scaleTransition.setFromX(1); // Initial scale
        scaleTransition.setFromY(1); // Initial scale
        scaleTransition.setToX(SCALE_FACTOR); // Double the width
        scaleTransition.setToY(SCALE_FACTOR); // Double the height
        scaleTransition.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
        scaleTransition.setInterpolator(Interpolator.LINEAR); // Use linear interpolation for smoother transition
        scaleTransition.play(); // Start the scale transition

        double radius = START_RADIUS;
        double angleStep = Math.toRadians(360 / 6); // 6-sided polygon

        for (int i = 0; i < NUM_SEGMENTS; i++) {
            drawSegment(gc, CENTER_X, CENTER_Y, radius, angleStep);

            radius *= SCALING_FACTOR;
        }
    }

    private static void drawSegment(GraphicsContext gc, double centerX, double centerY, double radius, double angleStep) {
        gc.beginPath();
        for (int i = 0; i < 6; i++) {
            double x = centerX + radius * Math.cos(i * angleStep);
            double y = centerY + radius * Math.sin(i * angleStep);
            if (i == 0) {
                gc.moveTo(x, y);
            } else {
                gc.lineTo(x, y);
            }
        }
        gc.closePath();
        gc.stroke();
    }
    
    public static double getTunnelRadius() {
        return START_RADIUS;
    }
}
