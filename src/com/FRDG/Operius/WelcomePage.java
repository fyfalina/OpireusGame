package com.FRDG.Operius;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WelcomePage extends Application {

    private static final String FONT_PATH = "/ressources/fonts/PressStart2P.ttf"; 
    private static final Image BACKGROUND_IMAGE = new Image("/ressources/images/start_background.jpg"); 
    
    @Override
    public void start(Stage primaryStage) throws Exception {

    	 // Create an ImageView with the background image
        ImageView backgroundImage = new ImageView(BACKGROUND_IMAGE);
        backgroundImage.setFitWidth(1000); // Set the width to match the scene width
        backgroundImage.setFitHeight(600); // Set the height to match the scene height

        // Create a StackPane to hold the background image
        StackPane backgroundPane = new StackPane(backgroundImage);

        Font retroFont = Font.loadFont(getClass().getResourceAsStream(FONT_PATH), 20);
        
        // Create the text node
        Text startText = new Text("Press ENTER to start");
        startText.setFont(retroFont);
        startText.setFill(Color.WHITE); // Set the color of the text

        // Position the text node at the center of the scene
        StackPane.setAlignment(startText, Pos.BOTTOM_CENTER);
        StackPane.setMargin(startText, new Insets(40));

        // Add the text node to the StackPane
        backgroundPane.getChildren().add(startText);

        // Create the scene
        Scene welcomeScene = new Scene(backgroundPane, 1000, 600);

        // Set the scene and title
        primaryStage.setTitle("Opireus Game");
        primaryStage.setScene(welcomeScene);
        primaryStage.show();
        
        // Handle key press event
        welcomeScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    // Switch to the game window when ENTER key is pressed
                    GameWindow gameWindow = new GameWindow();
                    try {
                        gameWindow.start(primaryStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}