/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Source code: https://github.com/jaxio/celerio/
 * Follow us on twitter: @jaxiosoft
 * This header can be customized in Celerio conf...
 * Template pack-angular:src/main/java/domain/EntityMeta_.java.e.vm
 */
package com.willbe.giftapp.domain;

import java.time.Instant;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User_.class)
public abstract class User__ {

    // Raw attributes
    public static volatile SingularAttribute<User_, Integer> id;
    public static volatile SingularAttribute<User_, String> login;
    public static volatile SingularAttribute<User_, String> password;
    public static volatile SingularAttribute<User_, String> email;
    public static volatile SingularAttribute<User_, Boolean> isEnabled;
    public static volatile SingularAttribute<User_, Civility> civility;
    public static volatile SingularAttribute<User_, CountryCode> countryCode;
    public static volatile SingularAttribute<User_, String> firstName;
    public static volatile SingularAttribute<User_, String> lastName;
    public static volatile SingularAttribute<User_, Instant> creationDate;
    public static volatile SingularAttribute<User_, String> creationAuthor;
    public static volatile SingularAttribute<User_, Instant> lastModificationDate;
    public static volatile SingularAttribute<User_, String> lastModificationAuthor;
    public static volatile SingularAttribute<User_, Integer> version;

    // One to one
    public static volatile SingularAttribute<User_, Passport> passport;

    // Many to many
    public static volatile ListAttribute<User_, Role> roles;
}