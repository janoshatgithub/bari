package dk.jsh.itdiplom.dbsw.bari.wicket;           

import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;

/** 
 * Wicket application.
 *
 * @author Jan S. Hansen
 */
public class Application extends WebApplication {

    /**
     * Constructor.
     */
    public Application() {
    }

    /**
     * Returns home page for the application.
     */
    @Override
    public Class getHomePage() {
        //return Overview.class;
        return Login.class;
    }

    @Override
    public Session newSession(Request request, Response response) {
        return new BariSession(request);
    }
}