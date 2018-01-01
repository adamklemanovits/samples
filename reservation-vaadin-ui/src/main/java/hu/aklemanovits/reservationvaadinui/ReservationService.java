package hu.aklemanovits.reservationvaadinui;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

/**
 * @author aklemanovits on 2017. 12. 29.
 */
@FeignClient(value = "RESERVATION-SERVICE", fallback = ReservationServiceFallback.class)
@RequestMapping("/reservations")
public interface ReservationService {

    @RequestMapping(method = POST)
    Reservation createReservation(@RequestBody Reservation reservation);

    @RequestMapping(method = PUT)
    void updateReservation(@RequestBody Reservation reservation);

    @RequestMapping(method = GET)
    Collection<Reservation> getAllReservations();

    @RequestMapping(path = "/{id}", method = GET)
    Reservation getReservation(@PathVariable("id") Long id);

    @RequestMapping(path = "/{id}", method = DELETE)
    void deleteReservation(@PathVariable("id") Long id);
}
