package seedu.address.model.person.supplier;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicateSupplierException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of suppliers that enforces uniqueness between its elements and does not allow nulls.
 * A supplier is considered unique by comparing using {@code Supplier#isSameSupplier(Supplier)}. As such, adding and
 * updating of suppliers uses Supplier#isSameSupplier(Supplier) for equality so as to ensure that the supplier being
 * added or updated is unique in terms of identity in the UniqueSupplierList. However, the removal of a supplier uses
 * Supplier#equals(Object) so as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Supplier#isSameSupplier(Supplier)
 */
public class UniqueSupplierList implements Iterable<Supplier> {

    private final ObservableList<Supplier> internalList = FXCollections.observableArrayList();
    private final ObservableList<Supplier> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Supplier toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameSupplier);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Supplier toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateSupplierException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the supplier {@code target} in the list with {@code editedSupplier}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setSupplier(Supplier target, Supplier editedSupplier) {
        requireAllNonNull(target, editedSupplier);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedSupplier) && contains(editedSupplier)) {
            throw new DuplicateSupplierException();
        }

        internalList.set(index, editedSupplier);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Supplier toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setSuppliers(seedu.address.model.person.supplier.UniqueSupplierList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setSuppliers(List<Supplier> suppliers) {
        requireAllNonNull(suppliers);
        if (!suppliersAreUnique(suppliers)) {
            throw new DuplicateSupplierException();
        }

        internalList.setAll(suppliers);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Supplier> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Supplier> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.person.supplier.UniqueSupplierList // instanceof handles nulls
                && internalList.equals(((seedu.address.model.person.supplier.UniqueSupplierList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean suppliersAreUnique(List<Supplier> suppliers) {
        for (int i = 0; i < suppliers.size() - 1; i++) {
            for (int j = i + 1; j < suppliers.size(); j++) {
                if (suppliers.get(i).isSameSupplier(suppliers.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
