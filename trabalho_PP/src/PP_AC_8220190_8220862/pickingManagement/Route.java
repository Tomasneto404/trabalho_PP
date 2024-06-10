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

import com.estg.pickingManagement.exceptions.RouteException;
import PP_AC_8220190_8220862.pickingManagement.Vehicle;

import PP_AC_8220190_8220862.core.AidBox;
import PP_AC_8220190_8220862.core.Container;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <strong> Route </strong>
 * <p>
 * this classe identifies a route </p>
 *
 */
public class Route implements com.estg.pickingManagement.Route {

    private final int MAX_AIDBOXS = 20;

    private int aidBoxCounter;

    private AidBox[] aidBoxs;

    private Vehicle vehicle;

    private double totalDistance;

    private double totalDuration;

    private double totalCapacityBoxs;

    /**
     * <strong>Instance Constructor Method</strong>
     * <p>
     * This method defines the default values for all the constructors methods
     * of the instance.</p>
     */
    {
        this.aidBoxs = new AidBox[MAX_AIDBOXS];
        this.totalDistance = 0.0;
        this.totalDuration = 0.0;
        this.aidBoxCounter = 0;
        this.totalCapacityBoxs = 0.0;

    }

    /**
     * <strong> Route() </strong <p>
     * Route constructor method </p>
     *
     * @param aidBoxs receives a variable of AidBox type
     * @param vehicle receives a variable of Vehicle type
     */
    public Route(AidBox[] aidBoxs, Vehicle vehicle) {
        this.vehicle = vehicle;
        this.aidBoxs = aidBoxs;
    }

    /**
     * <strong> Route() </strong>
     * <p>
     * Route constructor method </p>
     *
     * @param vehicle receives a variable of Vehicle type
     */
    public Route(Vehicle vehicle) {
        this.vehicle = vehicle;

    }

    /**
     * <strong> getAidBoxs() </strong>
     * <p>
     * Obtains all aidBoxs </p>
     *
     * @return all aidboxs
     */
    public AidBox[] getAidBoxs() {
        return this.aidBoxs;
    }

    /**
     * <strong> addAidBox() </strong>
     * <p>
     * adds a supply crate to a route </p>
     *
     * @param aidbox that you want to add
     * @throws RouteException if the supply box is null, if it already exists or
     * if it is not compatible with the vehicle or if the capacity exceeds the
     * maximum capacity of a vehicle
     */
    @Override
    public void addAidBox(com.estg.core.AidBox aidbox) throws RouteException {
        AidBox aidBox = (AidBox) aidbox;
        Container[] containers = aidBox.getContainers();

        if (aidBox == null) {
            throw new RouteException("The aid box is null");
        }

        if (containsAidBox(aidBox)) {
            throw new RouteException("The aid box exists");
        }

        if (!verifyCompatibility(containers)) {
            throw new RouteException("Doesn't exist compatibility between AidBox and Vehicle");
        }

        if (this.totalCapacityBoxs >= vehicle.getMaxCapacity()) {
            throw new RouteException("The vehicle is full");
        }

        this.aidBoxs[this.aidBoxCounter++] = aidBox;
    }

    /**
     * <strong> removeAidBox() </strong>
     *
     * @param aidbox that you want to remove
     * @return the aidbox that was removed
     * @throws RouteException if the aidbox does not exist
     */
    @Override
    public AidBox removeAidBox(com.estg.core.AidBox aidbox) throws RouteException {
        AidBox aidBox = (AidBox) aidbox;
        int pos = getIndex(aidBox);

        if (pos == -1) {
            throw new RouteException("This aidbox doesn´t exist");
        }

        for (int i = pos; i < this.aidBoxCounter; i++) {
            this.aidBoxs[i] = this.aidBoxs[i + 1];
        }

        this.aidBoxs[this.aidBoxCounter--] = null;

        return aidBox;
    }

    /**
     * <strong> containsAidBox() </strong>
     * <p>
     * checks if the aidbox array already contains that aidbox </p>
     *
     * @param aidbox that you want to check
     * @return true if it exists and false if not
     */
    @Override
    public boolean containsAidBox(com.estg.core.AidBox aidbox) {
        AidBox aidBox = (AidBox) aidbox;

        for (AidBox box : this.aidBoxs) {
            if (box != null && box.equals(aidbox)) {
                return true;
            }
        }

        return false;

    }

    /**
     * <strong> replaceAidBox() </strong>
     * <p>
     * replaces an aidbox with another checking whether it already exists or not
     * </p>
     *
     * @param aidbox aidbox that replaces
     * @param aidbox1 aidbox that will be replaced
     * @throws RouteException If the aidboxes are null, if the replaced aidbox
     * does not exist, if the replacement aidbox exists and if there is no
     * compatibility with a vehicle
     */
    @Override
    public void replaceAidBox(com.estg.core.AidBox aidbox, com.estg.core.AidBox aidbox1) throws RouteException {

        AidBox aidBox = (AidBox) aidbox;
        AidBox aidBox1 = (AidBox) aidbox1;

        int pos = getIndex(aidBox1);

        Container[] containers = aidBox.getContainers();

        if (aidBox1 == null || aidBox == null) {
            throw new RouteException("The value is null");
        }

        if (!containsAidBox(aidBox1)) {
            throw new RouteException("The aidbox doesn't exist");
        }

        if (containsAidBox(aidbox)) {
            throw new RouteException("The aidbox replace exists");
        }

        if (!verifyCompatibility(containers)) {
            throw new RouteException("Doesn't exist compatibility");
        }

        this.aidBoxs[pos] = aidBox;

    }

