package seedu.address.testutil;

import static seedu.address.testutil.TypicalCustomers.CUSTOMER_ALICE;
import static seedu.address.testutil.TypicalCustomers.CUSTOMER_BENSON;
import static seedu.address.testutil.TypicalCustomers.CUSTOMER_CARL;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import seedu.address.model.AddressBook;
import seedu.address.model.reservation.Remark;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.table.Table;

public class TypicalReservation {
    public static final Reservation ALICE_RESERVATION = new Reservation(
            CUSTOMER_ALICE.getPhone(), 2,
            LocalDateTime.parse("2021-11-11T20:00"), new Table(2, 10),
            new Remark("Surprise birthday party"), Set.of()
    );
    public static final Reservation BENSON_RESERVATION = new Reservation(
            CUSTOMER_BENSON.getPhone(), 3,
            LocalDateTime.parse("2021-11-12T19:00"), new Table(3, 11),
            new Remark("have kids"), Set.of()
    );
    public static final Reservation CARL_RESERVATION = new Reservation(
            CUSTOMER_CARL.getPhone(), 3,
            LocalDateTime.parse("2021-11-11T09:00"), new Table(3, 12),
            new Remark(""), Set.of()
    );

    // prevent initialization
    private TypicalReservation() {}

    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        ab.addCustomer(CUSTOMER_ALICE);
        ab.addCustomer(CUSTOMER_BENSON);
        ab.addCustomer(CUSTOMER_CARL);
        for (Reservation res : getTypicalReservations()) {
            ab.addReservation(res);
        }
        return ab;
    }

    public static List<Reservation> getTypicalReservations() {
        return new ArrayList<>(Arrays.asList(ALICE_RESERVATION, BENSON_RESERVATION, CARL_RESERVATION));
    }
}
