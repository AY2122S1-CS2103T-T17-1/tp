package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Phone;
import seedu.address.model.person.customer.Customer;
import seedu.address.model.person.employee.Employee;
import seedu.address.model.person.supplier.Supplier;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.reservation.ReservationsManager;
import seedu.address.model.table.Table;
import seedu.address.model.table.TableManager;

/**
 * The API of the Model component.
 */
public interface Model {

    /** {@code Predicate} that always evaluate to true */
    Predicate<Customer> PREDICATE_SHOW_ALL_CUSTOMERS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Reservation> PREDICATE_SHOW_ALL_RESERVATIONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Supplier> PREDICATE_SHOW_ALL_SUPPLIERS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Employee> PREDICATE_SHOW_ALL_EMPLOYEES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a customer with the same identity as {@code customer} exists in the address book.
     */
    boolean hasCustomer(Customer customer);

    /**
     * Returns true if a customer with the same phone as {@code phone} exists in the address book.
     */
    boolean hasCustomerWithPhone(Phone phone);

    /**
     * Returns true if an employee with the same identity as {@code employee} exists in the address book.
     */
    boolean hasEmployee(Employee employee);

    /**
     * Deletes the given customer.
     * The customer must exist in the address book.
     */
    void deleteCustomer(Customer target);
    /**
     * Deletes the given employee.
     * The employee must exist in the address book.
     */
    void deleteEmployee(Employee employee);

    /**
     * Adds the given customer.
     * {@code customer} must not already exist in the address book.
     */
    void addCustomer(Customer customer);
    /**
     * Adds the given employee.
     * {@code employee} must not already exist in the address book.
     */
    void addEmployee(Employee employee);

    /**
     * Replaces the given customer {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The customer identity of {@code editedCustomer} must not be the same as another existing customer in
     * the address book.
     */
    void setCustomer(Customer target, Customer editedCustomer);

    /** Sets the comparator for the customer list */
    void setCustomerComparator(Comparator<Customer> customerComparator);

    /** Returns a sortable view of the customer list */
    ObservableList<Customer> getSortableCustomerList();

    /** Resets the customer list to its default sorting state */
    void resetCustomerListToDefaultSortState();

    /**
     * Replaces the given employee {@code target} with {@code editedEmployee}.
     * {@code target} must exist in the address book.
     * The employee identity of {@code editedEmployee} must not be the
     * same as another existing employee in the address book.
     */
    void setEmployee(Employee target, Employee editedEmployee);

    /** Sets the comparator for the employee list */
    void setEmployeeComparator(Comparator<Employee> employeeComparator);

    /**
     * Returns true if the same reservation exists in the database.
     */
    boolean hasReservation(Reservation reservation);

    /**
     * Deletes the given reservation.
     * The reservation must exist in the database.
     */
    void deleteReservation(Reservation target);

    /**
     * Adds the given reservation.
     */
    void addReservation(Reservation reservation);

    /**
     * Replaces the given reservation {@code target} with {@code editedReservation}.
     */
    void setReservation(Reservation target, Reservation editedReservation);

    /**
     * Removes all reservations
     */
    void resetReservations();

    /**
     * Adds the given table list.
     */
    void setTableList(List<Table> tableList);

    /**
     * Resets the count of tables to zero
     */
    void resetTableCount();


    /** Returns an unmodifiable view of the filtered customer list */
    ObservableList<Customer> getFilteredCustomerList();

    /** Returns an unmodifiable view of the filtered employee list */
    ObservableList<Employee> getFilteredEmployeeList();

    /**
     * Returns an unmodifiable view of the filtered reservation list
     */
    ObservableList<Reservation> getFilteredReservationList();

    ReservationsManager getReservationsManager();

    TableManager getTableManager();

    /**

     * Updates the filter of the filtered customer list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCustomerList(Predicate<Customer> predicate);

    /** Returns a sortable view of the employee list */
    ObservableList<Employee> getSortableEmployeeList();

    /** Resets the employee list to its default sorting state */
    void resetEmployeeListToDefaultSortState();

    /**
     * Updates the filter of the filtered Employee list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEmployeeList(Predicate<Employee> predicate);

    /**
     * Returns true if a supplier with the same identity as {@code supplier} exists in the address book.
     */
    boolean hasSupplier(Supplier supplier);

    /**
     * Deletes the given supplier.
     * The person must exist in the address book.
     */
    void deleteSupplier(Supplier target);

    /**
     * Adds the given supplier.
     * {@code supplier} must not already exist in the address book.
     */
    void addSupplier(Supplier supplier);

    /**
     * Replaces the given person {@code target} with {@code editedSupplier}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedSupplier} must not be the same as another existing supplier in the address
     * book.
     */
    void setSupplier(Supplier target, Supplier editedSupplier);

    /** Sets the comparator for the supplier list */
    void setSupplierComparator(Comparator<Supplier> supplierComparator);

    /** Returns an unmodifiable view of the filtered supplier list */
    ObservableList<Supplier> getFilteredSupplierList();

    /** Returns a sortable view of the supplier list */
    ObservableList<Supplier> getSortableSupplierList();

    /** Resets the supplier list to its default sorting state */
    void resetSupplierListToDefaultSortState();

    /**
     * Updates the filter of the filtered supplier list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredSupplierList(Predicate<Supplier> predicate);

    /**
     * Updates the filter of the filtered reservation list to filter by the given {@code predicate}
     */
    void updateFilteredReservationList(Predicate<Reservation> predicate);
}
