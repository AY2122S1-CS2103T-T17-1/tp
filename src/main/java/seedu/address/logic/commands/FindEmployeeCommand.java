package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.util.CommandUtil;
import seedu.address.model.Model;
import seedu.address.model.person.employee.EmployeeClassContainsKeywordsPredicate;

/**
 * Finds and lists all employees in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindEmployeeCommand extends Command {

    public static final String COMMAND_WORD = "finde";

    public static final String MESSAGE_USAGE = CommandUtil.formatCommandWord(COMMAND_WORD)
            + ": Finds all employees that contain "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + CommandUtil.formatCommandWord(COMMAND_WORD) + " 2021-12-08 0800";

    private final EmployeeClassContainsKeywordsPredicate predicate;

    public FindEmployeeCommand(EmployeeClassContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEmployeeList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EMPLOYEES_LISTED_OVERVIEW,
                        model.getFilteredEmployeeList().size()), false, false, false,
                true, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindEmployeeCommand // instanceof handles nulls
                && predicate.equals(((FindEmployeeCommand) other).predicate)); // state check
    }
}
