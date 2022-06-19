package project2;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import project1testlatter.SeparatePosition;

public class TankPane extends Application {
    PathTransition transition;
    int c = 140, r = 140;
    int eachBlockSize = 50;
    SeparatePosition position = new SeparatePosition(50, 50);
    MapPane mapPane;
    ImageView imageView = new ImageView(new Image("images/tank-up.png"));
    Circle circle;
    public void start(Stage primaryStage) {
        mapPane.setX(150);
        mapPane.setY(150);
        imageView.setX(mapPane.getX());
        imageView.setY(mapPane.getY());
        Pane pane = new Pane();
        pane.getChildren().add(imageView);
        mapPane.setX(175);
        mapPane.setY(150);
        circle = new Circle(mapPane.getX(), mapPane.getY(), 5);
        pane.getChildren().add(circle);
        Scene scene = new Scene(pane, 700, 700, Color.DARKSEAGREEN);

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
//                case LEFT: moveLeft(); break;
//                case RIGHT: moveRight(); break;
//                case UP: moveUp(); break;
//                case DOWN: moveDown(); break;
                case SHIFT: fire(); break;
            }
        });

        primaryStage.setTitle("MoveImage"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show();
    }
//    void moveLeft() {
//        imageView.setImage(new Image("images/tank-left.png"));
//        transition = new PathTransition(Duration.millis(1000), new Line(eachBlockSize / 2 + eachBlockSize * (mapPane.getX()),
//                eachBlockSize / 2 + eachBlockSize * (mapPane.getY()),
//                eachBlockSize / 2 + eachBlockSize * (mapPane.getX() - 1),
//                eachBlockSize / 2 + eachBlockSize * (mapPane.getY())), mapPane.getImageView());
//        transition.play();
//        mapPane.setX(mapPane.getX() - 1);
////        position.setX(position.getX() - 1);
//    }
//    void moveRight() {
//        imageView.setImage(new Image("images/tank-right.png"));
//        transition = new PathTransition(Duration.millis(1000), new Line(eachBlockSize / 2 + eachBlockSize * mapPane.getX(),
//                eachBlockSize / 2 + eachBlockSize * mapPane.getY(),
//                eachBlockSize / 2 + eachBlockSize * (mapPane.getX() + 1),
//                eachBlockSize / 2 + eachBlockSize * mapPane.getY()), mapPane.getImageView());
//        transition.play();
//        mapPane.setX(mapPane.getX() + 1);
////        position.setX(position.getX() + 1);
//    }
//    void moveDown() {
//        imageView.setImage(new Image("images/tank-down.png"));
//        transition = new PathTransition(Duration.millis(1000), new Line(eachBlockSize / 2 + eachBlockSize * mapPane.getX(),
//                eachBlockSize / 2 + eachBlockSize * mapPane.getY(),
//                eachBlockSize / 2 + eachBlockSize * mapPane.getX(),
//                eachBlockSize / 2 + eachBlockSize * ((mapPane.getY()) + 1)), mapPane.getImageView());
//        transition.play();
//        mapPane.setY(mapPane.getY() + 1);
////        position.setY(position.getY() + 1);
//    }
//    void moveUp() {
//        imageView.setImage(new Image("images/tank-up.png"));
//        transition = new PathTransition(Duration.millis(1000), new Line(eachBlockSize / 2 + eachBlockSize * mapPane.getX(),
//                eachBlockSize / 2 + eachBlockSize * mapPane.getY(),
//                eachBlockSize / 2 + eachBlockSize * mapPane.getX(),
//                eachBlockSize / 2 + eachBlockSize * ((mapPane.getY()) - 1)), mapPane.getImageView());
//        transition.play();
//        mapPane.setY(mapPane.getY() - 1);
////        position.setY(position.getY() - 1);
//    }
    void fire() {
        transition = new PathTransition(Duration.millis(1000), new Line(eachBlockSize / 2 + eachBlockSize * mapPane.getX(),
                eachBlockSize / 2 + eachBlockSize * mapPane.getY(),
                eachBlockSize / 2 + eachBlockSize * mapPane.getX(),
                eachBlockSize / 2 + eachBlockSize * ((mapPane.getY()) - 5)), circle);
        transition.play();
        mapPane.setY(mapPane.getY() - 5);
        position.setY(position.getY() - 1);
    }
}