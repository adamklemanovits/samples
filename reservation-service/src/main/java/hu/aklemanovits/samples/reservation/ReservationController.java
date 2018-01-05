package hu.aklemanovits.samples.reservation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author aklemanovits on 2017. 12. 15.
 */
@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(final ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.createOrUpdateReservation(reservation);
    }

    @PutMapping
    void updateReservation(@RequestBody Reservation reservation) {
        reservationService.createOrUpdateReservation(reservation);
    }

    @GetMapping
    Collection<Reservation> getAllReservations(){
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    Reservation getReservation(@PathVariable Long id) {
        return reservationService.getReservationByid(id);
    }

    @DeleteMapping("/{id}")
    void deleteReservation(@PathVariable Long id){
        reservationService.deleteReservation(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void handleNoReservationFound(ReservationNotFoundException ex) {
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void handleInvalidRequest(IllegalArgumentException ex) {
    }
}
