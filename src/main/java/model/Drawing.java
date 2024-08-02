package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Drawing implements Serializable {
    private ArrayList<IFigure> shapes = null;

    public Drawing(int size) {
        if (size > 3) {
            this.shapes = new ArrayList<>(size);
        } else {
            this.shapes = new ArrayList<>(3);
        }
    }

    public Drawing() {
        this(3);
    }

    public ArrayList<IFigure> getShapes() {
        return (ArrayList<IFigure>)this.shapes.clone();
    }

    public int getCount() {
        return this.shapes.size();
    }

    public void set(IFigure shape, int k) {
        if (k >= 0 && k < getCount()) {
            Object cp = ((Figure)shape).clone();
            this.shapes.set(k, (IFigure)cp);
        }
    }

    public IFigure get(int k) {
        if (k >= 0 && k < getCount())
            return this.shapes.get(k);
        return null;
    }

    public int getID(IFigure f) {
        if (f != null)
            for (int i = 0; i < getCount(); i++) {
                if (((Figure)this.shapes.get(i)).equals(f))
                    return i;
            }
        return -1;
    }

    public int isPointInside(double x, double y) {
        int id = -1;
        for (int i = 0; i < getCount(); i++) {
            if (((Figure)this.shapes.get(i)).isPointInside(x, y)) {
                id = i;
                break;
            }
        }
        return id;
    }

    public int add(IFigure shape) {
        Object cp = ((Figure)shape).clone();
        this.shapes.add((IFigure)cp);
        return getCount();
    }

    public boolean remove(int k) {
        return (this.shapes.remove(k) != null);
    }

    public void remove() {
        this.shapes.clear();
    }

    public void sort(Comparator<? super IFigure> c) {
        Object[] oh = this.shapes.toArray();
        IFigure[] sh = new IFigure[oh.length];
        int k = 0;
        byte b;
        int i;
        Object[] arrayOfObject1;
        for (i = (arrayOfObject1 = oh).length, b = 0; b < i; ) {
            Object o = arrayOfObject1[b];
            sh[k++] = (IFigure)o;
            b++;
        }
        if (c == null) {
            Arrays.sort((Object[])sh);
        } else {
            Arrays.sort(sh, c);
        }
        remove();
        IFigure[] arrayOfIFigure1;
        for (i = (arrayOfIFigure1 = sh).length, b = 0; b < i; ) {
            IFigure o = arrayOfIFigure1[b];
            add(o);
            b++;
        }
    }

    public String toString() {
        String str = "Figuren: " + getCount() + " von " + this.shapes.size() + "\n\\n";
        for (IFigure o : this.shapes) {
            if (o != null)
                str = String.valueOf(str) + o + "\n\n";
        }
        return str;
    }

    public int hashCode() {
        int hashMultiplier = 11;
        int hc = 123 + getCount() * 11;
        for (IFigure o : this.shapes)
            hc = 11 * hc + o.hashCode();
        return hc;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Drawing d = (Drawing)obj;
        if (d.shapes.size() != this.shapes.size())
            return false;
        for (int k = 0; k < this.shapes.size(); k++) {
            if (this.shapes.get(k) != null) {
                if (this.shapes.get(k) == d.shapes.get(k))
                    return false;
                if (!((IFigure)this.shapes.get(k)).equals(d.shapes.get(k)))
                    return false;
            }
        }
        return true;
    }

    public Drawing clone() {
        Drawing d = new Drawing(this.shapes.size());
        if (getCount() > 0)
            for (int k = 0; k < this.shapes.size(); k++) {
                if (this.shapes.get(k) != null) {
                    Figure figure = (Figure)this.shapes.get(k);
                    Figure fig = Figure.createInstanceByName(figure.getForm());
                    if (figure.getForm().equals("Triangle"))
                        ((Polygon)fig).setNEdges(3);
                    fig.setForm(figure.getForm());
                    fig.setPosition((Double)(figure.getPosition()).x, (Double)(figure.getPosition()).y);
                    fig.setSize((Double)(figure.getSize()).x, (Double)(figure.getSize()).y);
                    fig.setColors((Color)(figure.getColors()).x, (Color)(figure.getColors()).y);
                    d.shapes.add(fig);
                }
            }
        return d;
    }

    public static void main(String[] args) {
        Rectangle r = new Rectangle(Double.valueOf(50.0D), Double.valueOf(20.0D), Double.valueOf(100.0D), Double.valueOf(50.0D));
        Ellipse e = new Ellipse(Double.valueOf(150.0D), Double.valueOf(200.0D), Double.valueOf(50.0D), Double.valueOf(200.0D));
        Rectangle r1 = new Rectangle(Double.valueOf(50.0D), Double.valueOf(20.0D), Double.valueOf(100.0D), Double.valueOf(80.0D));
        Ellipse e1 = new Ellipse(Double.valueOf(150.0D), Double.valueOf(200.0D), Double.valueOf(90.0D), Double.valueOf(200.0D));
        Drawing c1 = new Drawing(3);
        c1.add(r);
        c1.add(e);
        Drawing c2 = new Drawing(5);
        c2.add(r);
        c2.add(e1);
        c2.add(r1);
        c2.add(e);
        System.out.println();
        System.out.println("c1: " + c1);
        System.out.println();
        System.out.println("c2: " + c2);
        System.out.println();
        System.out.println("c1 == c2: " + c1.equals(c2));
        c1 = c2.clone();
        System.out.println("c1 == c2: " + c1.equals(c2));
        ArrayList<IFigure> list = c2.getShapes();
        System.out.println();
        System.out.println("list: " + list);
        list.remove(2);
        System.out.println();
        System.out.println("c2: " + c2);
        c1.sort(null);
        c1.sort((o1, o2) -> (int)(o1.area() - o2.area()));
        System.out.println("c1: " + c1);
        System.out.println("\n");
        c1.sort(null);
        System.out.println("c1: " + c1);
        System.out.println("\n");
    }
}
