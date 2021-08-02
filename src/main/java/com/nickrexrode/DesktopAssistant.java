package com.nickrexrode;


import com.nickrexrode.config.ConfigManager;
import com.nickrexrode.gui.ViewPanel;
import com.nickrexrode.gui.loader.LoaderSplashScreen;
import com.nickrexrode.internal.BasicLoadedApplication;
import com.nickrexrode.internal.application.ApplicationManager;
import com.nickrexrode.internal.base.State;
import com.nickrexrode.internal.io.FileManager;
import com.nickrexrode.logging.Logger;
import com.nickrexrode.web.RequestManager;
import com.nickrexrode.web.TestClassData;
import com.nickrexrode.web.request.Request;
import com.nickrexrode.web.request.TestRequest;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
import java.util.ResourceBundle;

public final class DesktopAssistant extends Application implements State{
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


    private Stage primaryStage;

    public double xOffset = 0;
    public double yOffset = 0;

    public static void main(String[] args) {

        launch();


    }

    public Stage getPrimaryStage() {
        return this.primaryStage;
    }




    @Override
    public void start(Stage primaryStage) throws IOException {

        //PreLoading
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/loader/Loader.fxml"));
        AnchorPane root = loader.load();

        ((LoaderSplashScreen) loader.getController()).setInstance(this);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.initStyle(StageStyle.UNDECORATED);


        //Loading
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

        primaryStage.show();


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
//        Logger.loading("Loading Desktop Assistant");
        FileManager.getInstance();

        ConfigManager.getInstance();

        ApplicationManager.getInstance();
        RequestManager.getInstance();
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
        ConfigManager.getInstance().save();
        return true;
    }


}
