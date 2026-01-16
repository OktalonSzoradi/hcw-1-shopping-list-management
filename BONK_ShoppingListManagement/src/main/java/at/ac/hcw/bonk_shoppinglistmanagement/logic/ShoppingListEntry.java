package at.ac.hcw.bonk_shoppinglistmanagement.logic;

import javafx.beans.property.*;

import java.util.Objects;

public final class ShoppingListEntry implements ShoppingListElement {

    private final ObjectProperty<Product> product = new SimpleObjectProperty<>();
    private final IntegerProperty amount = new SimpleIntegerProperty();
    private final IntegerProperty priority = new SimpleIntegerProperty();
    private final BooleanProperty checked = new SimpleBooleanProperty();

    public ShoppingListEntry(Product product, int amount, int priority, boolean checked) {
        this.product.set(Objects.requireNonNull(product, "product must not be null"));
        this.amount.set(amount);
        this.priority.set(priority);
        this.checked.set(checked);
    }

    public Product getProduct() { return product.get(); }
    public void setProduct(Product product) {
        this.product.set(Objects.requireNonNull(product));
    }

    public int getAmount() { return amount.get(); }
    public void setAmount(int amount) { this.amount.set(amount); }

    public int getPriority() { return priority.get(); }
    public void setPriority(int priority) { this.priority.set(priority); }

    public boolean isChecked() { return checked.get(); }
    public void setChecked(boolean checked) { this.checked.set(checked); }

    public ObjectProperty<Product> productProperty() { return product; }
    public IntegerProperty amountProperty() { return amount; }
    public IntegerProperty priorityProperty() { return priority; }
    public BooleanProperty checkedProperty() { return checked; }

    public String toJSON() {
        return "{"
                + "\"type\":\"entry\","
                + "\"product\":" + product.get().toJSON() + ","
                + "\"amount\":" + getAmount() + ","
                + "\"priority\":" + getPriority() + ","
                + "\"checked\":" + isChecked()
                + "}";
    }

    @Override
    public String toString() {
        String text = getProduct().getName()
                + " x" + getAmount()
                + " (prio " + getPriority() + ")";
        return isChecked() ? "✔ " + text : "☐ " + text;
    }
}
