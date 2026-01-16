package at.ac.hcw.bonk_shoppinglistmanagement.logic;

import javafx.beans.property.*;

import java.util.Objects;

public class Product {

    private final IntegerProperty productID = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty size = new SimpleStringProperty();
    private final DoubleProperty price = new SimpleDoubleProperty();
    private final BooleanProperty isFavorite = new SimpleBooleanProperty();

    public Product(int productID, String name, String size, double price, boolean isFavorite) {
        this.productID.set(productID);
        this.name.set(Objects.requireNonNull(name, "name must not be null"));
        this.size.set(size);
        this.price.set(price);
        this.isFavorite.set(isFavorite);
    }

    public Product(int productID, String name, double price, boolean isFavorite) {
        this(productID, name, null, price, isFavorite);
    }

    public int getProductID() { return productID.get(); }
    public void setProductID(int id) { this.productID.set(id); }

    public String getName() { return name.get(); }
    public void setName(String name) { this.name.set(Objects.requireNonNull(name)); }

    public String getSize() { return size.get(); }
    public void setSize(String size) { this.size.set(size); }

    public double getPrice() { return price.get(); }
    public void setPrice(double price) { this.price.set(price); }

    public boolean isFavorite() { return isFavorite.get(); }
    public void setFavorite(boolean favorite) { this.isFavorite.set(favorite); }

    public IntegerProperty productIDProperty() { return productID; }
    public StringProperty nameProperty() { return name; }
    public StringProperty sizeProperty() { return size; }
    public DoubleProperty priceProperty() { return price; }
    public BooleanProperty isFavoriteProperty() { return isFavorite; }

    public String toJSON() {
        return "{"
                + "\"productID\":" + getProductID() + ","
                + "\"name\":\"" + jsonEscape(getName()) + "\","
                + "\"size\":" + (getSize() == null ? "null" : "\"" + jsonEscape(getSize()) + "\"") + ","
                + "\"price\":" + getPrice() + ","
                + "\"isFavorite\":" + isFavorite()
                + "}";
    }

    private static String jsonEscape(String s) {
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    @Override
    public String toString() {
        String s = getName();
        if (getSize() != null && !getSize().isBlank()) s += " (" + getSize() + ")";
        return s + (isFavorite() ? " ★" : "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product other)) return false;
        return getProductID() == other.getProductID();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(getProductID());
    }
}
