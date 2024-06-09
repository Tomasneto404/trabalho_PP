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

import com.estg.core.ItemType;
import com.estg.core.exceptions.VehicleException;

/**
 * <strong>Vehicle</strong>
 * <p>
 * This class identifies a vehicle</p>
 */
public class RefrigeratedVehicle extends Vehicle {

    private double maxKilometers;

    private ItemType item;

    /**
     * <strong> RegrigereratedVehicle() </strong>
     * <p>
     * constructor method </p>
     *
     * @param plate variable of String type
     * @param maxCapacity variable of double type
     * @param maxKilometers variable of double type
     */
    public RefrigeratedVehicle(String plate, double maxCapacity, double maxKilometers) {
        super(plate, maxCapacity);
        this.maxKilometers = maxKilometers;
    }

    /**
     * <strong> getMaxKilometers() </strong>
     * <p>
     * getters corresponding to a max kilometers </p>
     *
     * @return the refrigererated vehicle's max kilometers
     */
    public double getMaxKilometers() {
        return this.maxKilometers;
    }

    /**
     * <strong> setItem() </strong>
     * <p>
     * setter corresponding to a item, it verifies if the item type equal to
     * perishable food </p>
     *
     * @param item variable of ItemType type
     * @throws VehicleException excepction corresponding a vehicle
     */
    @Override
    public void setItem(ItemType item) throws VehicleException {
        if (item != ItemType.PERISHABLE_FOOD) {
            throw new VehicleException("Wrong type");
        }
        this.item = item;
    }

}
