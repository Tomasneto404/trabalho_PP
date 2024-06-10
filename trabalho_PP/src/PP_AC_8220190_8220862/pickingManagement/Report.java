/*
* Nome: Tomás Leonardo Leão Sousa Neto
* Número: 8220862
* Turma: LSIRC12T1
*
* Nome: Tânia Sofia da Silva Morais
* Número: 8220190
* Turma: LSIRC12T1
 */
package PP_AC_8220190_8220862.pickingManagement;

import PP_AC_8220190_8220862.core.AidBox;
import PP_AC_8220190_8220862.core.Institution;
import PP_AC_8220190_8220862.enums.VehicleState;
import com.estg.core.exceptions.AidBoxException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <strong> Report </strong>
 * <p>
 * this class identifies a report </p>
 */
public class Report implements com.estg.pickingManagement.Report {

    private LocalDateTime date;

    private Institution institution;

    /**
     * <strong> Report() </strong>
     * <p>
     * Report constructor method </p>
     *
     * @param institution the institution that is intended to make a report
     */
    public Report(Institution institution) {
        this.institution = institution;
        this.date = LocalDateTime.now();
    }
    
    /**
     * <strong> getUsedVehicles() </strong>
     * <p>
     * Get all vehicles used </p>
     *
     * @return the number of vehicles used
     */
    @Override
    public int getUsedVehicles() {

        if (this.institution == null) {
            throw new NullPointerException("Institution is not initialized.");
        }

        if (this.institution.getVehicles() == null) {
            throw new NullPointerException("Vehicles array is empty.");
        }

        int counter = 0;

        for (Vehicle vhcl : this.institution.getVehicles()) {
            if ((vhcl != null) && (vhcl.getState() == VehicleState.ACTIVE)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * <strong> getNotUsedVehicles() </strong>
     * <p>
     * get the number of unused vehicles </p>
     *
     * @return the number of unused vehicles
     */
    @Override
    public int getNotUsedVehicles() {
        if (this.institution == null) {
            throw new NullPointerException("Institution is not initialized.");
        }

        if (this.institution.getVehicles() == null) {
            throw new NullPointerException("Vehicles array is empty.");
        }

        int counter = 0;

        for (Vehicle vhcl : this.institution.getVehicles()) {
            if ((vhcl != null) && (vhcl.getState() == VehicleState.INACTIVE)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * <strong> getTotalDistance () <strong>
     * <p>
     * get the total distance </p>
     *
     * @return total distance
     */
    @Override
    public double getTotalDistance() {

        double sum = 0;

        AidBox[] aibxArray = this.institution.getAidBoxes();

        for (int i = 0; i < aibxArray.length; i++) {
            if (aibxArray[i] != null && aibxArray[i + 1] != null) {
                try {
                    sum += aibxArray[i].getDistance(aibxArray[i + 1]);
                } catch (AidBoxException e) {
                    e.getMessage();
                }

            }
        }
        return sum / 1000;
    }

    /**
     * <strong> getTotalDuration () <strong>
     * <p>
     * get the total duration</p>
     *
     * @return total duration
     */
    @Override
    public double getTotalDuration() {
        double sum = 0;

        AidBox[] aibxArray = this.institution.getAidBoxes();

        for (int i = 0; i < aibxArray.length; i++) {
            if (aibxArray[i] != null && aibxArray[i + 1] != null) {
                try {
                    sum += aibxArray[i].getDuration(aibxArray[i + 1]);
                } catch (AidBoxException e) {
                    e.getMessage();
                }

            }
        }
        return sum / 3600;
    }

    /**
     * <strong> getDate() </strong>
     * <p>
     * get the report date </p>
     *
     * @return a varibale of LocalDateTime type
     */
    @Override
    public LocalDateTime getDate() {
        return this.date;
    }

    /**
     * <strong> getPickedContainers() </strong>
     * <p>
     * gets the number of containers that were picked up
     *
     * @return 1
     */
    @Override
    public int getPickedContainers() {
        return 1;
    }

    /**
     * <strong> getNonPickedContainers() </strong>
     * <p>
     * gets the number of containers that weren't picked up
     *
     * @return 1
     */
    @Override
    public int getNonPickedContainers() {
        return 1;
    }

}
