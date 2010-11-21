package dk.jsh.itdiplom.dbsw.bari.business;

import dk.jsh.itdiplom.dbsw.bari.domain.BariCase;
import dk.jsh.itdiplom.dbsw.bari.domain.DiscussionMessage;
import dk.jsh.itdiplom.dbsw.bari.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Business metods for DescussionMessage.
 *
 * @author Jan S. Hansen
 */
public class DiscussionMessageBusiness {
   /**
     * Create new DiscussionMessage.
     *
     * @param discussionMessage DiscussionMessage
     */
    public static void saveNew(DiscussionMessage discussionMessage) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(discussionMessage);
        tx.commit();
        session.close();
    }

    /**
     * Get all DiscussionMessages for a specific BariCase.
     *
     * @param bariCase BariCase.
     * @return a List of DiscussionMessage objects.
     */
    public static List<DiscussionMessage> getAllDiscussionMessages(
            BariCase bariCase) {
        List<DiscussionMessage> discussionMessages =
                new ArrayList<DiscussionMessage>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "select discussionMessage from "
                + "dk.jsh.itdiplom.dbsw.bari.domain.DiscussionMessage discussionMessage "
                + "where bariCase.id = :id "
                + "order by discussionMessage.created";
        Query query = session.createQuery(hql);
        query.setString("id", bariCase.getId().toString());
        discussionMessages = query.list();
        session.close();
        return discussionMessages;
    }
}
