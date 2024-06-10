/*
* Nome: Tomás Leonardo Leão Sousa Neto
* Número: 8220862
* Turma: LSIRC12T1
*
* Nome: Tânia Sofia da Silva Morais
* Número: 8220190
* Turma: LSIRC12T1
 */
package PP_AC_8220190_8220862.core;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <strong>Measurement</strong>
 * <p>
 * This class intends to implement the measurements of a count on a given date
 * </p>
 *
 */
public class Measurement implements com.estg.core.Measurement {

    private LocalDate date;

    private double value;

    /**
     * <strong> Measurement()</strong>
     * <p>Measurement constructor method.</p>
     * @param value receives the container weight in kg.
     * @param date The date of the measurement.
     */
    public Measurement(double value, LocalDate date) {        
        this.value = value;
        this.date = date;
    }

    /**
     * <strong> getDate() </strong>
     * <p> getter corresponding to the weight insertion date </p>
     * @return the weight insertion date
     */
    @Override
    public LocalDateTime getDate() {
        return this.date.atStartOfDay();
    }

    /**
     * <strong> getValue() </strong>
     * <p> weight getter in kg </p>
     * @return the weight of the container in kg
     */
    @Override
    public double getValue() {
        return this.value;
    }

}
