package at.ac.hcw.bonk_shoppinglistmanagement.logic;

public class ShoppingListSeparator implements ShoppingListElement {
    public ShoppingListSeparator() {
    }

    @Override
    public String toJSON() {
        return "{ \"type\": \"separator\" }";
    }
}
