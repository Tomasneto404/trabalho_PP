/*
* Nome: Tomás Leonardo Leão Sousa Neto
* Número: 8220862
* Turma: LSIRC12T1
*
* Nome: Tânia Sofia da Silva Morais
* Número: 8220190
* Turma: LSIRC12T1
 */
package PP_AC_8220190_8220862.classes;

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

    private GeographicCoordinates coordinates;

    private Container[] containers;

    /**
     * <strong>Instance Constructor Method</strong>
     * <p>
     * This method defines the default values for all the constructors methods of
     * the instance.</p>
     */
    {
        this.containers = new Container[MAX_CONTAINERS];
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    @Override
    public boolean addContainer(Container cntnr) throws ContainerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
