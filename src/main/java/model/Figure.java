package model;

import java.io.Serializable;

public abstract class Figure implements IFigure, Serializable {
    private static final long serialVersionUID = -4913882936112037419L;

    private Tupel<Double> position = null;

    private Tupel<Double> size = null;

    private Tupel<Color> colors = null;

    private String form = null;

    public Figure(Double xpos, Double ypos, Double width, Double height, Color fillcolor, Color linecolor) {
        setPosition(xpos, ypos);
        setSize(width, height);
        setColors(fillcolor, linecolor);
        this.form = getClass().getSimpleName();
    }

    public Figure(Tupel<Double> position, Tupel<Double> size, Tupel<Color> colors) {
        setPosition((Double)position.x, (Double)position.y);
        setSize((Double)size.x, (Double)size.y);
        setColors((Color)colors.x, (Color)colors.y);
        this.form = getClass().getSimpleName();
    }

    public Figure(Double xpos, Double ypos, Double width, Double height) {
        this(xpos, ypos, width, height, Color.RED, Color.BLUE);
    }

    public Figure(Tupel<Double> position, Tupel<Double> size) {
        this(position, size, new Tupel<>(Color.RED, Color.BLUE));
    }

    public static Figure createInstanceByName(String pform) {
        Figure figure = null;
        String pname = Figure.class.getPackageName();
        String ppform = new String(pform);
        try {
            if (pform.equals("Triangle"))
                ppform = "Polygon";
            Class<?> clazz;
            if (!pname.equals("")) {
                clazz = Class.forName(pname + "." + ppform);
            } else {
                clazz = Class.forName(ppform);
            }
            Object obj = clazz.getConstructor().newInstance();
            if (obj instanceof Figure) {
                figure = (Figure) obj;
            }

            if (pform.equals("Triangle") && figure instanceof Polygon) {
                ((Polygon) figure).setNEdges(3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return figure;
    }


    public boolean isPointInside(double s, double t) {
        double w2 = ((Double)(getSize()).x).doubleValue() / 4.0D, h2 = ((Double)(getSize()).y).doubleValue() / 4.0D;
        return (s > ((Double)(getPosition()).x).doubleValue() + w2 && s < ((Double)(getPosition()).x).doubleValue() + 3.0D * w2 && t > ((Double)(getPosition()).y).doubleValue() + h2 &&
                t < ((Double)(getPosition()).y).doubleValue() + 3.0D * h2);
    }

    public void setPosition(Double xpos, Double ypos) {
        this.position = new Tupel<>(Double.valueOf(xpos.doubleValue()), Double.valueOf(ypos.doubleValue()));
    }

    public void setSize(Double width, Double height) {
        this.size = new Tupel<>(Double.valueOf(width.doubleValue()), Double.valueOf(height.doubleValue()));
    }

    public void setColors(Color fillcolor, Color linecolor) {
        this.colors = new Tupel<>(fillcolor.clone(), linecolor.clone());
    }

    public void setForm(String form) {
        this.form = new String(form);
    }

    public Tupel<Double> getPosition() {
        return this.position;
    }

    public Tupel<Double> getSize() {
        return this.size;
    }

    public Tupel<Color> getColors() {
        return this.colors;
    }

    public String getForm() {
        return this.form;
    }

    public double circumference() {
        return 2.0D * (((Double)(getSize()).x).doubleValue() + ((Double)(getSize()).y).doubleValue());
    }

    public double area() {
        return ((Double)(getSize()).x).doubleValue() * ((Double)(getSize()).y).doubleValue();
    }

    public int compareTo(IFigure o) {
        return (int)(area() - o.area());
    }

    public int compare(IFigure o1, IFigure o2) {
        return (int)(o1.circumference() - o2.circumference());
    }

    private double format(double x) {
        return (int)(10.0D * x) / 10.0D;
    }

    public String toString() {
        String str = String.valueOf(getForm()) + ": Position (" + format(((Double)(getPosition()).x).doubleValue()) + "," + format(((Double)(getPosition()).y).doubleValue()) + ")," +
                " Size (" + format(((Double)(getSize()).x).doubleValue()) + "," + format(((Double)(getSize()).y).doubleValue()) + ")";
        str = String.valueOf(str) + ", Colors " + getColors();
        str = String.valueOf(str) + ", Circumference = " + format(circumference()) + ", Area = " + format(area());
        return str;
    }

    public int hashCode() {
        int hc = this.form.hashCode();
        int hashMultiplier = 21;
        hc = hc * 21 + this.position.hashCode() + this.size.hashCode() + this.colors.hashCode();
        return hc;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Figure d = (Figure)obj;
        if (!this.form.equals(d.getForm()))
            return false;
        if (!this.position.equals(d.getPosition()))
            return false;
        if (!this.size.equals(d.getSize()))
            return false;
        if (!this.colors.equals(d.getColors()))
            return false;
        return true;
    }

    public static void main(String[] args) {}

    public Figure() {}

    public abstract Figure clone();
}
