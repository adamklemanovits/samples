package hu.aklemanovits.reservation.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author aklemanovits on 2017. 12. 15.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Reservation(final String name) {
        this.name = name;
    }
}
