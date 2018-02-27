/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Source code: https://github.com/jaxio/celerio/
 * Follow us on twitter: @jaxiosoft
 * This header can be customized in Celerio conf...
 * Template pack-angular:src/main/java/domain/Entity.java.e.vm
 */
package com.willbe.giftapp.domain;

import com.google.common.base.MoreObjects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.logging.Logger;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "config_")
public class Config_ implements Identifiable<Integer>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(Config_.class.getName());

    // Raw attributes
    private Integer id;
    private String ruleValue;

    // Many to one
    private User_ user;
    private App_ app;

    @Override
    public String entityClassName() {
        return Config_.class.getSimpleName();
    }

    // -- [id] ------------------------

    @Override
    @Column(name = "id", precision = 10)
    @GeneratedValue(strategy = IDENTITY)
    @Id
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Config_ id(Integer id) {
        setId(id);
        return this;
    }

    @Override
    @Transient
    public boolean isIdSet() {
        return id != null;
    }
    // -- [ruleValue] ------------------------

    @Size(max = 4000)
    @Column(name = "rule_value", length = 4000)
    public String getRuleValue() {
        return ruleValue;
    }

    public void setRuleValue(String ruleValue) {
        this.ruleValue = ruleValue;
    }

    public Config_ ruleValue(String ruleValue) {
        setRuleValue(ruleValue);
        return this;
    }

    // -----------------------------------------------------------------
    // Many to One support
    // -----------------------------------------------------------------

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // many-to-one: Config_.user ==> User_.id
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @JoinColumn(name = "user_id")
    @ManyToOne
    public User_ getUser() {
        return user;
    }

    /**
     * Set the {@link #user} without adding this Config_ instance on the passed {@link #user}
     */
    public void setUser(User_ user) {
        this.user = user;
    }

    public Config_ user(User_ user) {
        setUser(user);
        return this;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // many-to-one: Config_.app ==> App_.id
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @NotNull
    @JoinColumn(name = "app_id", nullable = false)
    @ManyToOne
    public App_ getApp() {
        return app;
    }

    /**
     * Set the {@link #app} without adding this Config_ instance on the passed {@link #app}
     */
    public void setApp(App_ app) {
        this.app = app;
    }

    public Config_ app(App_ app) {
        setApp(app);
        return this;
    }

    /**
     * Apply the default values.
     */
    public Config_ withDefaults() {
        return this;
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof Config_ && hashCode() == other.hashCode());
    }

    private IdentifiableHashBuilder identifiableHashBuilder = new IdentifiableHashBuilder();

    @Override
    public int hashCode() {
        return identifiableHashBuilder.hash(log, this);
    }

    /**
     * Construct a readable string representation for this Config_ instance.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this) //
                .add("id", getId()) //
                .add("ruleValue", getRuleValue()) //
                .toString();
    }
}