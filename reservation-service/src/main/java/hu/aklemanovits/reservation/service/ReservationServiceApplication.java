package hu.aklemanovits.reservation.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class ReservationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationServiceApplication.class, args);
    }

    @Bean
    ReservationService reservationService(ReservationRepository reservationRepository) {
        return new ReservationService(reservationRepository);
    }

    @Bean
    CommandLineRunner commandLineRunner(ReservationRepository reservationRepository) {
        return args -> reservationRepository.save(new Reservation("Test-name", "Test-table", 10));
    }
}
