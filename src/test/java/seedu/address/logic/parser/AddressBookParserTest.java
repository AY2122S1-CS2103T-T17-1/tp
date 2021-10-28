package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CUSTOMER;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SUPPLIER;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCustomerCommand;
import seedu.address.logic.commands.AddEmployeeCommand;
import seedu.address.logic.commands.AddSupplierCommand;
import seedu.address.logic.commands.CheckCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCustomerCommand;
import seedu.address.logic.commands.DeleteEmployeeCommand;
import seedu.address.logic.commands.DeleteSupplierCommand;
import seedu.address.logic.commands.EditCustomerCommand;
import seedu.address.logic.commands.EditCustomerCommand.EditCustomerDescriptor;
import seedu.address.logic.commands.EditEmployeeCommand;
import seedu.address.logic.commands.EditEmployeeCommand.EditEmployeeDescriptor;
import seedu.address.logic.commands.EditSupplierCommand;
import seedu.address.logic.commands.EditSupplierCommand.EditSupplierDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCustomerCommand;
import seedu.address.logic.commands.FindEmployeeCommand;
import seedu.address.logic.commands.FindSupplierCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCustomerCommand;
import seedu.address.logic.commands.ListEmployeeCommand;
import seedu.address.logic.commands.ListReservationCommand;
import seedu.address.logic.commands.ListSupplierCommand;
import seedu.address.logic.commands.ReserveCommand;
import seedu.address.logic.parser.enums.EnumTypeOfCheck;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.customer.Customer;
import seedu.address.model.person.customer.CustomerClassContainsKeywordsPredicate;
import seedu.address.model.person.employee.Employee;
import seedu.address.model.person.employee.EmployeeClassContainsKeywordsPredicate;
import seedu.address.model.person.supplier.Supplier;
import seedu.address.model.person.supplier.SupplierClassContainsKeywordsPredicate;
import seedu.address.model.reservation.ListContainsReservationPredicate;
import seedu.address.testutil.CustomerBuilder;
import seedu.address.testutil.CustomerUtil;
import seedu.address.testutil.EditCustomerDescriptorBuilder;
import seedu.address.testutil.EditEmployeeDescriptorBuilder;
import seedu.address.testutil.EditSupplierDescriptorBuilder;
import seedu.address.testutil.EmployeeBuilder;
import seedu.address.testutil.EmployeeUtil;
import seedu.address.testutil.SupplierBuilder;
import seedu.address.testutil.SupplierUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();


    @Test
    public void parseCommand_addCustomer() throws Exception {
        Customer customer = new CustomerBuilder().build();
        AddCustomerCommand command =
                (AddCustomerCommand) parser.parseCommand(CustomerUtil.getAddCustomerCommand(customer));
        assertEquals(new AddCustomerCommand(customer), command);
    }

    @Test
    public void parseCommand_addEmployee() throws Exception {
        Employee employee = new EmployeeBuilder().build();
        AddEmployeeCommand command = (AddEmployeeCommand) parser.parseCommand(EmployeeUtil
                .getAddEmployeeCommand(employee));
        assertEquals(new AddEmployeeCommand(employee), command);
    }

    @Test
    public void parseCommand_addSupplier() throws Exception {
        Supplier supplier = new SupplierBuilder().build();
        AddSupplierCommand command = (AddSupplierCommand) parser.parseCommand(SupplierUtil
                .getAddSupplierCommand(supplier));
        assertEquals(new AddSupplierCommand(supplier), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deleteCustomer() throws Exception {
        DeleteCustomerCommand command = (DeleteCustomerCommand) parser.parseCommand(
                DeleteCustomerCommand.COMMAND_WORD + " " + INDEX_FIRST_CUSTOMER.getOneBased());
        assertEquals(new DeleteCustomerCommand(INDEX_FIRST_CUSTOMER), command);
    }

    @Test
    public void parseCommand_deleteEmployee() throws Exception {
        DeleteEmployeeCommand command = (DeleteEmployeeCommand) parser.parseCommand(
                DeleteEmployeeCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteEmployeeCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_deleteSupplier() throws Exception {
        DeleteSupplierCommand command = (DeleteSupplierCommand) parser.parseCommand(
                DeleteSupplierCommand.COMMAND_WORD + " " + INDEX_FIRST_SUPPLIER.getOneBased());
        assertEquals(new DeleteSupplierCommand(INDEX_FIRST_SUPPLIER), command);
    }

    @Test
    public void parseCommand_editCustomer() throws Exception {
        Customer customer = new CustomerBuilder().build();
        EditCustomerDescriptor descriptor = new EditCustomerDescriptorBuilder(customer).build();
        EditCustomerCommand command =
                (EditCustomerCommand) parser.parseCommand(EditCustomerCommand.COMMAND_WORD + " "
                + INDEX_FIRST_CUSTOMER.getOneBased() + " " + CustomerUtil.getEditCustomerDescriptorDetails(descriptor));
        assertEquals(new EditCustomerCommand(INDEX_FIRST_CUSTOMER, descriptor), command);
    }

    @Test
    public void parseCommand_editEmployee() throws Exception {
        Employee employee = new EmployeeBuilder().build();
        EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder(employee).build();
        EditEmployeeCommand command = (EditEmployeeCommand) parser.parseCommand(EditEmployeeCommand.COMMAND_WORD
            + " " + INDEX_FIRST_PERSON.getOneBased() + " " + EmployeeUtil.getEditEmployeeDescriptorDetails(descriptor));
        assertEquals(new EditEmployeeCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_editSupplier() throws Exception {
        Supplier supplier = new SupplierBuilder().build();
        EditSupplierDescriptor descriptor = new EditSupplierDescriptorBuilder(supplier).build();
        EditSupplierCommand command =
                (EditSupplierCommand) parser.parseCommand(EditSupplierCommand.COMMAND_WORD + " "
                        + INDEX_FIRST_SUPPLIER.getOneBased() + " "
                        + SupplierUtil.getEditSupplierDescriptorDetails(descriptor));
        assertEquals(new EditSupplierCommand(INDEX_FIRST_SUPPLIER, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findCustomer() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCustomerCommand command = (FindCustomerCommand) parser.parseCommand(
                FindCustomerCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCustomerCommand(new CustomerClassContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findEmployee() throws Exception {
        List<String> keywords = Arrays.asList("goo far daz");
        FindEmployeeCommand command = (FindEmployeeCommand) parser.parseCommand(
                FindEmployeeCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindEmployeeCommand(new EmployeeClassContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findSupplier() throws Exception {
        List<String> keywords = Arrays.asList("boo", "jar", "faz");
        FindSupplierCommand command = (FindSupplierCommand) parser.parseCommand(
                FindSupplierCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindSupplierCommand(new SupplierClassContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_check() throws Exception {
        String dateString = "2021-10-10";
        String timeString = "1900";
        LocalDate date = LocalDate.parse(dateString);
        LocalTime time = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HHmm"));
        EnumTypeOfCheck typeOfCheck = EnumTypeOfCheck.DateTime;

        CheckCommand command = (CheckCommand) parser.parseCommand(
                CheckCommand.COMMAND_WORD + " " + dateString + " " + timeString);
        assertEquals(new CheckCommand(new ListContainsReservationPredicate(date, time, typeOfCheck)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listCustomer() throws Exception {
        assertTrue(parser.parseCommand(ListCustomerCommand.COMMAND_WORD) instanceof ListCustomerCommand);
        assertTrue(parser.parseCommand(ListCustomerCommand.COMMAND_WORD + " 3") instanceof ListCustomerCommand);
    }

    @Test
    public void parseCommand_listEmployee() throws Exception {
        assertTrue(parser.parseCommand(ListEmployeeCommand.COMMAND_WORD) instanceof ListEmployeeCommand);
        assertTrue(parser.parseCommand(ListEmployeeCommand.COMMAND_WORD + " 3") instanceof ListEmployeeCommand);
    }

    @Test
    public void parseCommand_listSupplier() throws Exception {
        assertTrue(parser.parseCommand(ListSupplierCommand.COMMAND_WORD) instanceof ListSupplierCommand);
        assertTrue(parser.parseCommand(ListSupplierCommand.COMMAND_WORD + " 3") instanceof ListSupplierCommand);
    }

    @Test
    public void parseCommand_listReservation() throws Exception {
        assertTrue(parser.parseCommand(ListReservationCommand.COMMAND_WORD) instanceof ListReservationCommand);
        assertTrue(parser.parseCommand(ListReservationCommand.COMMAND_WORD + " 3") instanceof ListReservationCommand);
    }

    @Test
    public void parseCommand_reserve() throws Exception {
        assertTrue(parser.parseCommand(
                ReserveCommand.COMMAND_WORD + " 2 p/98765432 at/2021-11-11 2000") instanceof ReserveCommand
        );
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () ->
                parser.parseCommand("unknownCommand"));
    }
}
