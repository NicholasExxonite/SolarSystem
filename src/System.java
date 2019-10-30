//import javax.jws.WebParam;
//import javax.swing.*;

//import javax.swing.*;
//import java.awt.*;
//import java.awt.image.*;
//import java.awt.event.*;
//import java.util.*;
//
///**
// * This class provides a graphical user interface to a model of the solar system
// * @author Joe Finney
// */
//public class System extends JFrame
//{
//    private int width = 300;
//    private int height = 300;
//    private boolean exiting = false;
//
//    //private ArrayList<SolarObject> things = new ArrayList<SolarObject>();
//    private ArrayList<Sun> suns = new ArrayList<Sun>();
//    public ArrayList<Planet> planets = new ArrayList<>();
//    private ArrayList<Planet> temp = new ArrayList<>();
//
//
//    /**
//     * Create a view of the Solar SolSystem.
//     * Once an instance of the SolarSystem class is created,
//     * a window of the appropriate size is displayed, and
//     * objects can be displayed in the solar system
//     *
//     * @param width the width of the window in pixels.
//     * @param height the height of the window in pixels.
//     */
//    public System(int width, int height)
//    {
//        this.width = width;
//        this.height = height;
//
//        this.setTitle("The Solar SolSystem");
//        this.setSize(width, height);
//        this.setBackground(Color.BLACK);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setVisible(true);
//    }
//
//    /**
//     * A method called by the operating system to draw onto the screen - <p><B>YOU DO NOT (AND SHOULD NOT) NEED TO CALL THIS METHOD.</b></p>
//     */
//    public void paint (Graphics gr)
//    {
//        BufferedImage i = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
//        Graphics2D g = i.createGraphics();
//
//        //g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//        synchronized (this)
//        {
//            if (!this.exiting) {
//                g.clearRect(0, 0, width, height);
//                for (Sun s : suns) {
//                    g.setColor(s.col);
//                    g.fillOval(s.x, s.y, s.diameter, s.diameter);
//                }
//                for( Planet p : planets){
//                    g.setColor(p.getCol());
//                    g.fillOval(p.getX(), p.getY(), (int)p.getDiameter(), (int)p.getDiameter());
//                }
//            }
//
//            gr.drawImage(i, 0, 0, this);
//        }
//    }
//
//    //
//    // Shouldn't really handle colour this way, but the student's haven't been introduced
//    // to constants properly yet, and Color.getColor() doesn't seem to work... hmmm....
//    //
//    private Color getColourFromString(String col)
//    {
//        Color color;
//
//        if (col.charAt(0) == '#')
//        {
//            color = new Color(
//                    Integer.valueOf( col.substring( 1, 3 ), 16 ),
//                    Integer.valueOf( col.substring( 3, 5 ), 16 ),
//                    Integer.valueOf( col.substring( 5, 7 ), 16 ) );
//        }
//        else
//        {
//            try
//            {
//                java.lang.reflect.Field field = Color.class.getField(col);
//                color = (Color)field.get(null);
//            } catch (Exception e) {
//                color = Color.WHITE;
//            }
//        }
//        return color;
//    }
//
//    /**
//     * Draws a round shape in the window at the given co-ordinates that represents an object in the solar system.
//     * The SolarSystem class uses <i>Polar Co-ordinates</i> to represent the location
//     * of objects in the solar system.
//     *
//     * @param distance the distance from the sun to the object.
//     * @param angle the angle (in degrees) that represents how far the planet is around its orbit of the sun.
//     * @param diameter the size of the object.
//     * @param col the colour of this object, as a string. Case insentive. <p>One of: BLACK, BLUE, CYAN, DARK_GRAY, GRAY, GREEN, LIGHT_GRAY,
//     * MAGENTA, ORANGE, PINK, RED, WHITE, YELLOW. Alternatively, a 24 bit hexadecimal string representation of an RGB colour is also accepted, e.g. "#FF0000"</p>
//     */
////	public void drawSolarObject(double distance, double angle, double diameter, String col)
////	{
////		Color colour = this.getColourFromString(col);
////		double centreOfRotationX = ((double) width) / 2.0;
////		double centreOfRotationY = ((double) height) / 2.0;
////
////		double rads = Math.toRadians(angle);
////		double x = (int) (centreOfRotationX + distance * Math.sin(rads)) - diameter / 2;
////		double y = (int) (centreOfRotationY + distance * Math.cos(rads)) - diameter / 2;
////
////		synchronized (this)
////		{
////			if (things.size() > 1000)
////			{
////				SolSystem.out.println("\n\n");
////				SolSystem.out.println(" ********************************************************* ");
////				SolSystem.out.println(" ***** Only 1000 Entities Supported per Solar SolSystem ***** ");
////				SolSystem.out.println(" ********************************************************* ");
////				SolSystem.out.println("\n\n");
////				SolSystem.out.println("If you are't trying to add this many things");
////				SolSystem.out.println("to your SolarSystem, then you have probably");
////				SolSystem.out.println("forgotten to call the finishedDrawing() method");
////				SolSystem.out.println("See the JavaDOC documentation for more information");
////				SolSystem.out.println("\n-- Joe");
////				SolSystem.out.println("\n\n");
////
////				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
////			}
////			else
////			{
////				SolarObject t = new SolarObject((int)x, (int)y, (int)diameter, colour);
////				things.add(t);
////			}
////		}
////	}
//    public void drawSun(double distance, double angle, double diameter, String col)
//    {
//        Color colour = this.getColourFromString(col);
//        double centreOfRotationX = ((double) width / 2.0);
//        double centreOfRotationY = ((double) height/ 2.0);
//
//        double rads = Math.toRadians(angle);
//        double x = (int) (centreOfRotationX + distance * Math.sin(rads)) - diameter / 2;
//        double y = (int) (centreOfRotationY + distance * Math.cos(rads)) - diameter / 2;
//
//        synchronized (this)
//        {
//            Sun sun = new Sun((int)x, (int)y, (int)diameter, colour);
//            suns.add(sun);
//        }
//    }
//
//    public void addPlanet(Planet p)
//    {
//        double centreOfRotationX = ((double) width) / 2.0;
//        double centreOfRotationY = ((double) height) / 2.0;
//
//        double rads = Math.toRadians(p.getAngle());
//        double x = (int) (centreOfRotationX + p.getDistance() * Math.sin(rads)) - p.getDiameter() / 2;
//        double y = (int) (centreOfRotationY + p.getDistance() * Math.cos(rads)) - p.getDiameter() / 2;
//
//        synchronized (this)
//        {
//            if (planets.size() >= 100)
//            {
//                this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
//            }
//            else
//            {
//
//                p.setX((int)x);
//                p.setY((int)y);
//                //temp.add(p);
//                planets.add(p);
//
//            }
//
//        }
//    }
//    public void animate1(ArrayList<Planet> pls){
//        while (true)
//        {
//            synchronized (this)
//            {
//                for (Planet p:pls
//                     )
//                {
//                    addPlanet(p);
//                    p.move();
//                    try{
//                        this.repaint();
//                        Thread.sleep(10);
//                        pls.remove(p);
//                        addPlanet(p);
//                    }catch (Exception e){}
//
//                }
//            }
//        }
//    }
//    public void animate(Planet p){
//        while (true)
//        {
////            for(int i = 0; i == planets.size(); i++)
////            {
////                temp.set(i, planets.get(i));
////            }
////            temp = (ArrayList<Planet>) planets.clone();
////            planets = (ArrayList<Planet>) temp.clone();
////
////            {
////                for (Planet p: temp){
////                    p.move();
////                    addPlanet(p);
////                    finishedDrawing();
////                }
////                //temp.clear();
////            }
//            finishedDrawing();
//            p.move();
//            addPlanet(p);
//            //finishedDrawing();
//        }
//    }
//    /**
//     * Draws a round shape in the window at the given co-ordinates.
//     * The SolarSystem class uses <i>Polar Co-ordinates</i> to represent the location
//     * of objects in the solar system. This method operates in the same fashion as drawSolarObject, but
//     * provides additional co-ordinates to allow the programmer to use an arbitrary point about which
//     * the object orbits (e.g. a planet rather than the sun).
//     *
//     * @param distance the distance from this object to the point about which it is orbiting.
//     * @param angle the angle (in degrees) that represents how far the object is around its orbit.
//     * @param diameter the size of the object.
//     * @param col the colour of this object, as a string. Case insentive. <p>One of: BLACK, BLUE, CYAN, DARK_GRAY, GRAY, GREEN, LIGHT_GRAY,
//     * MAGENTA, ORANGE, PINK, RED, WHITE, YELLOW</p>
//     * @param centreOfRotationDistance the distance part of the polar co-ordinate about which this object orbits.
//     * @param centreOfRotationAngle the angular part of the polar co-ordinate about which this object orbits.
//     */
//    public void drawSolarObjectAbout(double distance, double angle, double diameter, String col, double centreOfRotationDistance, double centreOfRotationAngle)
//    {
//        Color colour = this.getColourFromString(col);
//        double centrerads = Math.toRadians(centreOfRotationAngle);
//        double centreOfRotationX = (((double) width) / 2.0) + centreOfRotationDistance * Math.sin(centrerads);
//        double centreOfRotationY = (((double) height) / 2.0) + centreOfRotationDistance * Math.cos(centrerads);
//
//        double rads = Math.toRadians(angle);
//        double x = (int) (centreOfRotationX + distance * Math.sin(rads)) - diameter / 2;
//        double y = (int) (centreOfRotationY + distance * Math.cos(rads)) - diameter / 2;
//
//        synchronized (this)
//        {
//            if (planets.size() > 1000)
//            {
////				SolSystem.out.println("\n\n");
////				SolSystem.out.println(" ******************************************************** ");
////				SolSystem.out.println(" ***** Only 1000 Entities Supported per Solar SolSystem ***** ");
////				SolSystem.out.println(" ********************************************************* ");
////				SolSystem.out.println("\n\n");
////				SolSystem.out.println("If you are't trying to add this many things");
////				SolSystem.out.println("to your SolarSystem, then you have probably");
////				SolSystem.out.println("forgotten to call the finishedDrawing() method");
////				SolSystem.out.println("See the JavaDOC documentation for more information");
////				SolSystem.out.println("\n-- Joe");
////				SolSystem.out.println("\n\n");
//
//                this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
//            }
//            else
//            {
//                SolarObject t = new SolarObject((int)x, (int)y, (int)diameter, colour);
//                //things.add(t);
//            }
//        }
//    }
//
//    /**
//     * Makes all objects drawn recently drawn to be made visible on the screen.
//     *
//     * Once called, all suns, planets and moons are displayed in the window.
//     */
//    public void finishedDrawing()
//    {
//        try
//        {
//            this.repaint();
//            Thread.sleep(10);
//            synchronized (this)
//            {
//                //things.clear();
//                planets.clear();
//            }
//        }
//        catch (Exception e) {}
//    }
//
//    private class SolarObject
//    {
//        public int x;
//        public int y;
//        public int diameter;
//        public Color col;
//
//        public SolarObject(int x, int y, int diameter, Color col)
//        {
//            this.x = x;
//            this.y = y;
//            this.diameter = diameter;
//            this.col = col;
//        }
//    }
//
//    private class Sun
//    {
//        public int x = 0;
//        public int y = 0;
//        //public int angle = 0;
//        public int diameter;
//        public Color col;
//
//        public Sun (int x, int y, int diameter, Color col)
//        {
//            this.x = x;
//            this.y = y;
//            this.diameter = diameter;
//            this.col = col;
//
//        }
//
//        public void move(){
//
//        }
//    }
//}

