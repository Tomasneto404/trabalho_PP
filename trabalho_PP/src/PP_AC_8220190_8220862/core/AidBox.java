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

import com.estg.core.ItemType;
import com.estg.core.exceptions.AidBoxException;
import com.estg.core.exceptions.ContainerException;
import com.estg.io.HTTPProvider;

import java.io.StringReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <strong>AidBox()</strong>
 *
 */
public class AidBox implements com.estg.core.AidBox {

    private final int MAX_CONTAINERS = 4;

    private String code;

    private String zone;

    private String refLocal;

    private GeographicCoordinates coordinates;

    private Container[] containers;

    private int containerCounter;

    private HTTPProvider provider;

    private double totalCapacity;

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
        this.totalCapacity = 0.0;
    }

    /**
     * <strong>AidBox()
     * <p>
     * AidBox constructor method.</p>
     *
     * @param code String value that represents the code of an AidBox.
     * @param zone String value that represents the zone where the AiBox is.
     */
    public AidBox(String code, String refLocal) throws AidBoxException {
        this.code = code;
        this.refLocal = refLocal;

        String aidBoxString = provider.getFromURL("https://data.mongodb-api.com/app/data-docuz/endpoint/aidboxesbyid?codigo=" + this.code);

        JSONParser parser = new JSONParser();

        try {
            StringReader reader = new StringReader(aidBoxString);

            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            this.coordinates = new GeographicCoordinates((double) jsonObject.get("Latitude"), (double) jsonObject.get("Longitude"));
            this.zone = (String) jsonObject.get("Zona");

            JSONArray containersArray = (JSONArray) jsonObject.get("Contentores");

            /*
            for (Object obj : containersArray) {
                
                JSONObject contentor = (JSONObject) obj;
                String codigo = (String) contentor.get("codigo");
                double capacidade = (double) contentor.get("capacidade");
                
                try {
                    addContainer(new Container(codigo, capacidade));
                } catch (ContainerException e) {
                    e.getMessage();
                }
                
            }*/
        } catch (Exception e) {
            throw new AidBoxException("Coulnd´t get data from this aidBox code.");
        }

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
     * @return Distance in meters from one aid box to another
     * @throws AidBoxException
     */
    @Override
    public double getDistance(com.estg.core.AidBox aidbox) throws AidBoxException {

        try {

            String jsonString = this.provider.getFromURL("https://data.mongodb-api.com/app/data-docuz/endpoint/distances?from=" + this.code + "&to=" + aidbox.getCode());

            JSONParser parser = new JSONParser();

            StringReader reader = new StringReader(jsonString);

            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            JSONArray toArray = (JSONArray) jsonObject.get("to");

            JSONObject firstObject = (JSONObject) toArray.get(0);

            Object distanceObj = firstObject.get("distance");

            double distance;

            if (distanceObj instanceof Long) {
                distance = ((Long) distanceObj).doubleValue();
            } else if (distanceObj instanceof Double) {
                distance = (Double) distanceObj;
            } else {
                throw new ParseException(ParseException.ERROR_UNEXPECTED_TOKEN, "Unexpected type for distance");
            }

            return distance;

        } catch (Exception e) {
            throw new AidBoxException("Couldn't get data from API.");
        }

    }

    /**
     * <strong>getDuration()</strong>
     * <p>
     * This method makes the request to the WEB API and returns the duration
     * from one aid box to another</p>
     *
     * @param aidbox
     * @return Duration in seconds from one aid box to another
     * @throws AidBoxException
     */
    @Override
    public double getDuration(com.estg.core.AidBox aidbox) throws AidBoxException {

        try {

            String jsonString = this.provider.getFromURL("https://data.mongodb-api.com/app/data-docuz/endpoint/distances?from=" + this.code + "&to=" + aidbox.getCode());

            JSONParser parser = new JSONParser();

            StringReader reader = new StringReader(jsonString);

            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            JSONArray toArray = (JSONArray) jsonObject.get("to");

            JSONObject firstObject = (JSONObject) toArray.get(0);

            Object durationObj = firstObject.get("duration");

            double duration;

            if (durationObj instanceof Long) {
                duration = ((Long) durationObj).doubleValue();
            } else if (durationObj instanceof Double) {
                duration = (Double) durationObj;
            } else {
                throw new ParseException(ParseException.ERROR_UNEXPECTED_TOKEN, "Unexpected type for duration");
            }

            return duration;

        } catch (Exception e) {
            throw new AidBoxException("Couldn't get data from API.");
        }
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
    public boolean addContainer(com.estg.core.Container cntnr) throws ContainerException {

        if (!canAddContainerToArray()) { //Verifica se cabe dentro do array

            throw new ContainerException("Containers array is full.");
        }

        if (verifyContainer((Container) cntnr)) { //Verifica se existe algum igual dentro do array

            throw new ContainerException("Container already exists in array.");

        }

        if (verifyContainerType((Container) cntnr)) { //Verifica se existe algum igual dentro do array

            throw new ContainerException("Container type already exists in container array.");

        }

        this.containers[this.containerCounter++] = (Container) cntnr;

        return true;
    }

    /**
     * <strong>canAddContainer()</strong>
     * <p>
     * This method verifys if the number of the container counter is minor then
     * the containers array lenght.</p>
     *
     * @return true if is possible to enter a new Container, false if it is not.
     */
    private boolean canAddContainerToArray() {
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
     * <strong>verifyContainerType()</strong>
     * <p>
     * This methods verifys if there is a container with the same type as the
     * one to be inserted. </p>
     *
     * @param cntnr
     * @return True if there is a Container with the same type as the one to be
     * inserted. False if it doesn't.
     */
    private boolean verifyContainerType(Container cntnr) {

        for (Container container : this.containers) {

            if (container.getType() == cntnr.getType()) {

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

    @Override
    public Object clone() throws CloneNotSupportedException {
        AidBox cloned = (AidBox) super.clone();
        cloned.containers = containers.clone();
        for (int i = 0; i < containers.length; i++) {
            cloned.containers[i] = (Container) containers[i].clone();
        }
        return cloned;
    }

    public double getTotalCapacity() {

        for (int i = 0; i < this.containerCounter; i++) {
            this.totalCapacity += this.containers[i].getCapacity();
        }

        return this.totalCapacity;
    }
}
