/*
* Nome: Tomás Leonardo Leão Sousa Neto
* Número: 8220862
* Turma: LSIRC12T1
*
* Nome: Tânia Sofia da Silva Morais
* Número: 8220190
* Turma: LSIRC12T1
 */
package PP_AC_8220190_8220862;

import PP_AC_8220190_8220862.core.AidBox;
import PP_AC_8220190_8220862.core.Container;
import com.estg.core.exceptions.ContainerException;
import com.estg.io.HTTPProvider;
import java.io.IOException;
import com.estg.core.exceptions.AidBoxException;


public class Demo {    
    
    public static void main(String[] args) throws ContainerException, IOException, AidBoxException{
        HTTPProvider provider = new HTTPProvider();
        
        AidBox box = new AidBox("CAIXF44", "coisa");
        AidBox box1 = new AidBox("CAIXF37", "coisa");
    
        Container container = new Container("3232", 323);
        
        Container container1 = new Container("3232", 323);
        
     
         System.out.println(provider.getFromURL("https://data.mongodb-api.com/app/data-docuz/endpoint/readings"));
         
         //System.out.println(box.getZone());
         //System.out.println(box.getCoordinates().getLatitude());
         //System.out.println(box.getCoordinates().getLongitude());
         //System.out.println(box.getDistance(box1));

    }
    
}
