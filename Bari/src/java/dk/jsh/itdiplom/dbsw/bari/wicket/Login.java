package dk.jsh.itdiplom.dbsw.bari.wicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.resources.StyleSheetReference;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 * BaRI login page.
 *
 * @author Jan S. Hansen
 */
public class Login extends WebPage {
    private String errorMessage = "";
    private TextField<String> userLogin;
    private TextField<String> password;
    
    /**
     * Constructor.
     */
    public Login() {
        add(new StyleSheetReference("stylesheet", BasePage.class, "style.css"));
        PropertyModel errorMessageModel =
                new PropertyModel(this, "errorMessage");
        add(new Label("error", errorMessageModel));
        //Add a form as an inner class.
        Form form = new Form("form") {
            //Handles required fields error.
            @Override
            protected void onError() {
            }
        };
        add(form);

        //Add fields to the form.
        userLogin = new TextField("userlogin", new Model(""));
        userLogin.setRequired(true);
        form.add(userLogin);

        password = new PasswordTextField("password", new Model(""));
        password.setRequired(true);
        form.add(password);

        form.add(new Link("barilogin") {
            @Override
            public void onClick() {
                setResponsePage(Overview.class);
            }
        });
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