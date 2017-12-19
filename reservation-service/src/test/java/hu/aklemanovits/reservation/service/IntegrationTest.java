package hu.aklemanovits.reservation.service;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author aklemanovits on 2017. 12. 15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void integration() {
        ResponseEntity<Reservation> createResopone = testRestTemplate.postForEntity("/reservations", new Reservation("test", "1A", 1), Reservation.class);

        assertThat(createResopone.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(createResopone.getBody().getId()).isNotNull();
        assertThat(createResopone.getBody().getName()).isEqualTo("test");
        assertThat(createResopone.getBody().getTable()).isEqualTo("1A");
        assertThat(createResopone.getBody().getHeadCount()).isEqualTo(1);

        ResponseEntity<Reservation> getResponse = testRestTemplate.getForEntity("/reservations/" + createResopone.getBody().getId(), Reservation.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody().getId()).isEqualTo(createResopone.getBody().getId());
        assertThat(getResponse.getBody().getName()).isEqualTo(createResopone.getBody().getName());
        assertThat(getResponse.getBody().getTable()).isEqualTo(createResopone.getBody().getTable());
        assertThat(getResponse.getBody().getHeadCount()).isEqualTo(createResopone.getBody().getHeadCount());

        getResponse.getBody().setHeadCount(2);

        testRestTemplate.put("/reservations", getResponse.getBody());

        ResponseEntity<Reservation> getUpdatedResponse = testRestTemplate.getForEntity("/reservations/" + getResponse.getBody().getId(), Reservation.class);

        assertThat(getUpdatedResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getUpdatedResponse.getBody().getId()).isEqualTo(getResponse.getBody().getId());
        assertThat(getUpdatedResponse.getBody().getName()).isEqualTo(getResponse.getBody().getName());
        assertThat(getUpdatedResponse.getBody().getTable()).isEqualTo(getResponse.getBody().getTable());
        assertThat(getUpdatedResponse.getBody().getHeadCount()).isEqualTo(2);

        testRestTemplate.delete("/reservations/" + getResponse.getBody().getId());
        getResponse = testRestTemplate.getForEntity("/reservations/" + createResopone.getBody().getId(), Reservation.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
