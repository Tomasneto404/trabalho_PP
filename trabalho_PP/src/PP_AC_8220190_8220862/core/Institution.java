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

import PP_AC_8220190_8220862.enums.VehicleState;
import PP_AC_8220190_8220862.core.AidBox;

import PP_AC_8220190_8220862.core.Container;

import com.estg.core.ItemType;
import com.estg.core.Measurement;
import com.estg.core.exceptions.AidBoxException;
import com.estg.core.exceptions.ContainerException;
import com.estg.core.exceptions.MeasurementException;
import com.estg.core.exceptions.PickingMapException;
import com.estg.core.exceptions.VehicleException;

import PP_AC_8220190_8220862.pickingManagement.PickingMap;

import java.time.LocalDateTime;

import PP_AC_8220190_8220862.pickingManagement.Vehicle;

/**
 * <strong>Institution</strong>
 * <p>
 * This classe identifies a institution </p>
 */
public class Institution implements com.estg.core.Institution {

    private final int MAX_AIDBOXS = 100;

    private final int MAX_MEASUREMENTS = 10;

    private final int MAX_VEHICLES = 10;

    private final int MAX_PICKINGMAPS = 10;

    private String name;

    private AidBox[] aidBoxs;

    private int aidBoxCounter;

    private Measurement[] measurements;

    private int measurementCounter;

    private Vehicle[] vehicles;

    private int vehicleCounter;

    private PickingMap[] pickingMaps;

    private int pickingMpaCounter;

    /**
     * <strong>Instance Constructor Method</strong>
     * <p>
     * This method defines the default values for all the constructors methods
     * of the instance.</p>
     */
    {
        this.aidBoxCounter = 0;
        this.vehicleCounter = 0;
        this.measurementCounter = 0;
        this.pickingMpaCounter = 0;
        this.aidBoxs = new AidBox[MAX_AIDBOXS];
        this.measurements = new Measurement[MAX_MEASUREMENTS];
        this.vehicles = new Vehicle[MAX_VEHICLES];
        this.pickingMaps = new PickingMap[MAX_PICKINGMAPS];
    }

    /**
     * <strong>Institution()</strong>
     * <p>
     * Constructor method of Institution</p>
     *
     * @param name Receives the name of the institution.
     */
    public Institution(String name) {
        this.name = name;
    }

    /**
     * <strong>Institution()</strong>
     * <p>
     * Constructor method of Institution</p>
     */
    public Institution() {

    }

    /**
     * <strong>getName()</strong>
     * <p>
     * This method returns a string with the name of the institution.</p>
     *
     * @return name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * <strong>addAidBox()</strong>
     * <p>
     * This method inserts and aidBox to the institution aidbox array.</p>
     *
     * @param aidbox
     * @return true if it was possible to insert the new aidBox.
     * @throws AidBoxException
     */
    @Override
    public boolean addAidBox(com.estg.core.AidBox aidbox) throws AidBoxException {

        if (!canAddAidBoxToArray()) {
            throw new AidBoxException("AidBox array is full.");
        }

        if (verifyAidBox((AidBox) aidbox)) {
            throw new AidBoxException("This AidBox already exists in the array.");
        }

        this.aidBoxs[this.aidBoxCounter++] = (AidBox) aidbox;

        return true;
    }

    /**
     * <strong>verifyAidBox()</strong>
     * <p>
     * This method verifys if there is and equal aidbox to the one to be
     * inserted in the array</p>
     *
     * @param adbx
     * @return True if already exists the same aindabox. false if it doesn't.
     */
    private boolean verifyAidBox(AidBox adbx) {
        if (this.aidBoxs != null || adbx != null) {
            for (AidBox aidbox : this.aidBoxs) {

                if (aidbox != null && aidbox.equals(adbx)) {

                    return true;

                }

            }
        }

        return false;
    }

    /**
     * <strong>canAddAidBoxToArray()</strong>
     * <p>
     * This method verifys if it's possible to fit a new aidBox to the
     * array.</p>
     *
     * @return True if there is space to insert a new aidBox.
     */
    private boolean canAddAidBoxToArray() {
        return this.aidBoxCounter < this.aidBoxs.length;
    }

