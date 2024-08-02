package model;

public class Polygon extends Figure {
    private static final long serialVersionUID = -7082201814855812246L;

    private int nEdges = 6;

    public Polygon(Double xpos, Double ypos, Double width, Double height, Color fillcolor, Color linecolor) {
        super(xpos, ypos, width, height, fillcolor, linecolor);
    }

    public Polygon(Tupel<Double> position, Tupel<Double> size, Tupel<Color> colors) {
        this((Double)position.x, (Double)position.y, (Double)size.x, (Double)size.y, (Color)colors.x, (Color)colors.y);
    }

    public Polygon(Double xpos, Double ypos, Double width, Double height) {
        this(xpos, ypos, width, height, Color.RED, Color.BLUE);
    }

    public Polygon(Tupel<Double> position, Tupel<Double> size) {
        this(position, size, new Tupel<>(Color.RED, Color.BLUE));
    }

    public void setNEdges(int nEdges) {
        this.nEdges = nEdges;
    }

    public int getNEdges() {
        return this.nEdges;
    }

    public double circumference() {
        if (this.nEdges == 3)
            return ((Double)(getSize()).x).doubleValue() + 2.0D * Math.sqrt(((Double)(getSize()).x).doubleValue() * ((Double)(getSize()).x).doubleValue() / 4.0D + ((Double)(getSize()).y).doubleValue() * ((Double)(getSize()).y).doubleValue());
        return 2.0D * (((Double)(getSize()).x).doubleValue() + ((Double)(getSize()).y).doubleValue());
    }

    public double area() {
        return ((Double)(getSize()).x).doubleValue() * ((Double)(getSize()).y).doubleValue() / 2.0D;
    }

    public Polygon clone() {
        Polygon p = new Polygon(getPosition(), getSize(), getColors());
        p.setForm(getForm());
        p.setNEdges(getNEdges());
        return p;
    }

    public static void main(String[] args) {
        Tupel<Double> p = new Tupel<>(Double.valueOf(0.0D), Double.valueOf(0.0D));
        Tupel<Double> s = new Tupel<>(Double.valueOf(10.0D), Double.valueOf(10.0D));
        Polygon c1 = new Polygon(p, s);
        Polygon c2 = new Polygon(Double.valueOf(10.0D), Double.valueOf(200.0D), Double.valueOf(30.0D), Double.valueOf(15.0D), Color.WHITE, Color.BLACK);
        System.out.println();
        System.out.println("c1: " + c1);
        System.out.println();
        System.out.println("c2: " + c2);
        System.out.println();
        Polygon r2 = new Polygon(Double.valueOf(100.0D), Double.valueOf(200.0D), Double.valueOf(300.0D), Double.valueOf(150.0D), Color.GREEN, Color.BLACK);
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
        Polygon c3 = new Polygon();
        c3 = (Polygon)FigureDOM.readFromObjectStream("RectC2.ser");
        System.out.println("c3: " + c3);
        System.out.println();
        System.out.println("XMLEncoder:");
        FigureDOM.writeXMLEncoder(c2, "RectC2.xml");
        Polygon c4 = new Polygon();
        c4 = (Polygon)FigureDOM.readXMLDecoder("RectC2.xml");
        System.out.println("c4: " + c4);
        System.out.println();
        System.out.println("XMLDOM:");
        FigureDOM.writeXMLDOM(c2, "Figure.xml2");
        Polygon c5 = new Polygon();
        c5 = (Polygon)FigureDOM.readXMLDOM("Figure.xml2");
        System.out.println("c5: " + c5);
        System.out.println();
    }

    public Polygon() {}
}
