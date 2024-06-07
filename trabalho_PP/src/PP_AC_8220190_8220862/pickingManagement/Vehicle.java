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

import PP_AC_8220190_8220862.enums.VehicleState;

import PP_AC_8220190_8220862.enums.VehicleState;

import com.estg.core.exceptions.VehicleException;
import com.estg.core.ItemType;

import PP_AC_8220190_8220862.enums.VehicleState;

/**
 * <strong>Vehicle</strong>
 * <p>
 * This class identifies a vehicle</p>
 */
public class Vehicle implements com.estg.pickingManagement.Vehicle {

    private ItemType item;

    private String plate;

    private double maxCapacity;
    
    private VehicleState state;

    /**
     * <strong>Vehicle() </strong>
     * <p>
     * contructor method </p>
     *
     * @param item receives a variable of ItemType type
     * @param maxCapacity receives a variable of double type
     * @throws VehicleException exception corresponding to a vehicle
     */
    public Vehicle(String plate, double maxCapacity) {

        this.plate = plate;
        this.item = item;
        this.state = VehicleState.ACTIVE;

        this.maxCapacity = maxCapacity;
    }

    /**
     * <strong> setItem(ItemType item) </strong>
     * <p>
     * allows you to change the type of item and check if it is perishable food
     * </p>
     *
     * @param item variable of ItemType type
     * @throws VehicleException exception corresponding a vehicle
     */
    public void setItem(ItemType item) throws VehicleException {
        if (item == ItemType.PERISHABLE_FOOD) {
            throw new VehicleException("Wrong type");
        }
        this.item = item;
    }

    /**
     * <strong> getPlate() </strong>
     * <p>
     * get the plate value </p>
     *
     * @return the plate
     */
    public String getPlate() {
        return this.plate;
    }

    /**
     * <strong> getSupplyType () </strong>
     * <p>
     * get the value of type </p>
     *
     * @return the item
     */
    @Override
    public ItemType getSupplyType() {
        return this.item;
    }

    /**
     * <strong> getMaxCapacity() </strong>
     * <p>
     * get the value of max capacity </p>
     *
     * @return the max capacity
     */
    @Override
    public double getMaxCapacity() {
        return this.maxCapacity;
    }
    
    /**
     * <strong>setState()</strong>
     * <p>Sets the value of state to the vehicle.</p>
     * @param state 
     */
    public void setState(VehicleState state) {
        this.state = state;
    }
    
    /**
     * <strong>getState()</strong>
     * <p>Gets the value of the actual state of the vehicle.</p>
     * @return The VehicleState object that identifies the actual state of vehicle.
     */
    public VehicleState getState(){
        return this.state;
    }

}
