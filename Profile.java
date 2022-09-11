public class Profile {
    private String username;
    private String password;
    private String emailAddress;
    private String firstName;
    private String lastName;
    private long phoneNum;

    public Profile(String username, String password, String emailAddress, String firstName, String lastName, long phoneNum) {
        setUsername(username);
        setPassword(password);
        setEmailAddress(emailAddress);
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNum(phoneNum);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getPhoneNum() {
        return this.phoneNum;
    }

    public void setPhoneNum(long phoneNum) {
        this.phoneNum = phoneNum;
    }

}
