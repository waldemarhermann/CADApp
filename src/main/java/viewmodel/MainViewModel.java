package viewmodel;
import model.*;
import view.IView;


public class MainViewModel extends ViewModel {

    private Drawing zeichnung;
    private Figure currentFigure;
    private int currentID;
    private String chosenForm;
    private Tupel<Color> chosenColors;
    private IOFigureViewModel vmIOFigure;
    private CADViewModel vmCAD;
    private IOFileViewModel vmIOFile;

    public MainViewModel() {
        this.zeichnung = new Drawing();
        this.currentFigure = new Rectangle(50.0, 50.0, 200.0, 100.0, Color.RED, Color.YELLOW);
        this.currentID = 0;
        this.chosenForm = "Arc";
        this.chosenColors = new Tupel<>(Color.BLUE, Color.RED);
        this.vmCAD = new CADViewModel(this);
        this.vmIOFile = new IOFileViewModel(this);
        this.vmIOFigure = new IOFigureViewModel(this);
    }

    public ViewModel getIOFigureVM() {
        return this.vmIOFigure;
    }

    public ViewModel getIOFileVM() {
        return this.vmIOFile;
    }

    public ViewModel getCADVM() {
        return this.vmCAD;
    }

    public Drawing getDrawing() {
        return this.zeichnung;
    }

    public void setDrawing(Drawing zeichnung) {
        this.zeichnung = zeichnung;
    }

    public Figure getCurrentFigure() {
        return this.currentFigure;
    }

    public int getCurrentID() {
        return this.currentID;
    }

    public void updateCurrentID(int id) {
        this.currentID = id;
        this.currentFigure = (Figure)getDrawing().get(this.currentID);
        updateDrawing();
    }

    public void updateCurrentFigure(Figure figure, int id) {
        if (figure != null) {
            boolean create = (id == -1);
            String form = figure.getForm();
            if (create || !form.equals(this.currentFigure.getForm())) {
                this.currentFigure = Figure.createInstanceByName(form);
            }
            this.currentFigure.setForm(figure.getForm());
            this.currentFigure.setPosition(figure.getPosition().x, figure.getPosition().y);
            this.currentFigure.setSize(figure.getSize().x, figure.getSize().y);
            this.currentFigure.setColors(figure.getColors().x, figure.getColors().y);
            if (create) {
                addFigure(this.currentFigure);
            } else {
                setFigureInDrawing(id, this.currentFigure);
            }
            updateDrawing();
        }
    }

    public void createNewFigure(Edges edge) {
        String form = getChosenForm();
        if (edge.regulizeEdges()) {
            Tupel<Color> chosenColors = getChosenColors();
            this.currentFigure = Figure.createInstanceByName(form);
            this.currentFigure.setColors(chosenColors.x, chosenColors.y);
            this.currentFigure.setPosition(edge.x0, edge.y0);
            this.currentFigure.setSize(edge.x1 - edge.x0, edge.y1 - edge.y0);
            this.currentFigure.setForm(form);
            addFigure(this.currentFigure);
            updateDrawing();
        }
    }

    public Tupel<Color> getChosenColors() {
        return this.chosenColors;
    }

    public void setChosenColors(Color c, String label) {
        if (label.contains("FillC")) {
            this.chosenColors.x = c;
        } else {
            this.chosenColors.y = c;
        }
    }

    public String getChosenForm() {
        return this.chosenForm;
    }

    public void setChosenForm(String chosenForm) {
        if (chosenForm != null)
            this.chosenForm = chosenForm;
    }

    public Figure getFigureFromDrawing(int id) {
        return (Figure)this.zeichnung.get(id);
    }

    public int getIDFromDrawing(IFigure figure) {
        return this.zeichnung.getID(figure);
    }

    public void setFigureInDrawing(int id, IFigure figure) {
        this.zeichnung.set(figure, id);
    }

    public void addFigure(Figure f) {
        if (f != null) {
            this.currentFigure = f;
            this.currentID = this.zeichnung.getCount();
            this.zeichnung.add((IFigure)f);
            updateDrawing();
        }
    }

    public void removeFigure(int id) {
        if (id >= 0 && id < this.zeichnung.getCount() && this.zeichnung.remove(id)) {
            if (id == currentID) {
                currentFigure = null;
                currentID = -1;
            }
            updateDrawing();
        }
    }

    public void updateDrawing() {
        if (this.view != null)
            this.view.update();
        System.out.println();
        System.out.println(this.zeichnung);
    }
}
