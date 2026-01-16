package at.ac.hcw.bonk_shoppinglistmanagement;

import at.ac.hcw.bonk_shoppinglistmanagement.logic.Product;
import at.ac.hcw.bonk_shoppinglistmanagement.logic.ShoppingList;
import at.ac.hcw.bonk_shoppinglistmanagement.logic.ShoppingListElement;
import at.ac.hcw.bonk_shoppinglistmanagement.logic.ShoppingListEntry;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListController {
    private final ObservableList<ShoppingList> shoppingLists = FXCollections.observableArrayList();
    private int shoppingListIdCounter = 0;
    private int productIdCounter = 0;

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
    private Label labelTotal;

    @FXML
    private TableView<ShoppingListEntry> tableViewProducts;
    @FXML
    private TableColumn<ShoppingListEntry, String> columnName;
    @FXML
    private TableColumn<ShoppingListEntry, String> columnSize;
    @FXML
    private TableColumn<ShoppingListEntry, Integer> columnAmount;
    @FXML
    private TableColumn<ShoppingListEntry, Double> columnPrice;

    private void initializeTable() {
        // Make table editable
        tableViewProducts.setEditable(true);

        // Name column
        columnName.setCellValueFactory(cellData -> cellData.getValue().getProduct().nameProperty());
        columnName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnName.setOnEditCommit(event -> event.getRowValue().getProduct().setName(event.getNewValue()));

        // Size column
        columnSize.setCellValueFactory(cellData -> cellData.getValue().getProduct().sizeProperty());
        columnSize.setCellFactory(TextFieldTableCell.forTableColumn());
        columnSize.setOnEditCommit(event -> event.getRowValue().getProduct().setSize(event.getNewValue()));

        // Price column
        columnPrice.setCellValueFactory(cellData -> cellData.getValue().getProduct().priceProperty().asObject());
        columnPrice.setCellFactory(TextFieldTableCell.forTableColumn(new javafx.util.converter.DoubleStringConverter()));
        columnPrice.setOnEditCommit(event -> event.getRowValue().getProduct().setPrice(event.getNewValue()));

        // Amount column
        columnAmount.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());
        columnAmount.setCellFactory(TextFieldTableCell.forTableColumn(new javafx.util.converter.IntegerStringConverter()));
        columnAmount.setOnEditCommit(event -> event.getRowValue().setAmount(event.getNewValue()));

        // Optional: update the TableView when selection changes
//        listViewShoppingLists.getSelectionModel().selectedItemProperty().addListener((obs, oldList, newList) -> {
//            if (newList != null) {
//                tableViewProducts.setItems(newList.getEntries()); // Assuming getEntries() returns ObservableList<ShoppingListEntry>
//            } else {
//                tableViewProducts.setItems(FXCollections.observableArrayList());
//            }
//        });
    }

    private void bindTotalToShoppingList(ShoppingList shoppingList) {
        labelTotal.textProperty().unbind();

        if (shoppingList == null) {
            labelTotal.setText("Total: €0.00");
            return;
        }

        labelTotal.textProperty().bind(Bindings.createStringBinding(() -> {
            double total = 0.0;

            for (ShoppingListElement el : shoppingList.getShoppingList()) {
                if (el instanceof ShoppingListEntry entry) {
                    total += entry.getAmount() * entry.getProduct().getPrice();
                }
            }

            return String.format("Total: €%.2f", total);
        }, createTotalDependencies(shoppingList)));
    }

    private Observable[] createTotalDependencies(ShoppingList shoppingList) {
        List<Observable> deps = new ArrayList<>();
        deps.add(shoppingList.getShoppingList()); // add/remove entries

        for (ShoppingListElement el : shoppingList.getShoppingList()) {
            if (el instanceof ShoppingListEntry entry) {
                deps.add(entry.amountProperty());
                deps.add(entry.getProduct().priceProperty());
            }
        }

        return deps.toArray(new Observable[0]);
    }


    @FXML
    public void initialize() {
        BooleanBinding noListSelected = listViewShoppingLists.getSelectionModel().selectedItemProperty().isNull();

        // --- ListView setup ---
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

        // --- Buttons reactive state ---
        buttonDeleteShoppingList.disableProperty().bind(noListSelected);
        buttonAddEntry.disableProperty().bind(noListSelected);
        buttonPrint.disableProperty().bind(noListSelected);

        // --- Label for current shopping list ---
        labelCurrentShoppingListTitle.textProperty().bind(Bindings.when(listViewShoppingLists.getSelectionModel().selectedItemProperty().isNotNull()).then(Bindings.selectString(listViewShoppingLists.getSelectionModel().selectedItemProperty(), "title")).otherwise("No shopping list selected"));

        // --- Initialize TableView columns ---
        initializeTable();

        // --- Update TableView when a shopping list is selected ---
        listViewShoppingLists.getSelectionModel().selectedItemProperty().addListener((obs, oldList, newList) -> {
            if (newList != null) {
                ObservableList<ShoppingListEntry> entries = FXCollections.observableArrayList();

                for (ShoppingListElement el : newList.getShoppingList()) {
                    if (el instanceof ShoppingListEntry entry) {
                        entries.add(entry);
                    }
                }

                bindTotalToShoppingList(newList);

                newList.getShoppingList().addListener((ListChangeListener<ShoppingListElement>) c -> bindTotalToShoppingList(newList));

                tableViewProducts.setItems(entries);
            } else {
                tableViewProducts.setItems(FXCollections.observableArrayList()); // empty table
            }
        });

        // Optional: placeholder for empty table
        tableViewProducts.setPlaceholder(new Label("Select a shopping list to see its products"));
    }

    @FXML
    public void newShoppingList() {
        shoppingLists.add(new ShoppingList(shoppingListIdCounter, "Untitled Shopping List", false));
        shoppingListIdCounter += 1;
    }

    @FXML
    public void addProduct() {
        // Get the currently selected shopping list
        ShoppingList selectedList = listViewShoppingLists.getSelectionModel().getSelectedItem();

        if (selectedList != null) {
            // Add a new product to it (placeholder)
            selectedList.addEntry(new Product(productIdCounter, "", "", 0.00, false), 1, 0);

            productIdCounter += 1;

            // Re-filter the entries to update the TableView
            ObservableList<ShoppingListEntry> entries = FXCollections.observableArrayList();
            for (ShoppingListElement el : selectedList.getShoppingList()) {
                if (el instanceof ShoppingListEntry entry) {
                    entries.add(entry);
                }
            }
            tableViewProducts.setItems(entries);
            tableViewProducts.getSelectionModel().selectLast();
        } else {
            // Should never happen if buttons are properly disabled
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Shopping List Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a shopping list first.");
            alert.showAndWait();
        }
    }

    @FXML
    public void showAboutPopup() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Shopping List Manager");

        alert.setContentText("Created by: Oktalon, Nemanja, & Karl");

        alert.showAndWait();
    }

    @FXML
    public void deleteSelectedList() {
        ShoppingList selectedList = listViewShoppingLists.getSelectionModel().getSelectedItem();

        if (selectedList == null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a shopping list to delete.");
            alert.showAndWait();
            return;
        }

        Alert confirm = new Alert(AlertType.CONFIRMATION);
        confirm.setTitle("Delete List");
        confirm.setHeaderText("Delete '" + selectedList + "'?");
        confirm.setContentText("Are you sure you want to delete this shopping list? This cannot be undone.");

        confirm.showAndWait().ifPresent(response -> {
            if (response == javafx.scene.control.ButtonType.OK) {
                listViewShoppingLists.getItems().remove(selectedList);

                listViewShoppingLists.getSelectionModel().clearSelection();
            }
        });
    }
}
