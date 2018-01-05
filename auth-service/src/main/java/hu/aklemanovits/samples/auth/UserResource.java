package hu.aklemanovits.samples.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author aklemanovits on 2017. 12. 28.
 */
@RestController
@RequestMapping("/user")
public class UserResource {

    private final AccountRepository accountRepository;

    @Autowired
    public UserResource(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping
    public Principal getUser(Principal principal) {
        return principal;
    }

    @GetMapping("{username}")
    public Account getUser(@PathVariable String username) {
        return accountRepository.findByUsername(username)
                .orElse(null);
    }
}
