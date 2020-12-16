package cs1302.arcade;

import java.util.Scanner;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;


/**
 * Application subclass for {@code ArcadeApp}.
 * @version 2019.fa
 */
public class ArcadeApp extends Application {

    // Items for representing GUI chessboard
    StackPane[][] stackpane = new StackPane[8][8];
    HBox[] hbox = new HBox[8];
    VBox vbox = new VBox();
    Group group = new Group();           // main container
    Rectangle[][] rectangles = new Rectangle[8][8];

    // Images for white pieces
    Image pawnW = new Image("file:resources/Chess_plt45.svg.png", 50, 50, true, true);
    Image knightW = new Image("file:resources/Chess_nlt45.svg.png", 50, 50, true, true);
    Image bishopW = new Image("file:resources/Chess_blt45.svg.png", 50, 50, true, true);
    Image rookW = new Image("file:resources/Chess_rlt45.svg.png", 50, 50, true, true);
    Image queenW = new Image("file:resources/Chess_qlt45.svg.png", 50, 50, true, true);
    Image kingW = new Image("file:resources/Chess_klt45.svg.png", 50, 50, true, true);

    // Images for black pieces
    Image pawnB = new Image("file:resources/Chess_pdt45.svg.png", 50, 50, true, true);
    Image knightB = new Image("file:resources/Chess_ndt45.svg.png", 50, 50, true, true);
    Image bishopB = new Image("file:resources/Chess_bdt45.svg.png", 50, 50, true, true);
    Image rookB = new Image("file:resources/Chess_rdt45.svg.png", 50, 50, true, true);
    Image queenB = new Image("file:resources/Chess_qdt45.svg.png", 50, 50, true, true);
    Image kingB = new Image("file:resources/Chess_kdt45.svg.png", 50, 50, true, true);

    // Objects for handling GUI mouse interaction
    String coordinates = "";
    boolean pieceClicked = false;

    // Non-GUI related objects
    ChessGame game = new ChessGame();
    ChessBoard board = game.getBoard();

    // Title Screen "Chess" letters represented as images created by me
    Image letterC = new Image("file:resources/letterC.png", 50, 50, true, true);
    Image letterH = new Image("file:resources/letterH.png", 50, 50, true, true);
    Image letterE = new Image("file:resources/letterE.png", 50, 50, true, true);
    Image letterS = new Image("file:resources/letterS.png", 50, 50, true, true);
    ImageView cView = new ImageView(letterC);
    ImageView hView = new ImageView(letterH);
    ImageView eView = new ImageView(letterE);
    ImageView sView1 = new ImageView(letterS);
    ImageView sView2 = new ImageView(letterS);

    // Title
    VBox boxes = new VBox();
    HBox titleVbox = new HBox();
    VBox buttonBox = new VBox();
    Text name = new Text("By: Matthew Tzou");
    Button playGame = new Button("Play");
    Button help = new Button("Help");
    Button exit = new Button("Exit");

    // Items for title screen animations
    Path[] paths = new Path[5];
    MoveTo[] moveTos = new MoveTo[5];
    LineTo[] lineTos = new LineTo[5];
    PathTransition[] pathTransitions = new PathTransition[5];
    int defaultX = 50;
    int tempY1 = 75;
    int tempY2 = 100;

    // Items for user interface
    HBox topBar = new HBox();
    MenuButton menu = new MenuButton("Menu");
    MenuItem returnToTitle = new MenuItem("Return to Title");
    MenuItem reset = new MenuItem("Reset Game");
    MenuItem close = new MenuItem("Exit Application");
    Button whiteResign = new Button("Resign for White");
    Button blackResign = new Button("Resign for Black");
    Text whiteScoring = new Text("White's Score: " + game.getWhiteScore());
    Text blackScoring = new Text("Black's Score: " + game.getBlackScore());

