package hu.aklemanovits.samples.reservation.ui.vaadin;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.Collections;

/**
 * @author aklemanovits on 2017. 12. 29.
 */
@FeignClient(value = "RESERVATION-SERVICE", fallback = ReservationService.Fallback.class)
public interface ReservationService {

    @RequestMapping(path = "/reservations", method = POST)
    Reservation createReservation(@RequestBody Reservation reservation);

    @RequestMapping(path = "/reservations", method = PUT)
    void updateReservation(@RequestBody Reservation reservation);

    @RequestMapping(path = "/reservations", method = GET)
    Collection<Reservation> getAllReservations();

    @RequestMapping(path = "/reservations/{id}", method = GET)
    Reservation getReservation(@PathVariable("id") Long id);

    @RequestMapping(path = "/reservations/{id}", method = DELETE)
    void deleteReservation(@PathVariable("id") Long id);

    @Component
    class Fallback implements ReservationService {

        @Override
        public Reservation createReservation(final Reservation reservation) {
            return null;
        }

        @Override
        public void updateReservation(final Reservation reservation) {
        }

        @Override
        public Collection<Reservation> getAllReservations()  {
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
}
