/*
* Nome: Tomás Leonardo Leão Sousa Neto
* Número: 8220862
* Turma: LSIRC12T1
*
* Nome: Tânia Sofia da Silva Morais
* Número: 8220190
* Turma: LSIRC12T1
 */
package PP_AC_8220190_8220862.core;

import com.estg.core.Container;
import com.estg.core.GeographicCoordinates;
import com.estg.core.ItemType;
import com.estg.core.exceptions.AidBoxException;
import com.estg.core.exceptions.ContainerException;

/**
 * <strong>AidBox</strong>
 * <p>Coisas</p>
 *
 */
public class AidBox implements com.estg.core.AidBox {

    private final int MAX_CONTAINERS = 10;

    private String code;

    private String zone;
    
    private String refLocal;

    private GeographicCoordinates coordinates;

    private Container[] containers;
    
    private int containerCounter;
    
    private double weight;

    /**
     * <strong>Instance Constructor Method</strong>
     * <p>
     * This method defines the default values for all the constructors methods of
     * the instance.</p>
     */
    {
        this.containers = new Container[MAX_CONTAINERS];
        this.containerCounter = 0;
    }

    /**
     * <strong>AidBox()</strong>
     * <p>AidBox constructor method.</p>
     * @param code String value that represents the code of an AidBox.
     * @param zone String value that represents the zone where the AiBox is.
     * @param coordinates GeographicCoordinates value that represents the actual coordinates of the AidBox.
     */
    public AidBox(String code, String zone, GeographicCoordinates coordinates) {
        this.code = code;
        this.zone = zone;
        this.coordinates = coordinates;
    }

    /**
     *<strong>getCode()</strong>
     * @return String that represents the AidBox code.
     */
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     *<strong>getZone()</strong>
     * @return String that represents the AidBox zone.
     */
    @Override
    public String getZone() {
        return this.zone;
    }

    /**
     * 
     * @return 
     */
    @Override
    public String getRefLocal() {
        return this.refLocal;
    }

    @Override
    public double getDistance(com.estg.core.AidBox aidbox) throws AidBoxException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getDuration(com.estg.core.AidBox aidbox) throws AidBoxException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GeographicCoordinates getCoordinates() {
        return this.coordinates;
    }

    /**
     * 
     * @param cntnr
     * @return
     * @throws ContainerException 
     */
    @Override
    public boolean addContainer(Container cntnr) throws ContainerException {
        
        if ( this.containerCounter < containers.length ) { //Verifica se cabe dentro do array
            
             if (!verifyContainer(cntnr)){ //Verifica se existe algum igual dentro do array
                 
                 this.containers[this.containerCounter++] = cntnr;
                 return true;
                 
             }
           
        }
        
        return false;
    }
    
    /**
     * <strong>verifyContainer()</strong>
     * <p>This method verifys if a given container already existes inside the containers array.</p>
     * @param cntnr - Container to be analyzed
     * @return true if already exists and equal container, false if not
     */
    private boolean verifyContainer(Container cntnr){
        
        for (int i = 0; i < this.containers.length; i++ ){
            if (containers[i].equals(cntnr)){
                return true;
            }
        }
        return false;
        
    }

    @Override
    public Container getContainer(ItemType it) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Container[] getContainers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
