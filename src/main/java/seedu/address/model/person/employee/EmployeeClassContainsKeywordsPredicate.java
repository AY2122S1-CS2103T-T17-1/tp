package seedu.address.model.person.employee;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code employee}'s details matches any of the keywords given.
 */
public class EmployeeClassContainsKeywordsPredicate implements Predicate<Employee> {
    private final List<String> keywords;

    public EmployeeClassContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Employee employee) {
        return keywords.stream()
                .allMatch(keyword ->
                        StringUtil.containsSubstringNotSingleWordIgnoreCase(
                                employee.getName().fullName, keyword)
                                || StringUtil.containsSubstringNotSingleWordIgnoreCase(
                                employee.getEmail().value, keyword)
                                || StringUtil.containsSubstringNotSingleWordIgnoreCase(
                                employee.getPhone().value, keyword)
                                || StringUtil.containsSubstringNotSingleWordIgnoreCase(
                                employee.getAddress().value, keyword)
                                || StringUtil.containsSubstringNotSingleWordIgnoreCase(
                                employee.getLeaves().currentLeaves, keyword)
                                || StringUtil.containsSubstringNotSingleWordIgnoreCase(
                                employee.getJobTitle().jobTitle, keyword)
                                || StringUtil.containsSubstringNotSingleWordIgnoreCase(
                                employee.getSalary().currentSalary, keyword)
                                || StringUtil.containsSubstringNotSingleWordIgnoreCase(
                                employee.getShifts().toString(), keyword)
                                || StringUtil.containsSubstringNotSingleWordIgnoreCase(
                                employee.getTags().toString(), keyword)
                );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmployeeClassContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((EmployeeClassContainsKeywordsPredicate) other).keywords)); // state check
    }

}
