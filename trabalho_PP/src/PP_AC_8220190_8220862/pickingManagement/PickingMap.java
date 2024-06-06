/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PP_AC_8220190_8220862.pickingManagement;

import com.estg.pickingManagement.Route;
import java.time.LocalDateTime;

/**
 *
 * @author tomas
 */
public class PickingMap implements com.estg.pickingManagement.PickingMap {

    private LocalDateTime date;
    
    private Route[] routes;
    
    public PickingMap() {
        this.date = LocalDateTime.now();
    }
    
    @Override
    public LocalDateTime getDate() {
        return this.date;
    }

    @Override
    public Route[] getRoutes() {
        return this.routes;
    }
    
}
