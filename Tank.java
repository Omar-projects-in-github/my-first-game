package project2;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import project1testlatter.SeparateMap;
import project1testlatter.SeparateMyPlayer;
import project1testlatter.SeparatePlayer;
import project1testlatter.SeparatePosition;

public class Tank extends SeparateMyPlayer {
    private SeparateMap map;
    private PathTransition transition, bulletTransition;
    protected int eachBlockSize = 50;
    public MapPane mapPane;
    private Bullet bullet = new Bullet();

    public void setMapPane(MapPane mapPane) {
        this.mapPane = mapPane;
        bindTankToMap();
    }

    public void bindTankToMap() {
        this.map = mapPane.getMap();
        for (int r = 0; r < this.map.charMap.length; r++) {
            for (int c = 0; c < this.map.charMap.length; c++) {
                if (this.map.charMap[r][c] == 'P' || this.map.charMap[r][c] == 'p') {
                    setPosition(c, r);
                }
            }
        }
    }

    public void bindBulletToMap() {
        this.map = mapPane.getMap();
        for (int r = 0; r < this.map.charMap.length; r++) {
            for (int c = 0; c < this.map.charMap.length; c++) {
                if (this.map.charMap[r][c] == 'P' || this.map.charMap[r][c] == 'p') {
//                    setPosition(c, r);
                    bullet.setCircle(new Circle(eachBlockSize / 2 + position.getX() * eachBlockSize,
                            eachBlockSize / 2 + position.getY() * eachBlockSize, 5));
                }
            }
        }
        bullet.getCircle().setFill(Color.ORANGE);
        this.mapPane.getChildren().add(bullet.getCircle());
        bullet.getCircle().toBack();
    }

