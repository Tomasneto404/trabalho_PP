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

/**
 * <strong>GeographicCoordinates</strong>
 * <p>This class represents coordinates trough latitude and longitude values</p>
 * 
 */
public class GeographicCoordinates implements com.estg.core.GeographicCoordinates {

    private double latitude;
    private double longitude;

    /**
     * <strong>GeographicCoordinates()</strong>
     * <p>This is the Constructor method to instance the object.</p>
     * @param latitude double value that represents the latitude of the coordinates
     * @param longitude double value that represents the longitude of the coordinates 
     */
    public GeographicCoordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * <strong>getLatitude()</strong>
     * @return The latitude value.
     */
    @Override
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * <strong>getLongitude()</strong>
     * @return The longitude value.
     */
    @Override
    public double getLongitude() {
        return this.latitude;
    }

    /**
     * <strong>getCoordinates()</strong>
     * @return An array with latitude and longitude.
     */
    public double[] getLatitudeAndLongitude() {
        return new double[]{this.latitude, this.longitude};
    }

}