    /**
     * <strong>addMeasurement()</strong>
     * <p>
     * This method makes verifications and inserts a new Measurement according
     * to a Container in the measurements array of the Institution.</p>
     *
     * @param msrmnt Measurement object to be inserted
     * @param cntnr Container of the measurement
     * @return true if it was possible to add the new Measurement to the array.
     * @throws ContainerException
     * @throws MeasurementException
     */
    @Override
    public boolean addMeasurement(com.estg.core.Measurement msrmnt, com.estg.core.Container cntnr) throws ContainerException, MeasurementException {

        if (!canAddMeasurementToArray()) {
            throw new MeasurementException("Measurements array is full.");
        }

        if (verifyMeasurement(msrmnt)) {
            throw new MeasurementException("This Measurement already exists in the array.");
        }

        if (verifyContainerMeasurements(msrmnt, (Container) cntnr)) {
            throw new ContainerException("Container already has a measurement with the same date.");
        }

        this.measurements[measurementCounter++] = msrmnt;

        return true;
    }

    /**
     * <strong>verifyContainerMeasurements()</strong>
     * <p>
     * This method verifys if a given container has in its Measurements array a
     * measurement with the same date as the one to be verified.</p>
     *
     * @param msrmnt Measurement to verify date
     * @param cntnr Container to check if already exists a Measurement with the
     * date of the measurement received.
     * @return True if there is a measurement with the same date. False if it
     * doesn't.
     */
    private boolean verifyContainerMeasurements(com.estg.core.Measurement msrmnt, Container cntnr) {

        if (msrmnt == null || cntnr == null) {
            throw new IllegalArgumentException("Measurement and Container must not be null");
        }

        Measurement[] msrmntsArray = cntnr.getMeasurements(msrmnt.getDate().toLocalDate());

        if (msrmntsArray == null) {
            throw new IllegalArgumentException("Measurements array must not be null");
        }

        return msrmntsArray.length > 1;
    }

    /**
     * <strong>verifyMeasurement()</strong>
     * <p>
     * This method verifys if there is and equal measurement to the one to be
     * inserted in the array</p>
     *
     * @param msrmnt
     * @return True if already exists the same measurement. false if it doesn't.
     */
    private boolean verifyMeasurement(Measurement msrmnt) {

        for (Measurement measurement : this.measurements) {

            if (measurement != null && measurement.equals(msrmnt)) {

                return true;

            }

        }

        return false;
    }

    /**
     * <strong>canAddMeasurementToArray()</strong>
     * <p>
     * This method verifys if it's possible to fit a new Measurement Object to
     * the array.</p>
     *
     * @return True if there is space to insert a new Measurement.
     */
    private boolean canAddMeasurementToArray() {
        return this.measurementCounter < this.measurements.length;
    }

    /**
     * <strong>getAidBoxes()</strong>
     * <p>
     * This method gets the aidBoxs array of the institution..</p>
     *
     * @return array with aidboxes of the institution.
     */
    @Override
    public AidBox[] getAidBoxes() {
        return this.aidBoxs;
    }

    /**
     * <strong>getContainer()</strong>
     * <p>
     * This method gets a container with a specific ItemType from a given
     * aidBox</p>
     *
     * @param aidbox Aid box to get the container
     * @param it The specific Item type.
     * @return The wanted container
     * @throws ContainerException If Container was not found.
     */
    @Override
    public Container getContainer(com.estg.core.AidBox aidbox, ItemType it) throws ContainerException {

        if (aidbox.getContainer(it) == null) {
            throw new ContainerException("Container with item type " + it + "doesn't exist in this AidBox.");
        }

        return (Container) aidbox.getContainer(it);
    }

    /**
     * <strong>getVehicles()</strong>
     * <p>
     * This method get the Vehicles array of the institution.</p>
     *
     * @return Array with the vehicles.
     */
    @Override
    public Vehicle[] getVehicles() {
        return this.vehicles;
    }

