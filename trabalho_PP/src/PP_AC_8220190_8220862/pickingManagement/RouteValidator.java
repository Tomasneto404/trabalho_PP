/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PP_AC_8220190_8220862.pickingManagement;

import PP_AC_8220190_8220862.pickingManagement.Route;

import PP_AC_8220190_8220862.core.AidBox;
import PP_AC_8220190_8220862.core.Container;

/**
 *
 * @author Asus
 */
public class RouteValidator implements com.estg.pickingManagement.RouteValidator {

    @Override
    public boolean validate(com.estg.pickingManagement.Route route, com.estg.core.AidBox aidbox) {
        AidBox aidBox = (AidBox) aidbox;
        Route route1 = (Route) route;
        Container[] containers = aidBox.getContainers();
        AidBox[] aidBoxs = route1.getAidBoxs();

        if (route1 == null || aidBox == null) {
            return false;
        }

        if (route1.containsAidBox(aidBox)) {
            return false;
        }

        if (!route1.verifyCompatibility(containers)) {
            return false;
        }

        if (route1.getTotalCapacityBoxs() > route1.getVehicle().getMaxCapacity() ) {
            return false;
        }
        return true;
    }

}
