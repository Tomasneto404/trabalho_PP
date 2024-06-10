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

import PP_AC_8220190_8220862.core.AidBox;
import PP_AC_8220190_8220862.core.Institution;
import com.estg.core.exceptions.PickingMapException;
import PP_AC_8220190_8220862.pickingManagement.Report;
import PP_AC_8220190_8220862.pickingManagement.Route;
import PP_AC_8220190_8220862.pickingManagement.RouteValidator;
import PP_AC_8220190_8220862.pickingManagement.Strategy;

/**
 * <strong> RouteGenerator() </strong>
 * <p>
 * this class identifies a route generator</p>
 *
 */
public class RouteGenerator implements com.estg.pickingManagement.RouteGenerator {

    /**
     * <strong> generateRoutes() </strong>
     * <p>
     * generates routes about a given institution with a given strategy, a route
     * validation and a report </p>
     *
     * @param instn variable of Institution type
     * @param strtg variable of Strategy type
     * @param rv variable of RouteValidator type
     * @param report variable of Report type
     * @return routes generated
     * @throws PickingMapException
     */
    @Override
    public Route[] generateRoutes(com.estg.core.Institution instn, com.estg.pickingManagement.Strategy strtg, com.estg.pickingManagement.RouteValidator rv, com.estg.pickingManagement.Report report) throws PickingMapException {
        Institution institution = (Institution) instn;
        Strategy strategy = (Strategy) strtg;
        RouteValidator validator = (RouteValidator) rv;
        Report report1 = (Report) report;

        AidBox[] aidboxes = toArrayAidBox(institution);
        Vehicle[] vehicles = toArrayVehicle(institution);

        Route[] routes = strategy.generate(institution, validator);

        if (report1 != null) {
            Report rp = new Report(institution);
        }

        return routes;
    }

    /**
     * <strong> getNumberOfAidBoxes() </strong>
     * <p> gets the number of aidboxes </p>
     * @param aidboxes array of Aidbox type
     * @return  the number of aidboxes
     */
    private int getNumberOfAidBoxes(AidBox[] aidboxes) {
        int number = 0;

        for (AidBox aidbox : aidboxes) {
            number++;
        }

        return number;
    }

     /**
     * <strong> getNumberOfVehicles() </strong>
     * <p> gets the number of vehicles </p>
     * @param aidboxes array of Vehicle type
     * @return  the number of vehicles
     */
    private int getNumberOfVehicles(Vehicle[] vh) {
        int number = 0;

        for (Vehicle vehicle : vh) {
            number++;
        }

        return number;
    }

     /**
     *
     * < strong> toArrayAidBox() </strong>
     * <p>
     * inserts an array of aidboxes into another array of aidboxes </p>
     *
     * @param institution variable of Institution type
     * @return array of aidboxes
     */
    private AidBox[] toArrayAidBox(Institution institution) {
        int number = getNumberOfAidBoxes(institution.getAidBoxes());

        AidBox[] aidboxes = new AidBox[number];

        int counter = 0;

        for (AidBox aidbox : institution.getAidBoxes()) {
            aidboxes[counter++] = aidbox;
        }

        return aidboxes;
    }

     /**
     *
     * < strong> toArrayVehicle() </strong>
     * <p>
     * inserts an array of vehicles into another array of vehicles</p>
     *
     * @param institution variable of Institution type
     * @return array of vehicles
     */
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
