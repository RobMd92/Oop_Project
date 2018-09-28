

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Oop_Project");
        addProduct ap = new addProduct();
        Search search = new Search();
        ViewDb view;
        ViewProducts vp=new ViewProducts();
        view = new ViewDb();
        Order order = new Order();
        TabPane tabPane = new TabPane();

        tabPane.getTabs().add(order);

        tabPane.getTabs().add(ap);
        tabPane.getTabs().add(view);
        tabPane.getTabs().add(vp);
        tabPane.getTabs().add(search);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);


        Scene scene = new Scene(tabPane, 300, 300);

        primaryStage.setScene(scene);


        primaryStage.show();
    }


}