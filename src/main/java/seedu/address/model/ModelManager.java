package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Phone;
import seedu.address.model.person.customer.Customer;
import seedu.address.model.person.employee.Employee;
import seedu.address.model.person.supplier.Supplier;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.reservation.ReservationsManager;
import seedu.address.model.table.Table;
import seedu.address.model.table.TableManager;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Customer> filteredCustomers;
    private final ObservableList<Customer> sortableCustomers;
    private final FilteredList<Employee> filteredEmployees;
    private final FilteredList<Supplier> filteredSuppliers;
    private final ObservableList<Employee> sortableEmployees;
    private final ObservableList<Supplier> sortableSuppliers;
    private final FilteredList<Reservation> filteredReservations;
    private final ReservationsManager reservationsManager;
    private final TableManager tableManager;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredCustomers = new FilteredList<>(this.addressBook.getCustomerList());
        sortableCustomers = this.addressBook.getSortableCustomerList();
        filteredEmployees = new FilteredList<>(this.addressBook.getEmployeeList());
        filteredSuppliers = new FilteredList<>(this.addressBook.getSupplierList());
        sortableEmployees = this.addressBook.getSortableEmployeeList();
        sortableSuppliers = this.addressBook.getSortableSupplierList();
        filteredReservations = new FilteredList<>(this.addressBook.getReservationList());
        reservationsManager = this.addressBook.getReservationsManager();
        tableManager = this.addressBook.getTableManager();
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasCustomer(Customer customer) {
        requireNonNull(customer);
        return addressBook.hasCustomer(customer);
    }

    @Override
    public boolean hasCustomerWithPhone(Phone phone) {
        requireNonNull(phone);
        return addressBook.hasCustomerWithPhone(phone);
    }

    @Override
    public boolean hasEmployee(Employee employee) {
        requireNonNull(employee);
        return addressBook.hasEmployee(employee);
    }

    @Override
    public boolean hasSupplier(Supplier supplier) {
        requireNonNull(supplier);
        return addressBook.hasSupplier(supplier);
    }

    @Override
    public void deleteCustomer(Customer target) {
        addressBook.removeCustomer(target);
    }
    @Override
    public void deleteEmployee(Employee employee) {
        addressBook.removeEmployee(employee);
    }

    @Override
    public void deleteSupplier(Supplier target) {
        addressBook.removeSupplier(target);
    }

    @Override
    public void addCustomer(Customer customer) {
        addressBook.addCustomer(customer);
        updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
    }

    @Override
    public void setCustomerComparator(Comparator<Customer> customerComparator) {
        this.addressBook.setCustomerComparator(customerComparator);
    }

    @Override
    public void addEmployee(Employee employee) {
        addressBook.addEmployee(employee);
        updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
    }

    @Override
    public void addSupplier(Supplier supplier) {
        addressBook.addSupplier(supplier);
        updateFilteredSupplierList(PREDICATE_SHOW_ALL_SUPPLIERS);
    }

    @Override
    public void setCustomer(Customer target, Customer editedCustomer) {
        requireAllNonNull(target, editedCustomer);

        addressBook.setCustomer(target, editedCustomer);
    }
    @Override
    public void setEmployee(Employee target, Employee editedEmployee) {
        requireAllNonNull(target, editedEmployee);
        addressBook.setEmployee(target, editedEmployee);
    }

    @Override
    public void setEmployeeComparator(Comparator<Employee> employeeComparator) {
        this.addressBook.setEmployeeComparator(employeeComparator);
    }

    @Override
    public void setSupplier(Supplier target, Supplier editedSupplier) {
        requireAllNonNull(target, editedSupplier);
        addressBook.setSupplier(target, editedSupplier);
    }

    @Override
    public void setSupplierComparator(Comparator<Supplier> supplierComparator) {
        this.addressBook.setSupplierComparator(supplierComparator);
    }

    @Override
    public boolean hasReservation(Reservation reservation) {
        requireNonNull(reservation);
        return addressBook.hasReservation(reservation);
    }

    @Override
    public void deleteReservation(Reservation target) {
        addressBook.removeReservation(target);
    }

    @Override
    public void addReservation(Reservation reservation) {
        addressBook.addReservation(reservation);
        updateFilteredReservationList(PREDICATE_SHOW_ALL_RESERVATIONS);
    }

    @Override
    public void setReservation(Reservation target, Reservation editedReservation) {
        requireAllNonNull(target, editedReservation);
        addressBook.setReservation(target, editedReservation);
    }

    @Override
    public void setTableList(List<Table> tableList) {
        requireNonNull(tableList);
        addressBook.setTableList(tableList);
    }

    @Override
    public void resetReservations() {
        addressBook.resetReservations();
    }

    @Override
    public void resetTableCount() {
        addressBook.resetTableCount();
    }

    //=========== Filtered Employee List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Employee} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Employee> getFilteredEmployeeList() {
        return filteredEmployees;
    }

    @Override
    public ObservableList<Employee> getSortableEmployeeList() {
        return sortableEmployees;
    }

    @Override
    public void resetEmployeeListToDefaultSortState() {
        this.addressBook.resetEmployeeListToDefaultSortState();
    }

    @Override
    public void updateFilteredEmployeeList(Predicate<Employee> predicate) {
        requireNonNull(predicate);
        filteredEmployees.setPredicate(predicate);
    }

    //=========== Filtered Customer List Accessors =========================================================

    /**
     * Returns an unmodifiable view of the list of {@code Customer} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Customer> getFilteredCustomerList() {
        return filteredCustomers;
    }

    @Override
    public ObservableList<Customer> getSortableCustomerList() {
        return sortableCustomers;
    }

    @Override
    public void resetCustomerListToDefaultSortState() {
        this.addressBook.resetCustomerListToDefaultSortState();
    }

    @Override
    public void updateFilteredCustomerList(Predicate<Customer> predicate) {
        requireNonNull(predicate);
        filteredCustomers.setPredicate(predicate);
    }

    //=========== Filtered Supplier List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Supplier} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Supplier> getFilteredSupplierList() {
        return filteredSuppliers;
    }

    @Override
    public ObservableList<Supplier> getSortableSupplierList() {
        return sortableSuppliers;
    }

    @Override
    public void resetSupplierListToDefaultSortState() {
        this.addressBook.resetSupplierListToDefaultSortState();
    }

    @Override
    public void updateFilteredSupplierList(Predicate<Supplier> predicate) {
        requireAllNonNull(predicate);
        filteredSuppliers.setPredicate(predicate);
    }

    //=========== Filtered Reservation List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Reservation} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Reservation> getFilteredReservationList() {
        return filteredReservations;
    }

    @Override
    public void updateFilteredReservationList(Predicate<Reservation> predicate) {
        requireNonNull(predicate);
        filteredReservations.setPredicate(predicate);
    }

    @Override
    public ReservationsManager getReservationsManager() {
        return this.reservationsManager;
    }

    @Override
    public TableManager getTableManager() {
        return this.tableManager;
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredCustomers.equals(other.filteredCustomers)
                && filteredEmployees.equals(other.filteredEmployees)
                && filteredSuppliers.equals(other.filteredSuppliers)
                && filteredReservations.equals(other.filteredReservations)
                && reservationsManager.equals(other.reservationsManager)
                && tableManager.equals(other.tableManager);
    }
}
