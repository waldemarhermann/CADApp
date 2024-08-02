package model;

import java.awt.Color;
import java.io.Serializable;

public class Tupel<T> implements Serializable {
    public T x;

    public T y;

    public Tupel(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public Tupel() {}

    public String toString() {
        String str = "(" + this.x + "," + this.y + ")";
        return str;
    }

    public int hashCode() {
        int hc = 22;
        int hashMultiplier = 21;
        hc = hc * 21 + this.x.hashCode() + this.y.hashCode();
        return hc;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tupel<T> d = (Tupel<T>)obj;
        if (this.x == d.x || this.y == d.y)
            return false;
        if (!this.x.equals(d.x) || !this.y.equals(d.y))
            return false;
        return true;
    }

    public Tupel<T> clone() {
        return new Tupel(this.x, this.y);
    }

    public static void main(String[] args) {
        Tupel<Integer> t1 = new Tupel<>(Integer.valueOf(100), Integer.valueOf(50));
        int a = ((Integer)t1.x).intValue() * ((Integer)t1.y).intValue();
        System.out.println("t1: " + t1 + "  a = " + a);
        System.out.println();
        Tupel<Double> t2 = new Tupel<>(Double.valueOf(100.0D), Double.valueOf(50.0D));
        double a2 = ((Double)t2.x).doubleValue() * ((Double)t2.y).doubleValue();
        System.out.println("t2: " + t2 + "  a2 = " + a2);
        System.out.println();
        Tupel<Color> t3 = new Tupel<>(Color.BLUE, Color.RED);
        System.out.println("t3: " + t3);
        System.out.println();
    }
}
