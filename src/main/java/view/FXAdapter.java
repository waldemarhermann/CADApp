package view;

import javafx.scene.shape.Shape;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Paint;
import model.Figure;
import model.IFigure;
import model.Tupel;


public class FXAdapter {
    public static Shape getShape(IFigure iF) {
        Shape s = null;
        Figure f = (Figure)iF;
        String form = f.getForm();
        if (form.equals("Arc")) {
            s = getShape((model.Arc)f);
        } else if (form.equals("Triangle")) {
            s = getShape((model.Polygon)f, 3);
        } else if (form.equals("Polygon")) {
            s = getShape((model.Polygon)f, 6);
        } else if (form.equals("Line")) {
            s = getShape((model.Line)f);
        } else if (form.equals("Ellipse")) {
            s = getShape((model.Ellipse)f);
        } else if (form.equals("Rectangle")) {
            s = getShape((model.Rectangle)f);
        }
        return s;
    }

    public static void setShapeColors(Figure f, Shape s) {
        Tupel<model.Color> tc = f.getColors();
        s.setFill(getFXfromRGB(tc.x));
        s.setStroke(getFXfromRGB(tc.y));
        s.setStrokeWidth(3.0D);
    }

    public static Shape getShape(model.Arc f) {
        double a = ((Double)(f.getSize()).x).doubleValue() / 2.0D;
        double b = ((Double)(f.getSize()).y).doubleValue() / 2.0D;
        Arc arc = new Arc(((Double)(f.getPosition()).x).doubleValue() + a, ((Double)(f.getPosition()).y).doubleValue() + b, a, b,
                0.0D, Math.sqrt(((Double)(f.getSize()).x).doubleValue() * ((Double)(f.getSize()).x).doubleValue() + ((Double)(f.getSize()).y).doubleValue() * ((Double)(f.getSize()).y).doubleValue()));
        setShapeColors((Figure)f, (Shape)arc);
        return (Shape)arc;
    }

    public static Shape getShape(model.Ellipse f) {
        double a = ((Double)(f.getSize()).x).doubleValue() / 2.0D, b = ((Double)(f.getSize()).y).doubleValue() / 2.0D;
        Ellipse ellipse = new Ellipse(((Double)(f.getPosition()).x).doubleValue() + a, ((Double)(f.getPosition()).y).doubleValue() + b, a, b);
        setShapeColors((Figure)f, (Shape)ellipse);
        return (Shape)ellipse;
    }

