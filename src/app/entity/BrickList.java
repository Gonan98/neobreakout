package app.entity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BrickList {

    private List<Brick> bricks;

    public BrickList() {
        bricks = new ArrayList<>();
        generate();
    }

    public void generate() {
        int marginRight = 10;
        int marginBottom = 10;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                Brick b = new Brick((j + 5) * 40, (i + 3) * 20, 40,20,1);

                if (j > 0) {
                    b.setX(b.getX() + marginRight * j);
                }
                if (i > 0) {
                    b.setY(b.getY() + marginBottom * i);
                }
                bricks.add(b);
            }
        }
    }

    public void draw(Graphics2D g) {
        for(Brick b : bricks) {
            b.draw(g);
        }
    }

    public void destroyBrick(Brick b) {
        bricks.remove(b);
    }

    public List<Brick> getBricks() {
        return bricks;
    }
}