    /**
     * Return a mouse event handler that registers the user clicking squares on the board. The
     * method then checks to see whether a piece has been clicked and if that piece is of the
     * color who has to move.
     *
     * @param x the x coordinate of the square on the board represented as a 2D array
     * @param y the y coordinate of the square on the board represented as a 2D array
     * @return the mouse event handler for clicking a square
     */
    private EventHandler<? super MouseEvent> registerClick(int x, int y) {
        return event -> {
            // Checks whether a valid piece had been clicked previously
            if ((pieceClicked == false)) {
                // Concatenates coordinates if a valid click is registered
                if (validClick(x, y) == true) {
                    coordinates = coordinates + x + " " + y + " ";
                    pieceClicked = true;
                }
            } else if ((pieceClicked == true)) {
                coordinates = coordinates + x + " " + y;
                // Runs the coordinate processing after four ints have been concatenated
                game.promptUser(coordinates);
                // Resets the coordinates and whether a piece had been clicked
                coordinates = "";
                pieceClicked = false;
            }
            Platform.runLater(() -> updateBoard());
            // Checks whether the game is in a check scenario
            if (game.isInCheck() == true) {
                // Nested if statements check for black being in check and/or checkmated
                if (game.blackInCheck() == true) {
                    if (game.blackInCheckmate() == true) {
                        Stage message = new Stage();
                        VBox messageVbox = new VBox(10);
                        messageVbox.getChildren().add(new Text("Black was checkmated. White won!"));
                        Scene dialogScene = new Scene(messageVbox, 250, 200);
                        message.setScene(dialogScene);
                        message.show();
                        game.newBoard();
                        board = game.getBoard();
                        game.resetScores();
                        updateBoard();
                    }
                    // Nested if statements check for white being in check and/or checkmated
                } else if (game.whiteInCheck() == true) {
                    if (game.whiteInCheckmate() == true) {
                        Stage message = new Stage();
                        VBox messageVbox = new VBox(10);
                        messageVbox.getChildren().add(new Text("White was checkmated. Black won!"));
                        Scene dialogScene = new Scene(messageVbox, 250, 200);
                        message.setScene(dialogScene);
                        message.show();
                        game.newBoard();
                        board = game.getBoard();
                        game.resetScores();
                        updateBoard();
                    }
                }
            }
        };
    } // registerClick

