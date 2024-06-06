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

import com.estg.core.exceptions.VehicleException;
import com.estg.core.ItemType;

/**
<<<<<<< HEAD:trabalho_PP/src/pickingManagement/Vehicle.java
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

=======
 *
 * @author tomas
 */
class ObjectOutputStream {
    
>>>>>>> 94769fc30c9d146d83d002a6680d6f4a3cc1c212:trabalho_PP/src/PP_AC_8220190_8220862/core/ObjectOutputStream.java
}
