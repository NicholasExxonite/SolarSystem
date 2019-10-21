public class coursework {
    public static void main(String[] args) {
        SolarSystem system = new SolarSystem(800, 800);
        system.drawSun(0, 0, 200, "YELLOW");
        system.drawPlanet(100,0, 50, 5, "BLUE");
        //system.drawSolarObject(0, 0, 100, "BLUE");
        system.finishedDrawing();
    }
}
