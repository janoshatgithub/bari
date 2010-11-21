package dk.jsh.itdiplom.dbsw.bari.wicket;
import dk.jsh.itdiplom.dbsw.bari.business.DiscussionMessageBusiness;
import dk.jsh.itdiplom.dbsw.bari.domain.BariCase;
import dk.jsh.itdiplom.dbsw.bari.domain.BariUser;
import dk.jsh.itdiplom.dbsw.bari.domain.DiscussionMessage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;

/**
 * Discussion page.
 * 
 * @author Jan S. Hansen
 */
public final class Discussion extends BasePage {
    private TextField<String> user;
    private TextArea<String> message;

    /**
     * Constructor.
     * 
     * @param bariCase A BaRI user.
     */
    public Discussion(final BariCase bariCase) {
        add(new Label("product", new Model(bariCase.getProduct().getName())));
        add(new Label("type", new Model(bariCase.getType().getDescription())));
        add(new Label("title", new Model(bariCase.getTitle())));

        //Get all discussion messages and build a discussion log.
        List<DiscussionMessage> discussionMessages =
                DiscussionMessageBusiness.getAllDiscussionMessages(bariCase);
        if (discussionMessages.size() > 0) {
            StringBuilder log = new StringBuilder();
            for (DiscussionMessage discussionMessage : discussionMessages) {
                log.append(standardDateTimeFormat.format(discussionMessage.getCreated()));
                log.append(" af ");
                log.append(discussionMessage.getBariUser().getFullname());
                log.append(":\n");
                log.append(discussionMessage.getMessage());
                log.append("\n\n");
            }
            add(new TextArea("log", new Model(log.toString())));
        }
        else {
            add(new TextArea("log", new Model("Der er ingen indlæg.")));
        }

        //Create and add a form
        Form form = new Form("form") {
            //Handles required fields error.
            @Override
            protected void onError() {
                List<String> emptyFields = new ArrayList<String>();
                if (!message.checkRequired()) {
                    emptyFields.add("Indlæg");
                    message.add(new AttributeModifier("style", true,
                            new Model("border-color:red;")));
                }
                else {
                    message.add(new AttributeModifier("style", true,
                            new Model("border-color:default;")));
                }
                StringBuilder errorMessage = new StringBuilder();
                if (emptyFields.size() == 1) {
                    errorMessage.append("Feltet ");
                    errorMessage.append("'").append(emptyFields.get(0))
                            .append("'");
                }
                else {
                    errorMessage.append("Felterne ");
                    int fieldCounter = 1;
                    for (String field : emptyFields) {
                        errorMessage.append("'").append(field).append("'");
                        if (fieldCounter < emptyFields.size() -1) {
                            errorMessage.append(", ");
                        }
                        if (fieldCounter == emptyFields.size() -1) {
                            errorMessage.append(" og ");
                        }
                        fieldCounter++;
                    }
                }
                errorMessage.append(" skal udfyldes.");
                setErrorMessage(errorMessage.toString());
            }
        };
        add(form);

        //Add fields to the form.
        message = new TextArea("message", new Model(""));
        message.setRequired(true);
        form.add(message);

        //Add links and buttons to the form.
        form.add(new Link("goBack") {
            @Override
            public void onClick() {
                Page page = new Update(bariCase);
                setResponsePage(page);
            }
        });

        form.add(new Button("save") {
            @Override
            public void onSubmit() {
                BariUser bariUser = BariSession.get().getBariUser();
                DiscussionMessage discussionMessage = new DiscussionMessage(
                        bariCase, new Date(), bariUser,
                        message.getModelObject());
                DiscussionMessageBusiness.saveNew(discussionMessage);
                Page page = new Discussion(bariCase);
                setResponsePage(page);
            }
      });
    }
}