    /**
     * <strong>addVehicle()</strong>
     * <p>
     * This method adds a new vehicle to the array of vehicles.</p>
     *
     * @param vhcl Vehicle to be inserted.
     * @return True if it was possible to insert the new vehicle.
     * @throws VehicleException If ocurred and error during the validations to
     * insert the new vehicle.
     */
    @Override
    public boolean addVehicle(com.estg.pickingManagement.Vehicle vhcl) throws VehicleException {

        Vehicle vehicle = (Vehicle) vhcl;

        if (!canAddVehicleToArray()) {
            throw new VehicleException("Vehicle array is full.");
        }

        if (verifyVehicle(vehicle)) {
            throw new VehicleException("This vehicle already exists inside the array.");
        }

        this.vehicles[this.vehicleCounter++] = vehicle;

        return true;
    }

    /**
     * <strong>canAddvehicleToArray()</strong>
     * <p>
     * This method verifys if it's possible to fit a new vehicle to the
     * array.</p>
     *
     * @return True if it was possible to insert the new vehicle.
     */
    private boolean canAddVehicleToArray() {
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

        if (this.vehicles != null && vhcl != null) {
            for (Vehicle vehicle : this.vehicles) {

                if ((vehicle != null) && vehicle.equals(vhcl)) {

                    return true;

                }

            }
        }

        return false;
    }

    /**
     * <strong> disableVehicle() </strong>
     * <p>
     * changes the state of a vehicle to inactive, disabling it </p>
     *
     * @param vhcl Vehicle value that represents a vehicle
     * @throws VehicleException If ocurred and error during the validations to
     * insert the new vehicle.
     */
    @Override
    public void disableVehicle(com.estg.pickingManagement.Vehicle vhcl) throws VehicleException {
        for (Vehicle vehicle : this.vehicles) {
            if (vehicle.equals((Vehicle) vhcl)) {
                vehicle.setState(VehicleState.INACTIVE);
            }
        }
    }

    /**
     * <strong> enableVehicle() </strong>
     * <p>
     * Enables a vehicle, changing the state to active </p>
     *
     * @param vhcl receives the vehicle whose status is intended to be changed
     * @throws VehicleException If ocurred and error during the validations to
     * insert the new vehicle.
     */
    @Override
    public void enableVehicle(com.estg.pickingManagement.Vehicle vhcl) throws VehicleException {
        for (Vehicle vehicle : this.vehicles) {
            if (vehicle.equals((Vehicle) vhcl)) {
                vehicle.setState(VehicleState.ACTIVE);
            }
        }
    }

    /**
     * <strong> getVehicle </strong>
     * <p>
     * allows you to obtain a specific vehicle through registration </p>
     *
     * @param vehiclePlate of the vehicle you intend to obtain
     * @return the vehicle if found and null if not found
     */
    public Vehicle getVehicle(String vehiclePlate) {

        if (this.vehicles != null) {
            for (Vehicle vhcl : this.vehicles) {

                if (vhcl != null && vhcl.getPlate() == vehiclePlate) {
                    return vhcl;
                }

            }
        }
        return null;
    }

    /**
     * <strong>getPickingMaps()</strong>
     * <p>
     * This method gets the pickingMaps array.</p>
     *
     * @return The PickingMaps array.
     */
    @Override
    public PickingMap[] getPickingMaps() {
        return this.pickingMaps;
    }

    /**
     * <strong> getPickingMaps() </strong>
     * <p>
     * obtain a picking map between two given dates </p>
     *
     * @param ldt start date
     * @param ldt1 final date
     * @return the picking maps that are found between these dates
     */
    @Override
    public PickingMap[] getPickingMaps(LocalDateTime ldt, LocalDateTime ldt1) {
        int counter = countPickingMaps(ldt, ldt1);
        PickingMap[] mapsBetweenTwoDates = new PickingMap[counter];
        int index = 0;

        for (int i = 0; i < counter; i++) {
            LocalDateTime tmp = this.pickingMaps[i].getDate();
            if ((tmp.isAfter(ldt) && tmp.isBefore(ldt1)) || tmp.equals(ldt) || tmp.equals(ldt1)) {
                mapsBetweenTwoDates[index++] = this.pickingMaps[i];
            }
        }
        return mapsBetweenTwoDates;
    }

