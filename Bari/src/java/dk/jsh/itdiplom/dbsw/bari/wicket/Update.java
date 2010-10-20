package dk.jsh.itdiplom.dbsw.bari.wicket;

import dk.jsh.itdiplom.dbsw.bari.bussiness.BariCaseBusiness;
import dk.jsh.itdiplom.dbsw.bari.domain.BariCase;
import dk.jsh.itdiplom.dbsw.bari.domain.Constants.CaseStatus;
import dk.jsh.itdiplom.dbsw.bari.domain.Constants.DevStatus;
import dk.jsh.itdiplom.dbsw.bari.domain.Constants.Type;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.hibernate.StaleObjectStateException;

/**
 * BariCase update page.
 *
 * @author Jan S. Hansen
 */
public final class Update extends BasePage {
    private TextField<String> title;
    private DropDownChoice<String> type;
    private DropDownChoice<String> caseStatus;
    private DropDownChoice<String> devStatus;
    private TextArea<String> description;
    private TextArea<String> conclusion;

    /**
     * Constructor.
     * 
     * @param bariCase A BaRI user.
     */
    public Update(final BariCase bariCase) {
        
        //Add a form.
        Form form = new Form("form") {
            //Handle required fields errors.
            @Override
            protected void onError() {
                List<String> emptyFields = new ArrayList<String>();
                if (!title.checkRequired()) {
                    emptyFields.add("Overskrift");
                    title.add(new AttributeModifier("style", true,
                            new Model("border-color:red;")));
                }
                else {
                    title.add(new AttributeModifier("style", true,
                            new Model("border-color:default;")));
                }
                if (!type.checkRequired()) {
                    emptyFields.add("Type");
                    type.add(new AttributeModifier("style", true,
                            new Model("border-color:red;")));
                }
                else {
                    type.add(new AttributeModifier("style", true,
                            new Model("border-color:default;")));
                }
                if (!description.checkRequired()) {
                    emptyFields.add("Beskrivelse");
                    description.add(new AttributeModifier("style", true,
                            new Model("border-color:red;")));
                }
                else {
                    description.add(new AttributeModifier("style", true,
                            new Model("border-color:defalut;")));
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

        //Add form fields.
        title = new TextField("title", new Model(bariCase.getTitle()));
        title.setRequired(true);
        form.add(title);
        type = new DropDownChoice("type", 
                new Model(bariCase.getType().getDescription()),
                Type.getDescriptions());
        type.setRequired(true);
        form.add(type);
        form.add(new TextField("user", new Model(bariCase.getBariUser())));
        form.add(new TextField("created",
                new Model(standardDateTimeFormat.format(bariCase.getCreated()))));
        form.add(new TextField("finished", 
                new Model(bariCase.getFinished() == null ? ""
                :standardDateTimeFormat.format(bariCase.getFinished()))));
        caseStatus = new DropDownChoice("caseStatus", 
                new Model(bariCase.getCaseStatus().getDescription()),
                CaseStatus.getDescriptions());
        caseStatus.setRequired(true);
        form.add(caseStatus);
        devStatus = new DropDownChoice("devStatus", 
                new Model(bariCase.getDevStatus().getDescription()),
                DevStatus.getDescriptions());
        devStatus.setRequired(true);
        form.add(devStatus);
        description = new TextArea("description",
                new Model(bariCase.getDescription())) ;
        description.setRequired(true);
        form.add(description);
        conclusion = new TextArea("conclusion",
                new Model(bariCase.getConclusion()));
        form.add(conclusion);

        //Add form links and buttons
        form.add(new Link("showDiscussion") {
            @Override
            public void onClick() {
                Page page = new Discussion(bariCase);
                setResponsePage(page);
            }
        });

        form.add(new Link("cancel") {
            @Override
            public void onClick() {
                setResponsePage(Overview.class);
            }
        });
        
        Button saveButton = new Button("save") {
            @Override
            public void onSubmit() {
                bariCase.setTitle(title.getModelObject());
                bariCase.setType(Type.getType(type.getModelObject()));
                bariCase.setCaseStatus(
                        CaseStatus.getCaseStatus(caseStatus.getModelObject()));
                bariCase.setDevStatus(
                        DevStatus.getDevStatus(devStatus.getModelObject()));
                bariCase.setDescription(description.getModelObject());
                bariCase.setConclusion(conclusion.getModelObject());
                try {
                    BariCaseBusiness.update(bariCase);
                    setResponsePage(Overview.class);
                }
                catch (StaleObjectStateException sose) {
                    setErrorMessage("Sagen kan ikke gemmes, " +
                            "da den er rettet af en anden!");
                }
            }
        };
        form.add(saveButton);
        Link deleteLink = new Link("delete") {
            @Override
            public void onClick() {
                BariCaseBusiness.delete(bariCase);
                setResponsePage(Overview.class);
            }
        };
        deleteLink.add(new JS_EventConfirmation("onclick", "Er du sikker på" +
                " at du vil slette?"));
        form.add(deleteLink);

        //Disable fields and save button, if case is finished.
        if (bariCase.getFinished() != null) {
            title.setEnabled(false);
            type.setEnabled(false);
            caseStatus.setEnabled(false);
            devStatus.setEnabled(false);
            description.setEnabled(false);
            conclusion.setEnabled(false);
            saveButton.setEnabled(false);
        }
    }

    /**
     * Inner class that adds a javascript confirm dialog to a attribute.
     */
    private class JS_EventConfirmation extends AttributeModifier {

	public JS_EventConfirmation(String event, String msg) {
		super(event, true, new Model(msg));
	}

        @Override
	protected String newValue(final String currentValue,
			final String replacementValue) {
                String result = "if (confirm('" + replacementValue + "')) ";
		if (currentValue != null) {
			result += currentValue + ";";
		}
		return result;
	}
    }
}