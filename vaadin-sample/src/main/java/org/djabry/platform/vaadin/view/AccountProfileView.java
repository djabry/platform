package org.djabry.platform.vaadin.view;

import com.vaadin.navigator.ViewChangeListener;
import org.djabry.platform.service.api.AuthenticationService;
import org.djabry.platform.service.security.annotations.ReadOwn;
import org.djabry.platform.vaadin.presenter.Sections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.vaadin.spring.navigator.VaadinView;
import org.vaadin.spring.stuff.sidebar.SideBarItem;

/**
 * Created by djabry on 07/01/15.
 */
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@VaadinView(name = AccountProfileView.VIEW_NAME)
@ReadOwn
@SideBarItem(sectionId = Sections.ACCOUNT, caption = "Profile")
public class AccountProfileView extends BaseMainViewAbstr {

    public static final String VIEW_NAME = "profile";
    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public void clear() {

    }

    /**
     * This view is navigated to.
     * <p/>
     * This method is always called before the view is shown on screen.
     * {@link ViewChangeEvent#getParameters() event.getParameters()} may contain
     * extra parameters relevant to the view.
     *
     * @param event ViewChangeEvent representing the view change that is
     *              occurring. {@link ViewChangeEvent#getNewView()
     *              event.getNewView()} returns <code>this</code>.
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {


    }
}
