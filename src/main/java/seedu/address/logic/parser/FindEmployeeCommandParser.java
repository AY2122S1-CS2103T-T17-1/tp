package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindEmployeeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.employee.EmployeeClassContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindEmployeeCommand object
 */
public class FindEmployeeCommandParser implements Parser<FindEmployeeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindEmployeeCommand
     * and returns a FindEmployeeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindEmployeeCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEmployeeCommand.MESSAGE_USAGE));
        }

        //String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindEmployeeCommand(new EmployeeClassContainsKeywordsPredicate(Arrays.asList(trimmedArgs)));
    }

}
