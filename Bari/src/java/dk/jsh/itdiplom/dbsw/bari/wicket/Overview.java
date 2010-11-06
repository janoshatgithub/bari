package dk.jsh.itdiplom.dbsw.bari.wicket;

import dk.jsh.itdiplom.dbsw.bari.bussiness.BariCaseBusiness;
import dk.jsh.itdiplom.dbsw.bari.domain.BariCase;
import dk.jsh.itdiplom.dbsw.bari.domain.Constants.CaseStatus;
import dk.jsh.itdiplom.dbsw.bari.domain.Constants.Type;
import java.util.LinkedList;
import java.util.List;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;

/**
 * Overview page for all BariCases.
 * 
 * @author Jan S. Hansen
 */
public final class Overview extends BasePage {
    private DropDownChoice<String> dropDownChoiceType;
    private DropDownChoice<String> statusDownChoiceType;

    /**
     * Constructor.
     */
    public Overview() {
        this(Type.ERROR, "Alle");
    }

    /**
     * Constructor. 
     * 
     * @param type Type used as default on overview page.
     * @param status Status uses as default on overview page.
     */
    public Overview(Type type, String status) {
        //Add search form.
        Form form = new Form("form");
        add(form);
        dropDownChoiceType = new DropDownChoice("type",
                new Model(type.getDescription()), Type.getDescriptions());
        form.add(dropDownChoiceType);
        LinkedList<String> statusList =
                new LinkedList<String>(CaseStatus.getDescriptions());
        statusList.addFirst("Alle");
        statusDownChoiceType =
                new DropDownChoice("status", new Model(status), statusList);
        form.add(statusDownChoiceType);
        form.add(new Button("search") {
            @Override
            public void onSubmit() {
                Page page = new Overview( 
                        Type.getType(dropDownChoiceType.getModelObject()),
                        statusDownChoiceType.getModelObject());
                setResponsePage(page);
            }
        });

        //Add table with search results.
        CaseStatus cs = null;
        if (!"Alle".equals(statusDownChoiceType.getModelObject())) {
            cs = CaseStatus.getCaseStatus(statusDownChoiceType.getModelObject());
        }
        List<BariCase> bariCases = BariCaseBusiness.getAllBariCases(type, cs);
        PageableListView pageableListView =
                new PageableListView("pageable", bariCases, 10) {
            @Override
            protected void populateItem(final ListItem item) {
                final BariCase bariCase = (BariCase) item.getModelObject();
                item.add(new Label("title", bariCase.getTitle()));
                item.add(new Label("created",
                        standardDateTimeFormat.format(bariCase.getCreated())));
                item.add(new Label("finished", 
                        bariCase.getFinished() == null ? ""
                        :standardDateTimeFormat.format(bariCase.getFinished())));
                item.add(new Label("casestatus",
                        bariCase.getCaseStatus().getDescription()));
                item.add(new Label("devstatus",
                        bariCase.getDevStatus().getDescription()));
                item.add(new Link("action") {
                    @Override
                    public void onClick() {
                        Page page = new Update(bariCase);
                        setResponsePage(page);
                    }
                });
                item.add(new AttributeModifier("class",
                        true, new AbstractReadOnlyModel<String>()
                {
                    @Override
                    public String getObject()
                    {
                        return (item.getIndex() % 2 == 1) ? "even" : "odd";
                    }
                }));
            }
        };
        add(pageableListView);
        add(new PagingNavigator("navigator", pageableListView));
    }
}