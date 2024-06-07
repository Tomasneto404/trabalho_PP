/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PP_AC_8220190_8220862.core;

import com.estg.core.AidBox;
import com.estg.core.Container;
import com.estg.core.ItemType;
import com.estg.core.Measurement;
import com.estg.core.exceptions.AidBoxException;
import com.estg.core.exceptions.ContainerException;
import com.estg.core.exceptions.MeasurementException;
import com.estg.core.exceptions.PickingMapException;
import com.estg.core.exceptions.VehicleException;
import com.estg.pickingManagement.PickingMap;
import com.estg.pickingManagement.Vehicle;
import java.time.LocalDateTime;



/**
 *
 * @author tomas
 */
public class Institution implements com.estg.core.Institution {

    private final int MAX_AIDBOXS = 10;
    
    private final int MAX_MEASUREMENTS = 10;
    
    private final int MAX_VEHICLES = 10;
    
    private String name;
    
    private AidBox[] aidBoxs;
    
    private int aidBoxCounter;
    
    private Measurement[] measurements;
    
    private int measurementCounter;
    
    private Vehicle[] vehicles;
    
    private int vehicleCounter;
    
    /**
     * <strong>Institution()</strong>
     * <p>Constructor method of Institution</p>
     * @param name Receives the name of the institution.
     */
    public Institution(String name) {
        this.name = name;
        this.aidBoxCounter = 0;
        this.vehicleCounter = 0;
        this.measurementCounter = 0;
        this.aidBoxs = new AidBox[MAX_AIDBOXS];
        this.measurements = new Measurement[MAX_MEASUREMENTS];
        this.vehicles = new Vehicle[MAX_VEHICLES];
    }
    
    /**
     * <strong>getName()</strong>
     * <p>This method returns a string with the name of the institution.</p>
     * @return name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * <strong>addAidBox()</strong>
     * <p>This method inserts and aidBox to the institution aidbox array.</p>
     * @param aidbox
     * @return true if it was possible to insert the new aidBox.
     * @throws AidBoxException 
     */
    @Override
    public boolean addAidBox(AidBox aidbox) throws AidBoxException {
        
        if ( !canAddAidBoxToArray() ) {
            throw new AidBoxException("AidBox array is full.");
        }
        
        if ( verifyAidBox(aidbox) ) {
            throw new AidBoxException("This AidBox already exists in the array.");
        }             
                
        this.aidBoxs[this.aidBoxCounter++] = aidbox;
        
        return true;
    }
    
    /**
     * <strong>verifyAidBox()</strong>
     * <p>This method verifys if there is and equal aidbox to the one to be inserted in the array</p>
     * @param adbx
     * @return True if already exists the same aindabox. false if it doesn't.
     */
    private boolean verifyAidBox(AidBox adbx) {
        for (AidBox aidbox : this.aidBoxs) {

            if ( aidbox.equals(adbx) ) {

                return true;

            }

        }

        return false;
    }
    
    /**
     * <strong>canAddAidBoxToArray()</strong>
     * <p>This method verifys if it's possible to fit a new aidBox to the array.</p>
     * @return True if there is space to insert a new aidBox.
     */
    private boolean canAddAidBoxToArray() {
        return this.aidBoxCounter < this.aidBoxs.length;
    }

    
    @Override
    public boolean addMeasurement(Measurement msrmnt, Container cntnr) throws ContainerException, MeasurementException {
        
        if ( !canAddMeasurementToArray() ) {
            throw new MeasurementException("Measurements array is full");
        }
        
                
        
        this.measurements[measurementCounter++] = msrmnt;
        
        return true;
    }
    
    private boolean verifyMeasurement() {
        
        
        
        return true;
    }
    
    /**
     * <strong>canAddMeasurementToArray()</strong>
     * <p>This method verifys if it's possible to fit a new Measurement Object to the array.</p>
     * @return True if there is space to insert a new Measurement.
     */
    private boolean canAddMeasurementToArray(){
        return this.measurementCounter < this.measurements.length;
    }

    /**
     * <strong>getAidBoxes()</strong>
     * <p>This method gets the aidBoxs array of the institution..</p>
     * @return array with aidboxes of the institution.
     */
    @Override
    public AidBox[] getAidBoxes() {
        return this.aidBoxs;
    }

    /**
     * <strong>getContainer()</strong>
     * <p>This method gets a container with a specific ItemType from a given aidBox</p>
     * @param aidbox Aid box to get the container
     * @param it The specific Item type.
     * @return The wanted container
     * @throws ContainerException If Container was not found. 
     */
    @Override
    public Container getContainer(AidBox aidbox, ItemType it) throws ContainerException {
        
        if (aidbox.getContainer(it) == null) {
             throw new ContainerException("Container with item type " + it + "doesn't exist in this AidBox.");
        }
        
        return aidbox.getContainer(it);
    }

    /**
     * <strong>getVehicles()</strong>
     * <p>This method get the Vehicles array of the institution.</p>
     * @return Array with the vehicles.
     */
    @Override
    public Vehicle[] getVehicles() {
        return this.vehicles;
    }

    /**
     * <strong>addVehicle()</strong>
     * <p>This method adds a new vehicle to the array of vehicles.</p>
     * @param vhcl Vehicle to be inserted.
     * @return True if it was possible to insert the new vehicle.
     * @throws VehicleException If ocurred and error during the validations to insert the new vehicle.
     */
    @Override
    public boolean addVehicle(Vehicle vhcl) throws VehicleException {
        
        if ( !canAddVehicleToArray() ) {
            throw new VehicleException("Vehicle array is full.");
        }
        
        if ( verifyVehicle(vhcl) ) {
            throw new VehicleException("This vehicle already exists inside the array.");
        }
     
        this.vehicles[this.vehicleCounter++] = vhcl;
        
        return true;
    }
    
    /**
     * <strong>canAddvehicleToArray()</strong>
     * <p>This method verifys if it's possible to fit a new vehicle to the array.</p>
     * @return True if it was possible to insert the new vehicle.
     */
    private boolean canAddVehicleToArray(){
        return this.vehicleCounter < this.vehicles.length;
    }

    /**
     * <strong>verifyVehicle()</strong>
     * <p>
     * This method verifys if a given vehicle already existes inside the
     * vehicles array.</p>
     *
     * @param vhcl - Vehicle to be analyzed
     * @return true if already exists an equal vehicle, false if it doesn't
     */
    private boolean verifyVehicle(Vehicle vhcl) {
        
        for (Vehicle vehicle : this.vehicles) {

            if ( vehicle.equals(vhcl) ) {

                return true;

            }

        }

        return false;
    }
    
    @Override
    public void disableVehicle(Vehicle vhcl) throws VehicleException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void enableVehicle(Vehicle vhcl) throws VehicleException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PickingMap[] getPickingMaps() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PickingMap[] getPickingMaps(LocalDateTime ldt, LocalDateTime ldt1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PickingMap getCurrentPickingMap() throws PickingMapException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addPickingMap(PickingMap pm) throws PickingMapException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getDistance(AidBox aidbox) throws AidBoxException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
