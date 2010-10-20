package dk.jsh.itdiplom.dbsw.bari.domain;

import java.io.Serializable;

/**
 * BariUser entity class.
 *
 * @author Jan S. Hansen
 */
public class BariUser implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String login;
    private String password;
    private String fullname;
    private String email;

    public BariUser() {
    }

    public BariUser(String login, String password, String fullname,
            String email) {
        this.login = login;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}