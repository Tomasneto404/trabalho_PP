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
public class ContainerImp implements com.estg.core.Container {

    private final int MAX_MEASUREMENT = 100;

    private int counter;

    private String code;

    private double capacity;

    private ItemType type;

    private Measurement[] measurements;

    /**
     * <strong>Instance Constructor Method</strong>
     * <p>
     * This method defines the default values for all the constructors methods
     * of the instance.</p>
     */
    {
        this.measurements = new Measurement[MAX_MEASUREMENT];
        this.counter = 0;
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
    public ContainerImp(String code, double capacity) {
        this.code = code;
        this.capacity = capacity;
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

    /**
     * <strong> getCapacity() </strong>
     * <p>
     * method to obtain a capacity </p>
     *
     * @return the container's capacity
     */
    @Override
    public double getCapacity() {
        return this.capacity;
    }

    /**
     * <strong> getType() </strong>
     * <p>
     * method to obtain a type </p>
     *
     * @return the container's type
     */
    @Override
    public ItemType getType() {
        return this.type;
    }

    /**
     * <strong> getMeasurements() </strong>
     * <p>
     * method to obtain the measurements </p>
     *
     * @return the container's measurements
     */
    @Override
    public Measurement[] getMeasurements() {
        return this.measurements;
    }

    /**
     * <strong> getMeasurements(LocalDat ld) </strong>
     * <p>
     * This function allows you to obtain the measurements of a container on a
     * given date </p>
     *
     * @param ld receives a date of type LocalDate
     * @return the container's measurments in one date
     */
    @Override
    public Measurement[] getMeasurements(LocalDate ld) {

        Measurement[] tmp = new Measurement[MAX_MEASUREMENT];
        int contador = 0;

        //convert a LocalDate variable in a LocalDateTime variable
        LocalDateTime ld2 = convertToDatetime(ld);

        for (Measurement measurement : this.measurements) {
            if (measurement.getDate().equals(ld2)) {
                tmp[contador++] = measurement;
            }
        }

        return tmp;
    }

    /**
     * <strong> addMeasurement(Measurement msrmnt) </strong>
     * <p>
     * This function checks whether the capacity and the measurements array have
     * already reached the limit and whether this measurement already exists. If
     * none of the options happen, add a measurement </p>
     *
     * @param msrmnt receives a variable of Measurement type
     * @return true if a measurement is added
     * @throws MeasurementException exception corresponding to a measurement
     */
    @Override
    public boolean addMeasurement(Measurement msrmnt) throws MeasurementException {

        if (this.counter == MAX_MEASUREMENT) {
            throw new MeasurementException("Measurements are full");
        }

        if (this.capacity == msrmnt.getValue()) {
            throw new MeasurementException("The capacity is full");
        }

        if (exist(msrmnt)) {
            throw new MeasurementException("The measurement exist");
        }

        this.measurements[counter++] = msrmnt;

        return true;
    }

    /**
     * <strong> updateMeasurement(Measurement msrmnt) </strong>
     * <p>
     * This function allows you to update a measurement </p>
     *
     * @param msrmt a variable of Measurement's type
     * @return true if it was successufly
     * @throws MeasurementException exception corresponding to a measurement
     */
    public boolean updateMeasurement(Measurement msrmt) throws MeasurementException {

        int pos = getIndex(msrmt);

        if (pos == -1) {
            throw new MeasurementException("Measurement doesn't exist");
        }

        this.measurements[pos] = msrmt;
        return true;
    }

    /**
     * <strong> delete(Measurement msrmt)</strong>
     * <p>
     * deletes a given measurement from the measurement array </p>
     *
     * @param msrmt a variable of Measurement's type
     * @throws MeasurementException exception corresponding to a measurement
     */
    public void delete(Measurement msrmt) throws MeasurementException {
        int pos = getIndex(msrmt);

        if (pos == -1) {
            throw new MeasurementException("Measurement doesn't exist");
        }

        for (int i = pos; i < this.counter; i++) {
            this.measurements[i] = this.measurements[i + 1];
        }

        this.measurements[this.counter--] = null;
    }

    /**
     * <strong> listMeasurements </strong>
     * <p> lists all measurements that are contracted within the measurement array </p>
     * @return measurement array
     */
    public Measurement[] listMeasurements() {
        Measurement[] temp = new Measurement[this.counter];
        
        for (int i = 0; i < this.counter; i++) {
            temp[i] = this.measurements[i];
        }
        
        return temp;
    }
    /**
     * <strong>equals()</strong>
     * <p>
     * This method verifys if a given Object is equal a specific Container</p>
     *
     * @param o - Object to be compared
     * @return True if que object is equal, False if it is not
     */
    @Override
    public boolean equals(Object o) {

        if (o instanceof ContainerImp) {
            if (this.code.equals((ContainerImp) o)) {
                return true;
            }
        }

        return false;
    }

    /**
     * <strong> exist(Measurement measurement) </strong>
     *
     * @param measurement receives a variable of type Measurement
     * @return true if it exists, false if it does not exist
     */
    public boolean exist(Measurement measurement) {

        for (Measurement msrm : this.measurements) {
            if (msrm != null && msrm.equals(measurement)) {
                return true;
            }
        }

        return false;
    }

    /**
     * <strong> getIndex(Measurement msrmt) </strong>
     * <p>
     * This function allows you to identify the address of a given
     * measurement</p>
     *
     * @param msrmt receveis a variable of Measurement type
     * @return i if find the address and -1 otherwise
     */
    private int getIndex(Measurement msrmt) {
        for (int i = 0; i < this.counter; i++) {
            if (this.measurements[i].equals(msrmt)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * <strong> convertToDateTime (LocalDate date) </strong>
     * <p>
     * This function transforms a variable of type LocalDate into
     * LocalDateTime</p>
     *
     * @param date receives a date of type LocalDate
     * @return a date of type LocalDateTime
     */
    private LocalDateTime convertToDatetime(LocalDate date) {
        return date.atStartOfDay();
    }

}
