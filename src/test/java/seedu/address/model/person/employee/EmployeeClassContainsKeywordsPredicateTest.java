package seedu.address.model.person.employee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EmployeeBuilder;

public class EmployeeClassContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        EmployeeClassContainsKeywordsPredicate firstPredicate =
                new EmployeeClassContainsKeywordsPredicate(firstPredicateKeywordList);
        EmployeeClassContainsKeywordsPredicate secondPredicate =
                new EmployeeClassContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EmployeeClassContainsKeywordsPredicate firstPredicateCopy =
                new EmployeeClassContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        EmployeeClassContainsKeywordsPredicate predicate =
                new EmployeeClassContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new EmployeeBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new EmployeeClassContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new EmployeeBuilder().withName("Alice Bob").build()));

        // Mixed-case keywords
        predicate = new EmployeeClassContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new EmployeeBuilder().withName("Alice Bob").build()));

        // Keywords contains same phone, email and address
        predicate = new EmployeeClassContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertTrue(predicate.test(new EmployeeBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));

        // Zero keywords
        predicate = new EmployeeClassContainsKeywordsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new EmployeeBuilder().withName("Alice").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Only one matching keyword
        EmployeeClassContainsKeywordsPredicate predicate =
                new EmployeeClassContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertFalse(predicate.test(new EmployeeBuilder().withName("Alice Carol").build()));

        // Non-matching keyword
        predicate = new EmployeeClassContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new EmployeeBuilder().withName("Alice Bob").build()));
    }
}
