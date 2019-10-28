import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.*;

/**
 * This class provides a graphical user interface to a model of the solar system
 * @author Joe Finney
 */
public class System extends JFrame
{
    private int width = 300;
    private int height = 300;
    private boolean exiting = false;

    //private ArrayList<SolarObject> things = new ArrayList<SolarObject>();
    private ArrayList<Sun> suns = new ArrayList<Sun>();
    public ArrayList<Planet> planets = new ArrayList<>();
    private ArrayList<Planet> temp = new ArrayList<>();


    /**
     * Create a view of the Solar SolSystem.
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

        this.setTitle("The Solar SolSystem");
        this.setSize(width, height);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * A method called by the operating system to draw onto the screen - <p><B>YOU DO NOT (AND SHOULD NOT) NEED TO CALL THIS METHOD.</b></p>
     */
    public void paint (Graphics gr)
    {
        BufferedImage i = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = i.createGraphics();

        //g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        synchronized (this)
        {
            if (!this.exiting) {
                g.clearRect(0, 0, width, height);
                for (Sun s : suns) {
                    g.setColor(s.col);
                    g.fillOval(s.x, s.y, s.diameter, s.diameter);
                }
                for( Planet p : planets){
                    g.setColor(p.getCol());
                    g.fillOval(p.getX(), p.getY(), (int)p.getDiameter(), (int)p.getDiameter());
                }
            }

            gr.drawImage(i, 0, 0, this);
        }
    }

    //
    // Shouldn't really handle colour this way, but the student's haven't been introduced
    // to constants properly yet, and Color.getColor() doesn't seem to work... hmmm....
    //
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

    /**
     * Draws a round shape in the window at the given co-ordinates that represents an object in the solar system.
     * The SolarSystem class uses <i>Polar Co-ordinates</i> to represent the location
     * of objects in the solar system.
     *
     * @param distance the distance from the sun to the object.
     * @param angle the angle (in degrees) that represents how far the planet is around its orbit of the sun.
     * @param diameter the size of the object.
     * @param col the colour of this object, as a string. Case insentive. <p>One of: BLACK, BLUE, CYAN, DARK_GRAY, GRAY, GREEN, LIGHT_GRAY,
     * MAGENTA, ORANGE, PINK, RED, WHITE, YELLOW. Alternatively, a 24 bit hexadecimal string representation of an RGB colour is also accepted, e.g. "#FF0000"</p>
     */
//	public void drawSolarObject(double distance, double angle, double diameter, String col)
//	{
//		Color colour = this.getColourFromString(col);
//		double centreOfRotationX = ((double) width) / 2.0;
//		double centreOfRotationY = ((double) height) / 2.0;
//
//		double rads = Math.toRadians(angle);
//		double x = (int) (centreOfRotationX + distance * Math.sin(rads)) - diameter / 2;
//		double y = (int) (centreOfRotationY + distance * Math.cos(rads)) - diameter / 2;
//
//		synchronized (this)
//		{
//			if (things.size() > 1000)
//			{
//				SolSystem.out.println("\n\n");
//				SolSystem.out.println(" ********************************************************* ");
//				SolSystem.out.println(" ***** Only 1000 Entities Supported per Solar SolSystem ***** ");
//				SolSystem.out.println(" ********************************************************* ");
//				SolSystem.out.println("\n\n");
//				SolSystem.out.println("If you are't trying to add this many things");
//				SolSystem.out.println("to your SolarSystem, then you have probably");
//				SolSystem.out.println("forgotten to call the finishedDrawing() method");
//				SolSystem.out.println("See the JavaDOC documentation for more information");
//				SolSystem.out.println("\n-- Joe");
//				SolSystem.out.println("\n\n");
//
//				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
//			}
//			else
//			{
//				SolarObject t = new SolarObject((int)x, (int)y, (int)diameter, colour);
//				things.add(t);
//			}
//		}
//	}
    public void drawSun(double distance, double angle, double diameter, String col)
    {
        Color colour = this.getColourFromString(col);
        double centreOfRotationX = ((double) width / 2.0);
        double centreOfRotationY = ((double) height/ 2.0);

        double rads = Math.toRadians(angle);
        double x = (int) (centreOfRotationX + distance * Math.sin(rads)) - diameter / 2;
        double y = (int) (centreOfRotationY + distance * Math.cos(rads)) - diameter / 2;

        synchronized (this)
        {
            Sun sun = new Sun((int)x, (int)y, (int)diameter, colour);
            suns.add(sun);
        }
    }

