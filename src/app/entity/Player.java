package app.entity;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Player extends Figure {

    public enum MoveDirection { RIGHT, LEFT, NONE }

    private int lifes;
    private MoveDirection direction;

    public Player(float x, float y, float w, float h, float speed) {
        super(x, y, w, h, speed);
        this.lifes = 3;
        this.direction = MoveDirection.NONE;
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        Rectangle2D rect = new Rectangle2D.Float(x,y,w,h);
        g.setColor(new Color(128,128,128));
        g.fill(rect);
    }

    @Override
    public void move() {
        super.move();
        switch (direction) {
            case RIGHT:
                x += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            default:
                break;
        }
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public MoveDirection getDirection() {
        return direction;
    }

    public void setDirection(MoveDirection direction) {
        this.direction = direction;
    }
}
