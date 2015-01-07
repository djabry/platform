package org.djabry.platform.vaadin.navigation;

import com.google.common.collect.Sets;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;
import lombok.extern.java.Log;
import org.springframework.aop.support.AopUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.vaadin.spring.security.Security;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * Created by djabry on 07/01/15.
 */
@Log
public class SecuredNavigator extends Navigator {
    private final Security security;

    /**
     * Creates a navigator that is tracking the active view using URI fragments
     * of the {@link Page} containing the given UI and replacing the contents of
     * a {@link com.vaadin.ui.ComponentContainer} with the active view.
     * <p/>
     * All components of the container are removed each time before adding the
     * active {@link View}. Views must implement {@link Component} when using
     * this constructor.
     * <p/>
     * Navigation is automatically initiated after {@code UI.init()} if a
     * navigator was created. If at a later point changes are made to the
     * navigator, {@code navigator.navigateTo(navigator.getState())} may need to
     * be explicitly called to ensure the current view matches the navigation
     * state.
     *
     * @param ui        The UI to which this Navigator is attached.
     * @param container The ComponentContainer whose contents should be replaced with
     */
    public SecuredNavigator(UI ui, ComponentContainer container, Security security) {
        super(ui, container);
        this.security = security;

        this.addViewChangeListener(new ViewChangeListener() {
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
