package com.example.demo1.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CheckOutForm {

    /**
     * Required patterns for variables used in check out form
     * used both in front- and backend
     */

    public static final String FIRSTNAME_PATTERN = "^[a-zA-ZôíèÔÍÈåäöÅÄÖ\\- ]*$";
    public static final String LASTNAME_PATTERN = "^[a-zA-ZôíèÔÍÈåäöÅÄÖ\\- ]*$";
    public static final String PHONENUMBER_PATTERN = "^[\\d ]+$|^\\d+-[\\d ]+$";
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)+$";
    public static final String ADDRESS_PATTERN = "^([A-Za-z0-9ôíèÔÍÈåäöÅÄÖ.\\- ,])+$";
    public static final String ZIPCODE_PATTERN = "^\\d{5}$|^\\d{3} \\d{2}$";
    public static final String CITY_PATTERN = "^[a-zA-ZôíèÔÍÈåäöÅÄÖ\\- ]*$";
    public static final int MAXLENGTH_SIZE = 30;
    public static final int MAXLENGTHPHONE_SIZE = 11;
    public static final int MINLENGTHPHONE_SIZE = 10;

    @NotNull
    @Size(max = MAXLENGTH_SIZE)
    @Pattern(regexp = FIRSTNAME_PATTERN)
    private String firstName;

    @NotNull
    @Size(max = MAXLENGTH_SIZE)
    @Pattern(regexp = LASTNAME_PATTERN)
    private String lastName;

    @NotNull
    @Size(min = MINLENGTHPHONE_SIZE, max = MAXLENGTHPHONE_SIZE)
    @Pattern(regexp = PHONENUMBER_PATTERN)
    private String phoneNumber;

    @NotNull
    @Pattern(regexp = EMAIL_PATTERN)
    private String email;

    @NotNull
    @Pattern(regexp = ADDRESS_PATTERN)
    private String address;

    @NotNull
    @Pattern(regexp = ZIPCODE_PATTERN)
    private String zipCode;

    @NotNull
    @Pattern(regexp = CITY_PATTERN)
    private String city;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "CheckOutForm{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
