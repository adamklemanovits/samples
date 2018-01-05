package hu.aklemanovits.samples.reservation;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * @author aklemanovits on 2017. 12. 18.
 */
@SpringBootTest(classes = ReservationServiceApplication.class)
@RunWith(SpringRunner.class)
public class CloudContractBaseClass {

    @Autowired
    private ReservationController reservationController;

    @MockBean
    private ReservationService reservationService;

    @Before
    public void setUp() throws Exception {
        given(reservationService.getReservationByid(1L))
                .willReturn(new Reservation(1L, "test","1A",1));

        given(reservationService.getReservationByid(-1L))
                .willThrow(new ReservationNotFoundException());

        given(reservationService.createOrUpdateReservation(any(Reservation.class)))
                .willReturn(new Reservation(1L, "test","1A",1));

        given(reservationService.getAllReservations())
                .willReturn(Arrays.asList(new Reservation(1L, "test", "1A", 1),
                                          new Reservation(2L,"test2","1B",2)));

        RestAssuredMockMvc.standaloneSetup(this.reservationController);
    }
}
