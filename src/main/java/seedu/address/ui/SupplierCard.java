package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.supplier.Supplier;

/**
 * An UI component that displays information of a {@code Supplier}.
 */
public class SupplierCard extends UiPart<Region> {

    private static final String FXML = "SupplierListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on Rhrh level 4</a>
     */

    public final Supplier supplier;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label supplyType;
    @FXML
    private Label deliveryDetails;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code SupplierCode} with the given {@code Supplier} and index to display.
     */
    public SupplierCard(Supplier supplier, int displayedIndex) {
        super(FXML);
        this.supplier = supplier;
        id.setText(displayedIndex + ". ");
        name.setText(supplier.getName().fullName);
        phone.setText("Phone: " + supplier.getPhone().value);
        address.setText("Address: " + supplier.getAddress().value);
        email.setText("Email: " + supplier.getEmail().value);
        supplier.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        supplyType.setText("Supply Type: " + supplier.getSupplyType().supplyType);
        deliveryDetails.setText("Delivery Details: " + supplier.getDeliveryDetails().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SupplierCard)) {
            return false;
        }

        // state check
        SupplierCard card = (SupplierCard) other;
        return id.getText().equals(card.id.getText())
                && supplier.equals(card.supplier);
    }
}
