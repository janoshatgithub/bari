package dk.jsh.itdiplom.dbsw.bari.business;

import dk.jsh.itdiplom.dbsw.bari.domain.BariUser;
import dk.jsh.itdiplom.dbsw.bari.domain.Product;
import dk.jsh.itdiplom.dbsw.bari.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Business metods for UserGroup.
 *
 * @author Jan S. Hansen
 */
public class UserGroupBusiness {

    /**
     * Get all Products a given bariUser can access.
     *
     * @param bariCase BariCase.
     * @return a List of DiscussionMessage objects.
     */
    public static List<Product> getAllDiscussionMessages(
            BariUser bariUser) {
        List<Product> products =
                new ArrayList<Product>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "select product from "
                + "dk.jsh.itdiplom.dbsw.bari.domain.Product product, "
                + "dk.jsh.itdiplom.dbsw.bari.domain.UserGroup userGroup " 
                + "where userGroup.bariUser.id = :userid and "
                + "product.id = userGroup.product.id "
                + "order by product.name";
        Query query = session.createQuery(hql);
        query.setString("userid", bariUser.getId().toString());
        products = query.list();
        session.close();
        return products;
    }
}