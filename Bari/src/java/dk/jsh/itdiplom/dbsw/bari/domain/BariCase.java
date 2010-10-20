package dk.jsh.itdiplom.dbsw.bari.domain;

import dk.jsh.itdiplom.dbsw.bari.domain.Constants.CaseStatus;
import dk.jsh.itdiplom.dbsw.bari.domain.Constants.DevStatus;
import dk.jsh.itdiplom.dbsw.bari.domain.Constants.Type;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * BariCase entity class.
 *
 * @author Jan S. Hansen
 */
@Entity
public class BariCase implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Version
    protected Integer version;
    protected String title;
    @Enumerated(EnumType.STRING)
    protected Type type;
    protected String bariUser;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected Date created;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected Date finished;
    @Enumerated(EnumType.STRING)
    protected CaseStatus caseStatus;
    @Enumerated(EnumType.STRING)
    protected DevStatus devStatus;
    protected String description;
    protected String conclusion;

    public BariCase() {
    }

    public BariCase(String title, Type type, String bariUser, Date created,
            Date finished, CaseStatus caseStatus, DevStatus devStatus,
            String description, String conclusion) {
        this.title = title;
        this.type = type;
        this.bariUser = bariUser;
        this.created = created;
        this.finished = finished;
        this.caseStatus = caseStatus;
        this.devStatus = devStatus;
        this.description = description;
        this.conclusion = conclusion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getBariUser() {
        return bariUser;
    }

    public void setBariUser(String bariUser) {
        this.bariUser = bariUser;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getFinished() {
        return finished;
    }

    public void setFinished(Date finished) {
        this.finished = finished;
    }

    public CaseStatus getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(CaseStatus caseStatus) {
        this.caseStatus = caseStatus;
    }

    public DevStatus getDevStatus() {
        return devStatus;
    }

    public void setDevStatus(DevStatus devStatus) {
        this.devStatus = devStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }
}