package constants;

public class LoginConst {

    public final static String USERNAME_PLACEHOLDER = "Username";
    public final static String PASSWORD_PLACEHOLDER = "Password";

    //__________VALIDATION_ERROR_MESSAGES__________//
    public final static String INVALID_USER_LOGIN_ERROR = "Epic sadface: Username and password do not match any user in this service";
    public final static String LOCKED_USER_LOGIN_ERROR = "Epic sadface: Sorry, this user has been locked out.";
    public final static String MISSING_USERNAME_ERROR = "Epic sadface: Username is required";
    public final static String MISSING_PASSWORD_ERROR = "Epic sadface: Password is required";

    //__________COLOR_CODES__________//
    public final static String LOGIN_BUTTON_COLOR = "#3ddc91";
    public final static String LOGIN_ERROR_BACKGROUND_COLOR = "#e2231a";
}
