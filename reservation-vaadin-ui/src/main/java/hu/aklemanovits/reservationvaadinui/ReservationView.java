package hu.aklemanovits.reservationvaadinui;

import static com.vaadin.ui.Grid.SelectionMode.SINGLE;
import static hu.aklemanovits.reservationvaadinui.ViewNames.RESERVATIONS;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author aklemanovits on 2017. 12. 29.
 */
@SpringView(name = RESERVATIONS)
public class ReservationView extends VerticalLayout implements View, InitializingBean {

    private final Grid<Reservation> reservationGrid = new Grid<>();
    private final Button create = new Button("Create");
    private final Button update = new Button("Update");
    private final Button delete = new Button("Delete");

    private final ReservationService reservationService;

    public ReservationView(ReservationService reservationService) {
        super();
        this.reservationService = reservationService;

        update.setEnabled(false);
        delete.setEnabled(false);
        delete.addClickListener(event -> {
            reservationService.deleteReservation(reservationGrid.getSelectedItems().iterator().next().getId());
            reservationGrid.getDataProvider().refreshAll();
        });

        reservationGrid.setWidth(100, Unit.PERCENTAGE);
        reservationGrid.setHeight(380, Unit.PIXELS);
        reservationGrid.addColumn(Reservation::getId).setCaption("Id");
        reservationGrid.addColumn(Reservation::getName).setCaption("Name");
        reservationGrid.addColumn(Reservation::getTable).setCaption("Table");
        reservationGrid.addColumn(Reservation::getHeadCount).setCaption("Head Count");
        reservationGrid.setSelectionMode(SINGLE);
                                                                    //TODO: optimalizálni a hívást
        reservationGrid.setDataProvider(DataProvider.fromCallbacks(query -> reservationService.getAllReservations().stream(),
                                                                   query -> reservationService.getAllReservations().size()));
        reservationGrid.addSelectionListener(event -> {
            update.setEnabled(true);
            delete.setEnabled(true);
        });
    }

    @Override
    public void afterPropertiesSet() {
        addComponent(new Label("Hello " + UI.getCurrent().getData()));
        addComponent(new HorizontalLayout(create, update, delete));
        addComponent(reservationGrid);


    }
}
