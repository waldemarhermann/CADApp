package model;

import java.util.Comparator;

public interface IFigure extends Comparator<IFigure>, Comparable<IFigure> {
    double circumference();

    double area();

    int compareTo(IFigure paramIFigure);

    int compare(IFigure paramIFigure1, IFigure paramIFigure2);
}