    /**
     * <strong> insertAfter() </strong>
     *
     * @param aidbox aidbox insert after
     * @param aidbox1 existing aidbox
     * @throws RouteException If the aidboxes are null, if the replaced aidbox
     * does not exist, if the replacement aidbox exists and if there is no
     * compatibility with a vehicle
     */
    @Override
    public void insertAfter(com.estg.core.AidBox aidbox, com.estg.core.AidBox aidbox1) throws RouteException {
        AidBox aidBox = (AidBox) aidbox;
        AidBox aidBox1 = (AidBox) aidbox1;

        int pos = getIndex(aidBox1);

        Container[] containers = aidBox.getContainers();

        if (aidBox1 == null || aidBox == null) {
            throw new RouteException("The value is null");
        }

        if (!containsAidBox(aidBox1)) {
            throw new RouteException("The aidbox doesn't exist");
        }

        if (containsAidBox(aidbox)) {
            throw new RouteException("The aidbox insert after exists");
        }

        if (!verifyCompatibility(containers)) {
            throw new RouteException("Doesn't exist compatibility");
        }

        this.aidBoxs[pos + 1] = aidBox;
    }

    /**
     * <strong> getRoute() </strong>
     * <p>
     * get the route of a set of aidboxes
     *
     * @return the set of aidboxes
     */
    @Override
    public AidBox[] getRoute() {
        AidBox[] tmp = new AidBox[this.aidBoxCounter];

        for (int i = 0; i < this.aidBoxCounter; i++) {
            if (this.aidBoxs[i] != null) {
                try {
                    tmp[i] = (AidBox) this.aidBoxs[i].clone();
                } catch (CloneNotSupportedException e) {
                    Logger.getLogger(Route.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }

        return tmp;
    }

    /**
     * <strong> getVehicle() </strong>
     * <p>
     * get the vehicle </p>
     *
     * @return the vehicle
     */
    @Override
    public Vehicle getVehicle() {
        if (this.vehicle instanceof RefrigeratedVehicle) {
            return (RefrigeratedVehicle) this.vehicle;
        }

        return this.vehicle;
    }

    /**
     * <strong> getTotalDistance() </strong>
     * <p>
     * get the total distance of the route </p>
     *
     * @return the total distance
     */
    @Override
    public double getTotalDistance() {

        for (int i = 0; i < this.aidBoxCounter; i++) {

            while (i < this.aidBoxCounter) {

                try {
                    totalDistance += this.aidBoxs[i].getDistance(this.aidBoxs[i + 1]);
                } catch (Exception e) {
                    e.getMessage();
                }

            }

        }

        return this.totalDistance;
    }

    /**
     * <strong> getTotalDuration() </strong>
     * <p>
     * Gets the total distance of a route </p>
     *
     * @return total duration
     */
    @Override
    public double getTotalDuration() {

        for (int i = 0; i < this.aidBoxCounter; i++) {

            while (i < this.aidBoxCounter) {

                try {
                    totalDuration += this.aidBoxs[i].getDuration(this.aidBoxs[i + 1]);
                } catch (Exception e) {
                    e.getMessage();
                }

            }
        }
        return this.totalDuration;
    }

    /**
     * <strong> getIndex </strong>
     * <p>
     * get the index of an aidbox </p>
     *
     * @param aidbox aidboz that you want to obtain the address
     * @return the position if it exists and -1 if it does not exist
     */
    private int getIndex(AidBox aidbox) {
        for (int i = 0; i < this.aidBoxCounter; i++) {
            if (this.aidBoxs[i].equals(aidbox)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * <strong> verifyCompatibility() </strong>
     * <p>
     * checks compatibility between a vehicle and a set of containers </p>
     *
     * @param containers set of containers that compatibility will be checked
     * @return false if there is no compatibility and true if there is
     */
    public boolean verifyCompatibility(Container[] containers) {
        for (Container container : containers) {
            if (container.getType() != vehicle.getSupplyType()) {
                return false;
            }
        }
        return true;
    }

    /**
     * <strong> getTotalCapacityBoxs() </strong>
     * <p> Get the full capacity of all supply crates </p>
     * @return the total capacity of all boxs
     */
    public double getTotalCapacityBoxs() {
        for (int i = 0; i < this.aidBoxCounter; i++) {
            this.totalCapacityBoxs += this.aidBoxs[i].getTotalCapacity();
        }

        return this.totalCapacityBoxs;
    }
}
