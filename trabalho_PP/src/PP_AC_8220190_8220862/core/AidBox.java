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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.estg.core.Container;
import com.estg.core.GeographicCoordinates;
import com.estg.core.ItemType;
import com.estg.core.exceptions.AidBoxException;
import com.estg.core.exceptions.ContainerException;
import com.estg.io.HTTPProvider;

/**
 * <strong>AidBox</strong>
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

    private HTTPProvider provider;

    private double weight;

    /**
     * <strong>Instance Constructor Method</strong>
     * <p>
     * This method defines the default values for all the constructors methods
     * of the instance.</p>
     */
    {
        this.containers = new Container[MAX_CONTAINERS];
        this.containerCounter = 0;
        this.provider = new HTTPProvider();
    }

    /**
     * <strong>AidBox()</strong>
     * <p>
     * AidBox constructor method.</p>
     *
     * @param code String value that represents the code of an AidBox.
     * @param zone String value that represents the zone where the AiBox is.
     * @param coordinates GeographicCoordinates value that represents the actual
     * coordinates of the AidBox.
     */
    public AidBox(String code, String zone) {
        this.code = code;
        this.zone = zone;
    }

    /**
     * <strong>getCode()</strong>
     *
     * @return String that represents the AidBox code.
     */
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * <strong>getZone()</strong>
     *
     * @return String that represents the AidBox zone.
     */
    @Override
    public String getZone() {
        return this.zone;
    }

    /**
     * <strong>getRefLocal()</strong>
     *
     * @return String that represents the AidBox ref local.
     */
    @Override
    public String getRefLocal() {
        return this.refLocal;
    }

    /**
     * <strong>getDistance()</strong>
     * <p>
     * This method makes the request to the WEB API and returns the distance
     * from one aid box to another</p>
     *
     * @param aidbox
     * @return Distance from one aid box to another
     * @throws AidBoxException
     */
    @Override
    public double getDistance(com.estg.core.AidBox aidbox) throws AidBoxException {
        String jsonString = this.provider.getFromURL("https://data.mongodb-api.com/app/data-docuz/endpoint/distances?from=" + this.code + "&to=" + aidbox.getCode());

        JSONParser parser = new JSONParser();

        JSONObject jsonObject = (JSONObject) parser.parse(jsonString);

        JSONArray stringToArray = (JSONArray) jsonObject.get("to");

        JSONObject object = (JSONObject) stringToArray.get(0);

        double distance = (double) object.get("distance");

        return distance;
    }

    @Override
    public double getDuration(com.estg.core.AidBox aidbox) throws AidBoxException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * <strong>getCoordinates()</strong>
     *
     * @return GeograpicCoordinates object that represents the coordinates of
     * the AidBox
     */
    @Override
    public GeographicCoordinates getCoordinates() {
        return this.coordinates;
    }

    /**
     * <strong>addContainer()</strong>
     * <p>
     * This method allows to insert a Container inside the containers array.</p>
     *
     * @param cntnr Container to be added
     * @return True if it was possible to insert the new container, false if it
     * wasn't.
     * @throws ContainerException
     */
    @Override
    public boolean addContainer(Container cntnr) throws ContainerException {

        if (canAddContainer()) { //Verifica se cabe dentro do array

            if (!verifyContainer(cntnr)) { //Verifica se existe algum igual dentro do array

                this.containers[this.containerCounter++] = cntnr;

                return true;

            } else {
                throw new ContainerException("Container already exists in array.");
            }

        } else {
            throw new ContainerException("Containers array is full.");
        }
    }

    /**
     * <strong>canAddContainer()</strong>
     * <p>
     * This method verifys if the number of the container counter is minor then
     * the containers array lenght.</p>
     *
     * @return true if is possible to enter a new Container, false if it is not.
     */
    private boolean canAddContainer() {
        return this.containerCounter < containers.length;
    }

    /**
     * <strong>verifyContainer()</strong>
     * <p>
     * This method verifys if a given container already existes inside the
     * containers array.</p>
     *
     * @param cntnr - Container to be analyzed
     * @return true if already exists an equal container, false if it doesn't
     */
    private boolean verifyContainer(Container cntnr) {

        for (Container container : this.containers) {

            if (container.equals(cntnr)) {

                return true;

            }

        }

        return false;

    }

    /**
     * <strong>getContainer()</strong>
     *
     * @param it The item type of the container to be returned
     * @return The container that corresponds to the item type. Null if there's
     * no container with the specific item type.
     */
    @Override
    public Container getContainer(ItemType it) {

        for (Container container : this.containers) {

            if (container.getType() == it) {

                return container;

            }

        }

        return null;

    }

    /**
     * <strong>getContainers()</strong>
     * <p>
     * This function returns all te containers inside an AidBox</p>
     *
     * @return The array of containers
     */
    @Override
    public Container[] getContainers() {
        return this.containers;
    }

}
