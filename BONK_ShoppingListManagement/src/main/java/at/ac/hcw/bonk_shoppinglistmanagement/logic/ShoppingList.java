package at.ac.hcw.bonk_shoppinglistmanagement.logic;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoppingList {

    private final int shoppingListID;
    private final List<ShoppingListElement> shoppingList; // NOT NULL
    private StringProperty title;
    private boolean isFavorite;                            // NOT NULL

    public ShoppingList(int shoppingListID, String title, boolean isFavorite) {
        this.shoppingListID = shoppingListID;
        this.title = new SimpleStringProperty(Objects.requireNonNull(title, "title must not be null"));
        this.isFavorite = isFavorite;
        this.shoppingList = new ArrayList<>();
    }

    public int getShoppingListID() {
        return shoppingListID;
    }

    public List<ShoppingListElement> getShoppingList() {
        return shoppingList;
    }

    public String getTitle() {
        return title.getValue();
    }

    public void setTitle(String title) {
        this.title = new SimpleStringProperty(Objects.requireNonNull(title, "title must not be null"));
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    // ---- App-Logik (sehr hilfreich) ----

    public void addElement(ShoppingListElement element) {
        shoppingList.add(Objects.requireNonNull(element, "element must not be null"));
    }

    public void addHeading(String content) {
        addElement(new ShoppingListHeading(content));
    }

    public void addSeparator() {
        addElement(new ShoppingListSeparator());
    }

    public void addEntry(Product product, int amount, int priority) {
        addElement(new ShoppingListEntry(product, amount, priority, false));
    }

    public boolean removeElementAt(int index) {
        if (index < 0 || index >= shoppingList.size()) return false;
        shoppingList.remove(index);
        return true;
    }

    public void print() {
    }

    @Override
    public String toString() {
        return title.getValue();
    }
}


