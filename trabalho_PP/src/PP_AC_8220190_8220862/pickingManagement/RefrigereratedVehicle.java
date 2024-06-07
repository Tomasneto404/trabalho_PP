/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PP_AC_8220190_8220862.pickingManagement;

import com.estg.core.ItemType;
import com.estg.core.exceptions.VehicleException;

/**
 *
 * @author Asus
 */
public class RefrigereratedVehicle extends Vehicle {
    
    private ItemType item;
    
    
    public RefrigereratedVehicle(String plate, double maxCapacity) {
        super(plate, maxCapacity);
    }
    
    @Override
     public void setItem(ItemType item) throws VehicleException {
        if (item != ItemType.PERISHABLE_FOOD) { 
            throw new VehicleException("Wrong type");
        }
        this.item = item;
    }
     
     
    
}
