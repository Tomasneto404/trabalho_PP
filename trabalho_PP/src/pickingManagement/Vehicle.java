/*
* Nome: Tomás Leonardo Leão Sousa Neto
* Número: 8220862
* Turma: LSIRC12T1
*
* Nome: Tânia Sofia da Silva Morais
* Número: 8220190
* Turma: LSIRC12T1
 */
package pickingManagement;

import com.estg.core.exceptions.VehicleException;
import com.estg.core.ItemType;

/**
 * <strong>Vehicle</strong>
 * <p>
 * This class identifies a vehicle</p>
 */
public class Vehicle implements com.estg.pickingManagement.Vehicle {

    private ItemType item;

    private double maxCapacity;

    /**
     * <strong>Vehicle() </strong>
     * <p> contructor method </p>
     * @param item receives a variable of ItemType type
     * @param maxCapacity receives a variable of double type
     * @throws VehicleException exception corresponding to a vehicle
     */
    public Vehicle(ItemType item, double maxCapacity) throws VehicleException {

        if (item == ItemType.PERISHABLE_FOOD) {
            throw new VehicleException("This type of item is not possible");
        }

        this.item = item;

        this.maxCapacity = maxCapacity;
    }

    @Override
    public ItemType getSupplyType() {
        return this.item;
    }

    @Override
    public double getMaxCapacity() {
        return this.maxCapacity;
    }

}
