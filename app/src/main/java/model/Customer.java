package model;

import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;
    
    public Customer(String firstName, String lastName, String email) {
        super();

        emailValidation(email); //validates email format

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
    }

    //Validates the format of the email address using regex.
    public void emailValidation(String email) {
        final String emailRegex = "^(.+)@(.+).com$";
        final Pattern pattern = Pattern.compile(emailRegex);
        
        if(!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException(email + ": Invalid input, enter correct format for email.");
        }
    }
}
