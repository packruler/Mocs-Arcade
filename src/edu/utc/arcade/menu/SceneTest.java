package edu.utc.arcade.menu;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Various on 1/18/2016.
 */

public class SceneTest extends Application {

    /*
    for my reference and anyone else: http://www.javafxtutorials.com/tutorials/hboxvbox/
    best site available really for info on the positioning and such of the boxes and elements.
     */


    //TODO: seperate these buttons per each screen

    Button btn1, btn2, btn3, btn4, btn5, btn6;
    Label label1, label2, label3, label4, label5;
    FlowPane flowpane1, flowpane2;
    Stage stage;
    Scene scene1, scene2;

    public void start(Stage mainScreen) {

        stage = mainScreen;

        //making things to put on the stages like buttons and labels
        btn1 = new Button("Kiosk Mode");
        btn2 = new Button("Admin Mode");
        btn3 = new Button("Settings");
        btn4 = new Button("Exit");
        btn5 = new Button("Main Menu");
        //btn6=new Button("Browse Games");

        btn1.setOnAction(e -> handleButtonAction(e));
        btn2.setOnAction(e -> handleButtonAction(e));
        btn3.setOnAction(e -> handleButtonAction(e));
        btn5.setOnAction(e -> handleButtonAction(e));

        //make an HBOX for the first 3 buttons
        //HBox hb=new HBox(btn1, btn2, btn3);
        //set the padding and spacing
        //hb.setSpacing(10);
        //hb.setPadding(new Insets(20));

        //make a VBOX for the last 3 buttons
        VBox vb = new VBox(btn1, btn2, btn3, btn4, btn5);
        //set the padding and spacing
        vb.setSpacing(30);
        vb.setPadding(new Insets(250));

        //make a VBOX for the last 3 buttons
        VBox vb2 = new VBox(btn1, btn5, btn4);
        //set the padding and spacing
        vb2.setSpacing(30);
        vb2.setPadding(new Insets(250));

        label1 = new Label("Main Menu");
        label2 = new Label("Kiosk Mode");
        label3 = new Label("Admin Mode");
        label4 = new Label("Settings");
        label5 = new Label("Main Menu");

        flowpane1 = new FlowPane();
        flowpane2 = new FlowPane();
        flowpane1.setVgap(10);
        flowpane2.setVgap(10);

        flowpane1.setStyle("-fx-background-color: tan; -fx-padding:10px");

        flowpane2.setStyle("-fx-background-color: black; -fx-padding:10px");

        //flowpane1.getChildren().addAll(label1, btn1, btn2, btn3, btn4);
        //flowpane1.getChildren().add(hb);
        flowpane1.getChildren().add(vb);
        //flowpane2.getChildren().addAll(label5, btn5);
        flowpane2.getChildren().add(vb2);

        scene1 = new Scene(flowpane1, 800, 600);
        scene2 = new Scene(flowpane2, 800, 600);

        mainScreen.setTitle("Welcome to the Mocs arcade cabinet!");
        mainScreen.setScene(scene1);
        mainScreen.show();
    }

    private void handleButtonAction(ActionEvent e) {
        if (e.getSource() == btn5)
            stage.setScene(scene1);
        else
            stage.setScene(scene2);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
