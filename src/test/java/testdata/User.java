package testdata;

import utils.RandomizerUtils;
import helpers.JsonReaderHelper;

public class User {

    private String username;
    private String password;

    public User(String fileName) {
        this.username = JsonReaderHelper.json(fileName).get("username").toString();
        this.password = JsonReaderHelper.json(fileName).get("password").toString();
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