//import java.awt.*;
//import javax.swing.*;
//import java.awt.image.*;
//import java.awt.event.*;
//import java.util.*;
//
//public class System extends JFrame
//{
//
//
//    //Model model;
//
//
//    Planet[] planets = new Planet[9];
//    int height = 300;
//    int width = 300;
//
//    final static int DELAY = 100;
//    boolean stop = false;
//
//    public System(int height, int width)
//    {
//
////        model = new Model();
////        model.setPreferredSize(new Dimension(1200, 1200));
////        add(model);
//
//        this.height = height;
//        this.width = width;
//
//        planets[0] = new Planet(this, 2, 300, 100, 30, "BLUE");
//
//        this.setBackground(Color.BLACK);
//        this.setSize(width, height);
//        this.setVisible(true);
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//
//        Thread thread = new Thread(() -> gameLoop());
//        thread.start();
//    }
//
////    class Model extends JPanel {
////        public Model() {
////           // setBackground(Color.BLACK);
////            setFocusable(true); //wasn't working
////            requestFocus();
////
////        }
////    }
//    private void gameLoop()
//    {
//        repaint();
//        try{
//            Thread.sleep(DELAY);
//        }catch (InterruptedException e){}
//    }
//    public void paint(Graphics gr)
//    {
//        BufferedImage i = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
//        Graphics2D g = i.createGraphics();
//
//       // g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        synchronized (this)
//        {
//            g.clearRect(0, 0, width, height);
//            for(Planet p : planets)
//            {
////                g.setColor(p.col);
////                g.fillOval((int)p.getX(), (int)p.getY(), p.getDiameter(), p.getDiameter());
//                p.draw(gr);
//            }
//            gr.drawImage(i, 0, 0, this);
//        }
//    }
//}

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;

