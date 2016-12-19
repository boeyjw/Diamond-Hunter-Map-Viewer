// The entry point of the game.
// This class loads up a JFrame window and
// puts a GamePanel into it.

package com.neet.DiamondHunter.Main;

import javax.swing.JFrame;

import com.neet.DiamondHunter.MapViewer.MapView;

import javafx.application.Application;

public class Game {
	private static String[] mainArgs;
	public static void main(String[] args) {
		mainArgs = args;
		JFrame window = new JFrame("Diamond Hunter");
		
		window.add(new GamePanel());
		
		window.setResizable(false);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static void runMapViewer() {
		Application.launch(MapView.class, mainArgs);
	}
	
}
