package hu.aklemanovits.reservation.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

/**
 * @author aklemanovits on 2017. 12. 15.
 */
@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceTest {

    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @Before
    public void setUp() throws Exception {
        reservationService = new ReservationService(reservationRepository);
    }

    @Test
    public void getReservationByName_shouldReturnReservationWithTheGivenName() {
        given(reservationRepository.findByid(1L)).willReturn(Optional.of(new Reservation(1L, "test", "1A", 1)));

        Reservation reservation = reservationService.getReservationByid(1L);

        assertThat(reservation.getName()).isEqualTo("test");
        verify(reservationRepository,times(1)).findByid(1L);
    }

    @Test(expected = ReservationNotFoundException.class)
    public void getReservationByName_shouldThrowExcepionIfItemNotFound() {
        given(reservationRepository.findByid(1L)).willReturn(Optional.empty());

        reservationService.getReservationByid(1L);
    }

    @Test
    public void createOrUpdateReservation_shouldSaveNewReservation() {
        Reservation newReservation = new Reservation("test", "1A", 1);
        Reservation returnReservation = new Reservation(1L, "test", "1A", 1);
        given(reservationRepository.save(any(Reservation.class))).willReturn(returnReservation);

        Reservation reservation = reservationService.createOrUpdateReservation(newReservation);

        assertThat(reservation.getId()).isNotNull();
        verify(reservationRepository,times(1)).save(any(Reservation.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void createOrUpdateReservation_shouldThrowExceptionIfReservationIsNull() {
        reservationService.createOrUpdateReservation(null);
    }

    @Test
    public void deleteReservation_shouldDeleteReservation() {
        reservationService.deleteReservation(1L);

        verify(reservationRepository,times(1)).delete(1L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteReservation_shouldThrowExceptionIfReservationIsNull() {
        reservationService.deleteReservation(null);
    }

}