    /**
     * <strong>getCurrentPickingMap()</strong>
     * <p>
     * This method returns the current picking map that is being used by the
     * Institution</p>
     *
     * @return The last PickingMap from the array of pickingMaps;
     * @throws PickingMapException If the PickingMap doesn't exist.
     */
    @Override
    public PickingMap getCurrentPickingMap() throws PickingMapException {

        PickingMap lastPckMp = this.pickingMaps[this.pickingMpaCounter];

        if (lastPckMp == null) {
            throw new PickingMapException("No current PickingMap is being used.");
        }

        return lastPckMp;
    }

    /**
     * <strong> addPickingMap </strong>
     * <p>
     * allows you to add a picking map </p>
     *
     * @param pm picking map that you want to add
     * @return true if successful and false if it already exists
     * @throws PickingMapException If the value of a picking map is null
     */
    @Override
    public boolean addPickingMap(com.estg.pickingManagement.PickingMap pm) throws PickingMapException {
        PickingMap pm2 = (PickingMap) pm;
        if (hasPickingMap(pm2)) {
            return false;
        }

        if (pm2 == null) {
            throw new PickingMapException("Picking map is null");
        }

        this.pickingMaps[this.pickingMpaCounter++] = pm2;

        return true;
    }

    /**
     * <strong> saveDataToFile() </strong>
     *
     * @param filePath receives a path to the file
     * @return true if save successfully
     */
    public boolean saveDataToFile(String filePath) {
        return true;
    }

    /**
     * <strong> getDistance() </strong>
     * <p>
     * get the distance of an aidbox </p>
     *
     * @param aidbox that you want to obtain the distance
     * @return the distance
     * @throws AidBoxException If the supply box does not exist
     */
    @Override
    public double getDistance(com.estg.core.AidBox aidbox) throws AidBoxException {
        AidBox aidbox1 = (AidBox) aidbox;

        if (!verifyAidBox(aidbox1)) {
            throw new AidBoxException("The aidbox doesn't exist");
        }

        return aidbox1.getDistance(aidbox1);
    }

    /**
     * <strong> hasPickingMap() </strong>
     * <p>
     * checks if there is a picking map </p>
     *
     * @param pickingmap that you want to check
     * @return true if exits, false if don't
     */
    public boolean hasPickingMap(PickingMap pickingmap) {
        for (PickingMap map : this.pickingMaps) {

            if (map.equals(pickingmap)) {

                return true;

            }

        }

        return false;
    }

    /**
     *
     * < strong> getContainer () </strong>
     * <p>
     * obtain a container using a given code </p>
     *
     * @param containerCode of the container you intend to obtain
     * @return container if it exists and null if not
     * @throws AidBoxException If the aidboxes array is null
     */
    public Container getContainer(String containerCode) throws AidBoxException {

        if (this.aidBoxs == null) {
            throw new AidBoxException("AidBoxs array is null.");
        }

        for (AidBox box : this.aidBoxs) {
            if (box == null) {
                continue;
            }

            Container[] containerArray = box.getContainers();
            if (containerArray == null) {
                continue;
            }

            for (Container container : containerArray) {
                if (container != null && container.getCode() != null && container.getCode().equals(containerCode)) {
                    return container;
                }
            }
        }

        return null;
    }

    /**
     * <strong> countPickingMaps() </strong>
     * <p>
     * counts the picking maps that are located on these dates </p>
     *
     * @param ld initial date
     * @param  ld2 final date
     * @return the number of picking maps
     */
    private int countPickingMaps(LocalDateTime ld, LocalDateTime ld2) {
        int counter = 0;

        for (int i = 0; i < this.pickingMpaCounter; i++) {
            LocalDateTime tmp = this.pickingMaps[i].getDate();
            if ((tmp.isAfter(ld) && tmp.isBefore(ld2)) || tmp.equals(ld) || tmp.equals(ld2)) {
                counter++;
            }
        }

        return counter;
    }
}