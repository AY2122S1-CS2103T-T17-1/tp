package seedu.address.model.reservation;

import seedu.address.model.person.Phone;

import java.time.LocalDateTime;
import java.util.Objects;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Reservation {
    private Phone phone;
    private int numberOfPeople;
    private LocalDateTime time;

    public Reservation(Phone phone, int numberOfPeople, LocalDateTime time) {
        requireAllNonNull(phone, numberOfPeople, time);

        this.numberOfPeople = numberOfPeople;
        this.time = time;
    }

    public Phone getPhone() {
        return phone;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        return numberOfPeople == that.numberOfPeople
                && phone.equals(that.phone)
                && time.equals(that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, numberOfPeople, time);
    }

    @Override
    public String toString() {
        return String.format("Reservation{phone=%s, numberOfPeople=%s, time=%s}", phone, numberOfPeople, time);
    }
}
