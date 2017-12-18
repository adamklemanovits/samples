package hu.aklemanovits.reservation.service;

import org.springframework.util.Assert;

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
}
