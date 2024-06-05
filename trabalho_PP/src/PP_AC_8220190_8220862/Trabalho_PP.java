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
import PP_AC_8220190_8220862.core.Measurement;
import com.estg.core.ItemType;
import com.estg.core.exceptions.ContainerException;
import com.estg.core.exceptions.MeasurementException;
import com.estg.io.HTTPProvider;
import java.io.IOException;
import menus.Menu;

public class Trabalho_PP {

    public static void main(String[] args) throws ContainerException, IOException, MeasurementException {
        HTTPProvider provider = new HTTPProvider();

        AidBox box = new AidBox("123", "lixa");


        try {

            Container container = new Container("3232", 323, ItemType.CLOTHING);

            Container container2 = new Container("3232", 323, ItemType.MEDICINE);
            // Create some measurements
            Measurement m1 = new Measurement(100);
            Measurement m2 = new Measurement(200);

            // Add measurements
            System.out.println(container.addMeasurement(m1));
            System.out.println(container.getMeasurements());

            System.out.println(container.updateMeasurement(m1));;

        } catch (MeasurementException e) {
            e.printStackTrace();
        }

        //System.out.println(box.addContainer(container));
        //System.out.println(box.getContainers());
        String string = provider.getFromURL("https://data.mongodb-api.com/app/data-docuz/endpoint/distances?from=CAIXF37&to=CAIXF44");
        System.out.println(string);
        
       

    }

}
