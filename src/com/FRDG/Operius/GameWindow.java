package com.FRDG.Operius;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GameWindow extends Application{
	private static final int MOVE_SPEED = 20; // Vitesse de déplacement
	private static final double LIMIT_LEFT = 100;
	private static final double LIMIT_RIGHT = 800;
	private Player player1;
    private Player currentPlayer;
    private double targetX;
    private AnimationTimer animationTimer;
    
	 @Override
	    public void start(Stage primaryStage) throws Exception {
		 	Pane pane = new Pane();
	        Scene scene = new Scene(pane, 1000, 600);
	        scene.setOnKeyPressed(this::handleKeyPress);
	        primaryStage.setTitle("Opireus Game");
	        primaryStage.setScene(scene);
	        Image backgroundImage = new Image("/ressources/images/game_background.jpg");
	        ImageView imgView = new ImageView(backgroundImage);
	        imgView.setFitWidth(1000);
	        imgView.setFitHeight(600);
	        pane.getChildren().add(imgView); 
	        
	        BoxBlur b = new BoxBlur(6, 6, 2);
	        
	        imgView.setEffect(b);
	        
	        Tunnel.createTunnel(pane);
	        
	        Circle fond = new Circle(500,278,25);
	        fond.setFill(Color.web("#2F1435"));
	        pane.getChildren().add(fond);
	        
	          
	        
	        // Apply the blend effect to the circle
	        fond.setEffect(b);
	        
	        // Créer les joueurs
	        player1 = new Player("Joueur 1", 'X');
	        currentPlayer = player1;
	        
	     // Set the position of the player's arrow
	        player1.getArrow().setLayoutX(450);
	        player1.getArrow().setLayoutY(570);
	        
	        // Add the player to the pane after other elements
	        pane.getChildren().add(player1.getArrow());
	        
	        targetX = currentPlayer.getArrow().getLayoutX(); // Initialize targetX

	        // Set up the animation timer
	        animationTimer = new AnimationTimer() {
	            private long lastUpdateTime = 0;

	            @Override
	            public void handle(long now) {
	                if (lastUpdateTime == 0) {
	                    lastUpdateTime = now;
	                    return;
	                }

	                double elapsedSeconds = (now - lastUpdateTime) / 5_000_000.0;
	                lastUpdateTime = now;

	                double interpolationFactor = 0.3; 
	                
	                double currentX = interpolate(currentPlayer.getArrow().getLayoutX(), targetX, interpolationFactor, elapsedSeconds);
	                double currentY = calculateYFromX(currentX);

	                currentPlayer.getArrow().setLayoutX(currentX);
	                currentPlayer.getArrow().setLayoutY(currentY);
	            }
	        };

	        animationTimer.start(); // Start the animation timer
	        
	        primaryStage.show();
	    }

	    private void handleKeyPress(KeyEvent event) {
	        switch (event.getCode()) {
	            case A:
	            case LEFT:
	            	targetX = Math.max(LIMIT_LEFT + 160, currentPlayer.getArrow().getLayoutX() - MOVE_SPEED);
	                break;
	            case D:
	            case RIGHT:
	                targetX = Math.min(LIMIT_RIGHT - 160, currentPlayer.getArrow().getLayoutX() + MOVE_SPEED);
	                break;
	            default:
	                break;
	        }
	    }

	    private double interpolate(double start, double end, double factor, double elapsedSeconds) {
	        return start + (end - start) * factor * elapsedSeconds;
	    }

	    private double calculateYFromX(double x) {
	        double normalizedX = (x - LIMIT_LEFT) / (LIMIT_RIGHT - LIMIT_LEFT);
	        double curveHeight = 200; 
	        return curveHeight * Math.sin(normalizedX * Math.PI) + 380;
	    }

}

