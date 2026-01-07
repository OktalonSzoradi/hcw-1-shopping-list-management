module at.ac.hcw.bonk_shoppinglistmanagement {
    requires javafx.controls;
    requires javafx.fxml;


    opens at.ac.hcw.bonk_shoppinglistmanagement to javafx.fxml;
    exports at.ac.hcw.bonk_shoppinglistmanagement;
}