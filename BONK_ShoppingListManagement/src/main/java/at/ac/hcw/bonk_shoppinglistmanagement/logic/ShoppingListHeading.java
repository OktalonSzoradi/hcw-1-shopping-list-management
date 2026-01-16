package at.ac.hcw.bonk_shoppinglistmanagement.logic;

public class ShoppingListHeading implements ShoppingListElement {
    private String content;

    public ShoppingListHeading(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toJSON() {
        return String.format("{ \"type\": \"heading\", \"content\": \"%s\" }", content);
    }
}
