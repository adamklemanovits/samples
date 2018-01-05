package hu.aklemanovits.samples.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author aklemanovits on 2017. 12. 22.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
