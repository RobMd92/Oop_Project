import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.sql.*;

class ViewProducts extends Tab {
    private Button refresh = new Button("Refresh");
    private Connection con;
    private Statement statement;
    private ResultSet resultSet;
    private ListView<String> list = new ListView<>();

    ViewProducts() throws SQLException {
        con = Singleton.getConnection();
        statement = con.createStatement();

        updateData();


        StackPane view = new StackPane();
        VBox vBox = new VBox();
        setText("View Products");


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
        vBox.getChildren().addAll(list, refresh);
        view.getChildren().add(vBox);


        setContent(view);


    }

    private void updateData() throws SQLException {
        list.getItems().clear();

        String query = "select * from Products ";

        resultSet = statement.executeQuery(query);
        System.out.println(query);

        while (resultSet.next()) {

            list.getItems().add(resultSet.getString("Id") + "\t" + resultSet.getString("Make") + " \t" + resultSet.getString("Model") + "\t  \t" + resultSet.getString("Description") + " \t€" + resultSet.getString("Price"));

        }
    }
}
