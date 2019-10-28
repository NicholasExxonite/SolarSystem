import java.awt.*;
import java.util.ArrayList;

public class Planet {
    private double distance;
    private double angle;
    private double diameter;
    private int velocity;
    private Color col;
    private int x, y;

    Planet(int distance, int diameter, int velocity, int angle, String col){
        Color colour = this.getColourFromString(col);
        this.diameter = diameter;
        this.velocity = velocity;
        this.distance = distance;
        this. angle = angle;
        this.col = colour;
        this.x = x;
        this.y = y;


    }


    public void setAngle(double a){
        this.angle = a;
    }

    public void move(){

        this.setAngle(angle + velocity);

    }

    public Color getCol() {
        return col;
    }

    public double getAngle() {
        return angle;
    }

    public double getDiameter() {
        return diameter;
    }

    public double getDistance() {
        return distance;
    }

    public int getVelocity() {
        return velocity;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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
}
