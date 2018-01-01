package hu.aklemanovits.reservationvaadinui;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author aklemanovits on 2017. 12. 29.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    private Long id;
    private String name;
    private String table;
    private Integer headCount;

    public Reservation(final String name, final String table, final Integer headCount) {
        this.name = name;
        this.table = table;
        this.headCount = headCount;
    }
}
