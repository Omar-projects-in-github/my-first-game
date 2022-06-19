package project2;

import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import project1testlatter.SeparateMap;
import project1testlatter.SeparateMyPlayer;
import project1testlatter.SeparatePlayer;
import project1testlatter.SeparatePosition;

public class BotPlayer extends Tank implements SeparatePlayer {
    private ImageView botImage = new ImageView(new Image("images/bot-up.png"));
    private SeparateMap mapBot;
    private PathTransition transitionBot, bulletTransitionBot;
    private SeparatePosition positionBot = new SeparatePosition();
    private MapPane mapPane;
    private Bullet bullet = new Bullet();

    public void setMapPane(MapPane mapPane) {
        this.mapPane = mapPane;
        bindTankToMap();
    }

    public void setPositionBot(int x, int y) {
        positionBot.setX(x);
        positionBot.setY(y);
    }

    public void bindTankToMap() {
        setPositionBot(1, 1);
        mapPane.getGridPane().add(botImage, 1, 1);
    }

    public void bindBulletToMap() {
        this.mapBot = mapPane.getMap();
        bullet.setCircle(new Circle(eachBlockSize / 2 + mapPane.getX() * eachBlockSize,
                eachBlockSize / 2 + mapPane.getY() * eachBlockSize, 5));
        bullet.getCircle().setFill(Color.ORANGE);
        this.mapPane.getChildren().add(bullet.getCircle());
        bullet.getCircle().toBack();
    }

