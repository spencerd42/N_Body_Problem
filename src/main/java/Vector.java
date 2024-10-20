public class Vector {
    double x_comp;
    double y_comp;
    double z_comp;

    public Vector(double x_comp, double y_comp, double z_comp) {
        this.x_comp = x_comp;
        this.y_comp = y_comp;
        this.z_comp = z_comp;
    }

    public double getXComp() {
        return x_comp;
    }

    public double getYComp() {
        return y_comp;
    }

    public double getZComp() {
        return z_comp;
    }

    public void setXComp(double newX) {
        x_comp = newX;
    }

    public void setYComp(double newY) {
        y_comp = newY;
    }

    public void setZComp(double newZ) {
        z_comp = newZ;
    }

    public double getMagnitude() {
        return Math.sqrt(Math.pow(x_comp, 2) + Math.pow(y_comp, 2) + Math.pow(z_comp, 2));
    }

    public Vector getUnitVector() {
        double magnitude = getMagnitude();
        return new Vector(x_comp / magnitude, y_comp / magnitude, z_comp / magnitude);
    }

    public Vector add(Vector v) {
        return new Vector(this.x_comp + v.x_comp, this.y_comp + v.y_comp, this.z_comp + v.z_comp);
    }
}
