package singlependulum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Math.*;

public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 1000;
    static final int SCREEN_HEIGHT = 700;
    static final int DELAY = 40;
    Timer timer;
    double angle = PI / 4;
    double angleV = 0;
    double angleA = 0;
    double[] bob = new double[2];
    int len = 400;
    int[] origin;
    double gravity = 9.78033;

    GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        origin = new int[]{500, 0};
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
        double force = gravity * sin(angle);
        angleA = (-1 * force) / len;
        angleV += angleA;
        angle += angleV;
        bob[0] = len * sin(angle) + origin[0];
        bob[1] = len * cos(angle) + origin[1];
        g.setColor(Color.white);
        g.drawLine(origin[0], origin[1], (int) bob[0], (int) bob[1]);
        g.fillOval((int) bob[0] - 32, (int) bob[1] - 32, 64, 64);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
