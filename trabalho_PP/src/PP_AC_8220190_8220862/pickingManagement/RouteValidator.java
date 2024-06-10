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

import PP_AC_8220190_8220862.pickingManagement.Route;

import PP_AC_8220190_8220862.core.AidBox;
import PP_AC_8220190_8220862.core.Container;

/**
 * <strong>RouteValidator </strong>
 * <p>
 * this classe identifies a routeValidator </p>
 *
 */
public class RouteValidator implements com.estg.pickingManagement.RouteValidator {

    /**
     * <strong> validate() </strong>
     * <p>
     * validates whether a route is valid </p>
     *
     * @param route variable of Route type
     * @param aidbox variable of AidBox type
     * @return false if the route and the aidbox are null, if the route already
     * has that aidbox, if there is no compatibility and if the total capacity
     * is greater than that of the vehicle. Returns true if successful.
     */
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

        if (route1.getTotalCapacityBoxs() > route1.getVehicle().getMaxCapacity()) {
            return false;
        }
        return true;
    }

}
