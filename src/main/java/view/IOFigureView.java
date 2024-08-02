package view;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Figure;
import model.FigureProperties;
import model.Tupel;
import viewmodel.IOFigureViewModel;
import viewmodel.ViewModel;



public class IOFigureView extends BorderPane implements IView {
    private IOFigureViewModel vm = null;

    private int propID = 0;

    private Figure propFigure = new FigureProperties(
            Double.valueOf(100.0D),
            Double.valueOf(200.0D),
            Double.valueOf(300.0D),
            Double.valueOf(150.0D),
            new model.Color(0, 255, 0),
            new model.Color(0, 0, 0)
    );

    private ObservableList<String> olForms = FXCollections.observableArrayList(IOFigureViewModel.forms);

    private LabeledText pnIndex = new LabeledText("Index", "1");
    private ComboBox<String> pnForm = new ComboBox(this.olForms);
    private LabeledText pnX = new LabeledText("X", "0");
    private LabeledText pnY = new LabeledText("Y", "0");
    private LabeledText pnA = new LabeledText("Width", "100");
    private LabeledText pnB = new LabeledText("Heigth", "50");
    private LabeledText pnFC = new LabeledText("FillC", javafx.scene.paint.Color.YELLOW);
    private LabeledText pnLC = new LabeledText("LineC", javafx.scene.paint.Color.BLUE);
    private LabeledText pnUmfang = new LabeledText("Umfang", "300", false);
    private LabeledText pnArea = new LabeledText("Flaeche", "5000", false);


