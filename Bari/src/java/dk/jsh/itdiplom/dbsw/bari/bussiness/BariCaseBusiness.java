package dk.jsh.itdiplom.dbsw.bari.bussiness;

import dk.jsh.itdiplom.dbsw.bari.domain.BariCase;
import dk.jsh.itdiplom.dbsw.bari.domain.Constants.CaseStatus;
import dk.jsh.itdiplom.dbsw.bari.domain.Constants.Type;
import dk.jsh.itdiplom.dbsw.bari.domain.Product;
import dk.jsh.itdiplom.dbsw.bari.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Business metods for BariCase.
 *
 * @author Jan S. Hansen
 */
public class BariCaseBusiness {
    /**
     * Create new BariCase.
     * 
     * @param bariCase BariCase 
     */
    public static void saveNew(BariCase bariCase) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(bariCase);
        tx.commit();
        session.close();
    }

    /**
     * Get all BariCase objects.
     *
     * @param product product return BariCases to this product.
     * @param type type of BariCases to return.
     * @param caseStatus type of caseStatuses to return.
     * @return a List of BariCase objects.
     */
    public static List<BariCase> getAllBariCases(Product product,
            Type type, CaseStatus caseStatus) {
        List<BariCase> bariCases = new ArrayList<BariCase>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "select bariCase from "
                + "dk.jsh.itdiplom.dbsw.bari.domain.BariCase bariCase "
                + "where bariCase.type = :type and "
                + "product.id = :productid ";
        if (caseStatus != null) {
            hql += "and bariCase.caseStatus = :caseStatus ";
        }
        hql += "order by bariCase.created desc";
        Query query = session.createQuery(hql);
        query.setString("type", type.toString());
        query.setString("productid", product.getId().toString());
        if (caseStatus != null) {
            query.setString("caseStatus", caseStatus.toString());
        }
        bariCases = query.list();
        session.close();
        return bariCases;
    }

    /**
     * Update a BariCase.
     * 
     * @param bariCase BariCase
     */
    public static void update(BariCase bariCase) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            if (bariCase.getCaseStatus().equals(CaseStatus.DONE)) {
                bariCase.setFinished(new Date());
            }
            session.update(bariCase);
            tx.commit();
        }
        finally {
            session.close();
        }
    }

    /**
     * Delete a bariCase and all discussion messages.
     * 
     * @param bariCase BariCase
     */
    public static void delete(BariCase bariCase) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String sql = "delete from DiscussionMessage where "
                + "bariCase_id = :id";
        Query query = session.createSQLQuery(sql);
        query.setString("id", bariCase.getId().toString());
        query.executeUpdate();
        session.delete(bariCase);
        tx.commit();
        session.close();
    }
}
