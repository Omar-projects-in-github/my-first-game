package project2;

import javafx.animation.PathTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import project1testlatter.SeparateMap;
import project1testlatter.SeparatePosition;

public class Bullet {
    Circle circle = new Circle(125, 125, 5);
    String direction = "up";
    public Bullet() {
        circle.setFill(Color.ORANGE);
    }

    public void setDirection (String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public Circle getCircle() {
        return circle;
    }
}
