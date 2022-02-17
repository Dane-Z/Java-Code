package homeworkTwelve;


/*
 * Dane Zeeb
 * Module 12 - Simple Calculator
 * En.605.201.85.SU20
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SimpleCalc extends Application {
	public static void main(String[] args) {
        launch(args);
    }
    /*
     * The meat of the code is split into 3 parts:
     * First, set gaps, alignment, orientation, and padding for the root node
     * Second, set the 3 label variables and corresponding text fields, along with a button
     * Finally, add all children to the parent root node, set the scene, and show it.
     * Some imports are not used but are there for future expansion on this project.
     */
    public void start(Stage myStage) {
        myStage.setTitle("Simple Calculator");
        FlowPane rootNode = new FlowPane(Orientation.VERTICAL, 5,5);
        rootNode.setPadding(new Insets(30));
        rootNode.setAlignment(Pos.CENTER);
        Scene myScene = new Scene(rootNode, 400, 300);
        
        Button calc = new Button("Calculate");
        Label fV = new Label("First Value:    ");
        Label sV = new Label("Second Value:    ");
        Label sum = new Label("Sum is:    ");
        TextField fF = new TextField();
        TextField sF = new TextField();
        TextField sumF = new TextField();
        sumF.setEditable(false);
        
        rootNode.getChildren().addAll(fV, fF, sV, sF, sum, sumF, calc);
        myStage.setScene(myScene);
        myStage.show();
    }
}
