package app.ui;

import app.entity.Ball;
import app.entity.Brick;
import app.entity.BrickList;
import app.entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    public static final int PANEL_WIDTH = 900;
    public static final int PANEL_HEIGHT = 500;
    public static final int DELAY = 30;

    private Player player;
    private Ball ball;
    private BrickList brickList;
    private Timer timer;
    private Random random;
    private boolean gameOver;
    private String message;

    public GamePanel() {
        message = "";
        gameOver = false;
        random = new Random();
        player = new Player((float)PANEL_WIDTH / 2, PANEL_HEIGHT - 40, 100, 20, 20);
        ball = new Ball(player.getX() + player.getW() / 3,
                        player.getY() - 20, 20, 20, 15, random.nextInt(45) + 270);
        brickList = new BrickList();
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
        g.setColor(new Color(192, 192,0));
        g.setFont(new Font("Ink Free", Font.BOLD, 24));
        g.drawString("Lifes: "+player.getLifes(), PANEL_WIDTH / 5, 24);
        g.drawString("Score: "+player.getScore(), PANEL_WIDTH * 4 / 5, 24);


        ball.draw((Graphics2D)g);
        brickList.draw((Graphics2D)g);
        player.draw((Graphics2D)g);

        if (gameOver) {
            g.setColor(new Color(128, 0,0));
            g.setFont(new Font("Ink Free", Font.BOLD, 76));
            g.drawString("Game Over", PANEL_WIDTH / 4, PANEL_HEIGHT / 2 + 36);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (brickList.getBricks().size() == 0) {
            message = "¡Ganaste!";
            timer.stop();
        }

        checkBallScreenCollision();
        checkBallPlayerCollision();
        checkBallBrickCollision();

        if(ball.getY() > PANEL_HEIGHT) {
            player.setLifes(player.getLifes() - 1);
            if (player.getLifes() == 0) {
                gameOver = true;
                message = "Game Over";
                timer.stop();
            } else {
                ball = new Ball(player.getX() + player.getW() / 3, player.getY() - 20, 20, 20, 15, random.nextInt(45) + 270);
            }
        }

        player.move(PANEL_WIDTH);
        ball.move();
        repaint();
    }

    void checkBallScreenCollision() {

        if (ball.getX() < 0) {
            ball.setAngle(180 - ball.getAngle());
        } else if (ball.getX() + ball.getW() + ball.getVx() > PANEL_WIDTH) {
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

    public void checkBallBrickCollision() {
        for (Brick brick : brickList.getBricks()) {

            if (ball.getX() < brick.getX() + brick.getW() &&
                    ball.getX() + ball.getW() > brick.getX() &&
                    ball.getY() < brick.getY() + brick.getH() &&
                    ball.getY() + ball.getH() > brick.getY()
            ) {

                if (ball.getX() < brick.getX() || ball.getX() + ball.getW() > brick.getX() + brick.getW()) {
                    ball.setAngle(180 - ball.getAngle());
                } else {
                    ball.setAngle(360 - ball.getAngle());
                }

                //ball.setAngle(360 - ball.getAngle());
                brickList.destroyBrick(brick);
                player.setScore(player.getScore() + 10);
                break;
            }

        }
    }
}
