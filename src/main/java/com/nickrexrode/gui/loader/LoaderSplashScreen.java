package com.nickrexrode.gui.loader;

import com.nickrexrode.loader.LoadingBarQueue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

public final class LoaderSplashScreen implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private ProgressBar progressBar;

    @FXML
    private Text countText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadingBarQueue loadingBarQueue = LoadingBarQueue.getInstance();
        loadingBarQueue.setLoaderController(this);
    }
    public void setText(String text){
        countText.setText(text);
    }

    public void setProgressBarStatus(double amount) {
        progressBar.setProgress(amount);
    }

    public void destroy() {
        mainPane.getScene().getWindow().hide();
    }

}
