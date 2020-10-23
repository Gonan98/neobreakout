package app.ui;

import app.entity.Ball;
import app.entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    public static final int PANEL_WIDTH = 1280;
    public static final int PANEL_HEIGHT = 720;
    public static final int DELAY = 30;

    private Player player;
    private Ball ball;
    private Timer timer;
    private Random random;

    public GamePanel() {
        random = new Random();
        player = new Player(0, PANEL_HEIGHT - 40, 100, 20, 20);
        ball = new Ball(player.getX() + player.getW() / 3,
                        player.getY() - 20, 20, 20, 15, 315);
        timer = new Timer(DELAY, this);
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        player.setDirection(Player.MoveDirection.RIGHT);
                        break;
                    case KeyEvent.VK_LEFT:
                        player.setDirection(Player.MoveDirection.LEFT);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_LEFT:
                        player.setDirection(Player.MoveDirection.NONE);
                        break;
                    default:
                        break;
                }
            }
        });
        this.setFocusable(true);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ball.draw((Graphics2D)g);
        player.draw((Graphics2D)g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        checkBallScreenCollision();
        checkBallPlayerCollision();
        player.move();
        ball.move();
        repaint();
    }

    void checkBallScreenCollision() {

        if (ball.getX() < 0) {
            ball.setAngle(180 - ball.getAngle());
        } else if (ball.getX() + ball.getW() > PANEL_WIDTH) {
            ball.setAngle(180 - ball.getAngle());
        } else if (ball.getY() < 0) {
            ball.setAngle(360 - ball.getAngle());
        }

    }

    public void checkBallPlayerCollision() {

        if (player.getX() < ball.getX() + ball.getW() &&
                player.getX() + player.getW() > ball.getX() &&
                player.getY() < ball.getY() + ball.getH() &&
                player.getY() + player.getH() > ball.getY()
        ) {
            if (ball.getVx() > 0) {
                ball.setAngle(random.nextInt(30) + 300);
            } else {
                ball.setAngle(random.nextInt(30) + 210);
            }
        }

    }
}
