package model;

public class Ellipse extends Figure {
    private static final long serialVersionUID = 2226556801814491411L;

    public Ellipse(Double xpos, Double ypos, Double width, Double height, Color fillcolor, Color linecolor) {
        super(xpos, ypos, width, height, fillcolor, linecolor);
    }

    public Ellipse(Tupel<Double> position, Tupel<Double> size, Tupel<Color> colors) {
        this((Double)position.x, (Double)position.y, (Double)size.x, (Double)size.y, (Color)colors.x, (Color)colors.y);
    }

    public Ellipse(Double xpos, Double ypos, Double width, Double height) {
        this(xpos, ypos, width, height, Color.GREEN, Color.BLACK);
    }

    public Ellipse(Tupel<Double> position, Tupel<Double> size) {
        this(position, size, new Tupel<>(Color.GREEN, Color.BLACK));
    }

    public Ellipse() {}

    public double circumference() {
        return Math.PI * (((Double)(getSize()).x).doubleValue() + ((Double)(getSize()).y).doubleValue()) / 2.0D;
    }

    public double area() {
        return ((Double)(getSize()).x).doubleValue() * ((Double)(getSize()).y).doubleValue() * Math.PI / 4.0D;
    }

    public Ellipse clone() {
        Ellipse tmp = new Ellipse(getPosition(), getSize(), getColors());
        tmp.setForm(getForm());
        return tmp;
    }

    public static void main(String[] args) {
        Tupel<Double> p = new Tupel<>(Double.valueOf(0.0D), Double.valueOf(0.0D));
        Tupel<Double> s = new Tupel<>(Double.valueOf(10.0D), Double.valueOf(10.0D));
        Ellipse c1 = new Ellipse(p, s);
        Ellipse c2 = new Ellipse(Double.valueOf(10.0D), Double.valueOf(200.0D), Double.valueOf(30.0D), Double.valueOf(15.0D), Color.WHITE, Color.BLACK);
        System.out.println();
        System.out.println("c1: " + c1);
        System.out.println();
        System.out.println("c2: " + c2);
        System.out.println();
        Ellipse r2 = new Ellipse(Double.valueOf(100.0D), Double.valueOf(200.0D), Double.valueOf(300.0D), Double.valueOf(150.0D), Color.GREEN, Color.BLACK);
        System.out.println("r2: " + r2);
        System.out.println();
        c1 = c2.clone();
        System.out.println("c1: " + c1);
        System.out.println();
        System.out.println("c2: " + c2);
        System.out.println();
        System.out.println("c1 == c2: " + c1.equals(c2));
        System.out.println("c1 == r2: " + c1.equals(r2));
    }
}
