package oopdoublependulum;

import java.awt.*;

import static java.lang.Math.*;
import static java.lang.Math.cos;

public class Pendulum {
    int len = 150;
    int mass = 30;
    double angle = PI / 2;
    double angularVelocity = 0;
    static double gravity = 6.78033;
    int xOffset = 500, yOffset = 100;
    Pivot pivot = null;

    // first pendulum
    public Pendulum(Pivot pivot) {
        this.pivot = pivot;
        this.xOffset = pivot.x;
        this.yOffset = pivot.y;
    }

    // first pendulum
    public Pendulum(int len, int mass, Pivot pivot) {
        this.len = len;
        this.mass = mass;
        this.pivot = pivot;
        this.xOffset = pivot.x;
        this.yOffset = pivot.y;
    }

    // second pendulum
    public Pendulum() {
    }

    // second pendulum
    public Pendulum(int len, int mass) {
        this.len = len;
        this.mass = mass;
    }

    public static void drawUpdate(Graphics g, Pendulum pendulum1, Pendulum pendulum2) {
        double num1 = -1 * gravity * (2 * pendulum1.mass + pendulum2.mass) * sin(pendulum1.angle);
        double num2 = -1 * pendulum2.mass * gravity * sin(pendulum1.angle - 2 * pendulum2.angle);
        double num3 = -2 * sin(pendulum1.angle - pendulum2.angle) * pendulum2.mass;
        double num4 = pendulum2.angularVelocity * pendulum2.angularVelocity * pendulum2.len + pendulum1.angularVelocity * pendulum1.angularVelocity * pendulum1.len * cos(pendulum1.angle - pendulum2.angle);
        double den = pendulum1.len * (2 * pendulum1.mass + pendulum2.mass - pendulum2.mass * cos(2 * pendulum1.angle - 2 * pendulum2.angle));
        double a1_a = (num1 + num2 + num3 * num4) / den;

        num1 = 2 * sin(pendulum1.angle - pendulum2.angle);
        num2 = (pendulum1.angularVelocity * pendulum1.angularVelocity * pendulum1.len * (pendulum1.mass + pendulum2.mass));
        num3 = gravity * (pendulum1.mass + pendulum2.mass) * cos(pendulum1.angle);
        num4 = pendulum2.angularVelocity * pendulum2.angularVelocity * pendulum2.len * pendulum2.mass * cos(pendulum1.angle - pendulum2.angle);
        den = pendulum2.len * (2 * pendulum1.mass + pendulum2.mass - pendulum2.mass * cos(2 * pendulum1.angle - 2 * pendulum2.angle));
        double a2_a = (num1 * (num2 + num3 + num4)) / den;

        double x1 = pendulum1.len * sin(pendulum1.angle);
        double y1 = pendulum1.len * cos(pendulum1.angle);

        double x2 = x1 + pendulum2.len * sin(pendulum2.angle);
        double y2 = y1 + pendulum2.len * cos(pendulum2.angle);

        g.setColor(Color.white);
        g.drawLine(pendulum1.xOffset, pendulum1.yOffset, (int) x1 + pendulum1.xOffset, (int) y1 + pendulum1.yOffset);
        g.fillOval((int) x1 + pendulum1.xOffset - pendulum1.mass / 2, (int) y1 + pendulum1.yOffset - pendulum1.mass / 2, pendulum1.mass, pendulum1.mass);
        g.drawLine((int) x1 + pendulum1.xOffset, (int) y1 + pendulum1.yOffset, (int) x2 + pendulum1.xOffset, (int) y2 + pendulum1.yOffset);
        g.fillOval((int) x2 + pendulum1.xOffset - pendulum2.mass / 2, (int) y2 + pendulum1.yOffset - pendulum2.mass / 2, pendulum2.mass, pendulum2.mass);

        pendulum1.angularVelocity += a1_a;
        pendulum2.angularVelocity += a2_a;
        pendulum1.angle += pendulum1.angularVelocity;
        pendulum2.angle += pendulum2.angularVelocity;

//         //as momentum increases  , slowly pendulum comes to rest
//         a1_v *= 0.99; // for drag
//         a2_v *= 0.99; // for drag
    }
}

























