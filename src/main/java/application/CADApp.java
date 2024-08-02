package application;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.MainView;
import viewmodel.MainViewModel;

public class CADApp extends Application {
    private MainView viewMain = null;

    private MainViewModel vmMain = null;

    public void start(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();
            Scene scene = new Scene((Parent)root, 1400.0D, 800.0D);
            this.vmMain = new MainViewModel();
            this.viewMain = new MainView(this.vmMain, primaryStage);
            root.setCenter((Node)this.viewMain);
            primaryStage.setScene(scene);
            primaryStage.setTitle("CADApp - Waldemar Hermann");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
