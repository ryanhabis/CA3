/**
 * @Author Ryan
 */
package CA3.bookRentalSystem.rental;

import java.time.LocalDate;
import java.util.Objects;

public class User
{
    private int userId = 0;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private LocalDate dob;
    private String phoneNumber;
    private String email;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String county;
    private String eircode;
    private Enum accountStatus;
    private String userType;



    public enum AccountStatus {
       enabled, disabled
   }

//    public enum UserType
//    {
//
//        ADMIN("ADMIN"), CUSTOMER("CUSTOMER");
//
//        private String name;
//
//        UserType(String name) {
//            this.name = name;
//        }
//
//        public String getUserTypeName() {
//            return name;
//        }
//    }

    public User() {
    }



    public User(String firstName, String lastName, String username, String password, LocalDate dob, String phoneNumber, String email, String addressLine1, String addressLine2, String city, String county, String eircode, Enum accountStatus, String userType) {
    //    this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        // Hash this password
        this.password = password;
        this.dob = dob;
       // LocalDate dob1 = LocalDate.of (1945, 12, 22);
        //this.dob = dob1;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.county = county;
        this.eircode = eircode;
        this.accountStatus = accountStatus;
        this.userType = userType;

    }

//    public User(String firstName, String lastName, String username, String password) {
//
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.username = username;
//        // Hash this password
//        this.password = password;
//    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
       // reference: Michelle's notes from second year
        this.password = String.valueOf(Math.abs(password.hashCode()%password.length()));
    }


    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
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

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getEircode() {
        return eircode;
    }

    public void setEircode(String eircode) {
        this.eircode = eircode;
    }
    public Enum getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Enum accountStatus) {
        this.accountStatus = accountStatus;
    }
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User users = (User) o;
        return userId == users.userId && phoneNumber == users.phoneNumber && Objects.equals(firstName, users.firstName) && Objects.equals(lastName, users.lastName) && Objects.equals(username, users.username) && Objects.equals(password, users.password) && Objects.equals(dob, users.dob) && Objects.equals(email, users.email) && Objects.equals(addressLine1, users.addressLine1) && Objects.equals(addressLine2, users.addressLine2) && Objects.equals(city, users.city) && Objects.equals(county, users.county) && Objects.equals(eircode, users.eircode) && Objects.equals(userType, users.userType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "users{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", usersName='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dob='" + dob + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", eircode='" + eircode + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}