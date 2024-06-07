/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PP_AC_8220190_8220862.pickingManagement;

import com.estg.pickingManagement.exceptions.RouteException;
import PP_AC_8220190_8220862.pickingManagement.Vehicle;

import PP_AC_8220190_8220862.core.AidBox;
import PP_AC_8220190_8220862.core.Container;
import com.estg.core.ItemType;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * isto +e etste
 */
public class Route implements com.estg.pickingManagement.Route {

    private final int MAX_AIDBOXS = 20;

    private int counter;

    private AidBox[] aidBoxs;

    private Vehicle vehicle;

    private double totalDistance;

    private double totalDuration;

    {
        this.aidBoxs = new AidBox[MAX_AIDBOXS];
        this.totalDistance = 0.0;
        this.totalDuration = 0.0;
        this.counter = 0;
    }

    public Route(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

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

        this.aidBoxs[this.counter++] = aidBox;
    }

    @Override
    public AidBox removeAidBox(com.estg.core.AidBox aidbox) throws RouteException {
        AidBox aidBox = (AidBox) aidbox;
        int pos = getIndex(aidBox);

        if (pos == -1) {
            throw new RouteException("This aidbox doesn´t exist");
        }

        for (int i = pos; i < this.counter; i++) {
            this.aidBoxs[i] = this.aidBoxs[i + 1];
        }

        this.aidBoxs[this.counter--] = null;

        return aidBox;
    }

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

    //com dúvidas
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
            throw new RouteException("The aidbox replace exists");
        }

        if (!verifyCompatibility(containers)) {
            throw new RouteException("Doesn't exist compatibility");
        }

        this.aidBoxs[pos + 1] = aidBox;
    }

    @Override
    public AidBox[] getRoute() {
        AidBox[] tmp = new AidBox[this.counter];

        for (int i = 0; i < this.counter; i++) {
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

    @Override
    public Vehicle getVehicle() {
        if (this.vehicle instanceof RefrigereratedVehicle) {
            return (RefrigereratedVehicle) this.vehicle;
        }

        return this.vehicle;
    }

    @Override
    public double getTotalDistance() {
        return this.totalDistance;
    }

    @Override
    public double getTotalDuration() {
        return this.totalDuration;
    }

    private int getIndex(AidBox aidbox) {
        for (int i = 0; i < this.counter; i++) {
            if (this.aidBoxs[i].equals(aidbox)) {
                return i;
            }
        }

        return -1;
    }

    private boolean verifyCompatibility(Container[] containers) {
        for (int i = 0; i < containers.length; i++) {
            if (containers[i].getType() != vehicle.getSupplyType()) {
                return false;
            }
        }
        return true;
    }

}
