package at.ac.hcw.bonk_shoppinglistmanagement;

import at.ac.hcw.bonk_shoppinglistmanagement.logic.ShoppingList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.StringConverter;

public class ShoppingListController {
    private final ObservableList<ShoppingList> shoppingLists =
            FXCollections.observableArrayList();

    @FXML
    private ListView<ShoppingList> listViewShoppingLists;

    private int shoppingListIdCounter = 0;

    @FXML
    public void initialize() {
        listViewShoppingLists.setItems(shoppingLists);
        listViewShoppingLists.setEditable(true);
        listViewShoppingLists.setCellFactory(lv -> new TextFieldListCell<>(new StringConverter<>() {

            @Override
            public String toString(ShoppingList item) {
                return item == null ? "" : item.getTitle();
            }

            @Override
            public ShoppingList fromString(String newTitle) {
                return new ShoppingList(-1, newTitle, false); // only used to commit edit
            }
        }));
    }

    @FXML
    public void newShoppingList() {
        shoppingLists.add(
                new ShoppingList(
                        shoppingListIdCounter,
                        "Untitled Shopping List",
                        false
                )
        );
        shoppingListIdCounter += 1;
    }
}
