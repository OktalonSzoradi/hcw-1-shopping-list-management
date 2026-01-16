package at.ac.hcw.bonk_shoppinglistmanagement;

import at.ac.hcw.bonk_shoppinglistmanagement.logic.ShoppingList;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.StringConverter;

public class ShoppingListController {
    private final ObservableList<ShoppingList> shoppingLists =
            FXCollections.observableArrayList();
    private int shoppingListIdCounter = 0;

    @FXML
    private ListView<ShoppingList> listViewShoppingLists;

    @FXML
    private Button buttonAddEntry;
    @FXML
    private Button buttonPrint;
    @FXML
    private Button buttonDeleteShoppingList;
    @FXML
    private Label labelCurrentShoppingListTitle;

    @FXML
    public void initialize() {
        BooleanBinding noListSelected = listViewShoppingLists.getSelectionModel()
                .selectedItemProperty()
                .isNull();

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

        buttonDeleteShoppingList.disableProperty().bind(noListSelected);
        buttonAddEntry.disableProperty().bind(noListSelected);
        buttonPrint.disableProperty().bind(noListSelected);

        labelCurrentShoppingListTitle.textProperty().bind(
                Bindings.when(listViewShoppingLists.getSelectionModel().selectedItemProperty().isNotNull())
                        .then(Bindings.selectString(
                                listViewShoppingLists.getSelectionModel().selectedItemProperty(),
                                "title"
                        ))
                        .otherwise("No shopping list selected")
        );
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

    @FXML
    public void showAboutPopup() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Shopping List Manager");

        alert.setContentText("Created by: Oktalon, Nemanja, & Karl");

        alert.showAndWait();
    }
}
