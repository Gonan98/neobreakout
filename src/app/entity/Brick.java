package app.entity;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Brick extends Figure {

    private int toughness;
    private int r;
    private int g;
    private int b;

    public Brick(float x, float y, float w, float h, int toughness) {
        super(x, y, w, h);
        Random random = new Random();
        this.toughness = toughness;
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        g.setColor(Color.ORANGE);
        g.fill(new Rectangle2D.Float(x,y,w,h));
    }

    public int getToughness() {
        return toughness;
    }

    public void setToughness(int toughness) {
        this.toughness = toughness;
    }

    public void setRGB(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
}