public class System extends JPanel
{
    Model model;
    CelestialBody[] celestialBodies = new CelestialBody[9];
    boolean[] descriptionSeen = new boolean[9];

    final static int DELAY = 50;
    double size = 1;

    boolean stop = false;
    int clicked = -1;

    public System()
    {
        model = new Model();
        model.setPreferredSize(new Dimension(1200, 1200));
        add(model);

        celestialBodies[0] = new CelestialBody(600, 650, -4.7, 0, 9, 8, Color.GRAY, 1000); //Mercury
        celestialBodies[1] = new CelestialBody(752, 600, 0, 2.5, 900, 12, new Color(207,153,52), 1000); //Venus
        celestialBodies[2] = new CelestialBody(600, 350, 1.8, 0, 900, 11, Color.BLUE, 2000); //Earth
        celestialBodies[3] = new CelestialBody(650, 150, 1.2, 0, 900, 7, Color.RED, 2000); //Mars
        celestialBodies[4] = new CelestialBody(600, 100, 1.2, 0, 900, 20, new Color(255,140,0), 2000); //Jupiter
        celestialBodies[5] = new CelestialBody(600, 50, 1.2, 0, 900, 15, new Color(112,128,144), 2000); //Saturn
        celestialBodies[6] = new CelestialBody(600, 25, 1.2, 0, 900, 15, new Color(196,233,238), 2000); //Uranus
        celestialBodies[7] = new CelestialBody(0, 600, 0, -1.2, 900, 13, new Color(66, 98, 243), 2000);//Neptune

        celestialBodies[8] = new CelestialBody(600, 600, .1, 0, 1000, 30, Color.ORANGE, 0);//Sun



        setBackground(Color.BLACK);


        Thread thread =  new Thread() {

            @Override
            public void run() {
                gameLoop();
            }
        };

        thread.start();
    }


