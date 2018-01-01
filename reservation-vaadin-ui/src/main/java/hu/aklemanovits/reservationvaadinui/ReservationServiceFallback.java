package hu.aklemanovits.reservationvaadinui;

import java.util.Collection;
import java.util.Collections;

/**
 * @author aklemanovits on 2018. 01. 01.
 */
public class ReservationServiceFallback implements ReservationService {

    @Override
    public Reservation createReservation(final Reservation reservation) {
        return null;
    }

    @Override
    public void updateReservation(final Reservation reservation) {

    }

    @Override
    public Collection<Reservation> getAllReservations() {
        return Collections.emptyList();
    }

    @Override
    public Reservation getReservation(final Long id) {
        return null;
    }

    @Override
    public void deleteReservation(final Long id) {

    }
}
