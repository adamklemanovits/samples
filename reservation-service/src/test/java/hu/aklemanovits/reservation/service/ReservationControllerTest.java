package hu.aklemanovits.reservation.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * @author aklemanovits on 2017. 12. 15.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    @Test
    public void getReservation_shouldReturnReservation() throws Exception {
        given(reservationService.getReservationByName(anyString())).willReturn(new Reservation("test"));

        mockMvc.perform(MockMvcRequestBuilders.get("/reservations/test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("test"));
    }

    @Test
    public void getReservation_shouldReturnNotFound() throws Exception {
        given(reservationService.getReservationByName(anyString())).willThrow(new ReservationNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/reservations/test"))
                .andExpect(status().isNotFound());
    }
}