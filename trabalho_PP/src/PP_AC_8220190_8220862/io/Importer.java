/*
* Nome: Tomás Leonardo Leão Sousa Neto
* Número: 8220862
* Turma: LSIRC12T1
*
* Nome: Tânia Sofia da Silva Morais
* Número: 8220190
* Turma: LSIRC12T1
 */
package PP_AC_8220190_8220862.io;

import PP_AC_8220190_8220862.core.Institution;
import com.estg.core.exceptions.InstitutionException;
import PP_AC_8220190_8220862.core.AidBox;
import PP_AC_8220190_8220862.core.Container;
import PP_AC_8220190_8220862.core.Measurement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;

/**
 *
 * @author tomas
 */
public class Importer implements com.estg.io.Importer {

    private String aidBoxsFile;

    private String readingsFile;

    public Importer(String aidBoxsFile, String readingsFile) {
        this.aidBoxsFile = aidBoxsFile;
        this.readingsFile = readingsFile;
    }

    @Override
    public void importData(com.estg.core.Institution instn) throws FileNotFoundException, IOException, InstitutionException {

        Institution inst = (Institution) instn;
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

        try {
            JSONParser parser = new JSONParser();

            Reader readerAidBoxs = new FileReader(this.aidBoxsFile);
            Object aidBoxJsonObj = parser.parse(readerAidBoxs);
            JSONArray aidBoxArray = (JSONArray) aidBoxJsonObj;

            Reader readerReadings = new FileReader(readingsFile);
            Object readingsJsonObj = parser.parse(readerReadings);
            JSONArray measurementsArray = (JSONArray) readingsJsonObj;

            //Add Aidbox to Institution
            for (Object aidBoxObj : aidBoxArray) {
                JSONObject box = (JSONObject) aidBoxObj;

                String code = (String) box.get("Codigo");
                String zone = (String) box.get("Zona");
                double latitude = ((Number) box.get("Latitude")).doubleValue();
                double longitude = ((Number) box.get("Longitude")).doubleValue();

                AidBox tmpBox = new AidBox(code, zone, latitude, longitude);

                JSONArray containerArray = (JSONArray) box.get("Contentores");

                for (Object containerObj : containerArray) {
                    JSONObject container = (JSONObject) containerObj;

                    String containerCode = (String) container.get("codigo");
                    double capacity = ((Number) container.get("capacidade")).doubleValue();

                    Container tmpCntnr = new Container(containerCode, capacity);

                    //Add Measurements to institution
                    for (Object readingsObj : measurementsArray) {
                        JSONObject measurement = (JSONObject) readingsObj;

                        String msrmntContainerCode = (String) measurement.get("contentor");

                        Container tmpContainer = inst.getContainer(msrmntContainerCode);

                        LocalDate date = LocalDate.parse(measurement.get("data").toString(), formatter);
                        long value = (long) measurement.get("valor");

                        Measurement tmpMsrmnt = new Measurement(value, date);
                        if (tmpContainer != null) {
                            inst.addMeasurement(tmpMsrmnt, tmpContainer);
                        } 

                    }

                    tmpBox.addContainer(tmpCntnr);
                }
                inst.addAidBox(tmpBox);
            }

            //System.out.println("Importado!!!!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
