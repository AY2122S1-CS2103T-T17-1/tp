package seedu.address.model.person.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CustomerBuilder;

public class CustomerClassContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        CustomerClassContainsKeywordsPredicate firstPredicate =
                new CustomerClassContainsKeywordsPredicate(firstPredicateKeywordList);
        CustomerClassContainsKeywordsPredicate secondPredicate =
                new CustomerClassContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CustomerClassContainsKeywordsPredicate firstPredicateCopy =
                new CustomerClassContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different Customer -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_customerClassContainsKeywords_returnsTrue() {
        // One keyword
        CustomerClassContainsKeywordsPredicate predicate =
                new CustomerClassContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new CustomerClassContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));

        // Mixed-case keywords
        predicate = new CustomerClassContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new CustomerClassContainsKeywordsPredicate(Arrays.asList("12345",
                "alice@email.com", "Main", "Street"));
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));

        // Zero keywords
        predicate = new CustomerClassContainsKeywordsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Only one matching keyword
        CustomerClassContainsKeywordsPredicate predicate =
                new CustomerClassContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertFalse(predicate.test(new CustomerBuilder().withName("Alice Carol").build()));

        // Non-matching keyword
        predicate = new CustomerClassContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));
    }
}
