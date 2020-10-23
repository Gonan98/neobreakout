package app.entity;

import java.awt.*;

public abstract class Figure {

    protected float x;
    protected float y;
    protected float w;
    protected float h;
    protected float speed;
    protected float angle;

    public Figure(float x, float y, float w, float h, float speed, float angle) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.speed = speed;
        this.angle = (float)(angle * (Math.PI / 180));
    }

    public Figure(float x, float y, float w, float h, float speed) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.speed = speed;
    }

    public void draw(Graphics2D g) {

    }

    public void move() {

    }

    public float getVx() {
        return (float)(speed * Math.cos(angle));
    }

    public float getVy() {
        return (float)(speed * Math.sin(angle));
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getAngle() {
        return (float)(angle * (180 / Math.PI));
    }

    public void setAngle(float angle) {
        if (angle > 360) angle -= 360;
        this.angle = (float)(angle * (Math.PI / 180));
    }
}
