package at.ac.hcw.bonk_shoppinglistmanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ShoppingListApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(ShoppingListApplication.class.getResource("shopping-list-view.fxml"));
        Scene scene = new Scene(loader.load(), 1000, 700);
        ShoppingListController controller = loader.getController();
        stage.setTitle("ShoppingList Management");
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args) {
        launch();
    }
}
