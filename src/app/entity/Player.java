package app.entity;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Player extends Figure {

    public enum MoveDirection { RIGHT, LEFT, NONE }

    private int lifes;
    private int score;
    private MoveDirection direction;

    public Player(float x, float y, float w, float h, float speed) {
        super(x, y, w, h, speed);
        this.lifes = 3;
        this.score = 0;
        this.direction = MoveDirection.NONE;
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        g.setColor(new Color(128,128,128));
        g.fill(new Rectangle2D.Float(x,y,w,h));
    }

    @Override
    public void move(int widthInterval) {
        super.move(widthInterval);
        switch (direction) {
            case RIGHT:
                if (x + w + speed <= widthInterval) {
                    x += speed;
                }
                break;
            case LEFT:
                if (x - speed >= 0) {
                    x -= speed;
                }
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
