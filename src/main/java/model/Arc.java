package model;

public class Arc extends Figure {
    private static final long serialVersionUID = -7082201814855812246L;

    public Arc(Double xpos, Double ypos, Double width, Double height, Color fillcolor, Color linecolor) {
        super(xpos, ypos, width, height, fillcolor, linecolor);
    }

    public Arc(Tupel<Double> position, Tupel<Double> size, Tupel<Color> colors) {
        this((Double)position.x, (Double)position.y, (Double)size.x, (Double)size.y, (Color)colors.x, (Color)colors.y);
    }

    public Arc(Double xpos, Double ypos, Double width, Double height) {
        this(xpos, ypos, width, height, Color.RED, Color.BLUE);
    }

    public Arc(Tupel<Double> position, Tupel<Double> size) {
        this(position, size, new Tupel<>(Color.RED, Color.BLUE));
    }

    public Arc() {}

    public double circumference() {
        return 2.0D * (((Double)(getSize()).x).doubleValue() + ((Double)(getSize()).y).doubleValue());
    }

    public double area() {
        return ((Double)(getSize()).x).doubleValue() * ((Double)(getSize()).y).doubleValue();
    }

    public Arc clone() {
        Arc tmp = new Arc(getPosition(), getSize(), getColors());
        tmp.setForm(getForm());
        return tmp;
    }

    public static void main(String[] args) {
        Tupel<Double> p = new Tupel<>(Double.valueOf(0.0D), Double.valueOf(0.0D));
        Tupel<Double> s = new Tupel<>(Double.valueOf(10.0D), Double.valueOf(10.0D));
        Arc c1 = new Arc(p, s);
        Arc c2 = new Arc(Double.valueOf(10.0D), Double.valueOf(200.0D), Double.valueOf(30.0D), Double.valueOf(15.0D), Color.WHITE, Color.BLACK);
        System.out.println();
        System.out.println("c1: " + c1);
        System.out.println();
        System.out.println("c2: " + c2);
        System.out.println();
        Arc r2 = new Arc(Double.valueOf(100.0D), Double.valueOf(200.0D), Double.valueOf(300.0D), Double.valueOf(150.0D), Color.GREEN, Color.BLACK);
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
        Arc c3 = new Arc();
        c3 = (Arc)FigureDOM.readFromObjectStream("RectC2.ser");
        System.out.println("c3: " + c3);
        System.out.println();
        System.out.println("XMLEncoder:");
        FigureDOM.writeXMLEncoder(c2, "RectC2.xml");
        Arc c4 = new Arc();
        c4 = (Arc)FigureDOM.readXMLDecoder("RectC2.xml");
        System.out.println("c4: " + c4);
        System.out.println();
        System.out.println("XMLDOM:");
        FigureDOM.writeXMLDOM(c2, "Figure.xml2");
        Arc c5 = new Arc();
        c5 = (Arc)FigureDOM.readXMLDOM("Figure.xml2");
        System.out.println("c5: " + c5);
        System.out.println();
    }
}
