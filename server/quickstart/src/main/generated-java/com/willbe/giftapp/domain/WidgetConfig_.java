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

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(WidgetConfig.class)
public abstract class WidgetConfig_ {

    // Raw attributes
    public static volatile SingularAttribute<WidgetConfig, Integer> id;
    public static volatile SingularAttribute<WidgetConfig, String> inputvalue;

    // Many to one
    public static volatile SingularAttribute<WidgetConfig, Config_> config;
}