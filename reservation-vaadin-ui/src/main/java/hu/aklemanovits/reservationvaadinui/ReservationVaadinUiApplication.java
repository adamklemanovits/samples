package hu.aklemanovits.reservationvaadinui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class ReservationVaadinUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationVaadinUiApplication.class, args);
	}
}
