package doublependulum;

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

    int r1 = 125; // length of first pendulum
    int r2 = 200; // length of second pendulum
    int m1 = 10; // mass of first pendulum
    int m2 = 50; // mass of second pendulum
    double a1 = PI / 2; // first pendulum angle
    double a2 = PI / 2; // second pendulum angle
    double a1_v = 0; // angular velocity pendulum 1
    double a2_v = 0; // angular velocity pendulum 2
    double gravity = 0.78033;

    int x_offset = SCREEN_WIDTH / 2;
    int y_offset = 100;

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
        double num1 = -1 * gravity * (2 * m1 + m2) * sin(a1);
        double num2 = -1 * m2 * gravity * sin(a1 - 2 * a2);
        double num3 = -2 * sin(a1 - a2) * m2;
        double num4 = a2_v * a2_v * r2 + a1_v * a1_v * r1 * cos(a1 - a2);
        double den = r1 * (2 * m1 + m2 - m2 * cos(2 * a1 - 2 * a2));
        double a1_a = (num1 + num2 + num3 * num4) / den;

        num1 = 2 * sin(a1 - a2);
        num2 = (a1_v * a1_v * r1 * (m1 + m2));
        num3 = gravity * (m1 + m2) * cos(a1);
        num4 = a2_v * a2_v * r2 * m2 * cos(a1 - a2);
        den = r2 * (2 * m1 + m2 - m2 * cos(2 * a1 - 2 * a2));
        double a2_a = (num1 * (num2 + num3 + num4)) / den;

        double x1 = r1 * sin(a1);
        double y1 = r1 * cos(a1);

        double x2 = x1 + r2 * sin(a2);
        double y2 = y1 + r2 * cos(a2);

        g.setColor(Color.white);
        g.drawLine(x_offset, y_offset, (int) x1 + x_offset, (int) y1 + y_offset);
        g.fillOval((int) x1 + x_offset - m1 / 2, (int) y1 + y_offset - m1 / 2, m1, m1);
        g.drawLine((int) x1 + x_offset, (int) y1 + y_offset, (int) x2 + x_offset, (int) y2 + y_offset);
        g.fillOval((int) x2 + x_offset - m2 / 2, (int) y2 + y_offset - m2 / 2, m2, m2);

        a1_v += a1_a;
        a2_v += a2_a;
        a1 += a1_v;
        a2 += a2_v;

//         //as momentum increases  , slowly pendulum comes to rest
//         a1_v *= 0.99; // for drag
//         a2_v *= 0.99; // for drag
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
