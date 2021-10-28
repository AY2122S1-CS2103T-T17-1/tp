package seedu.address.model.person.customer;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Customer}'s details matches any of the keywords given.
 */
public class CustomerClassContainsKeywordsPredicate implements Predicate<Customer> {
    private final List<String> keywords;

    public CustomerClassContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Customer customer) {
        return keywords.stream()
                .allMatch(keyword ->
                    StringUtil.containsSubstringIgnoreCase(
                            customer.getName().fullName, keyword)
                            || StringUtil.containsSubstringIgnoreCase(
                            customer.getEmail().value, keyword)
                            || StringUtil.containsSubstringIgnoreCase(
                            customer.getPhone().value, keyword)
                            || StringUtil.containsSubstringIgnoreCase(
                            customer.getAddress().value, keyword)
                            || StringUtil.containsSubstringIgnoreCase(
                            customer.getLoyaltyPoints().value, keyword)
                            || StringUtil.containsSubstringIgnoreCase(
                            customer.getAllergies().toString(), keyword)
                            || StringUtil.containsSubstringIgnoreCase(
                            customer.getSpecialRequests().toString(), keyword)
                            || StringUtil.containsSubstringIgnoreCase(
                            customer.getTags().toString(), keyword)
                );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CustomerClassContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((CustomerClassContainsKeywordsPredicate) other).keywords)); // state check
    }

}
