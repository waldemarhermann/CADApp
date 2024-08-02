package viewmodel;
import model.*;
import java.io.File;


public class IOFileViewModel extends ViewModel {

    private MainViewModel parent;
    private Drawing zeichnung;

    public IOFileViewModel(ViewModel parent) {
        this.parent = (MainViewModel)parent;
    }

    public void loadDrawing(File file) {
        String path = file.getPath();
        if (path.endsWith(".ser")) {
            this.zeichnung = DrawingDOM.readFromObjectStream(path);
        } else if (path.endsWith(".xml")) {
            this.zeichnung = DrawingDOM.readXMLDecoder(path);
        } else if (path.endsWith(".xml2")) {
            this.zeichnung = DrawingDOM.readXMLDOM(path);
        }
        this.parent.setDrawing(this.zeichnung);
        this.parent.updateDrawing();
    }

    public void saveDrawing(File file) {
        this.zeichnung = this.parent.getDrawing();
        String path = file.getPath();
        if (path.endsWith(".ser")) {
            DrawingDOM.writeToObjectStream(this.zeichnung, path);
        } else if (path.endsWith(".xml")) {
            DrawingDOM.writeXMLEncoder(this.zeichnung, path);
        } else if (path.endsWith(".xml2")) {
            DrawingDOM.writeXMLDOM(this.zeichnung, path);
        }
    }
}
