package seedu.address.model.person.supplier;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.SupplierBuilder;

public class SupplierClassContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        SupplierClassContainsKeywordsPredicate firstPredicate =
                new SupplierClassContainsKeywordsPredicate(firstPredicateKeywordList);
        SupplierClassContainsKeywordsPredicate secondPredicate =
                new SupplierClassContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        SupplierClassContainsKeywordsPredicate firstPredicateCopy =
                new SupplierClassContainsKeywordsPredicate(firstPredicateKeywordList);
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
        SupplierClassContainsKeywordsPredicate predicate =
                new SupplierClassContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new SupplierBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new SupplierClassContainsKeywordsPredicate(Arrays.asList("Alice", "chicken", "friends"));
        assertTrue(predicate.test(new SupplierBuilder().withName("Alice Bob").withSupplyType("Chicken")
                .withTags("friends").build()));

        // Only one matching keyword
        predicate = new SupplierClassContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertFalse(predicate.test(new SupplierBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new SupplierClassContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB", "fRiEnDs", "NoVEmBER"));
        assertTrue(predicate.test(new SupplierBuilder().withName("Alice Bob").withTags("friends")
                .withDeliveryDetails("2021-11-19 15:00").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        SupplierClassContainsKeywordsPredicate predicate =
                new SupplierClassContainsKeywordsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new SupplierBuilder().withName("Alice").build()));

        // Keywords match phone, email and address, but does not match anything else
        predicate = new SupplierClassContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street", "Beef", "Feb", "Fish", "Regular"));
        assertFalse(predicate.test(new SupplierBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));

        // Non-matching keyword
        predicate = new SupplierClassContainsKeywordsPredicate(Arrays.asList("Carol", "Beef", "Jan", "Texas"));
        assertFalse(predicate.test(new SupplierBuilder().withName("Alice Bob").withSupplyType("Chicken")
                .withTags("Regular").withAddress("Dallas").build()));
    }
}
