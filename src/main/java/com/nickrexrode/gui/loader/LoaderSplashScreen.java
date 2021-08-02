package com.nickrexrode.gui.loader;

import com.nickrexrode.DesktopAssistant;
import com.nickrexrode.loader.LoadingBarQueue;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public final class LoaderSplashScreen implements Initializable {
    private DesktopAssistant instance;

    @FXML
    private AnchorPane mainPane;
    @FXML
    private ProgressBar progressBar;

    @FXML
    private Text countText;

    public void setInstance(DesktopAssistant instance) {
        this.instance = instance;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadingBarQueue loadingBarQueue = LoadingBarQueue.getInstance();
        loadingBarQueue.setLoadingBar(this.progressBar);
        countText.setText(""+loadingBarQueue.getLastCount());
        new SplashScreenThread().start();


    }

    class SplashScreenThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(3000);

                //DO LOADING STUFF
                instance.load();
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
                                instance.xOffset = event.getSceneX();
                                instance.yOffset = event.getSceneY();
                            }
                        });
                        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                stage.setX(event.getScreenX() - instance.xOffset);
                                stage.setY(event.getScreenY() - instance.yOffset);
                            }
                        });


                        stage.setScene(scene);
                        stage.initStyle(StageStyle.UNDECORATED);
                        stage.show();

                        mainPane.getScene().getWindow().hide();
                    }
                });


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
