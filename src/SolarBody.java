//import java.awt.*;
//import java.util.ArrayList;
//
//public class Planet {
//    private double distance;
//    private double angle;
//    private double diameter;
//    private int velocity;
//    private Color col;
//    private int x, y;
//
//    Planet(int distance, int diameter, int velocity, int angle, String col){
//        Color colour = this.getColourFromString(col);
//        this.diameter = diameter;
//        this.velocity = velocity;
//        this.distance = distance;
//        this. angle = angle;
//        this.col = colour;
//        this.x = x;
//        this.y = y;
//
//
//    }
//
//
//    public void setAngle(double a){
//        this.angle = a;
//    }
//
//    public void move(){
//
//        this.setAngle(angle + velocity);
//
//    }
//
//    public Color getCol() {
//        return col;
//    }
//
//    public double getAngle() {
//        return angle;
//    }
//
//    public double getDiameter() {
//        return diameter;
//    }
//
//    public double getDistance() {
//        return distance;
//    }
//
//    public int getVelocity() {
//        return velocity;
//    }
//
//    public int getX() {
//        return x;
//    }
//
//    public void setX(int x) {
//        this.x = x;
//    }
//
//    public int getY() {
//        return y;
//    }
//
//    public void setY(int y) {
//        this.y = y;
//    }
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
//}

//import javax.swing.*;
//import java.awt.*;
//
//
//public class Planet
//{
//    private int systemWidth;
//    private int systemHeight;
//    private int diameter =0;
//    private double x ;
//    private double y ;
//    private double velocity =0;
//    Color col;
//    private double distance =0;
//    private int angle = 0;
//    boolean visible;
//    int counter = 0;
//    Planet[] planets;
//
//    public Planet(System system, double velocity, int distance, int diameter, int angle, String color)
//    {
//        Color col = this.getColourFromString(color);
//
//        this.velocity = velocity;
//        this.distance = distance;
//        this.diameter = diameter;
//        this.col = col;
//        this.angle = angle;
//        systemWidth = system.getWidth();
//        systemHeight = system.getHeight();
//
//        double centreOfRotationX = ((double) system.getWidth() / 2.0);
//        double centreOfRotationY = ((double) system.getHeight()/ 2.0);
//
//        double rads = Math.toRadians(angle);
//        this.x = (int) (centreOfRotationX + this.distance * Math.sin(rads)) - this.diameter / 2;
//        this.y = (int) (centreOfRotationY + this.distance * Math.cos(rads)) - this.diameter / 2;
//
//
//    }
//
//    public double getX() {
//        return x;
//    }
//
//    public double getY() {
//        return y;
//    }
//
//    public int getDiameter() {
//        return diameter;
//    }
//
//    private Color getColourFromString(String col)
//        {
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
//    public void draw(Graphics g)
//    {
//        synchronized (this){
//            g.setColor(col);
//            g.fillOval((int)this.x, (int)this.y, this.diameter, this.diameter);
//        }
//    }
//    public void move()
//    {
//        this.angle += this.velocity;
//    }
//}

import java.awt.*;
import java.util.*;

public class SolarBody
{

    private int mass = 0;
    private int diameter = 0;
    private double xLoc = 0;
    private double yLoc = 0;
    private double velX = 0;
    private double velY = 0;
    private double speed = 0;
    Color color;
    private double acceleration =0;
    private double dirX = 0;
    private double dirY = 0;
    private double distance = 0;
    private double initial=1000;
    private double max=0;
    boolean visible;
    int orbitDots[][] = new int[1000][2];
    int counter = 0;
    /**
     * Constructor for objects of class Planet
     */
    public SolarBody(double x, double y, double xVelocity, double yVelocity, int bodyMass, int bodyDiameter, Color bodyColor, double bodySpeed)
    {
        xLoc = x;
        yLoc = y;
        velX = xVelocity;
        velY = yVelocity;
        mass = bodyMass;
        diameter = bodyDiameter;
        color = bodyColor;
        speed = bodySpeed;
    }
    public double getXPosition(){
        return xLoc;
    }
    public double getYPosition(){
        return yLoc;
    }
    public int getMass(){
        return mass;
    }
    public int getDiameter(){
        return diameter;
    }
    public boolean getDescVisible() {
        return visible;
    }
    public void setDescVisible(boolean b) {
        visible = b;
    }

    public void move()
    {
        xLoc += velX;
        yLoc += velY;
    }


    public boolean hitPlanet(int x, int y, double scale)
    {
        return (x>600+(getXPosition()-diameter-600)*scale && x<600+(getXPosition()+diameter-600)*scale &&
                y>400+(getYPosition()-diameter-400)*scale && y<400+(getYPosition()+diameter-400)*scale);
    }


    public void update(double StarX, double StarY, int StarMass)
    {
//        if (visible){
//            orbitDots[counter][0]=(int)(xLoc+.5);
//            orbitDots[counter][1]=(int)(yLoc+.5);
//            counter = (counter+1)%1000;
//        }
//        else{
//            orbitDots = new int[1000][2];
//            counter = 0;
//        }
        distance = Math.sqrt((StarX - xLoc)*(StarX - xLoc) + (StarY - yLoc)*(StarY - yLoc));
        initial = Math.min(distance,initial);
        max = Math.max(distance,max);

        acceleration = StarMass/distance/distance;

        dirX = (StarX-xLoc)/distance;
        dirY = (StarY-yLoc)/distance;

        velX += dirX * acceleration;
        velY += dirY * acceleration;
        move();
    }
    public void draw(Graphics g, double size)
    {
        g.setColor(color);
        g.fillOval((int)(650+(xLoc-diameter/2-650)*size), (int)(500+(yLoc-diameter/2-500)*size),
                (int)(diameter*size), (int)(diameter*size));
    }
//    public void dispDesc(Graphics g, double scale)
//    {
//        g.setColor(color);
//        for (int[] orbit : orbitDots)
//            g.drawLine(orbit[0],orbit[1],orbit[0],orbit[1]);
//        g.setFont(new Font("Arial", Font.PLAIN, 10));
//        g.setColor(Color.MAGENTA);
//
//        g.drawString((Math.round(distance*100.0)/100.0) * 1000000 + " km",
//                diameter+(int)(600+(xLoc-diameter/2-600)*scale), 16+(int)(400+(yLoc-diameter/2-400)*scale)+diameter);
//
//    }
}

