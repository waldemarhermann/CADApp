package view;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import viewmodel.IOFileViewModel;
import viewmodel.ViewModel;



public class IOFileView extends BorderPane implements IView {

    private IOFileViewModel vm = null;
    private Stage stage = null;
    private final FileChooser fileChooser = new FileChooser();

    private EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent me) {
            if (me.getSource() == IOFileView.this.btnLoad) {
                IOFileView.this.btnLoadHandle((Event)me);
            } else if (me.getSource() == IOFileView.this.btnSave) {
                IOFileView.this.btnSaveHandle((Event)me);
            }
        }
    };

    private IOFigureView.ButtonAction btnLoad = new IOFigureView.ButtonAction("Load", this.handler);

    private IOFigureView.ButtonAction btnSave = new IOFigureView.ButtonAction("Save", this.handler);

    public IOFileView(ViewModel vm, Stage stage) {
        this.vm = (IOFileViewModel)vm;
        vm.registerView(this);
        this.stage = stage;
        this.fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Serialize", "*.ser"),
                new FileChooser.ExtensionFilter("XMLEncoder Files", "*.xml"),
                new FileChooser.ExtensionFilter("XMLDOM Files", "*.xml2"));
        this.fileChooser.setInitialDirectory(new File("./"));
        this.fileChooser.setInitialFileName("zch.ser");
        setPrefSize(600.0D, 100.0D);
        HBox center = new HBox();
        center.setAlignment(Pos.CENTER);
        center.setSpacing(10.0D);
        center.getChildren().addAll(this.btnLoad, new Label("        "), this.btnSave);
        setCenter((Node)center);
        BorderPane.setAlignment((Node)center, Pos.BOTTOM_CENTER);
    }

    private void btnLoadHandle(Event e) {
        File selectedFile = this.fileChooser.showOpenDialog((Window)this.stage);
        if (selectedFile != null)
            this.vm.loadDrawing(selectedFile);
    }

    private void btnSaveHandle(Event e) {
        File selectedFile = this.fileChooser.showSaveDialog((Window)this.stage);
        if (selectedFile != null)
            this.vm.saveDrawing(selectedFile);
    }

    public void update() {}
}
