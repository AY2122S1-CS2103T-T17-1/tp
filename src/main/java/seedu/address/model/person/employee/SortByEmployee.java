package seedu.address.model.person.employee;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Comparator;

/**
 * Represents the types of sorting methods we can apply to employees in the address book.
 */
public class SortByEmployee {

    private static final String NAME = "n";
    private static final String PHONE = "p";
    private static final String ADDRESS = "a";
    private static final String EMAIL = "e";
    private static final String SALARY = "sal";
    private static final String LEAVES = "l";
    private static final String JOBTITLE = "jt";

    private static final String NAME_DESC = "name";
    private static final String PHONE_DESC = "phone";
    private static final String ADDRESS_DESC = "address";
    private static final String EMAIL_DESC = "email";
    private static final String SALARY_DESC = "salary";
    private static final String LEAVES_DESC = "leaves";
    private static final String JOBTITLE_DESC = "job title";

    public static final String MESSAGE_CONSTRAINTS = "Sort by can only be 1 of the employee fields: "
            + NAME_DESC + "/" + PHONE_DESC + "/" + ADDRESS_DESC + "/" + EMAIL_DESC + "/" + SALARY_DESC + "/"
            + LEAVES + "/" + JOBTITLE_DESC + " and it should not be blank";

    private final String sortBy;

    /**
     * Constructs an {@code Address}.
     *
     * @param sortBy A valid sorting type.
     */
    public SortByEmployee(String sortBy) {
        requireNonNull(sortBy);
        checkArgument(isValidSortingOrder(sortBy), MESSAGE_CONSTRAINTS);
        this.sortBy = sortBy;
    }

    /**
     * Returns true if a given string is a valid sorting type.
     */
    public static boolean isValidSortingOrder(String test) {
        switch (test) {
        case NAME:
        case ADDRESS:
        case EMAIL:
        case PHONE:
        case SALARY:
        case LEAVES:
        case JOBTITLE:
            return true;
        default:
            return false;
        }
    }

    /**
     * Returns a comparator to sort the employee list based on the sorting type and order given.
     * @param isAscending A boolean representing if sorting order is ascending.
     * @return A comparator to sort the employee list.
     */
    public Comparator<Employee> selectComparator(boolean isAscending) {
        switch (sortBy) {
        case ADDRESS:
            return EmployeeComparator.getAddressComparator(isAscending);
        case EMAIL:
            return EmployeeComparator.getEmailComparator(isAscending);
        case PHONE:
            return EmployeeComparator.getPhoneComparator(isAscending);
        case SALARY:
            return EmployeeComparator.getSalaryComparator(isAscending);
        case LEAVES:
            return EmployeeComparator.getLeavesComparator(isAscending);
        case JOBTITLE:
            return EmployeeComparator.getJobTitleComparator(isAscending);
        default:
            return EmployeeComparator.getNameComparator(isAscending);
        }
    }

    @Override
    public String toString() {
        switch (sortBy) {
        case ADDRESS:
            return ADDRESS_DESC;
        case EMAIL:
            return EMAIL_DESC;
        case PHONE:
            return PHONE_DESC;
        case SALARY:
            return SALARY_DESC;
        case LEAVES:
            return LEAVES_DESC;
        case JOBTITLE:
            return JOBTITLE_DESC;
        default:
            return NAME_DESC;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortByEmployee // instanceof handles nulls
                && sortBy.equals(((SortByEmployee) other).sortBy)); // state check
    }

    @Override
    public int hashCode() {
        return sortBy.hashCode();
    }
}
