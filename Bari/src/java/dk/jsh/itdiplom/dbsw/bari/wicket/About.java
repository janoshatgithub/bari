package dk.jsh.itdiplom.dbsw.bari.wicket;

import org.apache.wicket.markup.html.image.Image;

/**
 * About page.
 *
 * @author Jan S. Hansen
 */
public class About extends BasePage {

    public About() {
        add(new Image("wtfm", "wtfm.jpg"));
    }
}