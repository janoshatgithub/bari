package dk.jsh.itdiplom.dbsw.bari.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Constants and enums.
 *
 * @author Jan S. Hansen
 */
public class Constants {
    private Constants() {}

    /**
     * BaRI case type: REQUEST, ERROR.
     */
    public enum Type {
        REQUEST("Nyt ønske"),
        ERROR("Fejl");

        private String description;

        Type(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public static List<String> getDescriptions() {
            List<String> descriptions = new ArrayList<String>();
            descriptions.add(ERROR.description);
            descriptions.add(REQUEST.description);
            return descriptions;
        }

        public static Type getType(String description) {
            if (REQUEST.description.equals(description)) return REQUEST;
            return ERROR;
        }
     }

    /**
     * Case status: NEW, CONSIDER, APPROVED, REJECTED and DONE.
     */
    public enum CaseStatus {
        NEW("Ny"),
        CONSIDER("Behandles"),
        APPROVED("Godkendt"),
        REJECTED("Afvist"),
        DONE("Afsluttet");

        private String description;

        CaseStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public static List<String> getDescriptions() {
            List<String> descriptions = new ArrayList<String>();
            descriptions.add(NEW.description);
            descriptions.add(CONSIDER.description);
            descriptions.add(APPROVED.description);
            descriptions.add(REJECTED.description);
            descriptions.add(DONE.description);
            return descriptions;
        }

        public static CaseStatus getCaseStatus(String description) {
            if (NEW.description.equals(description)) return NEW;
            if (CONSIDER.description.equals(description)) return CONSIDER;
            if (APPROVED.description.equals(description)) return APPROVED;
            if (DONE.description.equals(description)) return DONE;
            return REJECTED;
        }
    }

    /**
     * Developer status: NOTSTARTED, STARTED, READYTOTEST, TESTED and
     * INPRODUCTION.
     */
    public enum DevStatus {
        NOTSTARTED("Ej begyndt"),
        STARTED("I gang"),
        READYTOTEST("Klar til test"),
        TESTED("Testet"),
        INPRODUCTION("I prod.");

        private String description;

        DevStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public static List<String> getDescriptions() {
            List<String> descriptions = new ArrayList<String>();
            descriptions.add(NOTSTARTED.description);
            descriptions.add(STARTED.description);
            descriptions.add(READYTOTEST.description);
            descriptions.add(TESTED.description);
            descriptions.add(INPRODUCTION.description);
            return descriptions;
        }

        public static DevStatus getDevStatus(String description) {
            if (NOTSTARTED.description.equals(description)) return NOTSTARTED;
            if (STARTED.description.equals(description)) return STARTED;
            if (READYTOTEST.description.equals(description)) return READYTOTEST;
            if (TESTED.description.equals(description)) return TESTED;
            return INPRODUCTION;
        }
    }
}