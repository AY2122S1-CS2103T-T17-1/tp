package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.SortOrder;
import seedu.address.model.person.customer.Allergy;
import seedu.address.model.person.customer.LoyaltyPoints;
import seedu.address.model.person.customer.SortByCustomer;
import seedu.address.model.person.customer.SpecialRequest;
import seedu.address.model.person.employee.JobTitle;
import seedu.address.model.person.employee.Leaves;
import seedu.address.model.person.employee.Salary;
import seedu.address.model.person.employee.Shift;
import seedu.address.model.person.employee.SortByEmployee;
import seedu.address.model.person.supplier.DeliveryDetails;
import seedu.address.model.person.supplier.SortBySupplier;
import seedu.address.model.person.supplier.SupplyType;
import seedu.address.model.reservation.Remark;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HHmm";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm");

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_NUMBER_OF_PEOPLE =
            "Number of people is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_DATE_TIME_FORMAT =
            "Date time should be in the format of " + DATE_TIME_PATTERN;

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code numIfPeople} into an a non-zero unsigned integer and returns it.
     * Leading and trailing whitespaces will be trimmed
     * @param numOfPeople number of people as a string
     * @return number of people as an integer
     * @throws ParseException if the specified number of people is invalid (not non-zero unsigned integer).
     */
    public static int parseNumberOfPeople(String numOfPeople) throws ParseException {
        int result;

        try {
            result = Integer.parseInt(numOfPeople.trim());
        } catch (NumberFormatException nfe) {
            throw new ParseException(MESSAGE_INVALID_NUMBER_OF_PEOPLE);
        }

        if (result <= 0) {
            throw new ParseException(MESSAGE_INVALID_NUMBER_OF_PEOPLE);
        }
        return result;
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String loyaltyPoints} into an {@code loyaltyPoints}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code loyaltyPoints} is invalid.
     */
    public static LoyaltyPoints parseLoyaltyPoints(String loyaltyPoints) throws ParseException {
        requireNonNull(loyaltyPoints);
        String trimmedLoyaltyPoints = loyaltyPoints.trim();
        if (!LoyaltyPoints.isValidLoyaltyPoints(trimmedLoyaltyPoints)) {
            throw new ParseException(LoyaltyPoints.MESSAGE_CONSTRAINTS);
        }
        return new LoyaltyPoints(trimmedLoyaltyPoints);
    }

    /**
     * Parses a {@code String allergy} into a {@code Allergy}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code allergy} is invalid.
     */
    public static Allergy parseAllergy(String allergy) throws ParseException {
        requireNonNull(allergy);
        String trimmedAllergy = allergy.trim();
        if (!Allergy.isValidAllergyName(trimmedAllergy)) {
            throw new ParseException(Allergy.MESSAGE_CONSTRAINTS);
        }
        return new Allergy(trimmedAllergy);
    }

    /**
     * Parses {@code Collection<String> allergies} into a {@code Set<Allergy>}.
     */
    public static Set<Allergy> parseAllergies(Collection<String> allergies) throws ParseException {
        requireNonNull(allergies);
        final Set<Allergy> allergySet = new HashSet<>();
        for (String allergyName : allergies) {
            allergySet.add(parseAllergy(allergyName));
        }
        return allergySet;
    }

    /**
     * Parses a {@code String allergy} into a {@code Allergy}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code specialRequest} is invalid.
     */
    public static SpecialRequest parseSpecialRequest(String specialRequest) throws ParseException {
        requireNonNull(specialRequest);
        String trimmedSpecialRequest = specialRequest.trim();
        if (!SpecialRequest.isValidSpecialRequestName(trimmedSpecialRequest)) {
            throw new ParseException(SpecialRequest.MESSAGE_CONSTRAINTS);
        }
        return new SpecialRequest(trimmedSpecialRequest);
    }

    /**
     * Parses {@code Collection<String> specialRequests} into a {@code Set<SpecialRequest>}.
     */
    public static Set<SpecialRequest> parseSpecialRequests(Collection<String> specialRequests) throws ParseException {
        requireNonNull(specialRequests);
        final Set<SpecialRequest> specialRequestSet = new HashSet<>();
        for (String specialRequestName : specialRequests) {
            specialRequestSet.add(parseSpecialRequest(specialRequestName));
        }
        return specialRequestSet;
    }

    /**
     * Parses a {@code String allergy} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String leaves} into a {@code Leaves}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code leaves} is invalid.
     */
    public static Leaves parseLeaves(String leaves) throws ParseException {
        requireNonNull(leaves);
        String trimmedLeaves = leaves.trim();
        if (!Leaves.isValidLeaves(trimmedLeaves)) {
            throw new ParseException(Leaves.MESSAGE_CONSTRAINTS);
        }
        return new Leaves(trimmedLeaves);
    }

    /**
     * Parses a {@code String salary} into a {@code Salary}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code salary} is invalid.
     */
    public static Salary parseSalary(String salary) throws ParseException {
        requireNonNull(salary);
        String trimmedSalary = salary.trim();
        if (!Salary.isValidSalary(trimmedSalary)) {
            throw new ParseException(Salary.MESSAGE_CONSTRAINTS);
        }
        return new Salary(trimmedSalary);
    }

    /**
     * Parses a {@code String job title} into a {@code JobTitle}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code jobTitle} is invalid.
     */
    public static JobTitle parseJobTitle(String jobTitle) throws ParseException {
        requireNonNull(jobTitle);
        String trimmedJobTitle = jobTitle.trim();
        if (!JobTitle.isValidJobTitle(trimmedJobTitle)) {
            throw new ParseException(JobTitle.MESSAGE_CONSTRAINTS);
        }
        return new JobTitle(trimmedJobTitle);
    }

    /**
     * Parses a {@code String allergy} into a {@code Allergy}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code specialRequest} is invalid.
     */
    public static Shift parseShift(String shift) throws ParseException {
        requireNonNull(shift);
        String trimmedShift = shift.trim();
        if (!Shift.isValidShift(trimmedShift)) {
            throw new ParseException(Shift.MESSAGE_CONSTRAINTS);
        }
        return new Shift(trimmedShift);
    }

    /**
     * Parses {@code Collection<String> specialRequests} into a {@code Set<SpecialRequest>}.
     */
    public static Set<Shift> parseShifts(Collection<String> shifts) throws ParseException {
        requireNonNull(shifts);
        final Set<Shift> shiftSet = new HashSet<>();
        for (String shift : shifts) {
            shiftSet.add(parseShift(shift));
        }
        return shiftSet;
    }

    /**
     * Parses a {@code String supplyType} into a {@code SupplyType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code} is invalid.
     */
    public static SupplyType parseSupplyType(String supplyType) throws ParseException {
        requireNonNull(supplyType);
        String trimmedSupplyType = supplyType.trim();
        if (!SupplyType.isValidSupplyType(trimmedSupplyType)) {
            throw new ParseException(SupplyType.MESSAGE_CONSTRAINTS);
        }
        return new SupplyType(trimmedSupplyType);
    }

    /**
     * Parses a {@code String deliveryDetails} into a {@code DeliveryDetails}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code} is invalid.
     */
    public static DeliveryDetails parseDeliveryDetails(String deliveryDetails) throws ParseException {
        requireNonNull(deliveryDetails);
        String trimmedDeliveryDetails = deliveryDetails.trim();
        if (!DeliveryDetails.isValidDeliveryDetail(trimmedDeliveryDetails)) {
            throw new ParseException(DeliveryDetails.MESSAGE_CONSTRAINTS);
        }
        return new DeliveryDetails(trimmedDeliveryDetails);
    }

    /**
     * Parses a {@code String sortingOrder} into a {@code SortOrder}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code} is invalid.
     */
    public static SortOrder parseSortingOrder(String sortingOrder) throws ParseException {
        requireNonNull(sortingOrder);
        String trimmedSortingOrder = sortingOrder.trim().toLowerCase();
        if (!SortOrder.isValidSortingOrder(trimmedSortingOrder)) {
            throw new ParseException(SortOrder.MESSAGE_CONSTRAINTS);
        }
        return new SortOrder(trimmedSortingOrder);
    }

    /**
     * Parses a {@code String sortBy} into a {@code SortBy}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code} is invalid.
     */
    public static SortByEmployee parseSortByEmployee(String sortBy) throws ParseException {
        requireNonNull(sortBy);
        String trimmedSortBy = sortBy.trim().toLowerCase();
        if (!SortByEmployee.isValidSortingOrder(trimmedSortBy)) {
            throw new ParseException(SortByEmployee.MESSAGE_CONSTRAINTS);
        }
        return new SortByEmployee(trimmedSortBy);
    }

    /**
     * Parses a {@code String sortBy} into a {@code SortBy}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code} is invalid.
     */
    public static SortByCustomer parseSortByCustomer(String sortBy) throws ParseException {
        requireNonNull(sortBy);
        String trimmedSortBy = sortBy.trim().toLowerCase();
        if (!SortByCustomer.isValidSortingOrder(trimmedSortBy)) {
            throw new ParseException(SortByCustomer.MESSAGE_CONSTRAINTS);
        }
        return new SortByCustomer(trimmedSortBy);
    }

    /**
     * Parses a {@code String sortBy} into a {@code SortBy}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code} is invalid.
     */
    public static SortBySupplier parseSortBySupplier(String sortBy) throws ParseException {
        requireNonNull(sortBy);
        String trimmedSortBy = sortBy.trim().toLowerCase();
        if (!SortBySupplier.isValidSortingOrder(trimmedSortBy)) {
            throw new ParseException(SortBySupplier.MESSAGE_CONSTRAINTS);
        }
        return new SortBySupplier(trimmedSortBy);
    }

    /**
     * Parses {@code dateTime} into a {@code LocalDateTime} object.
     * @throws ParseException if {@code dateTime} is of invalid format.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws ParseException {
        requireNonNull(dateTime);
        String trimmedDateTime = dateTime.trim();

        LocalDateTime result;
        try {
            result = LocalDateTime.parse(trimmedDateTime, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException dtpe) {
            throw new ParseException(MESSAGE_INVALID_DATE_TIME_FORMAT);
        }
        return result;
    }

    /**
     * Parses {@code remark} into a {@code Remark} object.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if {@code remark} is of invalid format.
     */
    public static Remark parseRemark(String remark) throws ParseException {
        requireNonNull(remark);
        String trimmedRemark = remark.trim();

        if (trimmedRemark.isEmpty()) {
            return new Remark("");
        }
        if (!Remark.isValidRemark(trimmedRemark)) {
            throw new ParseException(Remark.MESSAGE_CONSTRAINTS);
        }
        return new Remark(trimmedRemark);
    }
}
