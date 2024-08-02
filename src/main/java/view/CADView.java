package view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.IFigure;
import viewmodel.CADViewModel;
import viewmodel.ViewModel;

public class CADView extends Group implements IView {
    private CADViewModel vm = null;

    private Pane pane = new Pane();

    private double width = 1100.0D;

    private double height = 700.0D;

    private EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
        public void handle(MouseEvent me) {
            if (me.getEventType() == MouseEvent.MOUSE_PRESSED) {
                CADView.this.vm.startFigure(me.getX(), me.getY());
            } else if (me.getEventType() == MouseEvent.MOUSE_RELEASED) {
                CADView.this.vm.finishFigure(me.getX(), me.getY());
            } else if (me.getEventType() == MouseEvent.MOUSE_CLICKED) {
                CADView.this.vm.chooseFigure(me.getX(), me.getY());
            }
        }
    };

    public CADView(ViewModel vm) {
        this.vm = (CADViewModel)vm;
        vm.registerView(this);
        this.pane.setStyle("-fx-background-color: black;");
        this.pane.setPrefSize(this.width, this.height);
        this.pane.setOnMousePressed(this.handler);
        this.pane.setOnMouseReleased(this.handler);
        this.pane.setOnMouseClicked(this.handler);
        update();
        getChildren().add(this.pane);
    }

    public void update() {
        this.pane.getChildren().clear();
        for (IFigure f : this.vm.getDrawing().getShapes())
            this.pane.getChildren().add(FXAdapter.getShape(f));
    }
}
