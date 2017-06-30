package ccpe001.familywallet.admin;

/**
 * Created by harithaperera on 6/26/17.
 */
public class UserData {


    public String firstName;
    public String lastName;
    public String userId;

    public UserData(){

    }

    public UserData(String f, String l, String u) {
        this.lastName = l;
        this.firstName = f;
        this.userId = u;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;

    }
}
