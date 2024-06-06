/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pickingManagement;

import com.estg.pickingManagement.Route;
import java.time.LocalDateTime;

/**
 *
 * @author tomas
 */
public class PickingMap implements com.estg.pickingManagement.PickingMap {

    private LocalDateTime date;
    
    @Override
    public LocalDateTime getDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Route[] getRoutes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
