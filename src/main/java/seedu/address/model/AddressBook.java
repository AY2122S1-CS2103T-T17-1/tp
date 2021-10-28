package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.person.Phone;
import seedu.address.model.person.customer.Customer;
import seedu.address.model.person.customer.UniqueCustomerList;
import seedu.address.model.person.employee.Employee;
import seedu.address.model.person.employee.UniqueEmployeeList;
import seedu.address.model.person.supplier.Supplier;
import seedu.address.model.person.supplier.UniqueSupplierList;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.reservation.ReservationsManager;
import seedu.address.model.table.Table;
import seedu.address.model.table.TableManager;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueCustomerList customers;
    private final UniqueEmployeeList employees;
    private final UniqueSupplierList suppliers;
    private final ReservationsManager reservationsManager;
    private final TableManager tableManager;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        customers = new UniqueCustomerList();
        employees = new UniqueEmployeeList();
        suppliers = new UniqueSupplierList();
        reservationsManager = new ReservationsManager();
        tableManager = new TableManager();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    public void setReservations(List<Reservation> reservations) {
        this.reservationsManager.setReservations(reservations);
    }

    public void setTables(List<Table> tables) {
        this.tableManager.setTables(tables);
    }

    /**
     * Replaces the contents of the supplier list with {@code suppliers}.
     * {@code persons} must not contain duplicate suppliers.
     */
    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers.setSuppliers(suppliers);
    }

    /**
     * Replaces the contents of the employee list with {@code employees}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setEmployees(List<Employee> employees) {
        this.employees.setEmployees(employees);
    }

    /**
     * Sets the employee comparator to sort the employee list.
     * @param employeeComparator The comparator to sort the employee list.
     */
    public void setEmployeeComparator(Comparator<Employee> employeeComparator) {
        employees.setComparator(employeeComparator);
    }

    /**
     * Replaces the contents of the customer list with {@code customers}.
     * {@code customers} must not contain duplicate customers.
     */
    public void setCustomers(List<Customer> customers) {
        this.customers.setCustomers(customers);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setCustomers(newData.getCustomerList());
        setEmployees(newData.getEmployeeList());
        setSuppliers(newData.getSupplierList());
        setReservations(newData.getReservationList());
        setTables(newData.getTableList());
    }

    //// customer-level operations

    /**
     * Returns true if a customer with the same identity as {@code customer} exists in the address book.
     */
    public boolean hasCustomer(Customer customer) {
        requireNonNull(customer);
        return customers.contains(customer);
    }

    /**
     * Returns true if a customer with the same phone as {@code phone} exists in the address book.
     */
    public boolean hasCustomerWithPhone(Phone phone) {
        requireNonNull(phone);
        return customers.containsCustomerWithPhone(phone);
    }

    /**
     * Adds a customer to the address book.
     * The customer must not already exist in the address book.
     */
    public void addCustomer(Customer c) {
        customers.add(c);
    }

    /**
     * Replaces the given customer {@code target} in the list with {@code editedCustomer}.
     * {@code target} must exist in the address book.
     * The customer identity of {@code editedCustomer} must not be the same as another existing customer in
     * the address book.
     */
    public void setCustomer(Customer target, Customer editedCustomer) {
        requireNonNull(editedCustomer);

        customers.setCustomer(target, editedCustomer);
    }

    public void removeCustomer(Customer key) {
        customers.remove(key);
    }

    /**
     * Sets the customer comparator to sort the customer list.
     * @param customerComparator The comparator to sort the customer list.
     */
    public void setCustomerComparator(Comparator<Customer> customerComparator) {
        customers.setComparator(customerComparator);
    }

    /**
     * Returns a sortable customer list
     */
    public ObservableList<Customer> getSortableCustomerList() {
        return customers.asSortableObservableList();
    }

    /**
     * Resets the customer list sorting to its default state.
     */
    public void resetCustomerListToDefaultSortState() {
        customers.resetCustomerListToDefaultSortState();
    }

    /// supplier level operations
    /**
     * Returns true if a supplier with the same identity as {@code supplier} exists in the address book.
     */
    public boolean hasSupplier(Supplier supplier) {
        requireNonNull(supplier);
        return suppliers.contains(supplier);
    }

    /**
     * Adds a supplier to the address book.
     * The supplier must not already exist in the address book.
     */
    public void addSupplier(Supplier s) {
        suppliers.add(s);
    }

    /**
     * Replaces the given supplier {@code target} in the list with {@code editedSupplier}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedSupplier} must not be the same as another existing supplier in the address
     * book.
     */
    public void setSupplier(Supplier target, Supplier editedSupplier) {
        requireNonNull(editedSupplier);

        suppliers.setSupplier(target, editedSupplier);
    }

    /**
     * Sets the supplier comparator to sort the supplier list.
     * @param supplierComparator The comparator to sort the supplier list.
     */
    public void setSupplierComparator(Comparator<Supplier> supplierComparator) {
        suppliers.setComparator(supplierComparator);
    }

    /**
     * Returns a sortable employee list
     */
    public ObservableList<Employee> getSortableEmployeeList() {
        return employees.asSortableObservableList();
    }

    /**
     * Resets the employee list sorting to its default state.
     */
    public void resetEmployeeListToDefaultSortState() {
        employees.resetEmployeeListToDefaultSortState();
    }

    /**
     * Returns a sortable supplier list`
     */
    public ObservableList<Supplier> getSortableSupplierList() {
        return suppliers.asSortableObservableList();
    }

    /**
     * Resets the supplier list sorting to its default state.
     */
    public void resetSupplierListToDefaultSortState() {
        suppliers.resetSupplierListToDefaultSortState();
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeSupplier(Supplier key) {
        suppliers.remove(key);
    }

    //// reservation-level operations

    /**
     * Check if {@code reservation} exists in the database
     */
    public boolean hasReservation(Reservation reservation) {
        requireNonNull(reservation);
        return reservationsManager.hasReservation(reservation);
    }

    /**
     * Adds a new reservation to the list
     */
    public void addReservation(Reservation reservation) {
        reservationsManager.addReservation(reservation);
    }

    /**
     * Replaces the reservation {@code target} in the list with {@code editedReservation}
     */
    public void setReservation(Reservation target, Reservation editedReservation) {
        requireNonNull(editedReservation);
        reservationsManager.setReservation(target, editedReservation);
    }

    /**
     * Removes {@code key} from the database
     * {@code key} must exist in the list
     */
    public void removeReservation(Reservation key) {
        reservationsManager.removeReservation(key);
    }

    //// table-level operations

    /**
     * Check if {@code table} exists in the database
     */
    public boolean hasTable(Table table) {
        requireNonNull(table);
        return tableManager.hasTable(table);
    }

    /**
     * Adds a new table to the list
     */
    public void addTable(Table table) {
        tableManager.addTable(table);
    }

    /**
     * Replaces the table {@code target} in the list with {@code editedTable}
     */
    public void setTable(Table target, Table editedTable) {
        requireNonNull(editedTable);
        tableManager.setTable(target, editedTable);
    }

    /**
     * Removes {@code key} from the database
     * {@code key} must exist in the list
     */
    public void removeTable(Table key) {
        tableManager.removeTable(key);
    }

    //// tableList-level operations

    /**
     * Adds a new table to the list
     */
    public void setTableList(List<Table> tableList) {
        tableManager.setTableList(tableList);
    }

    /**
     * Deletes all reservations
     */
    public void resetReservations() {
        reservationsManager.resetReservations();
    }

    /**
     * Resets the table list to an empty list
     */
    public void resetTableCount() {
        tableManager.resetTableCount();
    }

    //// util methods

    @Override
    public String toString() {
        // TODO: refine later
        return String.format(
                "%d customers\n%d employees\n%d suppliers\n%d reservations\n%d tables",
                customers.asUnmodifiableObservableList().size(),
                employees.asUnmodifiableObservableList().size(),
                suppliers.asUnmodifiableObservableList().size(),
                reservationsManager.getUnmodifiableObservableList().size(),
                tableManager.getUnmodifiableObservableList().size());
    }

    @Override
    public ObservableList<Customer> getCustomerList() {
        return customers.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Supplier> getSupplierList() {
        return suppliers.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Reservation> getReservationList() {
        return reservationsManager.getUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Table> getTableList() {
        return tableManager.getUnmodifiableObservableList();
    }

    public ReservationsManager getReservationsManager() {
        return reservationsManager;
    }

    public TableManager getTableManager() {
        return tableManager;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && customers.equals(((AddressBook) other).customers)
                && employees.equals(((AddressBook) other).employees)
                && suppliers.equals(((AddressBook) other).suppliers)
                && reservationsManager.equals(((AddressBook) other).reservationsManager));
    }

    @Override
    public ObservableList<Employee> getEmployeeList() {
        return employees.asUnmodifiableObservableList();
    }

    /**
     * Returns true if an employee with the same identity as {@code employee} exists in the address book.
     */
    public boolean hasEmployee(Employee employee) {
        requireNonNull(employee);
        return employees.contains(employee);
    }

    /**
     * Removes {@code employee} from this {@code AddressBook}.
     * {@code employee} must exist in the address book.
     */
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void setEmployee(Employee target, Employee editedEmployee) {
        requireNonNull(editedEmployee);
        employees.setEmployee(target, editedEmployee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customers, employees, suppliers, reservationsManager);
    }
}
