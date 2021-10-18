package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /**
     * The application should switch to customer view.
     */
    private final boolean showCustomer;

    /**
     * The application should switch to employee view.
     */
    private final boolean showEmployee;

    /**
     * The application should switch to supplier view.
     */
    private final boolean showSupplier;

    /**
     * The application should switch to reservation view.
     */
    private final boolean showReservation;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit,
                         boolean showCustomer, boolean showEmployee, boolean showSupplier,
                         boolean showReservation) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.showCustomer = showCustomer;
        this.showReservation = showReservation;
        this.showEmployee = showEmployee;
        this.showSupplier = showSupplier;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isShowCustomer() {
        return showCustomer;
    }

    public boolean isShowEmployee() {
        return showCustomer;
    }

    public boolean isShowReservation() {
        return showCustomer;
    }

    public boolean isShowSupplier() {
        return showSupplier;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit
                && showCustomer == otherCommandResult.showCustomer
                && showEmployee == otherCommandResult.showEmployee
                && showReservation == otherCommandResult.showReservation
                && showSupplier == otherCommandResult.showSupplier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, showCustomer, showSupplier,
                showEmployee, showReservation);
    }

}
