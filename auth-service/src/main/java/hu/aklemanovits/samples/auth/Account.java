package hu.aklemanovits.samples.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author aklemanovits on 2017. 12. 22.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Role> authorites;

    private Boolean active;

    private Boolean locked;

    public Account(String username, String password, Boolean active, Boolean locked) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.locked = locked;
    }
}
