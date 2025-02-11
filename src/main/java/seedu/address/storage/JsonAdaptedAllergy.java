package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.customer.Allergy;

/**
 * Jackson-friendly version of {@link Allergy}.
 */
class JsonAdaptedAllergy {

    private final String allergyName;

    /**
     * Constructs a {@code JsonAdaptedallergy} with the given {@code allergyName}.
     */
    @JsonCreator
    public JsonAdaptedAllergy(String allergyName) {
        this.allergyName = allergyName;
    }

    /**
     * Converts a given {@code allergy} into this class for Jackson use.
     */
    public JsonAdaptedAllergy(Allergy source) {
        allergyName = source.allergyName;
    }

    @JsonValue
    public String getAllergyName() {
        return allergyName;
    }

    /**
     * Converts this Jackson-friendly adapted allergy object into the model's {@code allergy} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted allergy.
     */
    public Allergy toModelType() throws IllegalValueException {
        if (!Allergy.isValidAllergyName(allergyName)) {
            throw new IllegalValueException(Allergy.MESSAGE_CONSTRAINTS);
        }
        return new Allergy(allergyName);
    }

}
