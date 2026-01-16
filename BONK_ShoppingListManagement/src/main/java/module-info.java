module at.ac.hcw.bonk_shoppinglistmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens at.ac.hcw.bonk_shoppinglistmanagement to javafx.fxml;
    exports at.ac.hcw.bonk_shoppinglistmanagement;
    exports at.ac.hcw.bonk_shoppinglistmanagement.logic;
}