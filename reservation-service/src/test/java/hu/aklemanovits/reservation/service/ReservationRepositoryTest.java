package hu.aklemanovits.reservation.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * @author aklemanovits on 2017. 12. 15.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ReservationRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void findByName_shouldFindItemWithTheGiveName() {
        Reservation persistedReservation = testEntityManager.persistFlushFind(new Reservation("test","1A",1));

        Optional<Reservation> reservation = reservationRepository.findByid(1L);

        assertThat(reservation.get()).isNotNull();
        assertThat(reservation.get().getName()).isEqualTo(persistedReservation.getName());
    }

    @Test
    public void findByName_shouldGiveEmptyOnItemNotFound() {
        Optional<Reservation> reservation = reservationRepository.findByid(1L);

        assertThat(reservation.isPresent()).isFalse();
    }
}