    private EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent me) {
            System.out.println("Button pressed: " + me.getSource());
            IOFigureView.this.propID = IOFigureView.this.getIndex();
            IOFigureView.this.getSettings();
            if (me.getSource() == IOFigureView.this.btnOK) {
                IOFigureView.this.vm.changeFigure(IOFigureView.this.propID, IOFigureView.this.propFigure);
            } else if (me.getSource() == IOFigureView.this.btnAdd) {
                IOFigureView.this.vm.addFigure(IOFigureView.this.propID, IOFigureView.this.propFigure);
            } else if (me.getSource() == IOFigureView.this.btnRemove) {
                IOFigureView.this.vm.removeFigure(IOFigureView.this.propID, IOFigureView.this.propFigure);
            }
        }
    };

    private ButtonAction btnOK = new ButtonAction("Change", this.handler);

    private ButtonAction btnAdd = new ButtonAction("Add", this.handler);

    private ButtonAction btnRemove = new ButtonAction("Remove", this.handler);

    public void handleEventIndex(int id) {
        this.propID = id;
        this.vm.changedID(this.propID);
        System.out.println("New ID = " + this.propID);
    }

    public void handleEventForm(String form) {
        this.vm.changedForm(form);
        System.out.println("New Form = " + form);
    }

    public void handleEventColor(javafx.scene.paint.Color c, String lblText) {
        Tupel<model.Color> colors = this.propFigure.getColors();
        if (lblText.equals("FillC")) {
            colors.x = FXAdapter.convertToModelColor(c);
        } else {
            colors.y = FXAdapter.convertToModelColor(c);
        }
        this.propFigure.setColors(colors.x, colors.y);
        this.vm.changedColor(FXAdapter.convertToModelColor(c), lblText);
        System.out.println("New Color's RGB = " + c.getRed() + " " + c.getGreen() + " " + c.getBlue());
    }

    public IOFigureView(ViewModel vm) {
        this.vm = (IOFigureViewModel)vm;
        vm.registerView(this);
        this.pnForm.setPrefWidth(150.0D);
        this.pnForm.setPrefHeight(50.0D);
        this.pnForm.setOnAction(e -> handleEventForm(getForm()));
        setPrefSize(200.0D, 800.0D);
        VBox right = new VBox();
        right.setAlignment(Pos.BASELINE_CENTER);
        right.setAlignment(Pos.BASELINE_RIGHT);
        right.setSpacing(8.0D);
        right.getChildren().addAll(new Node[] {
                this.pnIndex, this.pnForm, new Label("        "), new Label("Position     "), this.pnX, this.pnY,
                new Label("Size         "), this.pnA, this.pnB, new Label("Colors        "),
                this.pnFC, this.pnLC, this.btnOK, this.btnAdd, this.btnRemove, new Label("        "),
                new Label("Features     "), this.pnUmfang, this.pnArea
        });
        right.setPadding(new Insets(25.0D, 25.0D, 10.0D, 10.0D));
        setCenter((Node)right);
        this.pnForm.getSelectionModel().select(0);
    }

    public void update() {
        this.propID = this.vm.getID();
        Figure tempFigure = this.vm.getFigure();
        if (tempFigure != null) {
            this.propFigure = tempFigure.clone();
            updateIOFigure();
        } else {
            // Geeignete Handhabung, wenn keine Figur vorhanden ist
            System.out.println("Keine Figur zum Aktualisieren vorhanden.");
        }
        System.out.println("Update: " + tempFigure);
    }

    public void updateIOFigure() {
        if (this.propFigure != null) {
            this.pnIndex.setTextField(this.propID);
            this.pnForm.getSelectionModel().select(this.propFigure.getForm());
            this.pnX.setTextField(((Double)(this.propFigure.getPosition()).x).doubleValue());
            this.pnY.setTextField(((Double)(this.propFigure.getPosition()).y).doubleValue());
            this.pnA.setTextField(((Double)(this.propFigure.getSize()).x).doubleValue());
            this.pnB.setTextField(((Double)(this.propFigure.getSize()).y).doubleValue());
            this.pnFC.setColor(FXAdapter.getFXfromRGB(this.propFigure.getColors().x));
            this.pnLC.setColor(FXAdapter.getFXfromRGB(this.propFigure.getColors().y));
            this.pnUmfang.setTextField(this.propFigure.circumference());
            this.pnArea.setTextField(this.propFigure.area());
        }
    }

    public String getForm() {
        return (String)this.pnForm.getSelectionModel().getSelectedItem();
    }

    public int getIndex() {
        return this.pnIndex.getTextField();
    }

    public Figure getSettings() {
        this.propFigure.setForm(getForm());
        this.propFigure.setPosition(Double.valueOf(this.pnX.getTextField()), Double.valueOf(this.pnY.getTextField()));
        this.propFigure.setSize(Double.valueOf(this.pnA.getTextField()), Double.valueOf(this.pnB.getTextField()));

        // Example usage
        model.Color fillColor = FXAdapter.convertToModelColor(this.pnFC.getColor());
        model.Color lineColor = FXAdapter.convertToModelColor(this.pnLC.getColor());
        this.propFigure.setColors(fillColor, lineColor);

        return this.propFigure;
    }

    public static class ButtonAction extends Button {
        public ButtonAction(String btnText, EventHandler<ActionEvent> handler) {
            setText(btnText);
            setPadding(new Insets(5.0D, 5.0D, 5.0D, 5.0D));
            setPrefWidth(100.0D);
            addEventHandler(ActionEvent.ACTION, handler);
        }
    }

    private class LabeledText extends BorderPane {
        private Label lbl = null;

        private TextField txt = null;

        private final ColorPicker cp = new ColorPicker();

        public LabeledText(String lblText, javafx.scene.paint.Color rcColor) {
            this.lbl = createLabel(lblText);
            this.cp.setValue(rcColor); // rcColor ist bereits ein javafx.scene.paint.Color
            this.cp.setOnAction(e -> IOFigureView.this.handleEventColor(this.cp.getValue(), lblText));
            setLeft((Node)this.lbl);
            setRight((Node)this.cp);
        }

        public LabeledText(String lblText, String txtText, boolean enable) {
            this.lbl = createLabel(lblText);
            this.txt = createTextField(txtText, Boolean.valueOf(enable));
            setLeft((Node)this.lbl);
            setRight((Node)this.txt);
        }

        public LabeledText(String lblText, String txtText) {
            this(lblText, txtText, true);
            this.txt.textProperty().addListener((observable, oldValue, newValue) -> {
                if (lblText.equals("Index") && !newValue.trim().equals(oldValue.trim())) {
                    try {
                        int index = (int) Double.parseDouble(newValue.trim());
                        IOFigureView.this.handleEventIndex(index);
                    } catch (NumberFormatException e) {
                        // Fehlerbehandlung, z.B. ignorieren oder Log-Ausgabe
                        System.err.println("Fehler beim Parsen der Index-Zahl: " + newValue);
                    }
                }
            });
        }

        public void setTextField(double value) {
            this.txt.setText(String.valueOf(value));
        }

        public void setTextField(String str) {
            this.txt.setText(str);
        }

        public int getTextField() {
            String text = this.txt.getText().trim(); // Entfernt führende und nachfolgende Leerzeichen
            try {
                // Versucht, den Text zu einer Double-Zahl zu parsen und dann zu int zu konvertieren
                return (int) Double.parseDouble(text);
            } catch (NumberFormatException e) {
                // Behandlung des Fehlers, z.B. Rückgabe von -1 oder Ausgabe einer Fehlermeldung
                System.err.println("Fehler beim Parsen der Zahl: " + text);
                return -1; // oder eine andle.parseDoublere geeignete Fehlerbehandlung
            }
        }

        public String getForm() {
            return this.txt.getText();
        }

        public void setColor(Color rcColor) {
            this.cp.setValue(rcColor);
        }

        public Color getColor() {
            return this.cp.getValue();
        }

        private Label createLabel(String lblText) {
            Label lbl = new Label("  " + lblText + ":   ");
            lbl.setAlignment(Pos.BASELINE_CENTER);
            lbl.setPadding(new Insets(5.0D, 5.0D, 5.0D, 5.0D));
            return lbl;
        }

        private TextField createTextField(String txtText, Boolean ro) {
            TextField txt = new TextField("  " + txtText + "   ");
            txt.setAlignment(Pos.BASELINE_CENTER);
            txt.setPrefWidth(80.0D);
            txt.setDisable(!ro.booleanValue());
            return txt;
        }
    }
}
