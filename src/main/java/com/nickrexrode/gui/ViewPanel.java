package com.nickrexrode.gui;

import com.nickrexrode.application.base.Application;
import com.nickrexrode.gui.application.ApplicationContainer;
import com.nickrexrode.application.ApplicationManager;
import com.nickrexrode.base.State;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public final class ViewPanel extends HBox implements State {

    private List<ApplicationContainer> applicationContainers;
    private int startDisplayNumber;

    public ViewPanel() {
        this.applicationContainers = new ArrayList<>();
        this.setPrefSize(500,200);
        this.setMaxSize(500,200);
        this.setMinSize(500,200);
        this.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.setSpacing(12.5);
        this.setAlignment(Pos.CENTER);
        this.load();
    }


    public void shiftRight() {
        if (startDisplayNumber < applicationContainers.size()-3) {
            startDisplayNumber++;
        }
        update();
    }

    public void shiftLeft() {
        if (startDisplayNumber >0) {
            startDisplayNumber--;
        }
        update();
    }

    public void update() {
        this.getChildren().clear();
        this.getChildren().addAll(applicationContainers.subList(startDisplayNumber, startDisplayNumber+3));
    }

    @Override
    public boolean load() {
        ApplicationManager applicationManager = ApplicationManager.getInstance();
        List<Application> applications = applicationManager.getAllApplications();

        for (int i = 0; i < applications.size(); i++) {
            this.applicationContainers.add(new ApplicationContainer(applications.get(i)));
        }
        update();
        return true;
    }

    @Override
    public boolean shutdown() {
        return false;
    }

    @Override
    public boolean save() {
        return false;
    }
}