    /** {@inheritDoc} */
    @Override
    public void start(Stage stage) {
        // Sets the font to rockwell font
        name.setFont(Font.font("rockwell", FontWeight.BOLD, FontPosture.REGULAR, 20));
        whiteScoring.setFont(Font.font("rockwell", FontWeight.BOLD, FontPosture.REGULAR, 14));
        blackScoring.setFont(Font.font("rockwell", FontWeight.BOLD, FontPosture.REGULAR, 14));
        topBar.getChildren().addAll(menu, whiteResign, blackResign);
        vbox.getChildren().addAll(topBar);
        Scene boardScene = new Scene(vbox, 600, 480);
        // Initializes all hboxes representing chessboard rows
        for (int i = 0; i < 8; i++) {
            hbox[i] = new HBox();
        }
        // Updates the GUI chess board and sets up title animation
        updateBoard();
        setPathTransitions();

        playGame.setMinWidth(100);
        help.setMinWidth(100);
        exit.setMinWidth(100);
        titleVbox.getChildren().addAll(cView, hView, eView, sView1, sView2);
        buttonBox.getChildren().addAll(name, playGame, help, exit);
        buttonBox.setAlignment(Pos.CENTER);
        // Adjusts the buttons down as to not overlap with animation
        buttonBox.setTranslateY(150);
        boxes.getChildren().addAll(titleVbox, buttonBox);
        // Sets title background to white to match animated letters
        boxes.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY,
            Insets.EMPTY)));
        Scene title = new Scene(boxes, 300, 400);
        stage.setScene(title);
        stage.setTitle("cs1302-arcade!");
        stage.sizeToScene();
        stage.show();

        // Title buttons
        playGame.setOnAction(event -> stage.setScene(boardScene));
        exit.setOnAction(event -> System.exit(0));

        resignButtonActions();
        returnToTitle.setOnAction(event -> stage.setScene(title));
        reset.setOnAction(event -> {
            // Resets the board, scores, and GUI chess board
            game.newBoard();
            board = game.getBoard();
            game.resetScores();
            updateBoard();
        });
        close.setOnAction(event -> System.exit(0));
        help.setOnAction(event -> helpButtonAction());
        menu.getItems().addAll(returnToTitle, reset, close);
        // the group must request input focus to receive key events
        // @see https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#requestFocus--
        group.requestFocus();
    } // start

    /**
     * Represents the actions for the buttons representing resigning for white and resigning
     * for black. The method sets up the action that follows when the respective buttons are
     * clicked.
     */
    private void resignButtonActions() {
        whiteResign.setOnAction(event -> {
            final Stage message = new Stage();
            VBox messageVbox = new VBox(10);
            messageVbox.getChildren().add(new Text("White resigned. Black won!"));
            Scene dialogScene = new Scene(messageVbox, 200, 150);
            message.setScene(dialogScene);
            message.show();
            // Resets the board, scores, and GUI chess board
            game.newBoard();
            board = game.getBoard();
            game.resetScores();
            updateBoard();
        });
        blackResign.setOnAction(event -> {
            final Stage message = new Stage();
            VBox messageVbox = new VBox(10);
            messageVbox.getChildren().add(new Text("Black resigned. White won!"));
            Scene dialogScene = new Scene(messageVbox, 200, 150);
            message.setScene(dialogScene);
            message.show();
            // Resets the board, scores, and GUI chess board
            game.newBoard();
            board = game.getBoard();
            game.resetScores();
            updateBoard();
        });
    }

    /**
     * Represents the action for clicking the help button on the title. The method displays a popup
     * explaining the mechanisms for this particular chess application.
     */
    private void helpButtonAction() {
        final Stage dialog = new Stage();
        VBox dialogVbox = new VBox(10);
        dialogVbox.getChildren().add(new Text("Welcome to Matthew Tzou's JavaFX chess! This chess" +
            " \ngame is operated via click commands and follows \nconventional chess gameplay " +
            "rules. Scoring works based on \nuniversally-recognized chess piece values," +
            " where a pawn = 1 \npoint, knight and bishop = 3 points, rook = 5 points, and" +
            " \nqueen = 9 points. Start a game" +
            " whenever you are ready!"));
        Scene dialogScene = new Scene(dialogVbox, 400, 350);
        dialog.setScene(dialogScene);
        dialog.show();
    } // helpButtonAction

    /**
     * Updates the GUI representing the chess board. This includes the individual squares, the
     * pieces on each squares, and the text showing the scores.
     */
    public void updateBoard() {
        // Re-initializes the scoring each iteration to update scores
        whiteScoring = new Text("White's Score: " + game.getWhiteScore());
        blackScoring = new Text("Black's Score: " + game.getBlackScore());
        whiteScoring.setFont(Font.font("rockwell", FontWeight.BOLD, FontPosture.REGULAR, 14));
        blackScoring.setFont(Font.font("rockwell", FontWeight.BOLD, FontPosture.REGULAR, 14));
        // Clears previous GUI chessboard
        for (int i = 0; i < 8; i++) {
            hbox[i].getChildren().clear();
        } // for
        // Nested for loops that initialize hbox, rectangles, and stackpane for displaying squares
        for (int i = 0; i < 8; i++) {
            hbox[i] = new HBox();
            for (int j = 0; j < 8; j++) {
                rectangles[i][j] = new Rectangle(50, 50);
                stackpane[i][j] = new StackPane();
                updateRectangles(i, j);
                // if statement that checks if a piece has been clicked
                if (pieceClicked == true) {
                    // Scanner parses through coordinates to obtain x, y of clicked piece
                    Scanner coordinateScanner = new Scanner(coordinates);
                    int x = coordinateScanner.nextInt();
                    int y = coordinateScanner.nextInt();
                    // Clicked piece's square set to LIMEGREEN
                    rectangles[x][y].setFill(Color.LIMEGREEN);
                    coordinateScanner.close();
                }
                // Updates stackpanes to include all necessary graphics if piece exists on i, j
                if (board.getPiece(i, j) != null) {
                    updateStackpanes(i ,j);
                    // Stackpane only includes chess square if no piece present
                } else {
                    stackpane[i][j].getChildren().add(rectangles[i][j]);
                }
                // Adds each stackpane to the hbox representing the board row
                hbox[i].getChildren().add(stackpane[i][j]);
            } // for
            // Sets the text for scoring next to the first and last chessboard rows
            if (i == 0) {
                hbox[i].getChildren().add(blackScoring);
            } else if (i == 7) {
                hbox[i].getChildren().add(whiteScoring);
            }
        } // for
        // Nested for loops that set clickability to each stackpane square
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                stackpane[i][j].setOnMouseClicked(registerClick(i, j));
            }
        }
        for (int i = 0; i < 8; i++) {
            vbox.getChildren().add(hbox[i]);
        }
    } // updateBoard

    /**
     * Updates the squares by setting up their color.
     *
     * @param i the x coordinate of the square
     * @param j the y coordinate of the square
     */
    private void updateRectangles(int i, int j) {
        // if statement checks if i representing row is even
        if (i % 2 == 0) {
            // Alternates each subsequent square by WHEAT and SIENNA
            if ((j % 2 == 0)) {
                rectangles[i][j].setFill(Color.WHEAT);
            } else {
                rectangles[i][j].setFill(Color.SIENNA);
            }
            // else statement for i representing odd
        } else {
            // Alternates each subsequent square by WHEAT and SIENNA
            if ((j % 2 != 0)) {
                rectangles[i][j].setFill(Color.WHEAT);
            } else {
                rectangles[i][j].setFill(Color.SIENNA);
            }
        }
    }

    /**
     * Checks whether the user's mouse action on the chess board represents a possible click
     * response based on the color to move and the piece selected.
     *
     * @param x x coordinate of the 2D array representing the chessboard
     * @param y y coordinate of the 2D array representing the chessboard
     * @return true if the clicked square contains a piece whose color has the current turn
     */
    public boolean validClick(int x, int y) {
        // Checks whether the user clicked a square containing a piece
        if (board.getPiece(x, y) != null) {
            // Checks if it is white to move and a white piece has been clicked
            if ((game.getWhiteTurn() == true) && (board.getPiece(x, y).getWhite() == true)) {
                return true;
                // Checks if it is black to move and a black piece has been clicked
            } else if ((game.getWhiteTurn() == false) && (board.getPiece(x, y).getWhite()
                == false)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    } // validClick

    /**
     * Updates the stackpanes of the chessboard to add the image of the specified chess piece to
     * each stackpane square.
     *
     * @param i the x coordinate of the 2D array representing the chessboard
     * @param j the y coordinate of the 2D array representing the chessboard
     */
    private void updateStackpanes(int i, int j) {
        // Checks if the piece at i, j is white
        if (board.getPiece(i, j).getWhite() == true) {
            // if-else statements assign the correct image to the square based on the type of piece
            if (board.getPiece(i, j) instanceof Pawn) {
                stackpane[i][j].getChildren().addAll(rectangles[i][j], new ImageView(pawnW));
            } else if (board.getPiece(i, j) instanceof Bishop) {
                stackpane[i][j].getChildren().addAll(rectangles[i][j], new ImageView(bishopW));
            } else if (board.getPiece(i, j) instanceof Knight) {
                stackpane[i][j].getChildren().addAll(rectangles[i][j], new ImageView(knightW));
            } else if (board.getPiece(i, j) instanceof Rook) {
                stackpane[i][j].getChildren().addAll(rectangles[i][j], new ImageView(rookW));
            } else if (board.getPiece(i, j) instanceof Queen) {
                stackpane[i][j].getChildren().addAll(rectangles[i][j], new ImageView(queenW));
            } else if (board.getPiece(i, j) instanceof King) {
                stackpane[i][j].getChildren().addAll(rectangles[i][j], new ImageView(kingW));
            }
            // else for black piece at i, j
        } else {
            // if-else statements assign the correct image to the square based on the type of piece
            if (board.getPiece(i, j) instanceof Pawn) {
                stackpane[i][j].getChildren().addAll(rectangles[i][j], new ImageView(pawnB));
            } else if (board.getPiece(i, j) instanceof Bishop) {
                stackpane[i][j].getChildren().addAll(rectangles[i][j], new ImageView(bishopB));
            } else if (board.getPiece(i, j) instanceof Knight) {
                stackpane[i][j].getChildren().addAll(rectangles[i][j], new ImageView(knightB));
            } else if (board.getPiece(i, j) instanceof Rook) {
                stackpane[i][j].getChildren().addAll(rectangles[i][j], new ImageView(rookB));
            } else if (board.getPiece(i, j) instanceof Queen) {
                stackpane[i][j].getChildren().addAll(rectangles[i][j], new ImageView(queenB));
            } else if (board.getPiece(i, j) instanceof King) {
                stackpane[i][j].getChildren().addAll(rectangles[i][j], new ImageView(kingB));
            }
        }
    } // updateStackpanes

    /**
     * Sets up the transitions for the "Chess" letter art in the title.
     */
    private void setPathTransitions() {
        // for loop that runs 5 times representing 5 letters in "CHESS"
        for (int i = 0; i < 5; i++) {
            // New path with moveTo and lineTo to move along initialized for each letter
            paths[i] = new Path();
            moveTos[i] = new MoveTo(defaultX, tempY1);
            lineTos[i] = new LineTo(defaultX, tempY2);
            paths[i].getElements().addAll(moveTos[i], lineTos[i]);
            // New PathTransition initialized for each letter to oscillate every second
            pathTransitions[i] = new PathTransition();
            pathTransitions[i].setDuration(Duration.millis(1000));
            // Sets the first letter to show "C" with starting animation position defined
            if (i == 0) {
                pathTransitions[i].setNode(cView);
                tempY1 = 100;
                tempY2 = 75;
                // Sets the second letter to show "H" with starting animation position defined
            } else if (i == 1) {
                pathTransitions[i].setNode(hView);
                tempY1 = 75;
                tempY2 = 100;
                // Sets the third letter to show "E" with starting animation position defined
            } else if (i == 2) {
                pathTransitions[i].setNode(eView);
                tempY1 = 100;
                tempY2 = 75;
                // Sets the fourth letter to show "S" with starting animation position defined
            } else if (i == 3) {
                pathTransitions[i].setNode(sView1);
                tempY1 = 75;
                tempY2 = 100;
                // Sets the fifth letter to show "S" with starting animation position defined
            } else if (i == 4) {
                pathTransitions[i].setNode(sView2);
            }
            // Sets the transitions to operate an Integer.MAX_VALUE number of times
            pathTransitions[i].setPath(paths[i]);
            pathTransitions[i].setCycleCount(Integer.MAX_VALUE);
            // Allows the transitions to move back and forth
            pathTransitions[i].setAutoReverse(true);
            pathTransitions[i].play();
        }
    } // setPathTransitions
} // ArcadeApp
