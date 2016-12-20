package com.neet.DiamondHunter.MapViewer;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import com.neet.DiamondHunter.Main.GamePanel;
import com.neet.DiamondHunter.Manager.GameStateManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MapView extends Application {
	
	private static JFrame window;
	
	public static void runDHMainGame() {
		window = new JFrame("Diamond Hunter");
		
		window.add(new GamePanel());
		
		window.setResizable(false);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		WindowListener closeWindow = new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				window.setVisible(false);
			}
			
		};
		
		window.addWindowListener(closeWindow);
		
	}

	public static JFrame getWindow() {
		return window;
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
	
	public static void main(String[] args) {
		launch(args);
	}
}