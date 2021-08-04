package com.nickrexrode;

import com.nickrexrode.gui.loader.LoaderSplashScreen;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public final class Loader extends Application {

    private double xOffset;
    private double yOffset;
    private DesktopAssistant desktopAssistantInstance;
    private LoaderSplashScreen loaderController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        desktopAssistantInstance = new DesktopAssistant();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/loader/Loader.fxml"));

        AnchorPane root = loader.load();
        this.loaderController = loader.getController();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.initStyle(StageStyle.UNDECORATED);

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
        new LoadingScreenThread().start();

    }

    private class LoadingScreenThread extends Thread {
        @Override
        public void run() {

            desktopAssistantInstance.load();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    FXMLLoader loader = new FXMLLoader(DesktopAssistant.class.getResource("DesktopAssistant.fxml"));

                    loader.setController(new DesktopAssistant());
                    AnchorPane root = null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    root.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            desktopAssistantInstance.xOffset = event.getSceneX();
                            desktopAssistantInstance.yOffset = event.getSceneY();
                        }
                    });
                    root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            stage.setX(event.getScreenX() - desktopAssistantInstance.xOffset);
                            stage.setY(event.getScreenY() - desktopAssistantInstance.yOffset);
                        }
                    });

                    stage.setScene(scene);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();

                    loaderController.destroy();
                }
            });
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
