package dk.jsh.itdiplom.wsp.bari.wicket.test;

import dk.jsh.itdiplom.dbsw.bari.domain.BariUser;
import dk.jsh.itdiplom.dbsw.bari.domain.Constants;
import dk.jsh.itdiplom.dbsw.bari.wicket.BariSession;
import dk.jsh.itdiplom.dbsw.bari.wicket.CreateNew;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit test for CreateNew page.
 *
 * @author Jan S. Hansen
 */
public class CreateNewTest {
    /**
     * Test that required fields must be filled in.
     */
    @Test
    public void TestRequiredFields() {
        WicketTester wt = new WicketTester();
        BariUser bariUser = new BariUser("login", "password", "Bruger navn",
                Constants.UserRole.NORMAL);
        BariSession bariSession = BariSession.get();
                    bariSession.setBariUser(bariUser);

        CreateNew createNew = new CreateNew();
        wt.startPage(createNew);
        wt.assertLabel("error", ""); //No errors yet
        wt.setParameterForNextRequest("form:title", "");
        wt.setParameterForNextRequest("form:user", "");
        wt.setParameterForNextRequest("form:description", "");
        wt.setParameterForNextRequest("form:type", Constants.Type.ERROR);
        wt.submitForm("form");
        String error = wt.getTagByWicketId("error").getValue();
        assertTrue("Title/Overskrift should be part of the error message",
                error.contains("Overskrift"));
        assertTrue("User/Oprettet af should be part of the error message",
                error.contains("Oprettet af"));
        assertTrue("Descripten/Beskrivelse should be part of the error message",
                error.contains("Beskrivelse"));
    }
}