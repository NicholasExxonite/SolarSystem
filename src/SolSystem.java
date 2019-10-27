import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.*;

public class SolSystem extends JFrame {

    private int width = 300;
    private int height = 300;
    private boolean exiting = false;

    ArrayList<Sun> suns = new ArrayList<>();
    ArrayList<Planet> planets = new ArrayList<>();

    // how should I save them?
    /**
     * Create a view of the Solar System.
     * Once an instance of the SolarSystem class is created,
     * a window of the appropriate size is displayed, and
     * objects can be displayed in the solar system
     *
     * @param width the width of the window in pixels.
     * @param height the height of the window in pixels.
     */
    public System(int width, int height)
    {
        this.width = width;
        this.height = height;

        this.setTitle("The Solar System");
        this.setSize(width, height);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * A method called by the operating system to draw onto the screen - <p><B>YOU DO NOT (AND SHOULD NOT) NEED TO CALL THIS METHOD.</b></p>
     */
    public void paint (Graphics gr) {
        BufferedImage i = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = i.createGraphics();

        //g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        synchronized (this) {
            if (!this.exiting) {
                g.clearRect(0, 0, width, height);
                for (Sun s : suns) {
                    g.setColor(s.col);
                    g.fillOval(s.x, s.y, s.diameter, s.diameter);

                    try{ Thread.sleep(0); } catch (Exception e) {}
                }

                for (Planet p : planets) {
                    g.setColor(p.col);
                    g.fillOval(p.x, p.y, p.diameter, p.diameter);

                    try{ Thread.sleep(0); } catch (Exception e) {}
                }
                gr.drawImage(i, 0, 0, this);
            }
        }
    }
    public void drawSun(int diameter,  String col)
    {
        Color colour = this.getColourFromString(col);
//        double centreOfRotationX = ((double) width / 2.0);
//        double centreOfRotationY = ((double) height/ 2.0);
//
//        double rads = Math.toRadians(angle);
//        double x = (int) (centreOfRotationX + distance * Math.sin(rads)) - diameter / 2;
//        double y = (int) (centreOfRotationY + distance * Math.cos(rads)) - diameter / 2;

        synchronized (this)
        {
            Sun sun = new Sun((int)diameter, colour);
            suns.add(sun);
        }
    }
    public void drawPlanet(int diameter, double angle, double distance, int velocity, String color)
    {
        Color col = this.getColourFromString(color);
        double centreOfRotationX = ((double) width / 2.0);
        double centreOfRotationY = ((double) height/ 2.0);

        double rads = Math.toRadians(angle);
        double x = (int) (centreOfRotationX + distance * Math.sin(rads)) - diameter / 2;
        double y = (int) (centreOfRotationY + distance * Math.cos(rads)) - diameter / 2;

        synchronized (this)
        {
            if (planets.size() >= 200)
            {
                System.out.println("You have more than 200 planets");
                this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            }
            else
            {
                Planet p = new Planet((int)x, (int)y, diameter, (int)angle, (int)distance, velocity, col);
                planets.add(p);
                p.move(p);
            }
        }

    }

    private Color getColourFromString(String col)
    {
        Color color;

        if (col.charAt(0) == '#')
        {
            color = new Color(
                    Integer.valueOf( col.substring( 1, 3 ), 16 ),
                    Integer.valueOf( col.substring( 3, 5 ), 16 ),
                    Integer.valueOf( col.substring( 5, 7 ), 16 ) );
        }
        else
        {
            try
            {
                java.lang.reflect.Field field = Color.class.getField(col);
                color = (Color)field.get(null);
            } catch (Exception e) {
                color = Color.WHITE;
            }
        }
        return color;
    }

    public void finishedDrawing()
    {
        try
        {
            this.repaint();
            Thread.sleep(10);
            synchronized (this)
            {
                //things.clear();

            }
        }
        catch (Exception e) { }
    }

    private class Point
    {
        public int x;
        public int y;

        public Point(int x, int y)
        {
               this.x = x;
               this.y = y;
        }
    }
    private class Sun
    {
        public int x = width / 2;
        public int y = height / 2;
        public int diameter;
        public Color col;

        public Sun(int diameter, Color col)
        {
            this.x = ((width/2) - (diameter /2));
            this.y = ((height/2) - (diameter /2));
            this.diameter = diameter;
            this.col = col;
        }
    }
    private class Planet
    {
        public int x;
        public int y;
        public int velocity;
        public int angle;
        public int diameter;
        public int distance;
        public Color col;

        public Planet(int x, int y, int diameter, int angle, int distance, int velocity, Color col)
        {
            this.x = x;
            this.y = y;
            this.diameter = diameter;
            this.angle = angle;
            this.distance = distance;
            this.velocity = velocity;
            this.col = col;

        }
        public void move(Planet p)
        {
            while(this.angle <= 360)
            {
                drawPlanet(this.diameter, this.angle, this.distance, this.velocity, this.col.toString());

                this.angle++;
                if (this.angle == 360){
                    this.angle = 0;
                }
            }
        }
    }
}
