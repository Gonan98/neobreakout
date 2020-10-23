package app.entity;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ball extends Figure {

    public Ball(float x, float y, float w, float h, float speed, float angle) {
        super(x, y, w, h, speed, angle);
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        g.setColor(Color.WHITE);
        Ellipse2D ellipse = new Ellipse2D.Float(x,y,w,h);
        g.fill(ellipse);
    }

    @Override
    public void move() {
        super.move();
        x += getVx();
        y += getVy();
    }
}