    public void moveLeft() {
        mapPane.getImageView().setImage(new Image("images/tank1-left.png"));
        bullet.setDirection("left");
        if (((position.getX() - 1) >= 0) &&
                map.getValueAt(position.getY(), position.getX() - 1) == 'T')
            mapPane.getImageView().toBack();
        else if (((position.getX() - 1) >= 0) &&
                ((map.getValueAt(position.getY(), position.getX()) == 'T' &&
                map.getValueAt(position.getY(), position.getX() - 1) == 'W') ||
                        (map.getValueAt(position.getY(), position.getX()) == 'T' &&
                                map.getValueAt(position.getY(), position.getX() - 1) == 'S') ||
                        (map.getValueAt(position.getY(), position.getX()) == 'T' &&
                                map.getValueAt(position.getY(), position.getX() - 1) == 'B'))) {
            mapPane.getImageView().toBack();
        }
        else if (((position.getX() - 1) >= 0) &&
        map.getValueAt(position.getY(), position.getX() - 1) != 'T')
            mapPane.getImageView().toFront();
        if (((position.getX() - 1) >= 0) &&
                (map.getValueAt(position.getY(), position.getX() - 1) == '0' ||
                        map.getValueAt(position.getY(), position.getX() - 1) == 'P' ||
                        map.getValueAt(position.getY(), position.getX() - 1) == 'T')) {
            transition = new PathTransition(Duration.millis(1000), new Line(eachBlockSize / 2 + eachBlockSize * mapPane.getX(),
                    eachBlockSize / 2 + eachBlockSize * mapPane.getY(),
                    eachBlockSize / 2 + eachBlockSize * (mapPane.getX() - 1),
                    eachBlockSize / 2 + eachBlockSize * mapPane.getY()), mapPane.getImageView());
            transition.play();
            mapPane.setX(mapPane.getX() - 1);
            position.setX(position.getX() - 1);
        }
    }
    public void moveRight() {
        mapPane.getImageView().setImage(new Image("images/tank1-right.png"));
        bullet.setDirection("right");
        if (((position.getX() + 1) < map.charMap.length) &&
                map.getValueAt(position.getY(), position.getX() + 1) == 'T')
            mapPane.getImageView().toBack();
        else if (((position.getX() + 1) < map.charMap.length) &&
                ((map.getValueAt(position.getY(), position.getX()) == 'T' &&
                        map.getValueAt(position.getY(), position.getX() + 1) == 'W') ||
                        (map.getValueAt(position.getY(), position.getX()) == 'T' &&
                                map.getValueAt(position.getY(), position.getX() + 1) == 'S') ||
                        (map.getValueAt(position.getY(), position.getX()) == 'T' &&
                                map.getValueAt(position.getY(), position.getX() + 1) == 'B'))) {
            mapPane.getImageView().toBack();
        }
        else if (((position.getX() + 1) < map.charMap.length) &&
                map.getValueAt(position.getY(), position.getX() + 1) != 'T')
            mapPane.getImageView().toFront();
        if (((position.getX() + 1) < map.charMap.length) &&
                (map.getValueAt(position.getY(), position.getX() + 1) == '0' ||
                        map.getValueAt(position.getY(), position.getX() + 1) == 'P' ||
                        map.getValueAt(position.getY(), position.getX() + 1) == 'T')) {
            transition = new PathTransition(Duration.millis(1000), new Line(eachBlockSize / 2 + eachBlockSize * mapPane.getX(),
                    eachBlockSize / 2 + eachBlockSize * mapPane.getY(),
                    eachBlockSize / 2 + eachBlockSize * (mapPane.getX() + 1),
                    eachBlockSize / 2 + eachBlockSize * mapPane.getY()), mapPane.getImageView());
            transition.play();
            mapPane.setX(mapPane.getX() + 1);
            position.setX(position.getX() + 1);
        }
    }
    public void moveDown() {
        mapPane.getImageView().setImage(new Image("images/tank1-down.png"));
        bullet.setDirection("down");
        if (((position.getY() + 1) < map.charMap.length) &&
                map.getValueAt(position.getY() + 1, position.getX()) == 'T')
            mapPane.getImageView().toBack();
        else if (((position.getX() + 1) < map.charMap.length) &&
                ((map.getValueAt(position.getY(), position.getX()) == 'T' &&
                        map.getValueAt(position.getY() + 1, position.getX()) == 'W') ||
                        (map.getValueAt(position.getY(), position.getX()) == 'T' &&
                                map.getValueAt(position.getY() + 1, position.getX()) == 'S') ||
                        (map.getValueAt(position.getY(), position.getX()) == 'T' &&
                                map.getValueAt(position.getY() + 1, position.getX()) == 'B'))) {
            mapPane.getImageView().toBack();
        }
        else if (((position.getY() + 1) < map.charMap.length) &&
                map.getValueAt(position.getY() + 1, position.getX()) != 'T')
            mapPane.getImageView().toFront();
        if (((position.getY() + 1) < map.charMap.length) &&
                (map.getValueAt(position.getY() + 1, position.getX()) == '0' ||
                        map.getValueAt(position.getY() + 1, position.getX()) == 'P' ||
                        map.getValueAt(position.getY() + 1, position.getX()) == 'T')) {
            transition = new PathTransition(Duration.millis(1000), new Line(eachBlockSize / 2 + eachBlockSize * mapPane.getX(),
                    eachBlockSize / 2 + eachBlockSize * mapPane.getY(),
                    eachBlockSize / 2 + eachBlockSize * mapPane.getX(),
                    eachBlockSize / 2 + eachBlockSize * ((mapPane.getY()) + 1)), mapPane.getImageView());
            transition.play();
            mapPane.setY(mapPane.getY() + 1);
            position.setY(position.getY() + 1);
        }
    }
    public void moveUp() {
        mapPane.getImageView().setImage(new Image("images/tank1-up.png"));
        bullet.setDirection("up");
        if (((position.getY() - 1) >= 0) &&
                map.getValueAt(position.getY() - 1, position.getX()) == 'T')
            mapPane.getImageView().toBack();
        else if (((position.getX() - 1) >= 0) &&
                ((map.getValueAt(position.getY(), position.getX()) == 'T' &&
                        map.getValueAt(position.getY() - 1, position.getX()) == 'W') ||
                        (map.getValueAt(position.getY(), position.getX()) == 'T' &&
                                map.getValueAt(position.getY() - 1, position.getX()) == 'S') ||
                        (map.getValueAt(position.getY(), position.getX()) == 'T' &&
                                map.getValueAt(position.getY() - 1, position.getX()) == 'B'))) {
            mapPane.getImageView().toBack();
        }
        else if (((position.getY() - 1) >= 0) &&
                map.getValueAt(position.getY() - 1, position.getX()) != 'T')
            mapPane.getImageView().toFront();
        if (((position.getY() - 1) >= 0) &&
                (map.getValueAt(position.getY() - 1, position.getX()) == '0' ||
                        map.getValueAt(position.getY() - 1, position.getX()) == 'P' ||
                        map.getValueAt(position.getY() - 1, position.getX()) == 'T')) {
            transition = new PathTransition(Duration.millis(1000), new Line(eachBlockSize / 2 + eachBlockSize * mapPane.getX(),
                    eachBlockSize / 2 + eachBlockSize * mapPane.getY(),
                    eachBlockSize / 2 + eachBlockSize * mapPane.getX(),
                    eachBlockSize / 2 + eachBlockSize * ((mapPane.getY()) - 1)), mapPane.getImageView());
            transition.play();
            mapPane.setY(mapPane.getY() - 1);
            position.setY(position.getY() - 1);
        }
    }

