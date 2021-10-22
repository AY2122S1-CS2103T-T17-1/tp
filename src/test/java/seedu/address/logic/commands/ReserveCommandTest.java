package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalCustomers.getTypicalAddressBook;

import java.time.LocalDateTime;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Phone;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.table.Table;


class ReserveCommandTest {
    private static final int DUMMY_NUMBER_OF_PEOPLE = 2;
    private static final Phone DUMMY_PHONE = new Phone("98765432");
    private static final LocalDateTime DUMMY_DATE_TIME = LocalDateTime.parse("2021-11-11T20:00");
    private static final Table DUMMY_TABLE = new Table(5, 10);
    private static final int DIFFERENT_NUMBER_OF_PEOPLE = 3;
    private static final Phone DIFFERENT_PHONE = new Phone("12345678");
    private static final LocalDateTime DIFFERENT_DATE_TIME = LocalDateTime.parse("2021-11-12T21:00");

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullArgument_throwsNullPointerException() {
        // null phone
        assertThrows(NullPointerException.class, () ->
                new ReserveCommand(null, DUMMY_NUMBER_OF_PEOPLE, DUMMY_DATE_TIME));

        // null date time
        assertThrows(NullPointerException.class, () ->
                new ReserveCommand(DUMMY_PHONE, DUMMY_NUMBER_OF_PEOPLE, null));
    }

    @Test
    public void execute_validReservationButNoTables_throwsCommandException() throws Exception {
        ReserveCommand command = new ReserveCommand(DUMMY_PHONE, DUMMY_NUMBER_OF_PEOPLE, DUMMY_DATE_TIME);
        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    public void execute_validReservationWithTablesSetAlready_addSuccessful() throws Exception {
        ReserveCommand command = new ReserveCommand(DUMMY_PHONE, DUMMY_NUMBER_OF_PEOPLE, DUMMY_DATE_TIME);
        model.setTableList(Collections.singletonList(DUMMY_TABLE));
        Reservation expectedReservation =
                new Reservation(DUMMY_PHONE, DUMMY_NUMBER_OF_PEOPLE, DUMMY_DATE_TIME, DUMMY_TABLE);

        command.execute(model);

        assertTrue(model.hasReservation(expectedReservation));
    }

    @Test
    public void execute_duplicateReservation_throwsCommandException() {
        ReserveCommand reserveCommand = new ReserveCommand(
                DUMMY_PHONE, DUMMY_NUMBER_OF_PEOPLE, DUMMY_DATE_TIME
        );
        model.addReservation(new Reservation(DUMMY_PHONE, DUMMY_NUMBER_OF_PEOPLE, DUMMY_DATE_TIME, DUMMY_TABLE));
        assertThrows(CommandException.class, () -> reserveCommand.execute(model));
    }

    @Test
    public void equals() {
        ReserveCommand reserveCommand = new ReserveCommand(DUMMY_PHONE, DUMMY_NUMBER_OF_PEOPLE, DUMMY_DATE_TIME);
        ReserveCommand reserveCommandCopied = new ReserveCommand(DUMMY_PHONE, DUMMY_NUMBER_OF_PEOPLE, DUMMY_DATE_TIME);

        // same object -> returns true
        assertTrue(reserveCommand.equals(reserveCommand));

        // same values -> returns true
        assertTrue(reserveCommand.equals(reserveCommandCopied));

        // different types -> returns false
        assertFalse(reserveCommand.equals(2));

        // null -> returns false
        assertFalse(reserveCommand.equals(null));

        // different phone numbers -> returns false
        assertFalse(reserveCommand.equals(new ReserveCommand(
                DIFFERENT_PHONE,
                DUMMY_NUMBER_OF_PEOPLE,
                DUMMY_DATE_TIME
        )));

        // different number of people
        assertFalse(reserveCommand.equals(new ReserveCommand(
                DUMMY_PHONE,
                DIFFERENT_NUMBER_OF_PEOPLE,
                DUMMY_DATE_TIME
        )));

        // different date time
        assertFalse(reserveCommand.equals(new ReserveCommand(
                DUMMY_PHONE,
                DUMMY_NUMBER_OF_PEOPLE,
                DIFFERENT_DATE_TIME
        )));
    }
}
