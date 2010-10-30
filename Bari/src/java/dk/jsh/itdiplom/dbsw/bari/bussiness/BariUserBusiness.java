package dk.jsh.itdiplom.dbsw.bari.bussiness;

import dk.jsh.itdiplom.dbsw.bari.domain.BariUser;
import dk.jsh.itdiplom.dbsw.bari.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Business metods for BariUser.
 *
 * @author Jan S. Hansen
 */
public class BariUserBusiness {

    /**
     * Gets a bariUser from login and password.
     * 
     * @param login bari user login
     * @param password password
     * @return a BariUser or null if login or password is wrong
     */
    public static BariUser isValidUser(String login, String password) {
        BariUser bariUser = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "select bariUser from "
                + "dk.jsh.itdiplom.dbsw.bari.domain.BariUser bariUser "
                + "where bariUser.login = :login "
                + "and bariUser.password = :password";
        Query query = session.createQuery(hql);
        query.setString("login", login);
        query.setString("password", login);
        List<BariUser> bariUsers = query.list();
        if (bariUsers.size() == 1) {
            bariUser = bariUsers.get(0);
        }
        else if (bariUsers.size() > 1) {
            throw new RuntimeException("More then one user with login " + login);
        }
        session.close();
        return bariUser;
    }
}