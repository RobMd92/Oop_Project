import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.sql.*;

class Order extends Tab {

    private Button order = new Button("Order");
    private Button refresh = new Button("Refresh");
    private Connection con;
    private Statement statement;
    private ResultSet resultSet;
    private ListView<String> list = new ListView<>();

    Order() throws SQLException {
        con = Singleton.getConnection();

        statement = con.createStatement();

        updateData();

        StackPane ord = new StackPane();
        list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        VBox box = new VBox();
        HBox hbox = new HBox();
        setText("Order Product");
        Label label = new Label("Product Id");
        Label label1 = new Label("First Name");
        Label label2 = new Label("Second Name");
        Label label3 = new Label("Amount");
        TextField tex = new TextField();
        TextField tex1 = new TextField();
        TextField tex2 = new TextField();
        TextField tex3 = new TextField();

        order.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String ProId = tex.getText();
                String fname = tex1.getText();
                String lName = tex2.getText();
                String Amount = tex3.getText();

                try {
                    Statement s = con.createStatement();

                    String query = "INSERT INTO Orders VALUES ('" + ProId + "','" + fname + "','" + lName + "','" + Amount + "')";
                    s.executeUpdate(query);
                    System.out.println(query);
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }
        });

        refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    updateData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });


        hbox.getChildren().addAll(order, refresh);

        box.getChildren().addAll(list, label, tex, label1, tex1, label2, tex2, label3, tex3, hbox);

        ord.getChildren().add(box);


        setContent(ord);


    }

    private void updateData() throws SQLException {
        list.getItems().clear();

        String query = "select * from Products ";

        resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            list.getItems().add(resultSet.getString("Id") + "\t" + resultSet.getString("Make") + " \t" + resultSet.getString("Model") + "\t  \t" + resultSet.getString("Description") + " \t€" + resultSet.getString("Price"));
        }
    }
}
