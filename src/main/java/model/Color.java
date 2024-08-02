package model;

import java.io.Serializable;

public class Color implements Serializable {
    private static final long serialVersionUID = -4913885936112037419L;

    public static final Color WHITE = new Color(255, 255, 255);

    public static final Color LIGHT_GRAY = new Color(192, 192, 192);

    public static final Color GRAY = new Color(128, 128, 128);

    public static final Color DARK_GRAY = new Color(64, 64, 64);

    public static final Color BLACK = new Color(0, 0, 0);

    public static final Color RED = new Color(255, 0, 0);

    public static final Color PINK = new Color(255, 175, 175);

    public static final Color ORANGE = new Color(255, 200, 0);

    public static final Color YELLOW = new Color(255, 255, 0);

    public static final Color GREEN = new Color(0, 255, 0);

    public static final Color MAGENTA = new Color(255, 0, 255);

    public static final Color CYAN = new Color(0, 255, 255);

    public static final Color BLUE = new Color(0, 0, 255);

    public int r = 255, g = 255, b = 255, a = 255;

    public Color(int r, int g, int b, int a) {
        setColor(r, g, b, a);
    }

    public Color(int r, int g, int b) {
        setColor(r, g, b);
    }

    public void setColor(int r, int g, int b) {
        setColor(r, g, b, 255);
    }

    public void setColor(int r, int g, int b, int a) {
        if (r >= 0 || r < 256)
            this.r = r;
        if (g >= 0 || g < 256)
            this.g = g;
        if (b >= 0 || b < 256)
            this.b = b;
        if (a >= 0 || a < 256)
            this.a = a;
    }

    public int getRed() {
        return this.r;
    }

    public int getGreen() {
        return this.g;
    }

    public int getBlue() {
        return this.b;
    }

    public int getAlpha() {
        return this.a;
    }

    public String toString() {
        return "(" + this.r + "," + this.g + "," + this.b + ")";
    }

    public int hashCode() {
        int hc = 117;
        int hashMultiplier = 61;
        hc += 61 * (this.r + 61 * (this.g + 61 * (this.b + 61 * this.a)));
        return hc;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Color d = (Color)obj;
        return (d.getRed() == this.r && d.getGreen() == this.g && d.getBlue() == this.b && d.getAlpha() == this.a);
    }

    public Color clone() {
        return new Color(this.r, this.g, this.b, this.a);
    }

    public Color() {}
}
