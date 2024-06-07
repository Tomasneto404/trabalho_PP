/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PP_AC_8220190_8220862.pickingManagement;

import com.estg.pickingManagement.exceptions.RouteException;
import PP_AC_8220190_8220862.pickingManagement.Vehicle;

import PP_AC_8220190_8220862.core.AidBox;

/**
 *
 * isto +e etste
 */
public class Route implements com.estg.pickingManagement.Route {

    private final int MAX_AIDBOXS = 20;
    
    private AidBox[] aidBoxs;
    
    private Vehicle vehicle;
    
    private double totalDistance;
    
    private double totalDuration;
    
    {
        this.aidBoxs= new AidBox[MAX_AIDBOXS];
        this.totalDistance=0.0;
        this.totalDuration=0.0;
    }

    public Route (Vehicle vehicle){
        this.vehicle=vehicle;
    }
    
    @Override
    public void addAidBox(com.estg.core.AidBox aidbox) throws RouteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AidBox removeAidBox(com.estg.core.AidBox aidbox) throws RouteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsAidBox(com.estg.core.AidBox aidbox) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void replaceAidBox(com.estg.core.AidBox aidbox, com.estg.core.AidBox  aidbox1) throws RouteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertAfter(com.estg.core.AidBox  aidbox, com.estg.core.AidBox  aidbox1) throws RouteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AidBox[] getRoute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vehicle getVehicle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getTotalDistance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getTotalDuration() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
