package org.djabry.platform.vaadin.navigation;

import com.google.common.collect.Sets;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.UI;
import lombok.extern.java.Log;
import org.djabry.platform.vaadin.presenter.Action;
import org.djabry.platform.vaadin.presenter.StartupFilter;
import org.springframework.aop.support.AopUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.VaadinComponent;
import org.vaadin.spring.events.EventBusListenerMethod;
import org.vaadin.spring.events.EventScope;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * Created by djabry on 07/01/15.
 *
 * This class listens to view changes and stops navigation to any views that are not authorized
 *
 */

@UIScope
@VaadinComponent
@Log
public class NavigationSecurity {

    @EventBusListenerMethod(scope = EventScope.UI, filter = StartupFilter.class)
    public void registerSecurityListener(Action action) {

        Navigator navigator = UI.getCurrent().getNavigator();
        navigator.addViewChangeListener(new ViewChangeListener() {
            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {
                View newView = event.getNewView();


                if (!isSecuredObject(newView)) {
                    //return true;
                }

                try {
                    newView.enter(event);
                    return true;

                } catch (org.springframework.security.access.AccessDeniedException accessDeniedException) {
                    log.warning("Attempted to navigate to page without necessary authorization: " + event.getViewName());
                    //throw accessDeniedException;
                }

                return false;
            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {

            }
        });

        log.info("Registed navigator security listener");

    }

    private boolean isSecuredObject(Object o) {

        return this.isSecuredClass(AopUtils.getTargetClass(o));


    }

    private boolean isSecuredClass(Class oClass) {
        Set<Class<? extends Annotation>> annotationsToCheck = Sets.newHashSet(Secured.class, PreAuthorize.class);
        for (Class<? extends Annotation> annotation : annotationsToCheck) {
            if (oClass.getAnnotation(annotation) != null) {
                return true;
            }
        }

        return false;
    }

}
