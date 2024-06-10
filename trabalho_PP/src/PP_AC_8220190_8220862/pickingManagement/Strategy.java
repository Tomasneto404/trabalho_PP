/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PP_AC_8220190_8220862.pickingManagement;

import PP_AC_8220190_8220862.core.AidBox;
import PP_AC_8220190_8220862.core.Container;
import PP_AC_8220190_8220862.core.Institution;
import PP_AC_8220190_8220862.pickingManagement.Route;
import PP_AC_8220190_8220862.pickingManagement.RouteValidator;
import com.estg.pickingManagement.exceptions.RouteException;

/**
 *
 * @author Asus
 */
public class Strategy implements com.estg.pickingManagement.Strategy {

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

    private Route[] initializeRoutes(Vehicle[] vehicles) {
        Route[] routes = new Route[vehicles.length];

        for (int i = 0; i < vehicles.length; i++) {
            routes[i] = new Route(vehicles[i]);
        }

        return routes;
    }

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