package dk.jsh.itdiplom.dbsw.bari.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * UserGroup entity class.
 *
 * @author Jan S. Hansen
 */
@Entity
@Table(name="UserGroup",
       uniqueConstraints = {
            @UniqueConstraint(columnNames={"bariUser_id", "product_id"})
})
public class UserGroup implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Version
    @Column(nullable = false)
    protected Integer version;
    @ManyToOne(optional=false)
    @org.hibernate.annotations.ForeignKey(name="fk_from_usergroup_to_bariuser")
    protected BariUser bariUser;
    @ManyToOne(optional=false)
    @org.hibernate.annotations.ForeignKey(name="fk_from_usergroup_to_product")
    protected Product product;

    public UserGroup() {
    }

    public UserGroup(BariUser bariUser, Product product) {
        this.bariUser = bariUser;
        this.product = product;
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

    public BariUser getBariUser() {
        return bariUser;
    }

    public void setBariUser(BariUser bariUser) {
        this.bariUser = bariUser;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}