    public static Shape getShape(model.Polygon f, int nEdges) {
        javafx.scene.shape.Polygon polygon;
        Shape s = null;
        double[] points = new double[2 * nEdges];
        if (nEdges == 3) {
            points[0] = ((Double)(f.getPosition()).x).doubleValue();
            points[1] = ((Double)(f.getPosition()).y).doubleValue();
            points[2] = ((Double)(f.getPosition()).x).doubleValue() + ((Double)(f.getSize()).x).doubleValue();
            points[3] = ((Double)(f.getPosition()).y).doubleValue();
            points[4] = ((Double)(f.getPosition()).x).doubleValue() + ((Double)(f.getSize()).x).doubleValue() / 2.0D;
            points[5] = ((Double)(f.getPosition()).y).doubleValue() + ((Double)(f.getSize()).y).doubleValue();
            polygon = new javafx.scene.shape.Polygon(points);
        } else if (nEdges == 6) {
            points[0] = ((Double)(f.getPosition()).x).doubleValue() + 0.3D * ((Double)(f.getSize()).x).doubleValue();
            points[1] = ((Double)(f.getPosition()).y).doubleValue() + 0.0D * ((Double)(f.getSize()).y).doubleValue();
            points[2] = ((Double)(f.getPosition()).x).doubleValue() + 0.7D * ((Double)(f.getSize()).x).doubleValue();
            points[3] = ((Double)(f.getPosition()).y).doubleValue() + 0.0D * ((Double)(f.getSize()).y).doubleValue();
            points[4] = ((Double)(f.getPosition()).x).doubleValue() + 1.0D * ((Double)(f.getSize()).x).doubleValue();
            points[5] = ((Double)(f.getPosition()).y).doubleValue() + 0.5D * ((Double)(f.getSize()).y).doubleValue();
            points[6] = ((Double)(f.getPosition()).x).doubleValue() + 0.7D * ((Double)(f.getSize()).x).doubleValue();
            points[7] = ((Double)(f.getPosition()).y).doubleValue() + 1.0D * ((Double)(f.getSize()).y).doubleValue();
            points[8] = ((Double)(f.getPosition()).x).doubleValue() + 0.3D * ((Double)(f.getSize()).x).doubleValue();
            points[9] = ((Double)(f.getPosition()).y).doubleValue() + 1.0D * ((Double)(f.getSize()).y).doubleValue();
            points[10] = ((Double)(f.getPosition()).x).doubleValue() + 0.0D * ((Double)(f.getSize()).x).doubleValue();
            points[11] = ((Double)(f.getPosition()).y).doubleValue() + 0.5D * ((Double)(f.getSize()).y).doubleValue();
            polygon = new Polygon(points);
        } else {
            polygon = new Polygon(new double[] { ((Double)(f.getPosition()).x).doubleValue(), ((Double)(f.getPosition()).y).doubleValue(), ((Double)(f.getSize()).x).doubleValue(), ((Double)(f.getSize()).y).doubleValue() });
        }
        setShapeColors((Figure)f, (Shape)polygon);
        return (Shape)polygon;
    }

    public static Shape getShape(model.Line f) {
        Line line = new Line(((Double)(f.getPosition()).x).doubleValue(), ((Double)(f.getPosition()).y).doubleValue(), (
                (Double)(f.getPosition()).x).doubleValue() + ((Double)(f.getSize()).x).doubleValue(), ((Double)(f.getPosition()).y).doubleValue() + ((Double)(f.getSize()).y).doubleValue());
        setShapeColors((Figure)f, (Shape)line);
        return (Shape)line;
    }

    public static Shape getShape(model.Rectangle r) {
        Rectangle rectangle = new Rectangle(((Double)(r.getPosition()).x).doubleValue(), ((Double)(r.getPosition()).y).doubleValue(), ((Double)(r.getSize()).x).doubleValue(), ((Double)(r.getSize()).y).doubleValue());
        setShapeColors((Figure)r, (Shape)rectangle);
        return (Shape)rectangle;
    }

    public static java.awt.Color setRGBfromAWT(java.awt.Color c) {
        return new java.awt.Color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
    }

    public static model.Color convertToModelColor(javafx.scene.paint.Color fxColor) {
        int red = (int) (fxColor.getRed() * 255);
        int green = (int) (fxColor.getGreen() * 255);
        int blue = (int) (fxColor.getBlue() * 255);
        // Optional: int alpha = (int) (fxColor.getOpacity() * 255);
        return new model.Color(red, green, blue); // Optional: fügen Sie alpha hinzu, falls benötigt
    }

    public static javafx.scene.paint.Color setRGBfromFX(model.Color c) {
        double r = c.getRed() / 255.0;
        double g = c.getGreen() / 255.0;
        double b = c.getBlue() / 255.0;
        double a = c.getAlpha() / 255.0;
        return new javafx.scene.paint.Color(r, g, b, a);
    }

    public static java.awt.Color getAWTfromRGB(model.Color c) {
        return new java.awt.Color(c.getRed(), c.getGreen(), c.getBlue());
    }

    public static javafx.scene.paint.Color getFXfromRGB(model.Color c) {
        return new javafx.scene.paint.Color(c.getRed() / 255.0, c.getGreen() / 255.0, c.getBlue() / 255.0, c.getAlpha() / 255.0);
    }

    public static java.awt.Color getRGBFX(model.Color c) {
        return new java.awt.Color((float)c.getRed(), (float)c.getGreen(), (float)c.getBlue());
    }

}
