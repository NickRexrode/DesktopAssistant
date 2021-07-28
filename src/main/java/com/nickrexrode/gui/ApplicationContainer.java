package com.nickrexrode.gui;

import com.nickrexrode.DesktopAssistant;
import com.nickrexrode.external.Application;
import com.nickrexrode.internal.base.State;

import com.nickrexrode.internal.io.FileManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;


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


        String imageLocation;
        if (this.application.getThumbnailLocation().equals("default")) {
            imageLocation = getClass().getResource("defaultApplicationContainerImage.png").toString();
        } else {
            imageLocation = FileManager.HOME_DIRECTORY+File.separator+application.getThumbnailLocation();
            File file = new File(imageLocation);
            imageLocation = file.toURI().toString();
        }

        imageView.setImage(new Image(imageLocation));
        imageView.setOnMouseClicked(event -> {
            this.application.execute();
        });


    }


    public ApplicationContainer setApplication(Application application) {
        this.application = application;
        return this;
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
