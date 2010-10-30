package dk.jsh.itdiplom.dbsw.bari.wicket;           

import dk.jsh.itdiplom.dbsw.bari.domain.BariUser;
import dk.jsh.itdiplom.dbsw.bari.domain.Constants.Type;
import java.text.SimpleDateFormat;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.resources.StyleSheetReference;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/** 
 * Abstract wicket base page. Handles common error message handling, stylecheet
 * and menu links.
 *
 * @author Jan S. Hansen
 */
public abstract class BasePage extends WebPage {
    final static SimpleDateFormat standardDateTimeFormat =
            new SimpleDateFormat("dd/MM-yyyy HH:mm");
    private String errorMessage = "";
    private BariUser bariUser;

    /**
     * Constructor
     */
    public BasePage() {
        BariSession bariSession = BariSession.get();
        bariUser = bariSession.getBariUser();
        add(new Label("userandrole", new Model(bariUser.getFullname() + " som "
                + bariUser.getUserRole().getName())));

        PropertyModel errorMessageModel =
                new PropertyModel(this, "errorMessage");
        add(new Label("error", errorMessageModel));
        add(new StyleSheetReference("stylesheet", BasePage.class, "style.css"));

        add(new Link("createnew") {
            @Override
            public void onClick() {
                Page page = new CreateNew(bariUser);
                setResponsePage(page);
            }

            @Override
            public boolean isEnabled() {
                return BariSession.get().isAuthenticated();
            }
        });

        add(new Link("overview") {
            @Override
            public void onClick() {
                Page page = new Overview(bariUser, Type.ERROR, "Alle");
                setResponsePage(page);
            }

            @Override
            public boolean isEnabled() {
                return BariSession.get().isAuthenticated();
            }
        });

        add(new Link("logout") {
            @Override
            public void onClick() {
                BariSession bariSession = BariSession.get();
                bariSession.setBariUser(null);
                Page page = new Login();
                setResponsePage(page);
            }

            @Override
            public boolean isEnabled() {
                return BariSession.get().isAuthenticated();
            }
        });

        add(new Link("about") {
            @Override
            public void onClick() {
                Page page = new About();
                setResponsePage(page);
            }
        });
    }

    /**
     * Get BaRI user.
     */
    public BariUser getBariUser() {
        return bariUser;
    }

    /**
     * Set BaRI user.
     */
    public void setBariUser(BariUser bariUser) {
        this.bariUser = bariUser;
    }

    /**
     * Returns an error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Set error message.
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
