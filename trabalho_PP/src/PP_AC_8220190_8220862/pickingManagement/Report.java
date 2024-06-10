/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author Asus
 */
public class Report implements com.estg.pickingManagement.Report {

    private LocalDateTime date;

    private Institution institution;

    public Report(Institution institution) {
        this.institution = institution;
        this.date = LocalDateTime.now();
    }

    @Override
    public int getUsedVehicles() {
        
        if (this.institution == null) {
            throw new NullPointerException("Institution is not initialized.");
        }

        if ( this.institution.getVehicles() == null) {
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

    @Override
    public int getNotUsedVehicles() {
        if (this.institution == null) {
            throw new NullPointerException("Institution is not initialized.");
        }

        if ( this.institution.getVehicles() == null) {
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
        return sum/1000;
    }

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
        return sum/3600;
    }

    @Override
    public LocalDateTime getDate() {
        return this.date;
    }

    @Override
    public int getPickedContainers() {
        return 1;
    }

    @Override
    public int getNonPickedContainers() {
        return 1;
    }

}
