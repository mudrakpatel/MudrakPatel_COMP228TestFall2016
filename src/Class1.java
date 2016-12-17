/**
 * Created by Mudrak on 12/16/2016.
 */

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javax.swing.JOptionPane;
import java.awt.TextArea;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Class1 extends Application{
    /**
     * Database variables
     * */
    private static final String URL = "jdbc:sqlserver://localhost:1433;" +
            "databaseName=StudentInfo;user=mudrak;password=password";
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    /**
     * GUI JavaFX variables
     * */
    private static Label cityLabel;
    private static TextField cityTextField;
    private static Button displayButton;
    private static GridPane gridPaneLayout;
    private static TextArea textArea;
     /**
     * @method main
     * @void
     * */
    public static void main(String[] args) {
           launch(args);
    }

    /**
     * @method start
     * @void
     * @param primaryStage : Stage
     * */
    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("StudentInfo");
        /**
         * Initializing the components
         * */
        cityLabel = new Label("Select students by city:");
        cityTextField = new TextField();
        displayButton = new Button("Display");
        gridPaneLayout = new GridPane();
        /**
         * Add event listener on displayButton
         * */
         displayButton.setOnAction(event -> {
             /**
              * The database connection part
              * */
             try {
                 System.out.println("Connecting to database...");
                 //1) Get a connection to the database
                 connection = DriverManager.getConnection(URL);
                 System.out.println("Connected to database...");
                 //2) Create a statement using the connection object
                 statement = connection.createStatement();
                 System.out.println("Statement created...");
                 resultSet = statement.executeQuery("SELECT * FROM Student");
             } catch (SQLException exception) {
                 JOptionPane.showMessageDialog(null, "Look at console for error details...");
                 System.out.println(exception.getMessage());
             }
         });
         /**
         * Add components to the gridPaneLayout
         * */
        gridPaneLayout.add(cityLabel, 0, 0);
        GridPane.setHalignment(cityLabel, HPos.LEFT);
        gridPaneLayout.add(cityTextField,1,0);
        GridPane.setHalignment(cityTextField, HPos.RIGHT);
        gridPaneLayout.add(displayButton,1,1);
        GridPane.setHalignment(displayButton, HPos.RIGHT);
        /**
         * Set the gridPaneLayout properly
         * */
        gridPaneLayout.setAlignment(Pos.CENTER);
        gridPaneLayout.setPadding(new Insets(11.5, 12.5, 10.5, 5.5));
        gridPaneLayout.setHgap(6.0);
        gridPaneLayout.setVgap(6.0);
        /**
         * Setting the primaryStage and show it
         * */
        primaryStage.setScene(new Scene(gridPaneLayout, 400, 350));
        primaryStage.show();
      }
 }