    protected void fire() {
        bullet.getCircle().toFront();
        if (bullet.getDirection() == "left") {
            int counter = map.getSize(), k = 0, roadLength = 0;
            while (k++ < counter) {
                if (((position.getX() - k) >= 0) &&
                        ((map.getValueAt(position.getY(), position.getX() - k) != 'B') &&
                        (map.getValueAt(position.getY(), position.getX() - k) != 'S'))) {
                    roadLength++;
                }
                else
                    break;
            }
            bulletTransition = new PathTransition(Duration.millis(500), new Line(eachBlockSize / 2 + eachBlockSize * position.getX(),
                    eachBlockSize / 2 + eachBlockSize * position.getY(),
                    eachBlockSize * (position.getX() - roadLength),
                    eachBlockSize / 2 + eachBlockSize * position.getY()), bullet.getCircle());
            bulletTransition.play();
        }
        else if (bullet.getDirection() == "right") {
            int counter = map.getSize(), k = 0, roadLength = 0;
            while (k++ < counter) {
                if (((position.getX() + k) < map.charMap.length) &&
                        ((map.getValueAt(position.getY(), position.getX() + k) != 'B') &&
                        (map.getValueAt(position.getY(), position.getX() + k) != 'S')))
                    roadLength++;
                else
                    break;
            }
            bulletTransition = new PathTransition(Duration.millis(500), new Line(eachBlockSize / 2 + eachBlockSize * position.getX(),
                    eachBlockSize / 2 + eachBlockSize * position.getY(),
                    eachBlockSize + eachBlockSize * (position.getX() + roadLength),
                    eachBlockSize / 2 + eachBlockSize * position.getY()), bullet.getCircle());
            bulletTransition.play();
        }
        else if (bullet.getDirection() == "down") {
            int counter = map.getSize(), k = 0, roadLength = 0;
            while (k++ < counter) {
                if (((position.getY() + k) < map.charMap.length) &&
                        ((map.getValueAt(position.getY() + k, position.getX()) != 'B') &&
                        (map.getValueAt(position.getY() + k, position.getX()) != 'S')))
                    roadLength++;
                else
                    break;
            }
            bulletTransition = new PathTransition(Duration.millis(500), new Line(eachBlockSize / 2 + eachBlockSize * position.getX(),
                    eachBlockSize / 2 + eachBlockSize * position.getY(),
                    eachBlockSize / 2 + eachBlockSize * position.getX(),
                    eachBlockSize + eachBlockSize * (position.getY() + roadLength)), bullet.getCircle());
            bulletTransition.play();
        }
        else if (bullet.getDirection() == "up") {
            int counter = map.getSize(), k = 0, roadLength = 0;
            while (k++ < counter) {
                if (((position.getY() - k) >= 0) &&
                        ((map.getValueAt(position.getY() - k, position.getX()) != 'B') &&
                        (map.getValueAt(position.getY() - k, position.getX()) != 'S')))
                    roadLength++;
                else break;
            }
            bulletTransition = new PathTransition(Duration.millis(500), new Line(eachBlockSize / 2 + eachBlockSize * position.getX(),
                    eachBlockSize / 2 + eachBlockSize * position.getY(),
                    eachBlockSize / 2 + eachBlockSize * position.getX(),
                    eachBlockSize * (position.getY() - roadLength)), bullet.getCircle());
            bulletTransition.play();
        }
    }
}
