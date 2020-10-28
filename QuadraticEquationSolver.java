/*
Emily Farrell
CSC 360
QuadraticEquationSolver.java
Program description: QuadraticEquationSolver takes user input from a simple interface of text fields
and a button. Then, it transforms the strings inputted by the user into double values in order to calculate
the root of any requested quadratic equation, which the program then prints back out inside a text field
within the interface for the user to view.
 */


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class QuadraticEquationSolver extends Application {
    private TextField tfA = new TextField();
    private TextField tfB = new TextField();
    private TextField tfC = new TextField();
    private TextField tfRoots = new TextField();
    private Button btSolve = new Button("Solve");

    @Override // override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a border pane
        BorderPane pane = new BorderPane();

        // Place nodes in the pane
        pane.setCenter(getHBox());
        pane.setBottom(getSecondHBox());

        // set column width of text fields
        tfA.setPrefColumnCount(5);
        tfB.setPrefColumnCount(5);
        tfC.setPrefColumnCount(5);

        // process events
        btSolve.setOnAction(e -> calculateRoots());


        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Quadratic Equation Solver"); // set the stage title
        primaryStage.setScene(scene); // place the scene in the stage
        primaryStage.show(); // display the stage
    }

    private HBox getHBox() {
        HBox hBox = new HBox(15);
        hBox.setPadding(new Insets(10,10,10,10));

        Label aLabel = new Label("a:");
        Label bLabel = new Label("b:");
        Label cLabel = new Label("c:");
        Label rootsLabel = new Label("Roots of ax\u00B2 + bx + c:");

        hBox.getChildren().addAll(aLabel, tfA, bLabel, tfB, cLabel, tfC, rootsLabel, tfRoots);
        return hBox;
    }

    private HBox getSecondHBox() {
        HBox hBox2 = new HBox();
        hBox2.setPadding(new Insets(10,10,10,10));
        hBox2.setAlignment(Pos.BOTTOM_CENTER);

        hBox2.getChildren().add(btSolve);
        return hBox2;
    }

    private void calculateRoots() {
        double a; double b; double c;
        // Get values from text fields
        try {
            a = Double.parseDouble(tfA.getText());
        }
        catch (Exception ParseException){ //if anything but a double is input
            a = 0;
            tfA.setText("0"); // calculate as if 0 was entered
        }
        try { // same for fields b, c
            b = Double.parseDouble(tfB.getText());
        }
        catch (Exception ParseException){
            b = 0;
            tfB.setText("0");
        }
        try {
            c = Double.parseDouble(tfC.getText());
        }
        catch (Exception ParseException){
            c = 0;
            tfC.setText("0");
        }

        // find discriminant
        double discriminant = (Math.pow(b, 2) - (4 * a * c));

        // set usual value of possible roots
        double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
        double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);

        // cases to find root(s)
        if (a != 0) {
            if (discriminant < 0) {
                tfRoots.setText("No x");
            }
            else if (discriminant > 0) {
                tfRoots.setText(root1 + ", " + root2);
            }
            else if (discriminant == 0) {
                double root = -b / (2 * a);
                tfRoots.setText(String.valueOf(root));
            }
        }
        else {
            if (b != 0) {
                double root = -c / b;
                tfRoots.setText(String.valueOf(root));
            }
            else {
                if (c == 0) {
                    tfRoots.setText("All x");
                }
                else {
                    tfRoots.setText("No x");
                }
            }
        }
    }
}
