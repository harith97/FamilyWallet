package ccpe001.familywallet.transaction;

/**
 * Created by Knight on 7/9/2017.
 */

public class TransactionDetails {

    public String userID;
    public String amount;
    public String title;
    public String categoryName;
    public String date;
    public Integer categoryID;
    public String time;
    public String account;
    public String location;
    public String type;
    public String currency;



    public TransactionDetails() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public TransactionDetails(String userID, String amount, String title, String categoryName, String date, Integer categoryID, String time, String account, String location, String type, String currency) {
        this.userID = userID;
        this.amount = amount;
        this.title = title;
        this.categoryName = categoryName;
        this.date = date;
        this.categoryID = categoryID;
        this.time = time;
        this.account = account;
        this.location = location;
        this.type = type;
        this.currency = currency;
    }

    public String getUserID() {
        return userID;
    }

    public String getAmount() {
        return amount;
    }

    public String getTitle() {
        return title;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDate() {
        return date;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public String getTime() {
        return time;
    }

    public String getAccount() {
        return account;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public String getCurrency() {
        return currency;
    }
}
