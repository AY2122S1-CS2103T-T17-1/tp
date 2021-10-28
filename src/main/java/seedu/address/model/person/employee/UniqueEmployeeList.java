package seedu.address.model.person.employee;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicateEmployeeException;
import seedu.address.model.person.exceptions.EmployeeNotFoundException;

/**
 * A list of employees that enforces uniqueness between its elements and does not allow nulls.
 * An employee is considered unique by comparing using {@code Employee#isSameEmployee(Employee)}.
 * As such, adding and updating of employees uses Employee#isSameEmployee(Employee) for equality so as to
 * ensure that the employee being added or updated is unique in terms of identity in the UniqueEmployeeList.
 * However, the removal of an employee uses Employee#equals(Object) so as to ensure that the employee with exactly
 * the same fields will be removed.
 * Supports a minimal set of list operations.
 *
 * @see Employee#isSameEmployee(Employee)
 */
public class UniqueEmployeeList implements Iterable<Employee> {

    private final ObservableList<Employee> internalList = FXCollections.observableArrayList();
    private final ObservableList<Employee> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);
    private Comparator<Employee> employeeComparator = EmployeeComparator.getDefaultComparator();

    /**
     * Returns true if the list contains an equivalent employee as the given argument.
     */
    public boolean contains(Employee toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEmployee);
    }

    /**
     * Adds a employee to the list.
     * The employee must not already exist in the list.
     */
    public void add(Employee toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateEmployeeException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the employee {@code target} in the list with {@code editedEmployee}.
     * {@code target} must exist in the list.
     * The employee identity of {@code editedEmployee} must not be the same as another existing employee in the list.
     */
    public void setEmployee(Employee target, Employee editedEmployee) {
        requireAllNonNull(target, editedEmployee);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new EmployeeNotFoundException();
        }

        if (!target.isSamePerson(editedEmployee) && contains(editedEmployee)) {
            throw new DuplicateEmployeeException();
        }

        internalList.set(index, editedEmployee);
        internalList.sort(employeeComparator);
    }

    /**
     * Removes the equivalent employee from the list.
     * The employee must exist in the list.
     */
    public void remove(Employee toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EmployeeNotFoundException();
        }
        internalList.sort(employeeComparator);
    }

    public void setEmployees(UniqueEmployeeList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
        internalList.sort(employeeComparator);
    }

    /**
     * Replaces the contents of this list with {@code employees}.
     * {@code employees} must not contain duplicate persons.
     */
    public void setEmployees(List<Employee> employees) {
        requireAllNonNull(employees);
        if (!employeesAreUnique(employees)) {
            throw new DuplicateEmployeeException();
        }

        internalList.setAll(employees);
        internalList.sort(employeeComparator);
    }

    /**
     * Resets the employee list to its default sorting state.
     */
    public void resetEmployeeListToDefaultSortState() {
        internalList.sort(EmployeeComparator.getDefaultComparator());
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Employee> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns the backing list as a sortable {@code ObservableList}.
     */
    public ObservableList<Employee> asSortableObservableList() {
        return internalList;
    }

    public void setComparator(Comparator<Employee> comparator) {
        employeeComparator = comparator;
    }

    public Comparator<Employee> getComparator() {
        return employeeComparator;
    }

    @Override
    public Iterator<Employee> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueEmployeeList // instanceof handles nulls
                && internalList.equals(((UniqueEmployeeList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code employees} contains only unique employees.
     */
    private boolean employeesAreUnique(List<Employee> employees) {
        for (int i = 0; i < employees.size() - 1; i++) {
            for (int j = i + 1; j < employees.size(); j++) {
                if (employees.get(i).isSamePerson(employees.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
