/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PP_AC_8220190_8220862.pickingManagement;

import java.time.LocalDateTime;

/**
 *
 * @author Asus
 */
public class Report implements com.estg.pickingManagement.Report {

    private LocalDateTime date;

    private int nonPickedContainers;

    private int notUsedVehicles;

    private int pickedContainers;

    private double totalDistance;

    private double totalDuration;

    private int usedVehicles;

    public Report(LocalDateTime date, int nonPickedContainers, int notUsedVehicles, int pickedContainers, double getTotalDistance, double totalDuration, int usedVehicles) {
        this.date = date;
        this.nonPickedContainers = nonPickedContainers;
        this.notUsedVehicles = notUsedVehicles;
        this.pickedContainers = pickedContainers;
        this.totalDistance = getTotalDistance;
        this.totalDuration = totalDuration;
        this.usedVehicles = usedVehicles;
    }

    public void setNonPickedContainers(int nonPickedContainers) {
        this.nonPickedContainers = nonPickedContainers;
    }

    public void setNotUsedVehicles(int notUsedVehicles) {
        this.notUsedVehicles = notUsedVehicles;
    }

    public void setUsedVehicles(int usedVehicles) {
        this.usedVehicles = usedVehicles;
    }

    @Override
    public int getUsedVehicles() {
        return this.usedVehicles;
    }

    @Override
    public int getPickedContainers() {
        return this.pickedContainers;
    }

    @Override
    public double getTotalDistance() {
        return this.totalDistance;
    }

    @Override
    public double getTotalDuration() {
        return this.totalDuration;
    }

    @Override
    public int getNonPickedContainers() {
        return this.nonPickedContainers;
    }

    @Override
    public int getNotUsedVehicles() {
        return this.notUsedVehicles;
    }

    @Override
    public LocalDateTime getDate() {
        return this.date;
    }

}
