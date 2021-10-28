package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_CUSTOMERS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CustomerCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCustomers.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.customer.Customer;
import seedu.address.model.person.customer.CustomerClassContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCustomerCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        CustomerClassContainsKeywordsPredicate firstPredicate =
                new CustomerClassContainsKeywordsPredicate(Collections.singletonList("first"));
        CustomerClassContainsKeywordsPredicate secondPredicate =
                new CustomerClassContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCustomerCommand findFirstCommand = new FindCustomerCommand(firstPredicate);
        FindCustomerCommand findSecondCommand = new FindCustomerCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCustomerCommand findFirstCommandCopy = new FindCustomerCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_allPersonsFound() {
        String expectedMessage = String.format(MESSAGE_CUSTOMERS_LISTED_OVERVIEW,
                model.getFilteredCustomerList().size());
        ObservableList<Customer> customersList = model.getFilteredCustomerList();
        CustomerClassContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCustomerCommand command = new FindCustomerCommand(predicate);
        expectedModel.updateFilteredCustomerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(customersList, model.getFilteredCustomerList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsNamesNotFound() {
        String expectedMessage = String.format(MESSAGE_CUSTOMERS_LISTED_OVERVIEW, 0);
        CustomerClassContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCustomerCommand command = new FindCustomerCommand(predicate);
        expectedModel.updateFilteredCustomerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredCustomerList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private CustomerClassContainsKeywordsPredicate preparePredicate(String userInput) {
        return new CustomerClassContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
