package model;

public class Edges {
    private static double width = 1100.0D;

    private static double height = 700.0D;

    public double x0;

    public double x1;

    public double y0;

    public double y1;

    public boolean regulizeEdges() {
        if (this.x0 < 0.0D)
            this.x0 = 0.0D;
        if (this.y0 < 0.0D)
            this.y0 = 0.0D;
        if (this.x1 < 0.0D)
            this.x1 = 0.0D;
        if (this.y1 < 0.0D)
            this.y1 = 0.0D;
        if (this.x0 > width)
            this.x0 = width;
        if (this.y0 > height)
            this.y0 = height;
        if (this.x1 > width)
            this.x1 = width;
        if (this.y1 > height)
            this.y1 = height;
        if (this.x1 < this.x0) {
            double d = this.x0;
            this.x0 = this.x1;
            this.x1 = d;
        }
        if (this.y1 < this.y0) {
            double d = this.y0;
            this.y0 = this.y1;
            this.y1 = d;
        }
        if ((this.x1 - this.x0 > 0.0D && this.y1 - this.y0 > 5.0D) || (this.x1 - this.x0 > 5.0D && this.y1 - this.y0 > 0.0D))
            return true;
        return false;
    }

    public static boolean regulizeEdges(Tupel<Double> P0, Tupel<Double> P1) {
        double x0 = ((Double)P0.x).doubleValue(), x1 = ((Double)P1.x).doubleValue(), y0 = ((Double)P0.y).doubleValue(), y1 = ((Double)P1.y).doubleValue();
        if (x0 < 0.0D)
            x0 = 0.0D;
        if (y0 < 0.0D)
            y0 = 0.0D;
        if (x1 < 0.0D)
            x1 = 0.0D;
        if (y1 < 0.0D)
            y1 = 0.0D;
        if (x0 > width)
            x0 = width;
        if (y0 > height)
            y0 = height;
        if (x1 > width)
            x1 = width;
        if (y1 > height)
            y1 = height;
        if (x1 < x0) {
            double d = x0;
            x0 = x1;
            x1 = d;
        }
        if (y1 < y0) {
            double d = y0;
            y0 = y1;
            y1 = d;
        }
        P0.x = Double.valueOf(x0);
        P0.y = Double.valueOf(y0);
        P1.x = Double.valueOf(x1);
        P1.y = Double.valueOf(y1);
        if ((x1 - x0 > 0.0D && y1 - y0 > 5.0D) || (x1 - x0 > 5.0D && y1 - y0 > 0.0D))
            return true;
        return false;
    }
}
