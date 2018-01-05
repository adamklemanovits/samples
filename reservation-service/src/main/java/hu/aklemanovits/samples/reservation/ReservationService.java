package hu.aklemanovits.samples.reservation;

import org.springframework.util.Assert;

import java.util.List;

/**
 * @author aklemanovits on 2017. 12. 15.
 */
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation getReservationByid(Long id) {
        return reservationRepository.findByid(id)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found with the given id: " + id));
    }

    public Reservation createOrUpdateReservation(Reservation reservation) {
        Assert.notNull(reservation, "Reservation can not be null!");

        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        Assert.notNull(id, "Id can not be null!");

        reservationRepository.delete(id);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
}
