import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.sql.*;

class ViewDb extends Tab {
    private Button refresh = new Button("Refresh");
    private Connection con;
    private Statement statement;
    private ResultSet resultSet;
    private ListView<String> list = new ListView<>();

    ViewDb() throws SQLException {

        con = Singleton.getConnection();


        statement = con.createStatement();

        updateData();


        StackPane view = new StackPane();
        VBox vBox = new VBox();

        setText("View Orders");


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

        String query = "select * from Orders ";

        resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            list.getItems().add(resultSet.getString("fName") + "\t" + resultSet.getString("lName") + " \t" + resultSet.getString("productId") + "\t  \t" + resultSet.getString("amount"));

        }
    }
}