    public void moveLeftBot() {
        botImage.setImage(new Image("images/bot-left.png"));
        bullet.setDirection("left");
        if (((positionBot.getX() - 1) >= 0) &&
                mapBot.getValueAt(positionBot.getY(), positionBot.getX() - 1) == 'T')
            botImage.toBack();
        else if (((position.getX() - 1) >= 0) &&
                ((mapBot.getValueAt(positionBot.getY(), positionBot.getX()) == 'T' &&
                        mapBot.getValueAt(positionBot.getY(), positionBot.getX() - 1) == 'W') ||
                        (mapBot.getValueAt(positionBot.getY(), positionBot.getX()) == 'T' &&
                                mapBot.getValueAt(positionBot.getY(), positionBot.getX() - 1) == 'B') ||
                        (mapBot.getValueAt(positionBot.getY(), positionBot.getX()) == 'T' &&
                                mapBot.getValueAt(positionBot.getY(), positionBot.getX() - 1) == 'S'))) {
            mapPane.getImageView().toBack();
        }
        else if (((positionBot.getX() - 1) >= 0) &&
                mapBot.getValueAt(positionBot.getY(), positionBot.getX() - 1) != 'T')
            botImage.toFront();
        if (((positionBot.getX() - 1) >= 0) &&
                (mapBot.getValueAt(positionBot.getY(), positionBot.getX() - 1) == '0' ||
                        mapBot.getValueAt(positionBot.getY(), positionBot.getX() - 1) == 'P' ||
                        mapBot.getValueAt(positionBot.getY(), positionBot.getX() - 1) == 'T')) {
            transitionBot = new PathTransition(Duration.millis(1000), new Line(eachBlockSize / 2 + eachBlockSize * mapPane.getX(),
                    eachBlockSize / 2 + eachBlockSize * mapPane.getY(),
                    eachBlockSize / 2 + eachBlockSize * (mapPane.getX() - 1),
                    eachBlockSize / 2 + eachBlockSize * mapPane.getY()), botImage);
            transitionBot.play();
            mapPane.setX(mapPane.getX() - 1);
            positionBot.setX(positionBot.getX() - 1);
        }
    }
    public void moveRightBot() {
        botImage.setImage(new Image("images/bot-right.png"));
        bullet.setDirection("right");
        if (((positionBot.getX() + 1) < mapBot.charMap.length) &&
                mapBot.getValueAt(positionBot.getY(), positionBot.getX() + 1) == 'T')
            botImage.toBack();
        /*else if (((positionBot.getX() + 1) < mapBot.charMap.length) &&
                ((mapBot.getValueAt(positionBot.getY(), positionBot.getX()) == 'T' &&
                        mapBot.getValueAt(positionBot.getY(), positionBot.getX() + 1) == 'W') ||
                        (mapBot.getValueAt(positionBot.getY(), positionBot.getX()) == 'T' &&
                                mapBot.getValueAt(positionBot.getY(), positionBot.getX() + 1) == 'S') ||
                        (mapBot.getValueAt(positionBot.getY(), positionBot.getX()) == 'T' &&
                                mapBot.getValueAt(positionBot.getY(), positionBot.getX() + 1) == 'B'))) {
            mapPane.getImageView().toBack();
        }*/
        else if (((positionBot.getX() + 1) < mapBot.charMap.length) &&
                mapBot.getValueAt(positionBot.getY(), positionBot.getX() + 1) != 'T')
            mapPane.getImageView().toFront();
        if (((positionBot.getX() + 1) < mapBot.charMap.length) &&
                (mapBot.getValueAt(positionBot.getY(), positionBot.getX() + 1) == '0' ||
                        mapBot.getValueAt(positionBot.getY(), positionBot.getX() + 1) == 'P' ||
                        mapBot.getValueAt(positionBot.getY(), positionBot.getX() + 1) == 'T')) {
            transitionBot = new PathTransition(Duration.millis(1000), new Line(eachBlockSize / 2 + eachBlockSize * mapPane.getX(),
                    eachBlockSize / 2 + eachBlockSize * mapPane.getY(),
                    eachBlockSize / 2 + eachBlockSize * (mapPane.getX() + 1),
                    eachBlockSize / 2 + eachBlockSize * mapPane.getY()), botImage);
            transitionBot.play();
            mapPane.setX(mapPane.getX() + 1);
            positionBot.setX(positionBot.getX() + 1);
        }
    }
    public void moveDownBot() {
        botImage.setImage(new Image("images/bot-down.png"));
        bullet.setDirection("down");
        if (((positionBot.getY() + 1) < mapBot.charMap.length) &&
                mapBot.getValueAt(positionBot.getY() + 1, positionBot.getX()) == 'T')
            botImage.toBack();
        else if (((positionBot.getY() + 1) < mapBot.charMap.length) &&
                ((mapBot.getValueAt(positionBot.getY(), positionBot.getX()) == 'T' &&
                        mapBot.getValueAt(positionBot.getY() + 1, positionBot.getX()) == 'W') ||
                        (mapBot.getValueAt(positionBot.getY(), positionBot.getX()) == 'T' &&
                                mapBot.getValueAt(positionBot.getY() + 1, positionBot.getX()) == 'S') ||
                        (mapBot.getValueAt(positionBot.getY(), positionBot.getX()) == 'T' &&
                                mapBot.getValueAt(positionBot.getY() + 1, positionBot.getX()) == 'B'))) {
            mapPane.getImageView().toBack();
        }
        else if (((positionBot.getY() + 1) < mapBot.charMap.length) &&
                mapBot.getValueAt(position.getY() + 1, positionBot.getX()) != 'T')
            botImage.toFront();
        if (((positionBot.getY() + 1) < mapBot.charMap.length) &&
                (mapBot.getValueAt(positionBot.getY() + 1, positionBot.getX()) == '0' ||
                        mapBot.getValueAt(positionBot.getY() + 1, positionBot.getX()) == 'P' ||
                        mapBot.getValueAt(positionBot.getY() + 1, positionBot.getX()) == 'T')) {
            transitionBot = new PathTransition(Duration.millis(1000), new Line(eachBlockSize / 2 + eachBlockSize * mapPane.getX(),
                    eachBlockSize / 2 + eachBlockSize * mapPane.getY(),
                    eachBlockSize / 2 + eachBlockSize * mapPane.getX(),
                    eachBlockSize / 2 + eachBlockSize * ((mapPane.getY()) + 1)), botImage);
            transitionBot.play();
            mapPane.setY(mapPane.getY() + 1);
            positionBot.setY(positionBot.getY() + 1);
        }
    }
    public void moveUpBot() {
        botImage.setImage(new Image("images/bot-up.png"));
        bullet.setDirection("up");
        if (((positionBot.getY() - 1) >= 0) &&
                mapBot.getValueAt(positionBot.getY() - 1, positionBot.getX()) == 'T')
            botImage.toBack();
        else if (((positionBot.getY() - 1) >= 0) &&
                ((mapBot.getValueAt(positionBot.getY(), positionBot.getX()) == 'T' &&
                        mapBot.getValueAt(positionBot.getY() - 1, positionBot.getX()) == 'W') ||
                        (mapBot.getValueAt(positionBot.getY(), positionBot.getX()) == 'T' &&
                                mapBot.getValueAt(positionBot.getY() - 1, positionBot.getX()) == 'S') ||
                        (mapBot.getValueAt(positionBot.getY(), positionBot.getX()) == 'T' &&
                                mapBot.getValueAt(positionBot.getY() - 1, positionBot.getX()) == 'B'))) {
            mapPane.getImageView().toBack();
        }
        else if (((positionBot.getY() - 1) >= 0) &&
                mapBot.getValueAt(positionBot.getY() - 1, positionBot.getX()) != 'T')
            botImage.toFront();
        if (((positionBot.getY() - 1) >= 0) &&
                (mapBot.getValueAt(positionBot.getY() - 1, positionBot.getX()) == '0' ||
                        mapBot.getValueAt(positionBot.getY() - 1, positionBot.getX()) == 'P' ||
                        mapBot.getValueAt(positionBot.getY() - 1, positionBot.getX()) == 'T')) {
            transitionBot = new PathTransition(Duration.millis(1000), new Line(eachBlockSize / 2 + eachBlockSize * mapPane.getX(),
                    eachBlockSize / 2 + eachBlockSize * mapPane.getY(),
                    eachBlockSize / 2 + eachBlockSize * mapPane.getX(),
                    eachBlockSize / 2 + eachBlockSize * ((mapPane.getY()) - 1)), botImage);
            transitionBot.play();
            mapPane.setY(mapPane.getY() - 1);
            positionBot.setY(positionBot.getY() - 1);
        }
    }
    protected void fireBot() {
        bullet.getCircle().toFront();
        if (bullet.getDirection() == "left") {
            int counter = mapBot.getSize(), k = 0, roadLength = 0;
            while (k++ < counter) {
                if (((positionBot.getX() - k) >= 0) &&
                        ((mapBot.getValueAt(positionBot.getY(), positionBot.getX() - k) != 'B') &&
                                (mapBot.getValueAt(positionBot.getY(), positionBot.getX() - k) != 'S'))) {
                    roadLength++;
                }
                else
                    break;
            }
            bulletTransitionBot = new PathTransition(Duration.millis(500), new Line(eachBlockSize / 2 + eachBlockSize * positionBot.getX(),
                    eachBlockSize / 2 + eachBlockSize * positionBot.getY(),
                    eachBlockSize * (positionBot.getX() - roadLength),
                    eachBlockSize / 2 + eachBlockSize * positionBot.getY()), bullet.getCircle());
            bulletTransitionBot.play();
        }
        else if (bullet.getDirection() == "right") {
            int counter = mapBot.getSize(), k = 0, roadLength = 0;
            while (k++ < counter) {
                if (((positionBot.getX() + k) < mapBot.charMap.length) &&
                        ((mapBot.getValueAt(positionBot.getY(), positionBot.getX() + k) != 'B') &&
                                (mapBot.getValueAt(positionBot.getY(), positionBot.getX() + k) != 'S')))
                    roadLength++;
                else
                    break;
            }
            bulletTransitionBot = new PathTransition(Duration.millis(500), new Line(eachBlockSize / 2 + eachBlockSize * positionBot.getX(),
                    eachBlockSize / 2 + eachBlockSize * positionBot.getY(),
                    eachBlockSize + eachBlockSize * (positionBot.getX() + roadLength),
                    eachBlockSize / 2 + eachBlockSize * positionBot.getY()), bullet.getCircle());
            bulletTransitionBot.play();
        }
        else if (bullet.getDirection() == "down") {
            int counter = mapBot.getSize(), k = 0, roadLength = 0;
            while (k++ < counter) {
                if (((positionBot.getY() + k) < mapBot.charMap.length) &&
                        ((mapBot.getValueAt(positionBot.getY() + k, positionBot.getX()) != 'B') &&
                                (mapBot.getValueAt(positionBot.getY() + k, positionBot.getX()) != 'S')))
                    roadLength++;
                else
                    break;
            }
            bulletTransitionBot = new PathTransition(Duration.millis(500), new Line(eachBlockSize / 2 + eachBlockSize * positionBot.getX(),
                    eachBlockSize / 2 + eachBlockSize * positionBot.getY(),
                    eachBlockSize / 2 + eachBlockSize * positionBot.getX(),
                    eachBlockSize + eachBlockSize * (positionBot.getY() + roadLength)), bullet.getCircle());
            bulletTransitionBot.play();
        }
        else if (bullet.getDirection() == "up") {
            int counter = mapBot.getSize(), k = 0, roadLength = 0;
            while (k++ < counter) {
                if (((positionBot.getY() - k) >= 0) &&
                        ((mapBot.getValueAt(positionBot.getY() - k, positionBot.getX()) != 'B') &&
                                (mapBot.getValueAt(positionBot.getY() - k, positionBot.getX()) != 'S')))
                    roadLength++;
                else
                    break;
            }
            bulletTransitionBot = new PathTransition(Duration.millis(500), new Line(eachBlockSize / 2 + eachBlockSize * positionBot.getX(),
                    eachBlockSize / 2 + eachBlockSize * positionBot.getY(),
                    eachBlockSize / 2 + eachBlockSize * positionBot.getX(),
                    eachBlockSize * (positionBot.getY() - roadLength)), bullet.getCircle());
            bulletTransitionBot.play();
        }
    }
}
