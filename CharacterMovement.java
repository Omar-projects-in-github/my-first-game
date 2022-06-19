package project2;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static javafx.scene.input.KeyCode.*;

/**
 * Hold down an arrow key to have your hero move around the screen.
 * Hold down the shift key to have the hero run.
 */
public class CharacterMovement extends Application {

    private static final double W = 600, H = 400;

    private static final String HERO_IMAGE_LOC =
//            "http://icons.iconarchive.com/icons/raindropmemory/legendora/64/Hero-icon.png";
                "images/tank-up.png";

    private Image tankImage;
    private Node  tank;

    boolean boosting, up, down, right, left;

    @Override
    public void start(Stage stage) throws Exception {
        tankImage = new Image(HERO_IMAGE_LOC);
        tank = new ImageView(tankImage);


        Group dungeon = new Group();
        dungeon.getChildren().add(tank);

        moveTankTo(W / 2, H / 2);

         Scene scene = new Scene(dungeon, W, H, Color.BLACK);

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP:
                up = true; break;
                case DOWN:
                down = true; break;
                case LEFT: dungeon.getChildren().set(0, new ImageView(new Image("images/tank-left.png")));
                left  = true; break;
                case RIGHT: dungeon.getChildren().set(0, new ImageView(new Image("images/tank-right.png")));
                right  = true; break;
                case SHIFT: boosting = true; break;
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    up = false; break;
                    case DOWN:  down = false; break;
                    case LEFT:  left  = false; break;
                    case RIGHT: right  = false; break;
                    case SHIFT: boosting = false; break;
                }
            }
        });
        stage.setTitle("TankMoving");
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;

                if (up) {
                    dungeon.getChildren().set(0, new ImageView(new Image("images/tank-up.png")));
                    dy -= 1;
                }
                if (down) {
                    dungeon.getChildren().set(0, new ImageView(new Image("images/tank-down.png")));
                    dy += 1;
                }
                if (right) {
                    dungeon.getChildren().set(0, new ImageView(new Image("images/tank-right.png")));
                    dx += 1;
                }
                if (left) {
                    dungeon.getChildren().set(0, new ImageView(new Image("images/tank-left.png")));
                    dx -= 1;
                }
                if (boosting) { dx *= 3; dy *= 3; }

                moveTankBy(dx, dy);
            }
        };
        timer.start();
    }

    private void moveTankBy(int dx, int dy) {
        if (dx == 0 && dy == 0) return;

        double cx = tank.getBoundsInLocal().getWidth()  / 2;
        double cy = tank.getBoundsInLocal().getHeight() / 2;

        double x = cx + tank.getLayoutX() + dx;
        double y = cy + tank.getLayoutY() + dy;

        moveTankTo(x, y);
    }

    private void moveTankTo(double x, double y) {
        final double cx = tank.getBoundsInLocal().getWidth()  / 2;
        final double cy = tank.getBoundsInLocal().getHeight() / 2;

        if (x - cx >= 0 &&
                x + cx <= W &&
                y - cy >= 0 &&
                y + cy <= H) {
            tank.relocate(x - cx, y - cy);
        }
    }

    public static void main(String[] args) { launch(args); }
}
