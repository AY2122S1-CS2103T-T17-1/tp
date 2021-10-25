package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.supplier.SupplierClassContainsKeywordsPredicate;

/**
 * Finds and lists all suppliers in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindSupplierCommand extends Command {

    public static final String COMMAND_WORD = "findS";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all suppliers that contain "
            + "any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice chicken November";

    private final SupplierClassContainsKeywordsPredicate predicate;

    public FindSupplierCommand(SupplierClassContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredSupplierList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_SUPPLIERS_LISTED_OVERVIEW,
                        model.getFilteredSupplierList().size()), false, false, false,
                false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindSupplierCommand // instanceof handles nulls
                && predicate.equals(((FindSupplierCommand) other).predicate)); // state check
    }
}