    private void gameLoop() {

        while (true) {
            if (!stop)
            {
                for(int i = 0; i < celestialBodies.length-1; i++)
                {
                    celestialBodies[i].update(celestialBodies[8].getXPosition(),celestialBodies[8].getYPosition(),celestialBodies[8].getMass());
                }
            }
            repaint();

            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException ex) { }
        }
    }


    class Model extends JPanel {
        public Model() {

            setFocusable(true);
            requestFocus();

        }


        public void paint(Graphics g) {


            for (CelestialBody body : celestialBodies)
                body.draw(g, size);

            for(int count=0;count<=1000;count++) {
                g.setColor(Color.WHITE);

                // drawing some stars
                g.drawOval(50*count,100*count,1,1);
                g.drawOval(75*count,100*count,1,1);

                g.drawOval(100*count,200*count,1,1);
                g.drawOval(150*count,200*count,1,1);
                g.drawOval(200*count,200*count,1,1);
                g.drawOval(250*count,200*count,1,1);
                g.drawOval(300*count,200*count,1,1);
                g.drawOval(350*count,200*count,1,1);
                g.drawOval(400*count,100*count,1,1);
                g.drawOval(450*count,100*count,1,1);
                g.drawOval(500*count,100*count,1,1);
                g.drawOval(550*count,300*count,1,1);
                g.drawOval(600*count,300*count,1,1);
                g.drawOval(700*count,300*count,1,1);
                g.drawOval(800*count,300*count,1,1);
                g.drawOval(900*count,300-count,1,1);
                g.drawOval(1000*count,300-count,1,1);

            }

        }

    }

}