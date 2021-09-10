package ooppendulum;

import java.awt.*;

import static java.lang.Math.*;

public class Pendulum {
    static double gravity = 9.78033;
    double angle = PI / 4;
    double angleV = 0;
    double angleA = 0;
    double x, y;
    int len = 400;
    Pivot pivot;
    int diameter = 60;

    public Pendulum(Pivot pivot) {
        this.pivot = pivot;
    }

    public void drawUpdate(Graphics g) {
        double force = gravity * sin(this.angle);
        this.angleA = (-1 * force) / this.len;
        this.angleV += this.angleA;
        this.angle += this.angleV;
        this.x = this.len * sin(this.angle) + this.pivot.x;
        this.y = this.len * cos(this.angle) + this.pivot.y;
        g.setColor(Color.white);
        g.drawLine(this.pivot.x, this.pivot.y, (int) this.x, (int) this.y);
        g.fillOval((int) this.x - this.diameter / 2, (int) this.y - this.diameter / 2, this.diameter, this.diameter);

    }
}
