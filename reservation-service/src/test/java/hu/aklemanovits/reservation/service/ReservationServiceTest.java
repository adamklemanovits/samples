package hu.aklemanovits.reservation.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
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
        given(reservationRepository.findByName("test")).willReturn(Optional.of(new Reservation("test")));

        Reservation reservation = reservationService.getReservationByName("test");

        assertThat(reservation.getName()).isEqualTo("test");
    }

    @Test(expected = ReservationNotFoundException.class)
    public void getReservationByName_shouldThrowExcepionIfItemNotFound() {
        given(reservationRepository.findByName("test")).willReturn(Optional.empty());

        Reservation reservation = reservationService.getReservationByName("test");
    }
}