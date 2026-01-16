package at.ac.hcw.bonk_shoppinglistmanagement.logic;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoppingList {

    private final int shoppingListID;
    private final ObservableList<ShoppingListElement> shoppingList; // NOT NULL
    private StringProperty title;
    private boolean isFavorite;                            // NOT NULL

    public ShoppingList(int shoppingListID, String title, boolean isFavorite) {
        this.shoppingListID = shoppingListID;
        this.title = new SimpleStringProperty(Objects.requireNonNull(title, "title must not be null"));
        this.isFavorite = isFavorite;
        this.shoppingList = FXCollections.observableArrayList();
    }

    public int getShoppingListID() {
        return shoppingListID;
    }

    public ObservableList<ShoppingListElement> getShoppingList() {
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

    public void print() throws IOException {
//        #set page(paper: "a5")
//        = My Shopping List
//        #table(
//                        columns: (1.75em, 1fr, auto, auto, auto),
//                align: (center, start, start, end, end),
//                table.header(
//                        [], [*Name*], [*Size*], [*Amount*], [*Price*]
//          ),
//          [], [Milk], [1 Liter], [1], [€ 1.99],
//        )
//        == #h(1fr) *Total:* € 1.99
        double total = 0.00;

        System.out.println("Try to print shopping list, building document");
        StringBuilder theDocument = new StringBuilder(String.format("#set page(paper: \"a5\")\n= %s\n", getTitle()));
        theDocument.append("#table(columns: (1.75em, 1fr, auto, auto, auto), align: (center, start, start, end, end),\n");
        theDocument.append("table.header([], [*Name*], [*Size*], [*Amount*], [*Price*]),\n");
        for (ShoppingListElement shoppingListElement : shoppingList) {
            if (shoppingListElement instanceof ShoppingListEntry entry) {
                theDocument.append(
                        String.format(
                                "[], [%s], [%s], [%d], [€ %.2f],\n",
                                entry.getProduct().getName(),
                                entry.getProduct().getSize(),
                                entry.getAmount(),
                                entry.getProduct().getPrice()
                        )
                );
                total += entry.getAmount() * entry.getProduct().getPrice();
            }
        }
        theDocument.append(String.format(")\n== #h(1fr) *Total:* € %.2f", total));

        // Now to make the file
        String fileName = String.format("[%s]_%s", shoppingListID, getTitle());
        String fileExtension = ".typ";
        Path typFile = Path.of(fileName + fileExtension);
        System.out.printf("Now trying to make file %s...\n", fileName + fileExtension);
        try {
            Files.writeString(
                    typFile,
                    theDocument.toString(),
                    StandardCharsets.UTF_8
            );
        } catch (IOException e) {
            System.out.println("Failed to make file!!");
            e.printStackTrace();
            return;
        }

        // Compile it!
        System.out.println("Try to compile...");
        try {
            ProcessBuilder pb = new ProcessBuilder(
                    "typst",
                    "compile",
                    typFile.toAbsolutePath().toString()
            );

            pb.inheritIO(); // show Typst output in console
            Process process = pb.start();
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            System.out.println("Failed to compile file!!");
            e.printStackTrace();
        }

        // Now to open the PDF
        System.out.println("Compilation successful, trying to open PDF");
        new Thread(() -> {
            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(new File(fileName + ".pdf"));
                } else {
                    System.out.println("Couldn't open PDF");
                }
            } catch (Exception e) {
                System.out.println("Exception thrown while trying to open the PDF!");
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public String toString() {
        if (isFavorite)
            return getTitle() + "★";
        return getTitle();
    }
}


