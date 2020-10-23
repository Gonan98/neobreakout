package app.ui;

import javax.swing.*;

public class Game extends JFrame {

    private GamePanel panel;

    public Game() {
        panel = new GamePanel();
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
