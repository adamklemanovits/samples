package hu.aklemanovits.reservation.service;

import static org.mockito.BDDMockito.given;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

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
        given(reservationService.getReservationByName("test")).willReturn(new Reservation(1L, "test"));

        RestAssuredMockMvc.standaloneSetup(this.reservationController);
    }
}
