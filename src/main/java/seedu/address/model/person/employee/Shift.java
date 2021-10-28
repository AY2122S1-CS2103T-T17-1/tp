package seedu.address.model.person.employee;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Employee's shift in the address book.
 */
public class Shift {
    public static final String MESSAGE_CONSTRAINTS = "Working shifts should be in the format yyyy-mm-dd HHmm"
            + ".";

    public static final String VALIDATION_REGEX =
            "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9]) (2[0-3]|[01]?[0-9])([0-5]?[0-9])$";

    public final LocalDateTime workingShift;
    public final String shiftString;

    /**
     * Constructs an {@code Shift}.
     *
     * @param shift A valid shift.
     */
    public Shift(String shift) {
        requireNonNull(shift);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        checkArgument(isValidShift(shift), MESSAGE_CONSTRAINTS);
        workingShift = LocalDateTime.parse(shift, formatter);
        shiftString = this.workingShift.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns true if a given string is a valid shift.
     */
    public static boolean isValidShift(String test, DateTimeFormatter formatter) {
        try {
            formatter.parse(test);
            return true;
        } catch (DateTimeParseException dtpe) {
            return false;
        }
    }

    /**
     * Returns true if a given string is a valid shift.
     */
    public static boolean isValidShift(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return '[' + shiftString + ']';
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Shift // instanceof handles nulls
                && workingShift.equals(((Shift) other).workingShift)); // state check
    }

    @Override
    public int hashCode() {
        return workingShift.hashCode();
    }
}
