package com.nickrexrode;

import com.nickrexrode.config.ConfigManager;
import com.nickrexrode.gui.ViewPanel;
import com.nickrexrode.application.ApplicationManager;
import com.nickrexrode.base.State;
import com.nickrexrode.io.FileManager;
import com.nickrexrode.loader.LoadingBarQueue;
import com.nickrexrode.web.RequestManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public final class DesktopAssistant implements State {
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

    public double xOffset = 0;
    public double yOffset = 0;

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
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        stage.setIconified(true);
        save();

    }

    @Override
    public boolean load() {
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
        LoadingBarQueue.getInstance().save();
        return true;
    }


}
