package com.nickrexrode.gui;

import com.nickrexrode.DesktopAssistant;
import com.nickrexrode.config.ConfigManager;
import com.nickrexrode.exception.config.ConfigKeyNotFoundException;
import com.nickrexrode.exception.config.ConfigNotFoundException;
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

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ApplicationContainer.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }



        this.load();


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
        String imageLocation;
        if (this.application.getThumbnailLocation().equals("default")) {
            imageLocation = getClass().getResource("defaultApplicationContainerImage.png").toString();
        } else {
            imageLocation = new File(FileManager.HOME_DIRECTORY+File.separator+application.getThumbnailLocation()).toURI().toString();
        }

        imageView.setImage(new Image(imageLocation));
        imageView.setOnMouseClicked(event -> {
            this.application.execute();

        });

        try {
            String colorString = (String) ConfigManager.getInstance().getConfig(this.application.getName()).get("color");
            this.setBorder(new Border((new BorderStroke(Color.web(colorString), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))));
        } catch(ConfigKeyNotFoundException e) {
            this.setBorder(new Border((new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))));
        }


        return true;



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
