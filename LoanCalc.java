package ProjThree;

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

public class LoanCalc extends Application 
{
	TextField totF = new TextField();
	TextField annF = new TextField();
	TextField yearsF = new TextField();
	TextField loanF = new TextField();
	TextField monthF = new TextField();

	public static void main(String[] args) 
	{
		launch(args);
	}
	public void start(Stage myStage) 
	{
		myStage.setTitle("Loan Calculator");
		FlowPane rootNode = new FlowPane(Orientation.VERTICAL, 5,5);
		rootNode.setPadding(new Insets(30));
		rootNode.setAlignment(Pos.CENTER);
		Scene myScene = new Scene(rootNode, 500, 400);
		/*
		 * Set up button and Action Event handler.
		 */
		Button calc = new Button("Calculate");
		ButtonPressHandler calcHandler = new ButtonPressHandler();
		calc.setOnAction( calcHandler ); 
		/*
		 * Declare labels for the Text Boxes and doesn't let the user edit the text boxes for monthly or total payment.
		 */
		Label annIntRate = new Label("Annual Interest Rate (values between 0.00 & 1.00):    ");
		Label years = new Label("Number of Years:    ");
		Label loanAmt = new Label("Loan Amount:    ");
		Label monthlyPay = new Label("Monthly Payment:    ");
		Label totPay = new Label("Total Payment:    ");
		monthF.setEditable(false);
		totF.setEditable(false);
		/*
		 * Sets the Scene and displays the stage after adding all children to the root node.
		 */
		rootNode.getChildren().addAll(annIntRate, annF, years, yearsF, loanAmt, loanF, monthlyPay, monthF, totPay, totF, calc);
		myStage.setScene(myScene);
		myStage.show();
	}
	/*
	 * Class for handling the button press and calculations for each of the variables in the Loan calc. formula.
	 * Uses Math.pow to account for the "-n" exponent in the formula, and Math.round to provide realistic dollar amounts.
	 */
	class ButtonPressHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			/*
			 * Calculates i and accounts for if the user inputs a percentage number or decimal.
			 */
			double i = Double.parseDouble(annF.getText());
			if(i > 1.00)
			{
				i = i/100;
				i = i/12;
			}
			else 
			{
				i = i/12;
			}

			double yearsH = Double.parseDouble(yearsF.getText());
			double n = (yearsH * 12);
			double A = Double.parseDouble(loanF.getText());
			double expon = Math.pow((1 + i), -(n));

			double p = Math.round(((i * A)/(1-expon))*100);
			p = p/100;
			String monthly = String.valueOf(p);

			double totH = Math.round((n * p)*100);
			totH = totH/100;
			String totalH = String.valueOf(totH);

			monthF.setText(monthly);
			totF.setText(totalH);
		}
	}
}

