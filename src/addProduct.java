

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.sql.*;

class addProduct extends Tab {
    private Connection con;

    private Button add = new Button("Add");
    private Label labelId = new Label("ID");
    private Label title = new Label("Please fill in the relavant fields");
    private Label labelMake = new Label("Make:");
    private Label labelModel = new Label("Model:");
    private Label labelDescription = new Label("Description:");
    private Label labelPrice = new Label("Price €");
    private TextField textFieldId = new TextField();
    private TextField textFieldMake = new TextField();
    private TextField textFieldModel = new TextField();
    private TextField textFieldDescription = new TextField();
    private TextField textFieldPrice = new TextField();

    addProduct() throws SQLException {
        con = Singleton.getConnection();
        StackPane Ap = new StackPane();
        setText("Add Product");
        VBox box = new VBox();

        GridPane grid = new GridPane();

        grid.add(labelId, 1, 1);
        grid.add(textFieldId, 2, 1);
        grid.add(labelMake, 1, 2);
        grid.add(textFieldMake, 2, 2);
        grid.add(labelModel, 1, 3);
        grid.add(textFieldModel, 2, 3);
        grid.add(labelDescription, 1, 4);
        grid.add(textFieldDescription, 2, 4);
        grid.add(labelPrice, 1, 5);
        grid.add(textFieldPrice, 2, 5);
        box.getChildren().addAll(title, grid, add);

        Ap.getChildren().add(box);
        setContent(Ap);

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String sid = textFieldId.getText();
                String model = textFieldModel.getText();
                String make = textFieldMake.getText();
                String description = textFieldDescription.getText();
                String price = textFieldPrice.getText();


                try {
                    Statement rs = con.createStatement();
                    String query = "INSERT INTO Products VALUES ('" + sid + "',' " + make + "','" + model + "','" + description + "','\t" + price + "')";
                    rs.executeUpdate(query);
                    System.out.println(query);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
