package viewmodel;
import model.*;


public class IOFigureViewModel extends ViewModel {

    private MainViewModel parent;
    public static final String[] forms = new String[] { "Triangle", "Line", "Arc", "Polygon", "Rectangle", "Ellipse" };

    public IOFigureViewModel(ViewModel parent) {
        this.parent = (MainViewModel)parent;
    }

    public Figure getFigure() {
        return this.parent.getCurrentFigure();
    }

    public int getID() {
        return this.parent.getCurrentID();
    }

    public void addFigure(int id, Figure figure) {
        System.out.println("Figure to be Added with ID = " + id);
        System.out.println("Figure = " + figure);
        this.parent.updateCurrentFigure(figure, -1);
    }

    public void changedID(int id) {
        System.out.println("ID Updated to: " + id);
        this.parent.updateCurrentID(id);
    }

    public void changedForm(String form) {
        System.out.println("Selected Form:  " + form);
        this.parent.setChosenForm(form);
    }

    public void removeFigure(int id, Figure figure) {
        System.out.println("ID of Figure to be Removed = " + id);
        System.out.println("Figure = " + figure);
        this.parent.removeFigure(id);
    }

    public void changeFigure(int id, Figure figure) {
        this.parent.updateCurrentFigure(figure, id);
        System.out.println("ID of Modified Figure = " + id);
        System.out.println("Figure = " + figure);
        System.out.println("Figure = " + this.parent.getCurrentFigure());
    }

    public void changedColor(Color color, String label) {
        System.out.println(String.valueOf(label) + " Color selected " + color);
        this.parent.setChosenColors(color, label);
    }
}
