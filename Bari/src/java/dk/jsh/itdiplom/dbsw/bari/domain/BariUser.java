package dk.jsh.itdiplom.dbsw.bari.domain;

import dk.jsh.itdiplom.dbsw.bari.domain.Constants.UserRole;
import java.io.Serializable;
import javax.persistence.*;

/**
 * BariUser entity class.
 *
 * @author Jan S. Hansen
 */
@Entity
public class BariUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Version
    protected Integer version;
    @Column(length=20, nullable = false)
    protected String login;
    @Column(length=20, nullable = false)
    protected String password;
    @Column(length=50, nullable = false)
    protected String fullname;
    @Enumerated(EnumType.STRING)
    @Column(length=10, nullable = false)
    protected UserRole userRole;

    public BariUser() {
    }

    public BariUser(String login, String password, String fullname,
            UserRole userRole) {
        this.login = login;
        this.password = password;
        this.fullname = fullname;
        this.userRole = userRole;
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

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}