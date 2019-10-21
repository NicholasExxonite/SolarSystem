public class workshop1 {
    public static void main(String[] args) {
        SolarSystem system = new SolarSystem(600, 600);
        system.finishedDrawing();
        int angle = 0;
        while (angle <= 360){
//            system.drawSolarObject(0, 0, 100, "YELLOW");
//            system.drawSolarObject(200, angle, 30, "BLUE");
            angle ++;
            system.finishedDrawing();
            if(angle > 360){
                angle = 0;
            }
        }
    }
}
