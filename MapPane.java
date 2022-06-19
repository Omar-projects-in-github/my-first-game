package project2;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import project1testlatter.SeparateInvalidMapException;
import project1testlatter.SeparateMap;
import project1testlatter.SeparatePosition;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class MapPane extends Pane {
    GridPane gridPane = new GridPane();
    private int size;
    private char[][] charMap;
    private ImageView imageView = new ImageView(new Image("images/tank1-up.png"));
    private SeparateMap map;
    private SeparatePosition position = new SeparatePosition();
    private int x, y;
    public SeparateMap getMap() {
        return map;
    }

    public void setX(int x) {
        this.x = x;
    }
    public int getX() {
        return x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getY() {
        return y;
    }
    public int getSize(){
        return size;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void createMapPane() {
        try {
//            FileReader reader = new FileReader("C:\\Users\\Бахытжан\\IdeaProjects\\HelloWorld\\1-course-2semester\\src\\project2\\map0.txt");
//            Scanner input = new Scanner(reader);
            Scanner input = new Scanner(System.in);
            map = new SeparateMap(input);
            this.size = map.getSize();
            this.charMap = map.charMap;
        }
        catch (SeparateInvalidMapException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
//        catch (IOException e) {}
        paintMap();
    }
    protected void paintMap() {
        int i = 0;
        this.gridPane.setStyle("-fx-background-color: black");
        for (int r = 0; r < charMap.length; r++) {
            for (int c = 0; c < charMap.length; c++) {
                if (charMap[r][c] == 'S')
                    this.gridPane.add(new ImageView(new Image("images/steel-wall.png")), c, r);
                else if (charMap[r][c] == 'P') {
                    this.gridPane.add(imageView, c, r);
//                    if (i > 0)
//                        i = i + 5;
//                    else {
                        setImageView(imageView);
//                        i++;
//                    }
//                    System.out.println("image view was set");
                }
                else if (charMap[r][c] == 'B')
                    this.gridPane.add(new ImageView(new Image("images/brick-wall.png")), c, r);
                else if (charMap[r][c] == 'W')
                    this.gridPane.add(new ImageView(new Image("images/water.png")), c, r);
                else if (charMap[r][c] == 'T') {
                    this.gridPane.add(new ImageView(new Image("images/trees.png")), c, r);
                }
                else if (charMap[r][c] == '0')
                    this.gridPane.add(new ImageView(new Image("images/road.png")), c, r);
            }
        }
        getChildren().clear();
        getChildren().add(this.gridPane);
    }

    public void setImageView (ImageView view) {
        imageView = view;
    }

    public ImageView getImageView () {
        return imageView;
    }
}