package hu.aklemanovits.reservation.service;

/**
 * @author aklemanovits on 2017. 12. 15.
 */
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation getReservationByName(String name) {
        return reservationRepository.findByName(name)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found with the given name: " + name));
    }
}
