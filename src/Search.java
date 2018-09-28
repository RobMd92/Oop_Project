import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.sql.*;

class Search extends Tab {
    private Connection Con = Singleton.getConnection();
    Button search1 = new Button("Search");

    Search() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);


        ResultSet resultSet;
        StackPane search = new StackPane();
        setText("Search");
        VBox box = new VBox();

        Label label = new Label("Please enter a Product Id");
        TextField tex = new TextField();
        Label res = new Label();

        box.getChildren().addAll(label, tex, search1);
        search.getChildren().add(box);
        setContent(search);


        search1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String sid = tex.getText();
                String query = "SELECT * FROM Products WHERE Id='"+sid+"'";
                String res = "";
                try {

                    Statement sr = Con.createStatement();
                    ResultSet red = sr.executeQuery(query);
                    while (red.next()) {
                        res = red.getString("Id") + "\t" + red.getString("Make") + " \t" + red.getString("Model") + "\t  \t" + red.getString("Description") + " \t€" + red.getString("Price");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                }
                alert.setTitle("Search Results");
                alert.setHeaderText(null);
                alert.setContentText("you have Searched: " + res.toString());
                alert.showAndWait();
                System.out.println(query);


            }
        });


    }
}
