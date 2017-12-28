package hu.aklemanovits.reservationvaadinui;

import static org.springframework.context.annotation.ScopedProxyMode.INTERFACES;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContext;

/**
 * @author aklemanovits on 2017. 12. 28.
 */
@SpringUI
@PreserveOnRefresh
@Scope(value = "prototype", proxyMode = INTERFACES)
public class ApplicationUI extends UI {

    @Override
    protected void init(final VaadinRequest request) {
        String username = (String) SecurityContext.class.cast(request.getWrappedSession().getAttribute("SPRING_SECURITY_CONTEXT")).getAuthentication()
                .getPrincipal();


        final VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(false);
        layout.setMargin(false);
        layout.setSizeFull();

        layout.addComponent(new Label("Welcome " + username));

        setContent(layout);
    }
}
