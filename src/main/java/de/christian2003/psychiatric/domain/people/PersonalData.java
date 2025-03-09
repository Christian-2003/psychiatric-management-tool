package de.christian2003.psychiatric.domain.people;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;


public final class PersonalData {

    private final String firstname;

    private final String lastname;


    private final LocalDate birthday;


    public PersonalData(String firstname, String lastname, LocalDate birthday) throws NullPointerException {
        if (firstname == null || lastname == null || birthday == null) {
            throw new NullPointerException();
        }
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
    }


    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }


    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, birthday);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PersonalData personalData) {
            return personalData.getFirstname().equals(firstname)
                    && personalData.getLastname().equals(lastname)
                    && personalData.getBirthday().equals(birthday);
        }
        return false;
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);

        StringBuilder builder = new StringBuilder();
        builder.append(firstname);
        builder.append(" ");
        builder.append(lastname);
        builder.append(", born on ");
        builder.append(formatter.format(birthday));

        return builder.toString();
    }

}