    public void addPlanet(Planet p)
    {
        double centreOfRotationX = ((double) width) / 2.0;
        double centreOfRotationY = ((double) height) / 2.0;

        double rads = Math.toRadians(p.getAngle());
        double x = (int) (centreOfRotationX + p.getDistance() * Math.sin(rads)) - p.getDiameter() / 2;
        double y = (int) (centreOfRotationY + p.getDistance() * Math.cos(rads)) - p.getDiameter() / 2;

        synchronized (this)
        {
            if (planets.size() >= 100)
            {
                this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            }
            else
            {

                p.setX((int)x);
                p.setY((int)y);
                //temp.add(p);
                planets.add(p);

            }

        }
    }
    public void animate1(ArrayList<Planet> pls){
        while (true)
        {
            synchronized (this)
            {
                for (Planet p:pls
                     )
                {
                    addPlanet(p);
                    p.move();
                    try{
                        this.repaint();
                        Thread.sleep(10);
                        pls.remove(p);
                        addPlanet(p);
                    }catch (Exception e){}

                }
            }
        }
    }
    public void animate(Planet p){
        while (true)
        {
//            for(int i = 0; i == planets.size(); i++)
//            {
//                temp.set(i, planets.get(i));
//            }
//            temp = (ArrayList<Planet>) planets.clone();
//            planets = (ArrayList<Planet>) temp.clone();
//
//            {
//                for (Planet p: temp){
//                    p.move();
//                    addPlanet(p);
//                    finishedDrawing();
//                }
//                //temp.clear();
//            }
            finishedDrawing();
            p.move();
            addPlanet(p);
            //finishedDrawing();
        }
    }
    /**
     * Draws a round shape in the window at the given co-ordinates.
     * The SolarSystem class uses <i>Polar Co-ordinates</i> to represent the location
     * of objects in the solar system. This method operates in the same fashion as drawSolarObject, but
     * provides additional co-ordinates to allow the programmer to use an arbitrary point about which
     * the object orbits (e.g. a planet rather than the sun).
     *
     * @param distance the distance from this object to the point about which it is orbiting.
     * @param angle the angle (in degrees) that represents how far the object is around its orbit.
     * @param diameter the size of the object.
     * @param col the colour of this object, as a string. Case insentive. <p>One of: BLACK, BLUE, CYAN, DARK_GRAY, GRAY, GREEN, LIGHT_GRAY,
     * MAGENTA, ORANGE, PINK, RED, WHITE, YELLOW</p>
     * @param centreOfRotationDistance the distance part of the polar co-ordinate about which this object orbits.
     * @param centreOfRotationAngle the angular part of the polar co-ordinate about which this object orbits.
     */
    public void drawSolarObjectAbout(double distance, double angle, double diameter, String col, double centreOfRotationDistance, double centreOfRotationAngle)
    {
        Color colour = this.getColourFromString(col);
        double centrerads = Math.toRadians(centreOfRotationAngle);
        double centreOfRotationX = (((double) width) / 2.0) + centreOfRotationDistance * Math.sin(centrerads);
        double centreOfRotationY = (((double) height) / 2.0) + centreOfRotationDistance * Math.cos(centrerads);

        double rads = Math.toRadians(angle);
        double x = (int) (centreOfRotationX + distance * Math.sin(rads)) - diameter / 2;
        double y = (int) (centreOfRotationY + distance * Math.cos(rads)) - diameter / 2;

        synchronized (this)
        {
            if (planets.size() > 1000)
            {
//				SolSystem.out.println("\n\n");
//				SolSystem.out.println(" ******************************************************** ");
//				SolSystem.out.println(" ***** Only 1000 Entities Supported per Solar SolSystem ***** ");
//				SolSystem.out.println(" ********************************************************* ");
//				SolSystem.out.println("\n\n");
//				SolSystem.out.println("If you are't trying to add this many things");
//				SolSystem.out.println("to your SolarSystem, then you have probably");
//				SolSystem.out.println("forgotten to call the finishedDrawing() method");
//				SolSystem.out.println("See the JavaDOC documentation for more information");
//				SolSystem.out.println("\n-- Joe");
//				SolSystem.out.println("\n\n");

                this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            }
            else
            {
                SolarObject t = new SolarObject((int)x, (int)y, (int)diameter, colour);
                //things.add(t);
            }
        }
    }

    /**
     * Makes all objects drawn recently drawn to be made visible on the screen.
     *
     * Once called, all suns, planets and moons are displayed in the window.
     */
    public void finishedDrawing()
    {
        try
        {
            this.repaint();
            Thread.sleep(10);
            synchronized (this)
            {
                //things.clear();
                planets.clear();
            }
        }
        catch (Exception e) {}
    }

    private class SolarObject
    {
        public int x;
        public int y;
        public int diameter;
        public Color col;

        public SolarObject(int x, int y, int diameter, Color col)
        {
            this.x = x;
            this.y = y;
            this.diameter = diameter;
            this.col = col;
        }
    }

    private class Sun
    {
        public int x = 0;
        public int y = 0;
        //public int angle = 0;
        public int diameter;
        public Color col;

        public Sun (int x, int y, int diameter, Color col)
        {
            this.x = x;
            this.y = y;
            this.diameter = diameter;
            this.col = col;

        }

        public void move(){

        }
    }
}
