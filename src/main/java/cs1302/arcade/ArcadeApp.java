
package cs1302.arcade;

import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ArcadeApp extends Application {

    Random rng = new Random();

    @Override
    public void start(Stage stage) {

	/* You are allowed to rewrite this start method, add other methods, 
	 * files, classes, etc., as needed. This currently contains some 
	 * simple sample code for mouse and keyboard interactions with a node
	 * (rectangle) in a group. 
	 */
	
	Group group = new Group();           // main container
	Rectangle r = new Rectangle(20, 20); // some rectangle
	r.setX(50);                          // 50px in the x direction (right)
	r.setY(50);                          // 50ps in the y direction (down)
	group.getChildren().add(r);          // add to main container
	
	// when the user clicks on the rectangle, send to random position
	r.setOnMouseClicked(event -> {
		System.out.println(event);
		r.setX(rng.nextDouble() * (640 - r.getWidth()));
		r.setY(rng.nextDouble() * (480 - r.getHeight()));
	    });

	// when the user presses left and right, move the rectangle
	group.setOnKeyPressed(event -> {
		System.out.println(event);
		if (event.getCode() == KeyCode.LEFT)  r.setX(r.getX() - 10.0);
		if (event.getCode() == KeyCode.RIGHT) r.setX(r.getX() + 10.0);
		// TODO bounds checking
	    });

        Scene scene = new Scene(group, 640, 480);
        stage.setTitle("cs1302-arcade!");
        stage.setScene(scene);
	stage.sizeToScene();
        stage.show();

	// the group must request input focus to receive key events
	// @see https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#requestFocus--
	group.requestFocus();

    } // start

    public static void main(String[] args) {
	try {
	    Application.launch(args);
	} catch (UnsupportedOperationException e) {
	    System.out.println(e);
	    System.err.println("If this is a DISPLAY problem, then your X server connection");
	    System.err.println("has likely timed out. This can generally be fixed by logging");
	    System.err.println("out and logging back in.");
	    System.exit(1);
	} // try
    } // main

} // ArcadeApp

