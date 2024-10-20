import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Comparator;

public class Simulation extends JPanel implements ActionListener {

    private static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = 800;
    private static final int DELAY = 17; // Milliseconds
    private static final int PADDING = 100;
    private int colorCalls;
    private static final Color[] colors = new Color[] {Color.red, new Color(255, 127, 0),
            Color.yellow, Color.green, Color.blue, new Color(127, 0, 255)};

    private ArrayList<Body> bodies;
    private Timer timer;
    private Random random;

    private final int NUM_BODIES = 3;

    public Simulation() {
        colorCalls = 0;

        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.WHITE);

        bodies = new ArrayList<>();
        random = new Random();

        addbodies();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void addbodies() {
        for (int i = 0; i < NUM_BODIES; i++) {
            bodies.add(new Body(random.nextInt(PANEL_WIDTH),
                    random.nextInt(PANEL_WIDTH),
                    random.nextInt(200),
                    50, PADDING, PANEL_WIDTH, nextColor(), bodies));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        bodies.sort(new ZComparator());
        for (Body body : bodies) {
            g.setColor(body.color);
            g.fillOval((int) body.x, (int) body.y, (int) body.z, (int) body.z);
            g.setColor(Color.black);
            g.drawOval((int) body.x, (int) body.y, (int) body.z, (int) body.z);
        }
    }

    static class ZComparator implements Comparator<Body> {
        @Override
        public int compare(Body b1, Body b2) {
            return Double.compare(b1.z, b2.z);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Body b : bodies) {
            b.tick();
        }

        repaint();
    }

    private Color nextColor() {
        int calls = colorCalls;
        colorCalls++;
        return colors[calls % colors.length];
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simulation");
        Simulation Simulation = new Simulation();
        Simulation.setBackground(Color.black);
        frame.add(Simulation);
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
