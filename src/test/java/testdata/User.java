package testdata;

import helpers.JSONReaderHelper;
import utils.RandomizerUtils;

public class User {

    private String username;
    private String password;

    public User(String fileName) {
        this.username = JSONReaderHelper.extractValue(fileName, "username");
        this.password = JSONReaderHelper.extractValue(fileName, "password");
    }

    //GETTERS
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getRandomUsername() {
        return username = getUsername() + RandomizerUtils.integerValue(9) + RandomizerUtils.stringValue(3);
    }

    //SETTERS
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
