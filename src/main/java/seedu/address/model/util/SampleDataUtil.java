package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.customer.Allergy;
import seedu.address.model.person.customer.Customer;
import seedu.address.model.person.customer.LoyaltyPoints;
import seedu.address.model.person.customer.SpecialRequest;
import seedu.address.model.person.employee.Employee;
import seedu.address.model.person.employee.JobTitle;
import seedu.address.model.person.employee.Leaves;
import seedu.address.model.person.employee.Salary;
import seedu.address.model.person.employee.Shift;
import seedu.address.model.person.supplier.DeliveryDetails;
import seedu.address.model.person.supplier.Supplier;
import seedu.address.model.person.supplier.SupplyType;
import seedu.address.model.reservation.Remark;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.table.Table;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    private static Table table1 = new Table(2, 1);
    private static Table table2 = new Table(10, 2);

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"), getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"), getTagSet("colleagues"))
        };
    }

    public static Supplier[] getSampleSuppliers() {
        return new Supplier[] {
            new Supplier(new Name("Supplier1"), new Phone("87438807"), new Email("alexyeoh@hotmail.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), getTagSet("friends"),
                    new SupplyType("Chicken"), new DeliveryDetails("2021-10-19 14:00")),
            new Supplier(new Name("Supplier2"), new Phone("99272758"), new Email("berniceyu@gmail.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("colleagues", "friends"), new SupplyType("Rice"),
                    new DeliveryDetails("12:00 2021-05-19")),
            new Supplier(new Name("Supplier3"), new Phone("92492021"), new Email("irfan@yahoo.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"), getTagSet("classmates"),
                    new SupplyType("Potatoes"), new DeliveryDetails("19-10-2021 10:00")),
            new Supplier(new Name("Supplier4"), new Phone("92624417"), new Email("royb@outlook.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"), getTagSet("colleagues"),
                    new SupplyType("Flour"), new DeliveryDetails("10:00 15-10-2021"))
        };
    }

    public static Employee[] getSampleEmployees() {
        return new Employee[] {
            new Employee(new Name("Javier Phon"), new Phone("81234567"), new Email("javphon@example.com"),
                    new Address("Blk 5 Bukit Batok St 2, #177-35"), getTagSet("Managerial", "Team B"),
                    new Leaves("14"), new Salary("8000"), new JobTitle("Head Chef"),
                    getShiftSet("2021-12-04 0800", "2021-12-05 0800", "2021-12-07 1700")),
            new Employee(new Name("Chetwin Low"), new Phone("85555555"), new Email("chetlqh@example.com"),
                    new Address("Blk 7 Yew Tee St 10, #10-35"), getTagSet("Intern", "Team B"), new Leaves("10"),
                    new Salary("1200"), new JobTitle("Waiter"),
                        getShiftSet("2021-12-04 0800", "2021-12-05 0800", "2021-12-07 1700")),
            new Employee(new Name("Clement Kong"), new Phone("99999999"), new Email("clementk@example.com"),
                    new Address("Blk 20 Bishan St 37, #03-10"), getTagSet("Managerial"), new Leaves("14"),
                    new Salary("7700"), new JobTitle("Branch Manager"),
                    getShiftSet("2021-12-05 0800", "2021-12-06 1700", "2021-12-08 0800")),
            new Employee(new Name("Pham Ba Thang"), new Phone("82974023"), new Email("pham@example.com"),
                    new Address("Blk 17 Bukit Gombat St 20, #01-02"), getTagSet("Team lead", "Team A"),
                    new Leaves("14"), new Salary("6000"), new JobTitle("Head Sous Chef"),
                    getShiftSet("2021-12-07 0800", "2021-12-08 1700", "2021-12-09 0800")),
            new Employee(new Name("Lee Hern Ping"), new Phone("90000000"), new Email("Leehp@example.com"),
                    new Address("Blk 1 Seragoon St 3, #05-05"), getTagSet("Team A"),
                    new Leaves("14"), new Salary("5000"), new JobTitle("Waiter"),
                    getShiftSet("2021-12-07 0800", "2021-12-09 0800", "2021-12-08 1700"))
        };
    }

    public static Customer[] getSampleCustomers() {
        return new Customer[] {
            new Customer(new Name("Javier Phon"), new Phone("87438807"), new Email("imphonofyou@example"
                        + ".com"), new Address("Blk 30 Geylang Street 29, #06-40"), new LoyaltyPoints("1000"),
                        getAllergySet("McSpicy", "Pineapples"), getSpecialRequestSet("Doesnt eat meat"),
                        getTagSet("boss")),
            new Customer(new Name("Pham Ba Thang"), new Phone("99272758"), new Email("igotchupham"
                        + "@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        new LoyaltyPoints("10000"), getAllergySet("Peanuts", "milk"),
                        getSpecialRequestSet("Nothing under 100 dollars"),
                        getTagSet("mosthandsome", "friends")),
            new Customer(new Name("Clement Kong"), new Phone("93210283"), new Email("kingkongbingbong"
                        + "@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                        new LoyaltyPoints("5000"), getAllergySet("Cheese", "rice"),
                        getSpecialRequestSet("Loves window seats"),
                        getTagSet("mousehuntspecialist")),
            new Customer(new Name("Lee Hern Ping"), new Phone("91031282"), new Email("wohernhandsome"
                        + "@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                        new LoyaltyPoints("1000"), getAllergySet("Apples"),
                        getSpecialRequestSet("Every visit is his birthday"),
                        getTagSet("secondmosthandsomeafterthang")),
            new Customer(new Name("Chetwin Low"), new Phone("92492021"), new Email("chetwinchickenwing"
                        + "@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                        new LoyaltyPoints("0500"),
                        getAllergySet("coriander"),
                        getSpecialRequestSet("Everything should be deep fried"),
                        getTagSet("sickofAB3"))
        };
    }

    public static Reservation[] getSampleReservations() {
        return new Reservation[] {
            new Reservation(new Phone("92492021"), 2, LocalDateTime.parse("2021-12-24T20:00"),
                    table1, new Remark("Surprise birthday party"), getTagSet("Chetwin")),
            new Reservation(new Phone("91031282"), 4, LocalDateTime.parse("2021-12-24T20:00"),
                    table2, new Remark("Family dinner, have kids"), getTagSet("hernpiblo", "10PercentOff")),
            new Reservation(new Phone("99272758"), 2, LocalDateTime.parse("2021-12-25T19:00"),
                    table1, new Remark(""), getTagSet("thang")),
            new Reservation(new Phone("93210283"), 3, LocalDateTime.parse("2021-10-30T19:00"),
                    table2 , new Remark("Propose to his gf"), getTagSet("clement")),
            new Reservation(new Phone("87438807"), 6, LocalDateTime.parse("2021-02-14T11:00"),
                    table2 , new Remark(""), getTagSet("javier", "20PercentOff"))
        };
    }

    public static Table[] getSampleTables() {
        return new Table[] {
            table1,
            table2
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Customer sampleCustomer : getSampleCustomers()) {
            sampleAb.addCustomer(sampleCustomer);
        }
        for (Employee sampleEmployee : getSampleEmployees()) {
            sampleAb.addEmployee(sampleEmployee);
        }
        for (Supplier sampleSupplier : getSampleSuppliers()) {
            sampleAb.addSupplier(sampleSupplier);
        }
        for (Reservation sampleReservation : getSampleReservations()) {
            sampleAb.addReservation(sampleReservation);
        }
        for (Table sampleTable : getSampleTables()) {
            sampleAb.addTable(sampleTable);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns an allergy set containing the list of strings given.
     */
    public static Set<Allergy> getAllergySet(String... strings) {
        return Arrays.stream(strings)
                .map(Allergy::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a special request set containing the list of strings given.
     */
    public static Set<SpecialRequest> getSpecialRequestSet(String... strings) {
        return Arrays.stream(strings)
                .map(SpecialRequest::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a shifts set containing the list of strings given.
     */
    public static Set<Shift> getShiftSet(String... strings) {
        return Arrays.stream(strings)
                .map(Shift::new)
                .collect(Collectors.toSet());
    }

}
