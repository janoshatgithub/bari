package dk.jsh.itdiplom.dbsw.bari.wicket;
import dk.jsh.itdiplom.dbsw.bari.bussiness.BariCaseBusiness;
import dk.jsh.itdiplom.dbsw.bari.bussiness.UserGroupBusiness;
import dk.jsh.itdiplom.dbsw.bari.domain.BariCase;
import dk.jsh.itdiplom.dbsw.bari.domain.BariUser;
import dk.jsh.itdiplom.dbsw.bari.domain.Constants.CaseStatus;
import dk.jsh.itdiplom.dbsw.bari.domain.Constants.DevStatus;
import dk.jsh.itdiplom.dbsw.bari.domain.Constants.Type;
import dk.jsh.itdiplom.dbsw.bari.domain.Product;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.StringValidator;

/**
 * Create new BariCase page.
 *
 * @author Jan S. Hansen
 */
public final class CreateNew extends BasePage {
    private TextField<String> title;
    private DropDownChoice<Product> products;
    private Product selectedProduct;
    private DropDownChoice<String> type;
    private TextArea<String> description;

    /**
     * Constructor.
     */
    public CreateNew() {
        BariUser bariUser = BariSession.get().getBariUser();
        List<Product> listOfProducts =
                UserGroupBusiness.getAllDiscussionMessages(bariUser);
        selectedProduct = listOfProducts.get(0);

        //Add a form as an inner class.
        Form form = new Form("form") {
            //Handles required fields error.
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
                if (!products.checkRequired()) {
                    emptyFields.add("Produkt");
                    products.add(new AttributeModifier("style", true,
                            new Model("border-color:red;")));
                }
                else {
                    products.add(new AttributeModifier("style", true,
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
                if (emptyFields.size() > 0) {
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
                }
                if (!description.isValid()) {
                    if (errorMessage.length() > 0) {
                        errorMessage.append(" ");
                    }
                    errorMessage.append("Beskrivelses feltet kan max. være 255 tegn langt.");
                    description.add(new AttributeModifier("style", true,
                            new Model("border-color:red;")));
                }
                else {
                    description.add(new AttributeModifier("style", true,
                            new Model("border-color:defalut;")));
                }
                setErrorMessage(errorMessage.toString());
            }
        };
        add(form);

        //Add fields to the form.
        title = new TextField("title", new Model(""));
        title.setRequired(true);
        form.add(title);
        products = new DropDownChoice("product", new Model(selectedProduct),
                listOfProducts, new ChoiceRenderer("name", "id"));
        products.setRequired(true);
        form.add(products);
        type = new DropDownChoice("type",
                new Model(Type.ERROR.getDescription()), Type.getDescriptions());
        type.setRequired(true);
        form.add(type);
        description = new TextArea("description", new Model(""));
        description.setRequired(true);
        description.add(StringValidator.maximumLength(255));
        form.add(description);

        //Add buttons to the form.
        form.add(new Link("cancel") {
            @Override
            public void onClick() {
                setResponsePage(Overview.class);
            }
        });
        form.add(new Button("save") {
            @Override
            public void onSubmit() {
                BariUser bariUser = BariSession.get().getBariUser();
                BariCase bariCase = new BariCase(
                        title.getModelObject(),
                        Type.getType(type.getModelObject()),
                        bariUser, products.getModelObject(),
                        new Date(), null, CaseStatus.NEW,
                        DevStatus.NOTSTARTED,
                        description.getModelObject(),
                        null);
                BariCaseBusiness.saveNew(bariCase);
                setResponsePage(Overview.class);
            }
      });
    }
}
