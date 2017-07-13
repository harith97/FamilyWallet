package ccpe001.familywallet;

/**
 * Created by harithaperera on 7/10/17.
 */

public class NotificationDetails{
    private String notiDate;
    private String notiDesc;
    private String notiTitle;

    public NotificationDetails(String notiDate, String notiDesc, String notiTitle) {
        this.notiDate = notiDate;
        this.notiDesc = notiDesc;
        this.notiTitle = notiTitle;
    }

    public NotificationDetails(){

    }

    public String getNotiDate() {
        return notiDate;
    }

    public void setNotiDate(String notiDate) {
        this.notiDate = notiDate;
    }

    public String getNotiDesc() {
        return notiDesc;
    }

    public void setNotiDesc(String notiDesc) {
        this.notiDesc = notiDesc;
    }

    public String getNotiTitle() {
        return notiTitle;
    }

    public void setNotiTitle(String notiTitle) {
        this.notiTitle = notiTitle;
    }
}
