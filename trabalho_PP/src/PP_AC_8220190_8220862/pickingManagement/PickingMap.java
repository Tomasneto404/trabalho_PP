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

import com.estg.pickingManagement.Route;
import java.time.LocalDateTime;

/**
 * <strong> PickingMap </strong>
 * <p>
 * This class identifies a picking map </p>
 */
public class PickingMap implements com.estg.pickingManagement.PickingMap {

    private final int MAX_ROUTES = 20;

    private LocalDateTime date;

    private Route[] routes;

    /**
     * <strong> PickingMap() </strong>
     * <p>
     * PickingMap constructor method </p>
     */
    public PickingMap() {
        this.date = LocalDateTime.now();
        this.routes = new Route[MAX_ROUTES];
    }

    /**
     * <strong> getDate() </strong>
     * <p> obtains a date of a picking map </p>
     * @return the date of a picking map
     */
    @Override
    public LocalDateTime getDate() {
        return this.date;
    }

    /**
     * <strong> getRoutes() </strong>
     * <p> obtains the route values </p>
     * @return the route values of a picking map
     */
    @Override
    public Route[] getRoutes() {
        return this.routes;
    }

}
