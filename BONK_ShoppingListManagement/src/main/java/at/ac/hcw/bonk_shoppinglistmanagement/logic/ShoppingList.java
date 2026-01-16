package at.ac.hcw.bonk_shoppinglistmanagement.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoppingList {

    private final int shoppingListID;
    private final List<ShoppingListElement> shoppingList; // NOT NULL
    private String title;                                  // NOT NULL
    private boolean isFavorite;                            // NOT NULL

    public ShoppingList(int shoppingListID, String title, boolean isFavorite) {
        this.shoppingListID = shoppingListID;
        this.title = Objects.requireNonNull(title, "title must not be null");
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
        return title;
    }

    public void setTitle(String title) {
        this.title = Objects.requireNonNull(title, "title must not be null");
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

    // UML-Methode
    public void print() {
        System.out.println("=== ShoppingList #" + shoppingListID + " ===");
        System.out.println("Title: " + title + (isFavorite ? "  ★" : ""));
        System.out.println();

        if (shoppingList.isEmpty()) {
            System.out.println("(empty)");
            return;
        }

        for (int i = 0; i < shoppingList.size(); i++) {
            ShoppingListElement el = shoppingList.get(i);
            // Wenn eure Elementklassen toString sauber machen -> super
            System.out.printf("%2d) %s%n", i, el.toString());
        }
    }
}


