package com.neet.DiamondHunter.ViewVivian;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MapView extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Diamond Hunter");
		Group root = new Group();
		MapPane mp = new MapPane();
		root.getChildren().add(mp);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		primaryStage.setResizable(false);
	}

}