package dk.jsh.itdiplom.dbsw.bari.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Discussion message entity class.
 * 
 * @author Jan S. Hansen
 */
@Entity
public class DiscussionMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Version
    @Column(nullable = false)
    protected Integer version;
    @ManyToOne(optional=false)
    @org.hibernate.annotations.ForeignKey(name="fk_from_discussionmessage_to_baricase")
    protected BariCase bariCase;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(nullable = false)
    protected Date created;
    @ManyToOne(optional=false)
    @org.hibernate.annotations.ForeignKey(name="fk_from_discussiommessage_to_bariuser")
    protected BariUser bariUser;
    @Column(length=400, nullable = false)
    protected String message;

    public DiscussionMessage() {
    }
    
    public DiscussionMessage(BariCase bariCase, Date created, BariUser bariUser,
            String message) {
        this.bariCase = bariCase;
        this.created = created;
        this.bariUser = bariUser;
        this.message = message;
    }

    public BariUser getBariUser() {
        return bariUser;
    }

    public void setBariUser(BariUser bariUser) {
        this.bariUser = bariUser;
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

    public void setVersion(Integer version) {
        this.version = version;
    }

    public BariCase getBariCase() {
        return bariCase;
    }

    public void setBariCase(BariCase bariCase) {
        this.bariCase = bariCase;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
