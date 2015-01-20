package nl.rooftopenergy.bionic.transfer;

/**
 * Created by Alex Iakovenko on 19.01.2015.
 */
public class PasswordTransfer {
    private  String oldPassword;
    private String newPassword;

    public PasswordTransfer(){}

    public PasswordTransfer(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
