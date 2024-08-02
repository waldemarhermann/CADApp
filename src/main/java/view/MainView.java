package view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import viewmodel.MainViewModel;



public class MainView extends BorderPane implements IView {

    private MainViewModel vm = null;
    private IOFileView viewIOFile = null;
    private IOFigureView viewIOFigure = null;
    private CADView viewCAD = null;
    private Stage stage = null;


    public MainView(MainViewModel vm, Stage stage) {
        try {
            this.vm = vm;
            this.stage = stage;
            vm.registerView(this);
            this.viewCAD = new CADView(vm.getCADVM());
            this.viewIOFigure = new IOFigureView(vm.getIOFigureVM());
            this.viewIOFile = new IOFileView(vm.getIOFileVM(), stage);
            setRight((Node)this.viewIOFigure);
            BorderPane.setAlignment((Node)this.viewIOFigure, Pos.CENTER_RIGHT);
            setCenter((Node)this.viewCAD);
            setBottom((Node)this.viewIOFile);
            BorderPane.setAlignment((Node)this.viewIOFile, Pos.BOTTOM_CENTER);
            setPrefSize(1400.0D, 700.0D);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        this.viewCAD.update();
        this.viewIOFigure.update();
    }
}
