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
import PP_AC_8220190_8220862.core.GeographicCoordinates;
import PP_AC_8220190_8220862.core.Container;
import com.estg.core.ItemType;
import com.estg.core.exceptions.ContainerException;
import com.estg.io.HTTPProvider;
import java.io.IOException;
import PP_AC_8220190_8220862.menus.Menu;


public class Demo {    
    
    public static void main(String[] args) throws ContainerException, IOException{
        HTTPProvider provider = new HTTPProvider();
        
        AidBox box = new AidBox("123", "lixa");
    
        Container container = new Container("3232", 323, ItemType.CLOTHING);
        
        Container container2=new Container("3232", 323, ItemType.MEDICINE);
        
     

        //System.out.println(box.addContainer(container));
        //System.out.println(box.getContainers()); 
        //String string = provider.getFromURL("https://data.mongodb-api.com/app/data-docuz/endpoint/aidboxesbyid?codigo=CAIXF37");
        
        //System.out.println(string);
        
        Menu menu = new Menu();
        menu.MainMenu();
     

        //String string = provider.getFromURL("https://data.mongodb-api.com/app/data-docuz/endpoint/distances?from=CAIXF37&to=CAIXF44");
        //System.out.println(string);

    }
    
}
