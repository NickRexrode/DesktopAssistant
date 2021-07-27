package com.nickrexrode;


import com.nickrexrode.gui.ViewPanel;
import com.nickrexrode.internal.BasicLoadedApplication;
import com.nickrexrode.internal.application.ApplicationManager;
import com.nickrexrode.internal.base.State;
import com.nickrexrode.internal.io.FileManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public final class DesktopAssistant extends Application implements State {
    @FXML
    private Button minimizeButton;
    @FXML
    private Button closeButton;
    @FXML
    private Text desktopAssistantText;
    @FXML
    private Button leftButton;
    @FXML
    private Button rightButton;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private ViewPanel viewSelectorBox;


    private double xOffset = 0;
    private double yOffset = 0;

    public static void main(String[] args) {

        launch();


    }




    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DesktopAssistant.fxml"));
        loader.setController(this);
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);


        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
        this.load();


    }


    @FXML
    private void handleLeftButtonClicked(ActionEvent e) {
        this.viewSelectorBox.shiftLeft();
    }
    @FXML
    private void handleRightButtonClicked(ActionEvent e) {
        this.viewSelectorBox.shiftRight();
    }
    @FXML
    private void handleCloseButtonClicked(ActionEvent e) {
        shutdown();
    }
    @FXML
    private void handleMinimizeButtonClick(ActionEvent e) {
        Stage stage = (Stage)((Button) e.getSource()).getScene().getWindow();
        stage.setIconified(true);
        save();

    }

    @Override
    public boolean load() {
        FileManager.getInstance();
        ApplicationManager.getInstance();

        return true;
    }

    @Override
    public boolean shutdown() {
        if (save()) {
            Platform.exit();
        }
        return true;
    }

    @Override
    public boolean save() {
        return true;
    }
}
