package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.util.CommandUtil;
import seedu.address.model.Model;

/**
 * Resets the employee list sorting to the default sorting type.
 */
public class ResetEmployeeSortCommand extends Command {

    public static final String COMMAND_WORD = "resete";

    public static final String MESSAGE_USAGE = CommandUtil.formatCommandWord(COMMAND_WORD)
            + ": Resets the employee list to the default sorting type.\n"
            + "Example: " + CommandUtil.formatCommandWord(COMMAND_WORD);

    public static final String SHOWING_RESET_MESSAGE = "Employee list sorting has been reset to default (name).";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.resetEmployeeListToDefaultSortState();
        return new CommandResult(SHOWING_RESET_MESSAGE, false, false, false, true, false, false);
    }
}
