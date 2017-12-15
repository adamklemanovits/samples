package hu.aklemanovits.reservation.service;

/**
 * @author aklemanovits on 2017. 12. 15.
 */
public class ReservationNotFoundException extends RuntimeException {

    public ReservationNotFoundException() {
        super();
    }

    public ReservationNotFoundException(String message) {
        super(message);
    }
}
