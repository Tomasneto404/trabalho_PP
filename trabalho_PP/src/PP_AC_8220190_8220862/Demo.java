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

import PP_AC_8220190_8220862.core.AidBoxImp;
import PP_AC_8220190_8220862.core.ContainerImp;
import com.estg.core.exceptions.ContainerException;
import com.estg.io.HTTPProvider;
import java.io.IOException;
import com.estg.core.exceptions.AidBoxException;


public class Demo {    
    
    public static void main(String[] args) throws ContainerException, IOException, AidBoxException{
        HTTPProvider provider = new HTTPProvider();
        
        AidBoxImp box = new AidBoxImp("CAIXF44", "coisa");
        AidBoxImp box1 = new AidBoxImp("CAIXF37", "coisa");
    
        ContainerImp container = new ContainerImp("3232", 323);
        
        ContainerImp container1 = new ContainerImp("3232", 323);
        
     
         System.out.println(provider.getFromURL("https://data.mongodb-api.com/app/data-docuz/endpoint/readings"));
         
         //System.out.println(box.getZone());
         //System.out.println(box.getCoordinates().getLatitude());
         //System.out.println(box.getCoordinates().getLongitude());
         //System.out.println(box.getDistance(box1));

    }
    
}
