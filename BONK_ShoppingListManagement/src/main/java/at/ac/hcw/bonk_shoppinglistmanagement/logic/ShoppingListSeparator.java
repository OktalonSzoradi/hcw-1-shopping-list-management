package at.ac.hcw.bonk_shoppinglistmanagement.logic;

public final class ShoppingListSeparator implements ShoppingListElement {
    public ShoppingListSeparator() {
    }

    @Override
    public String toJSON() {
        return "{ \"type\": \"separator\" }";

    @Override
    public String toString() {
        return "----------------";
    }
}
