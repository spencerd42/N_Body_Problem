import java.awt.*;
import java.util.Random;
import java.util.Set;
import java.util.ArrayList;

public class Body {
    double x, y, z;
    double x_speed, y_speed, z_speed;
    double size;
    int padding, bound;
    Color color;
    private final Random random;
    private static final double G = 6.67430e-11;
    ArrayList<Body> bodies;
    private static final double MASS = 6e24;

    public Body(double x, double y, double z, double x_speed, double y_speed, double z_speed,
                double size, int padding, int width, Color color, ArrayList<Body> bodies) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.x_speed = x_speed;
        this.y_speed = y_speed;
        this.z_speed = z_speed;
        this.padding = padding;
        this.bound = width;
        this.size = size;
        this.color = color;
        this.bodies = bodies;
        this.random = new Random();
    }

    public Body(double x, double y, double z, double size, int padding, int width, Color color, ArrayList<Body> bodies) {
        this(x, y, z, 0, 0, 0, size, padding, width, color, bodies);
    }

    public void tick() {
        x += x_speed;
        y += y_speed;
        z += z_speed;
        for (Body b : bodies) {
            if (b != this) {
                accelerate(b);
            }
        }
    }

    private void accelerate(Body b) {
        Vector displacement_vector = getDisplacementVector(b);
        double multiplier = G * MASS / Math.pow(displacement_vector.getMagnitude(), 3);
        x_speed += multiplier * displacement_vector.x_comp;
        y_speed += multiplier * displacement_vector.y_comp;
        z_speed += multiplier * displacement_vector.z_comp;
    }

    private Vector getDisplacementVector(Body b) {
        return new Vector(b.x - this.x, b.y - this.y, b.z - this.z);
    }
}