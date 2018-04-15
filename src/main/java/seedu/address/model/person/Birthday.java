package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Calendar;
import java.util.GregorianCalendar;




//@@author deborahlow97
/**
 * Represents a Person's birthday in CollegeZone.
 * Guarantees: immutable; is valid as declared in {@link #isValidBirthday(String)}
 */
public class Birthday {

    public static final String MESSAGE_BIRTHDAY_CONSTRAINTS = "Person birthday should be a valid date.";
    public static final String BIRTHDAY_VALIDATION_REGEX =
            "^(((0[1-9]|[12]\\d|3[01])\\/(0[13578]|1[02])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)"
                    + "\\/(0[13456789]|1[012])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])\\/02\\/((19|[2-9]\\d)"
                    + "\\d{2}))|(29\\/02\\/((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|"
                    + "[3579][26])00))))$";

    public final String value;

    /**
     * Constructs an {@code Birthday}.
     *
     * @param birthday A valid birthday.
     */
    public Birthday(String birthday) {
        requireNonNull(birthday);
        checkArgument(isValidBirthday(birthday), MESSAGE_BIRTHDAY_CONSTRAINTS);
        this.value = birthday;
    }

    /**
     * Returns if a given string is a valid person birthday.
     */
    public static boolean isValidBirthday(String test) {
        return test.matches(BIRTHDAY_VALIDATION_REGEX);
    }

    //@@author sham-sheer
    /**
     * Converts Birth date to a time that is relative to current date, for sorting purposes
     */
    public static long birthDateToInt(String date) {
        Calendar calendar = Calendar.getInstance();
        long longDate = convertbirthDateToSeconds(date.toString());
        long currentDate = calendar.getTimeInMillis();
        long timeDiff = longDate - currentDate;
        if (timeDiff < 0) {
            return Long.MAX_VALUE;
        } else {
            return timeDiff;
        }
    }

    /**
     * Converts Birth date to seconds
     */
    public static long convertbirthDateToSeconds(String date) {
        if (date == "") {
            return 0;
        }
        int day = Integer.parseInt(date.toString().substring(0,
                2));
        int month = Integer.parseInt(date.toString().substring(3,
                5));
        int year = 2018;
        Calendar calendar = new GregorianCalendar();
        calendar.set(year, month - 1, day);
        long seconds = calendar.getTimeInMillis();
        return seconds;
    }

    //@@author deborahlow97
    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Birthday // instanceof handles nulls
                && this.value.equals(((Birthday) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
