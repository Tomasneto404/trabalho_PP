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

import com.estg.core.ItemType;
import com.estg.core.Measurement;
import com.estg.core.exceptions.MeasurementException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * *
 *
 * <strong>Container</strong>
 * <p>
 * This class identifies a container</p>
 */
public class Container implements com.estg.core.Container {

    private final int MAX_MEASUREMENT = 100;

    private String code;

    private double capacity;

    private ItemType type;

    private Measurement[] measurement;

    /**
     * <strong>Instance Constructor Method</strong>
     * <p>
     * This method defines the default values for all the constructors methods
     * of the instance.</p>
     */
    {
        this.measurement = new Measurement[MAX_MEASUREMENT];
    }

    /**
     * <strong> Container()</strong>
     * <p>
     * Container constructor method.</p>
     *
     * @param code receives the containers's code
     * @param capacity receives the container's capacity
     * @param type receives the container's type
     */
    public Container(String code, double capacity, ItemType type) {
        this.code = code;
        this.capacity = capacity;
        this.type = type;
    }

    /**
     * <strong> getCode() </strong>
     * <p>
     * method to obtain a code </p>
     *
     * @return the container's code
     */
    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public double getCapacity() {
        return this.capacity;
    }

    @Override
    public ItemType getType() {
        return this.type;
    }

    @Override
    public Measurement[] getMeasurements() {
        return this.measurement;
    }

    @Override
    public Measurement[] getMeasurements(LocalDate ld) {

        Measurement[] tmp = new Measurement[MAX_MEASUREMENT];
        int contador=0;
        LocalDateTime ld2= convertToDatetime(ld);

        for (int i = 0; i < this.measurement.length; i++) {
             if(this.measurement[i].getDate()==ld2){
                 tmp[++contador]=this.measurement[i];
             }
        }
        
        this.measurement=tmp;
        
        return this.measurement;
    }

    @Override
    public boolean addMeasurement(Measurement msrmnt) throws MeasurementException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Preciso deste método para usar no AidBox
    public boolean equals(Container cntnr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     private LocalDateTime convertToDatetime(LocalDate date){
         return date.atStartOfDay();
    }

}
