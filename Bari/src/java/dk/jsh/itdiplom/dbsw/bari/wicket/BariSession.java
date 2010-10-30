package dk.jsh.itdiplom.dbsw.bari.wicket;

import dk.jsh.itdiplom.dbsw.bari.domain.BariUser;
import org.apache.wicket.Request;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;

/**
 * Wicket/BaRI session.
 *
 * @author Jan S. Hansen
 */
public class BariSession extends WebSession {
    private BariUser bariUser;

    public BariSession(Request request) {
        super(request);
    }

    public static BariSession get() {
        return (BariSession) Session.get();
    }

    public boolean isAuthenticated() {
        return (bariUser != null);
    }

    public BariUser getBariUser() {
        return bariUser;
    }

    public void setBariUser(BariUser bariUser) {
        this.bariUser = bariUser;
    }


}