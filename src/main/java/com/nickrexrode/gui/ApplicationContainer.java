package com.nickrexrode.gui;

import com.nickrexrode.external.Application;
import com.nickrexrode.internal.base.State;
import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class ApplicationContainer extends AnchorPane implements State {

    private Application application;
    @FXML
    private ImageView imageView;


    public ApplicationContainer(Application application) {
        this.application = application;

        try {

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("ApplicationContainer.fxml"));

            loader.setRoot(this);

            loader.setController(this);

            loader.load();


        } catch (IOException e) {
            e.printStackTrace();
        }


        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        imageView.setOnMouseClicked(event -> {
            this.application.execute();
        });


    }

    @FXML
    public void initialize() {

    }

    public ApplicationContainer setApplication(Application application) {
        this.application = application;
        return this;
    }

    @FXML
    public void handleImageViewMouseClick(MouseEvent mouseEvent) {
        System.out.println("Clicked");
    }

    public Application getApplication() {
        return this.application;
    }


    @Override
    public boolean load() {
        return false;
    }

    @Override
    public boolean shutdown() {
        return true;
    }

    @Override
    public boolean save() {
        return true;
    }


}
