package cs1302.arcade;

import java.io.File;

import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Application subclass for {@code ArcadeApp}.
 * @version 2019.fa
 */
public class ArcadeApp extends Application {

    Group group = new Group();           // main container
    Random rng = new Random();           // random number generator
    Rectangle r = new Rectangle(20, 20); // some rectangle

    File bishopFile = new File("/resources/Chess_bdt60.png");
    Image bishop = new Image(bishopFile.toURI().toString());
    ImageView iv = new ImageView(bishop);

    /**
     * Return a mouse event handler that moves to the rectangle to a random
     * position any time a mouse event is generated by the associated node.
     * @return the mouse event handler
     */
    private EventHandler<? super MouseEvent> createMouseHandler() {
        return event -> {
            System.out.println(event);
            r.setX(rng.nextDouble() * (640 - r.getWidth()));
            r.setY(rng.nextDouble() * (480 - r.getHeight()));
        };
    } // createMouseHandler

    /**
     * Return a key event handler that moves to the rectangle to the left
     * or the right depending on what key event is generated by the associated
     * node.
     * @return the key event handler
     */
    private EventHandler<? super KeyEvent> createKeyHandler() {
        return event -> {
            System.out.println(event);
            switch (event.getCode()) {
            case LEFT:  // KeyCode.LEFT
                r.setX(r.getX() - 10.0);
                break;
            case RIGHT: // KeyCode.RIGHT
                r.setX(r.getX() + 10.0);
                break;
            default:
                // do nothing
            } // switch
            // TODO bounds checking
        };
    } // createKeyHandler

    /** {@inheritDoc} */
    @Override
    public void start(Stage stage) {

        /* You are allowed to rewrite this start method, add other methods,
         * files, classes, etc., as needed. This currently contains some
         * simple sample code for mouse and keyboard interactions with a node
         * (rectangle) in a group.
         */


        r.setX(50);                                // 50px in the x direction (right)
        r.setY(50);                                // 50ps in the y direction (down)
        group.getChildren().add(r);                // add to main container
        r.setOnMouseClicked(createMouseHandler()); // clicks on the rectangle move it randomly
        group.setOnKeyPressed(createKeyHandler()); // left-right key presses move the rectangle



        Scene scene = new Scene(group, 640, 480);
        stage.setTitle("cs1302-arcade!");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();

        // the group must request input focus to receive key events
        // @see https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#requestFocus--
        group.requestFocus();

    } // start

} // ArcadeApp
