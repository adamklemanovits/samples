package hu.aklemanovits.samples.reservation.ui.vaadin;

import static hu.aklemanovits.samples.reservation.ui.vaadin.ViewNames.RESERVATIONS;
import static org.springframework.context.annotation.ScopedProxyMode.INTERFACES;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContext;

/**
 * @author aklemanovits on 2017. 12. 28.
 */
@SpringUI
@PreserveOnRefresh
@Scope(value = "prototype", proxyMode = INTERFACES)
public class VaadinUI extends UI {

    @Autowired
    private SpringViewProvider viewProvider;

    private Navigator navigator;

    @Override
    protected void init(final VaadinRequest request) {
        super.setData(SecurityContext.class.cast(request.getWrappedSession().getAttribute("SPRING_SECURITY_CONTEXT")).getAuthentication()
                              .getPrincipal());
        if (navigator == null) {
            VerticalLayout layout = new VerticalLayout();
            layout.setSpacing(false);
            layout.setMargin(false);
            layout.setSizeFull();
            setContent(layout);
            navigator = new Navigator(this, new Navigator.ComponentContainerViewDisplay(layout));
            navigator.addProvider(viewProvider);
        }
        navigator.navigateTo(RESERVATIONS);
    }
}
