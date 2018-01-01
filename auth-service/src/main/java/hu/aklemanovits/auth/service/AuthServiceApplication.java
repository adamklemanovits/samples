package hu.aklemanovits.auth.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(RoleRepository roleRepository, AccountRepository accountRepository) {
        return args -> {
            List<Role> roles = roleRepository.save(Arrays.asList(new Role("ROLE_RESERVATION_VAADIN_UI_USER"),
                                                                 new Role("ROLE_RESERVATION_THYMELEAF_UI_USER")));
            Account account = new Account("aklemanovits", "password", true, false);
            account.setAuthorites(new HashSet<>(roles));

            accountRepository.save(account);
        };
    }

    @Bean
    UserDetailsService userDetailsService(AccountRepository accountRepository) {
        return new AccountUserDetailsService(accountRepository);
    }
}
