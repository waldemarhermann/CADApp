package model;

public class FigureProperties extends Figure {
    private static final long serialVersionUID = -7082201814855812246L;

    public FigureProperties(Double xpos, Double ypos, Double width, Double height, Color fillcolor, Color linecolor) {
        super(xpos, ypos, width, height, fillcolor, linecolor);
    }

    public FigureProperties(Tupel<Double> position, Tupel<Double> size, Tupel<Color> colors) {
        this((Double)position.x, (Double)position.y, (Double)size.x, (Double)size.y, (Color)colors.x, (Color)colors.y);
    }

    public FigureProperties(Double xpos, Double ypos, Double width, Double height) {
        this(xpos, ypos, width, height, Color.RED, Color.BLUE);
    }

    public FigureProperties(Tupel<Double> position, Tupel<Double> size) {
        this(position, size, new Tupel<>(Color.RED, Color.BLUE));
    }

    public FigureProperties() {
        this(Double.valueOf(0.0D), Double.valueOf(0.0D), Double.valueOf(100.0D), Double.valueOf(50.0D), Color.RED, Color.BLUE);
    }

    public double circumference() {
        return 0.0D;
    }

    public double area() {
        return 0.0D;
    }

    private double format(double x) {
        return (int)(10.0D * x) / 10.0D;
    }

    public String toString() {
        String str = String.valueOf(getForm()) + ": Position (" + format(((Double)(getPosition()).x).doubleValue()) + "," + format(((Double)(getPosition()).y).doubleValue()) + ")," +
                " Size (" + format(((Double)(getSize()).x).doubleValue()) + "," + format(((Double)(getSize()).y).doubleValue()) + ")";
        str = String.valueOf(str) + ", Colors " + getColors();
        return str;
    }

    public FigureProperties clone() {
        FigureProperties tmp = new FigureProperties(getPosition(), getSize(), getColors());
        tmp.setForm(getForm());
        return tmp;
    }

    public static void main(String[] args) {
        Tupel<Double> p = new Tupel<>(Double.valueOf(0.0D), Double.valueOf(0.0D));
        Tupel<Double> s = new Tupel<>(Double.valueOf(10.0D), Double.valueOf(10.0D));
        FigureProperties c1 = new FigureProperties(p, s);
        FigureProperties c2 = new FigureProperties(Double.valueOf(10.0D), Double.valueOf(200.0D), Double.valueOf(30.0D), Double.valueOf(15.0D), Color.WHITE, Color.BLACK);
        System.out.println();
        System.out.println("c1: " + c1);
        System.out.println();
        System.out.println("c2: " + c2);
        System.out.println();
        FigureProperties r2 = new FigureProperties(Double.valueOf(100.0D), Double.valueOf(200.0D), Double.valueOf(300.0D), Double.valueOf(150.0D), Color.GREEN, Color.BLACK);
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
        FigureProperties c3 = new FigureProperties();
        c3 = (FigureProperties)FigureDOM.readFromObjectStream("RectC2.ser");
        System.out.println("c3: " + c3);
        System.out.println();
        System.out.println("XMLEncoder:");
        FigureDOM.writeXMLEncoder(c2, "RectC2.xml");
        FigureProperties c4 = new FigureProperties();
        c4 = (FigureProperties)FigureDOM.readXMLDecoder("RectC2.xml");
        System.out.println("c4: " + c4);
        System.out.println();
        System.out.println("XMLDOM:");
        FigureDOM.writeXMLDOM(c2, "Figure.xml2");
        FigureProperties c5 = new FigureProperties();
        c5 = (FigureProperties)FigureDOM.readXMLDOM("Figure.xml2");
        System.out.println("c5: " + c5);
        System.out.println();
    }
}
