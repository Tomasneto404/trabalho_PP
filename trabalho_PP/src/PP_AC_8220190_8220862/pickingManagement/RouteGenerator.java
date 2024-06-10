/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PP_AC_8220190_8220862.pickingManagement;

import PP_AC_8220190_8220862.core.AidBox;
import PP_AC_8220190_8220862.core.Institution;
import com.estg.core.exceptions.PickingMapException;
import PP_AC_8220190_8220862.pickingManagement.Report;
import PP_AC_8220190_8220862.pickingManagement.Route;
import PP_AC_8220190_8220862.pickingManagement.RouteValidator;
import PP_AC_8220190_8220862.pickingManagement.Strategy;

/**
 *
 * @author Asus
 */
public class RouteGenerator implements com.estg.pickingManagement.RouteGenerator {

    @Override
    public Route[] generateRoutes(com.estg.core.Institution instn, com.estg.pickingManagement.Strategy strtg, com.estg.pickingManagement.RouteValidator rv, com.estg.pickingManagement.Report report) throws PickingMapException {
        Institution institution = (Institution) instn;
        Strategy strategy = (Strategy) strtg;
        RouteValidator validator = (RouteValidator) rv;
        Report report1 = (Report) report;

        AidBox[] aidboxes= toArrayAidBox(institution);
        Vehicle[] vehicles= toArrayVehicle(institution);
        
        Route[] routes= strategy.generate(institution, validator);
        
        if(report1!=null){
            Report rp= new Report(institution);
        }
        
        return routes;
    }

     private int getNumberOfAidBoxes(AidBox[] aidboxes) {
        int number = 0;

        for (AidBox aidbox : aidboxes) {
            number++;
        }

        return number;
    }

    private int getNumberOfVehicles(Vehicle[] vh) {
        int number = 0;

        for (Vehicle vehicle : vh) {
            number++;
        }

        return number;
    }
    
    private AidBox[] toArrayAidBox(Institution institution) {
        int number = getNumberOfAidBoxes(institution.getAidBoxes());

        AidBox[] aidboxes = new AidBox[number];

        int counter = 0;

        for (AidBox aidbox : institution.getAidBoxes()) {
            aidboxes[counter++] = aidbox;
        }

        return aidboxes;
    }

    private Vehicle[] toArrayVehicle(Institution institution) {
        int number = getNumberOfVehicles(institution.getVehicles());
        Vehicle[] vehicles = new Vehicle[number];
        int counter = 0;

        for (Vehicle vh : institution.getVehicles()) {
            vehicles[counter++] = vh;
        }

        return vehicles;
    }
}
