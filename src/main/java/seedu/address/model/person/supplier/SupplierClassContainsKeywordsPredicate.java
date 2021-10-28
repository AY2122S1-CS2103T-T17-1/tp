package seedu.address.model.person.supplier;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;


/**
 * Tests that a {@code Supplier}'s details matches any of the keywords given.
 */
public class SupplierClassContainsKeywordsPredicate implements Predicate<Supplier> {
    private final List<String> keywords;

    public SupplierClassContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Supplier supplier) {
        return keywords.stream()
                .allMatch(keyword ->
                         StringUtil.containsSubstringIgnoreCase(
                                 supplier.getName().fullName, keyword)
                                 || StringUtil.containsSubstringIgnoreCase(
                                 supplier.getEmail().value, keyword)
                                 || StringUtil.containsSubstringIgnoreCase(
                                 supplier.getPhone().value, keyword)
                                 || StringUtil.containsSubstringIgnoreCase(
                                 supplier.getAddress().value, keyword)
                                 || StringUtil.containsSubstringIgnoreCase(
                                 supplier.getSupplyType().supplyType, keyword)
                                 || StringUtil.containsSubstringIgnoreCase(
                                 supplier.getDeliveryDetails().toString(), keyword)
                                 || StringUtil.containsSubstringIgnoreCase(
                                 supplier.getTags().toString(), keyword)
                );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SupplierClassContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((SupplierClassContainsKeywordsPredicate) other).keywords)); // state check
    }
}
