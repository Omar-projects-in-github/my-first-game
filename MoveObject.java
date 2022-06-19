package project2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import project1testlatter.SeparateGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MoveObject extends Application {
    Tank tank1 = new Tank();
    BotPlayer bot = new BotPlayer();
    Scene scene;
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        MapPane mp = new MapPane();
        mp.createMapPane();
        SeparateGame game = new SeparateGame();
        game.setMap(mp.getMap());

        game.addPlayer(tank1);
        tank1.setMapPane(mp);
        tank1.bindBulletToMap();

//        Tank tank2 = new Tank();
//        game.addPlayer(tank2);
//        tank2.setMapPane(mp);
//        tank2.setPosition(9, 0);
//        tank2.bindBulletToMap();

        game.addPlayer(bot);
        bot.setMapPane(mp);
       // bot.bindTankToMap();
        bot.bindBulletToMap();
//         Create a scene and place it in the stage
        scene = new Scene(mp, 750, 750, Color.DARKSEAGREEN);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        /*executor.execute(new TankAction());
        executor.execute(new BotAction());
        executor.shutdown();*/
        TankAction tankA = new TankAction();
        tankA.start();
        BotAction botA = new BotAction();
        botA.start();

//        new Thread(() -> {
//            try {
//                while (true) {
//                    Platform.runLater(() -> {
//                        scene.setOnKeyPressed(e -> {
//                            switch (e.getCode()) {
//                                case LEFT:
//                                    tank1.moveLeft();
//                                    break;
//                                case RIGHT:
//                                    tank1.moveRight();
//                                    break;
//                                case DOWN:
//                                    tank1.moveDown();
//                                    break;
//                                case UP:
//                                    tank1.moveUp();
//                                    break;
//                                case ENTER:
//                                    tank1.fire();
//                                    break;
//                            }
//                        });
//                    });
//                    Thread.sleep(10000);
//                }
//            }
//            catch (InterruptedException ex) {}
//        }).start();

//        new Thread(() -> {
//            try {
//                while (true) {
//                    Platform.runLater(() -> {
//                        scene.setOnKeyPressed(e -> {
//                            switch (e.getCode()) {
//                                case A:
//                                    bot.moveLeftBot();
//                                    break;
//                                case D:
//                                    bot.moveRightBot();
//                                    break;
//                                case S:
//                                    bot.moveDownBot();
//                                    break;
//                                case W:
//                                    bot.moveUpBot();
//                                    break;
//                                case SPACE:
//                                    bot.fireBot();
//                                    break;
//                            }
//                        });
//                    });
//                    Thread.sleep(10000);
//                }
//            }
//            catch (InterruptedException ex) {}
//        }).start();

//        ArrayList<String> al = new ArrayList<>(Arrays.asList("right", "right"));
////                , "right", "right",
////                "down", "down", "right", "right", "right", "right", "fire", "right", "down", "right",
////                "right", "down", "down", "down", "left", "left", "left", "left", "right", "right", "down",
////                "fire", "down", "left", "left", "left", "left", "left", "left", "left", "left",
////                "right", "fire", "fire", "right", "right", "right", "right", "right", "right", "right",
////                "up", "up", "fire", "fire", "fire", "left"));
//            for (String str : al) {
//                switch (str) {
//                    case "left":
//                        bot.moveLeftBot();
//                        break;
//                    case "right":
//                            bot.moveRightBot();
//                        break;
//                    case "down":
//                            bot.moveDownBot();
//                        break;
//                    case "up":
//                            bot.moveUpBot();
//                        break;
//                    case "fire":
//                            bot.fireBot();
//                        break;
//                }
//            }

        primaryStage.setTitle("MoveObject"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    public static void main(String[] args) {
        launch(args);
    }

    public class BotAction extends Thread {
        public synchronized void run() {
            Platform.runLater(() -> {
                try {
//                while (true) {
                    scene.setOnKeyPressed(e -> {
                        switch (e.getCode()) {
                            case A:
                                bot.moveLeftBot();
                                break;
                            case D:
                                bot.moveRightBot();
                                break;
                            case S:
                                bot.moveDownBot();
                                break;
                            case W:
                                bot.moveUpBot();
                                break;
                            case SPACE:
                                bot.fireBot();
                                break;
                        }
                    });
                    Thread.sleep(2000);
//                }
                }
                catch (InterruptedException ex) {}
            });
        }
    }

    public class TankAction extends Thread {
        public synchronized void run() {
            Platform.runLater (() -> {
                try {
//                while (true) {
                    scene.setOnKeyPressed(e -> {
                        switch (e.getCode()) {
                            case LEFT:
                                tank1.moveLeft();
                                break;
                            case RIGHT:
                                tank1.moveRight();
                                break;
                            case DOWN:
                                tank1.moveDown();
                                break;
                            case UP:
                                tank1.moveUp();
                                break;
                            case ENTER:
                                tank1.fire();
                                break;
                        }
                    });
                    Thread.sleep(2000);
//                }
                }
                catch (InterruptedException ex) {}
            });
        }
    }
}
/*
5
P 0 W S S
T T 0 0 0
B B B B T
S W T 0 T
0 0 T T T
 */
/*
7
0 0 0 0 0 0 0
0 0 0 0 0 0 0
0 0 p 0 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
 */
/*
7
0 0 0 T 0 0 0
W 0 0 T 0 0 0
W 0 0 T 0 0 0
W T T P T T 0
W 0 0 W 0 0 0
W 0 0 W 0 0 0
W 0 0 0 0 0 0
 */
/*
15
0 0 0 W W W B B B S S S 0 0 0
0 0 0 T 0 0 T 0 0 T 0 0 T S W
0 S p T T T T B B W W T 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
B B B B W T T T T T T T S T S
B B B W W W W 0 0 0 T S S T B
0 0 0 0 S S S S S T W W B 0 B
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
B W W W T T T T W S S S 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
T T T T T W S B B B B B 0 0 0
0 T B B S S T T W W 0 0 0 B B
T T T T T T T T W W W 0 T T T
S S S S S S S S S S S S T T T
0 0 0 T T 0 0 T 0 T 0 T 0 0 T
 */