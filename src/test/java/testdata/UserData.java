package testdata;
import helpers.JSONReaderHelper;

public class UserData {

    private String firstName;
    private String lastName;
    private String zipCode;
    private String cardDetails;

    public UserData(String fileName) {
        this.firstName = JSONReaderHelper.extractValue(fileName, "firstName");
        this.lastName = JSONReaderHelper.extractValue(fileName, "lastName");
        this.zipCode = JSONReaderHelper.extractValue(fileName, "zipCode");
        this.cardDetails = JSONReaderHelper.extractValue(fileName, "cardDetails");
    }

    //GETTERS
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getZipCode() {
        return zipCode;
    }
    public String getCreditCard() {
        return cardDetails;
    }

    //SETTERS
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public void setCreditCard(String creditCard) {
        this.cardDetails = creditCard;
    }
}
