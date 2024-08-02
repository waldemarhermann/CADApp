package model;

public class Line extends Figure {
    private static final long serialVersionUID = -70234814855812246L;

    public Line(Double xpos, Double ypos, Double width, Double height, Color fillcolor, Color linecolor) {
        super(xpos, ypos, width, height, fillcolor, linecolor);
    }

    public Line(Tupel<Double> position, Tupel<Double> size, Tupel<Color> colors) {
        this((Double)position.x, (Double)position.y, (Double)size.x, (Double)size.y, (Color)colors.x, (Color)colors.y);
    }

    public Line(Double xpos, Double ypos, Double width, Double height) {
        this(xpos, ypos, width, height, Color.RED, Color.BLUE);
    }

    public Line(Tupel<Double> position, Tupel<Double> size) {
        this(position, size, new Tupel<>(Color.RED, Color.BLUE));
    }

    public Line() {}

    public double circumference() {
        return Math.sqrt(((Double)(getSize()).x).doubleValue() * ((Double)(getSize()).x).doubleValue() + ((Double)(getSize()).y).doubleValue() * ((Double)(getSize()).y).doubleValue());
    }

    public double area() {
        return 0.0D;
    }

    public Line clone() {
        Line tmp = new Line(getPosition(), getSize(), getColors());
        tmp.setForm(getForm());
        return tmp;
    }

    public static void main(String[] args) {
        Tupel<Double> p = new Tupel<>(Double.valueOf(0.0D), Double.valueOf(0.0D));
        Tupel<Double> s = new Tupel<>(Double.valueOf(10.0D), Double.valueOf(10.0D));
        Line c1 = new Line(p, s);
        Line c2 = new Line(Double.valueOf(10.0D), Double.valueOf(200.0D), Double.valueOf(30.0D), Double.valueOf(40.0D), Color.WHITE, Color.BLACK);
        System.out.println();
        System.out.println("c1: " + c1);
        System.out.println();
        System.out.println("c2: " + c2);
        System.out.println();
        Line r2 = new Line(Double.valueOf(100.0D), Double.valueOf(200.0D), Double.valueOf(300.0D), Double.valueOf(150.0D), Color.GREEN, Color.BLACK);
        System.out.println("r2: " + r2);
        System.out.println();
        c1 = c2.clone();
        System.out.println("c1: " + c1);
        System.out.println();
        System.out.println("c2: " + c2);
        System.out.println();
        System.out.println("c1 == c2: " + c1.equals(c2));
        System.out.println("c1 == r2: " + c1.equals(r2));
        System.out.println();
        System.out.println("c2: " + c2);
        System.out.println("Serializer:");
        FigureDOM.writeToObjectStream(c2, "RectC2.ser");
        Line c3 = new Line();
        c3 = (Line)FigureDOM.readFromObjectStream("RectC2.ser");
        System.out.println("c3: " + c3);
        System.out.println();
        System.out.println("XMLEncoder:");
        FigureDOM.writeXMLEncoder(c2, "RectC2.xml");
        Line c4 = new Line();
        c4 = (Line)FigureDOM.readXMLDecoder("RectC2.xml");
        System.out.println("c4: " + c4);
        System.out.println();
        System.out.println("XMLDOM:");
        FigureDOM.writeXMLDOM(c2, "Figure.xml2");
        Line c5 = new Line();
        c5 = (Line)FigureDOM.readXMLDOM("Figure.xml2");
        System.out.println("c5: " + c5);
        System.out.println();
    }
}
