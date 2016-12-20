package com.neet.DiamondHunter.MapViewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MapView extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception{
		try {
			Parent root = FXMLLoader.load(getClass().getResource("MapViewInterface.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("MapView.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Diamond Hunter Map Viewer"); 
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.sizeToScene();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}