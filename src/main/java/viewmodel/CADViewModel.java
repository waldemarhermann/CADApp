package viewmodel;
import model.*;
import view.*;

/**
 * Klasse CADViewModel
 */
public class CADViewModel extends ViewModel {

    private MainViewModel parent;
    private Edges edge;

    public CADViewModel(ViewModel parent) {
        this.parent = (MainViewModel)parent;
    }

    public Drawing getDrawing() {
        return this.parent.getDrawing();
    }

    public void startFigure(double x, double y) {
        this.edge = new Edges();
        this.edge.x0 = x;
        this.edge.y0 = y;
    }

    public void finishFigure(double x, double y) {
        this.edge.x1 = x;
        this.edge.y1 = y;
        this.parent.createNewFigure(this.edge);
    }

    public void chooseFigure(double x, double y) {
        Drawing currentDrawing = getDrawing();
        int id = currentDrawing.isPointInside(x, y);
        System.out.println("Mouse clicked at coordinates (" + x + ", " + y + ") with id = " + id);
        if (id != -1) {
            this.parent.updateCurrentID(id);
        }
    }
}
