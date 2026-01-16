package at.ac.hcw.bonk_shoppinglistmanagement;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ShoppingListController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
