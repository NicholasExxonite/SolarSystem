public class coursework {
    public static void main(String[] args) {
        System sys = new System(800, 800);
        sys.drawSun(0, 0, 200, "YELLOW");
        Planet p = new Planet(150, 50, 2, 30, "BLUE");
        Planet p1 = new Planet(200, 80, 1, 60, "GREEN");
        sys.addPlanet(p);
        sys.addPlanet(p1);
//        sys.animate(p);

        sys.animate(p);
    }
}
