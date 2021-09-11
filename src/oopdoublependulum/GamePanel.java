package oopdoublependulum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static oopdoublependulum.Pendulum.drawUpdate;

public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 1000;
    static final int SCREEN_HEIGHT = 700;
    static final int DELAY = 40;
    Timer timer;

    Pendulum pendulum1 = new Pendulum(new Pivot());
    Pendulum pendulum2 = new Pendulum(400, 60);

    GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        startGame();
    }

    public void startGame() {
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        drawUpdate(g, pendulum1, pendulum2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
