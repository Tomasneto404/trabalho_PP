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
import PP_AC_8220190_8220862.core.Container;
import PP_AC_8220190_8220862.core.Institution;
import PP_AC_8220190_8220862.pickingManagement.Route;
import PP_AC_8220190_8220862.pickingManagement.RouteValidator;
import com.estg.pickingManagement.exceptions.RouteException;

/**
 * <strong> Strategy </strong>
 * <p>
 * this class identifies a strategy </p>
 *
 */
public class Strategy implements com.estg.pickingManagement.Strategy {

    /**
     * <strong> generate() </strong>
     * <p>
     * This method allows you to generate a route considering a certain
     * strategy, the ordering </p>
     *
     * @param instn receives a variable Institution type
     * @param rv receives a variable of a RouteValidator type
     * @return the generated routes or the routes no empty
     */
    @Override
    public Route[] generate(com.estg.core.Institution instn, com.estg.pickingManagement.RouteValidator rv) {
        Institution institution = (Institution) instn;
        RouteValidator validator = (RouteValidator) rv;

        AidBox[] aidBoxes = toArrayAidBox(institution);
        Vehicle[] vehicles = toArrayVehicle(institution);
        Container[] containers = getAllContainers(aidBoxes);
        Route[] routes = initializeRoutes(vehicles);
        Route[] noEmpty = nonEmptyRoutes(routes);

        if (vehicles.length > 0) {

            bubbleSort(containers);

            for (Container container : containers) {
                if (!addContainertoroute(routes, container, validator)) {
                    throw new RuntimeException("Impossible to do this operation");
                }
            }

        }

        return noEmpty;

    }

    /**
     * <strong> nonEmptyRoutes() </strong>
     * <p>
     * This method allows you to filter empty routes </p>
     *
     * @param routes array of Route type
     * @return the routes no empty
     */
    private Route[] nonEmptyRoutes(Route[] routes) {
        int count = 0;
        int counter = 0;

        for (Route route : routes) {
            if (route.getAidBoxs().length > 0) {
                count++;
            }
        }

        Route[] noempty = new Route[count];
        for (Route route : routes) {
            if (route.getAidBoxs().length > 0) {
                noempty[counter++] = route;
            }
        }
        return noempty;
    }

    /**
     * <strong> bubbleSort() </strong>
     * <p>
     * Sort the containers by the most full </p>
     *
     * @param containers array of Container's type
     */
    private void bubbleSort(Container[] containers) {
        int length = containers.length;

        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1; j++) {
                if (containers[j].getCode().compareTo(containers[j + 1].getCode()) > 0) {
                    Container tmp = containers[j];
                    containers[j] = containers[j + 1];
                    containers[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * <strong> getAllContainers() </strong>
     * <p>
     * get all containers from an array of aidboxes </p>
     *
     * @param aidboxes a array of AidBox type
     * @return array of containers
     */
    private Container[] getAllContainers(AidBox[] aidboxes) {
        int totalContainers = 0;
        int index = 0;

        for (AidBox aidBox : aidboxes) {
            totalContainers += aidBox.getContainers().length;
        }

        Container[] containers = new Container[totalContainers];
        for (AidBox aidBox : aidboxes) {
            for (Container container : aidBox.getContainers()) {
                containers[index++] = container;
            }

        }

        return containers;
    }

    /**
     *
     * < strong> initializeRoutes() </strong>
     * <p>
     * initialize all routes </p>
     *
     * @param vehicles array of vehicles that will be used
     * @return initialized routes
     */
    private Route[] initializeRoutes(Vehicle[] vehicles) {
        Route[] routes = new Route[vehicles.length];

        for (int i = 0; i < vehicles.length; i++) {
            routes[i] = new Route(vehicles[i]);
        }

        return routes;
    }

    /**
     *
     * < strong> addContainerToRoute() </strong>
     * <p>
     * adds a container to a given route </p>
     *
     * @param routes array of Route type
     * @param container variable of Container type
     * @param rv variable of Route validator type
     * @return true if the insertion was successful and false if not
     */
    private boolean addContainertoroute(Route[] routes, Container container, RouteValidator rv) {
        AidBox aidBox = new AidBox(new Container[]{container});

        for (Route route : routes) {
            try {
                if (rv.validate(route, aidBox)) {
                    route.addAidBox(aidBox);
                    return true;
                }
            } catch (RouteException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    /**
     * <strong> getNumberOfAidBoxes() </strong>
     * <p>
     * gets the number of aidboxes in a given array </p>
     *
     * @param aidboxes array of AidBox type
     * @return number of aidboxes
     */
    private int getNumberOfAidBoxes(AidBox[] aidboxes) {
        int number = 0;

        for (AidBox aidbox : aidboxes) {
            number++;
        }

        return number;
    }

    /**
     * <strong> getNumberOfVehicles </strong>
     * <p>
     * gets the number of vehicles in a given array </p>
     *
     * @param vh array of Vehicle type
     * @return number of vehicles
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
