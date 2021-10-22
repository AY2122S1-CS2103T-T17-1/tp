package seedu.address.model.person.supplier;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeliveryDetails {

    public static final String MESSAGE_CONSTRAINTS =
            "Delivery details is wrongly formatted. You need to input a date in yyyy-mm-dd or dd-mm-yyyy "
                    + "format and a time in 24hr clock format (eg: 18:00 for 6 pm). "
                    + "You can choose to entire enter a date first or time first in any of the formats mentioned";

    private static final DateTimeFormatter[] dateTimeFormatters = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"), DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"), DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy")
    };

    private static DateTimeFormatter chosenFormat = null;

    private final LocalDateTime deliveryDetails;
    private final String deliveryDetailsString;

    /**
     * Constructs a {@code Name}.
     *
     * @param deliveryDetails A valid delivery detail.
     */
    public DeliveryDetails(String deliveryDetails) {
        requireNonNull(deliveryDetails);
        checkArgument(isValidDeliveryDetail(deliveryDetails), MESSAGE_CONSTRAINTS);
        this.deliveryDetails = LocalDateTime.parse(deliveryDetails, chosenFormat);
        this.deliveryDetailsString = this.deliveryDetails.format(DateTimeFormatter.ofPattern("d MMMM yyyy, h:mm a"));
    }

    /**
     * Returns true if a given string is a valid delivery detail.
     */
    public static boolean isValidDeliveryDetail(String test) {
        LocalDateTime temp = null;
        for (DateTimeFormatter dateTimeFormatter : dateTimeFormatters) {
            try {
                temp = LocalDateTime.parse(test, dateTimeFormatter);
                chosenFormat = dateTimeFormatter;
            } catch (DateTimeException e) {
                //do nothing
            }
        }
        return temp != null;
    }

    /**
     * Returns the original date time string entered into the app by the user.
     */
    public String getUnformattedDeliveryDetailsString() {
        return this.deliveryDetails.format(chosenFormat);
    }

    @Override
    public String toString() {
        return this.deliveryDetailsString;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeliveryDetails // instanceof handles nulls
                && this.deliveryDetails.equals(((DeliveryDetails) other).deliveryDetails)); // state check
    }

    @Override
    public int hashCode() {
        return this.deliveryDetails.hashCode();
    }
}
