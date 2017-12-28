package hu.aklemanovits.auth.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author aklemanovits on 2017. 12. 26.
 */
public class AccountUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public AccountUserDetailsService(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username)
                .map(this::toUser)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found."));
    }

    private User toUser(Account account) {
        return new User(account.getUsername(),
                        account.getPassword(),
                        account.getActive(),
                        !account.getLocked(),
                        !account.getLocked(),
                        !account.getLocked(),
                        account.getAuthorites());
    }
}
