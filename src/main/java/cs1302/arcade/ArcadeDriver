package cs1302.arcade;

import javafx.application.Application;

/** 
 * Driver for the {@code ArcadeApp} class.
 */
public class ArcadeDriver {

    /**
     * Main entry-point into the application.
     * @param args the command-line arguments.
     */
    public static void main(String[] args) {
        try {
            Application.launch(ArcadeApp.class, args);
        } catch (UnsupportedOperationException e) {
            System.out.println(e);
            System.err.println("If this is a DISPLAY problem, then your X server connection");
            System.err.println("has likely timed out. This can generally be fixed by logging");
            System.err.println("out and logging back in.");
            System.exit(1);
        } // try
    } // main

} // ArcadeDriver
