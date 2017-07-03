package ccpe001.familywallet.admin;

import java.security.PublicKey;

/**
 * Created by harithaperera on 6/26/17.
 */
public class UserData {


    public String firstName;
    public String lastName;
    public String userId;
    public String proPic;


    public void setProPic(String proPic) {
        this.proPic = proPic;
    }

    public String getProPic() {
        return proPic;
    }

    public UserData(){

    }


    public UserData(String p){
        this.proPic = p;
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
