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

import PP_AC_8220190_8220862.core.AidBox;
import PP_AC_8220190_8220862.core.Container;
import PP_AC_8220190_8220862.core.Institution;
import PP_AC_8220190_8220862.pickingManagement.Report;
import PP_AC_8220190_8220862.pickingManagement.Vehicle;
import com.estg.core.Measurement;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Exporter {

    private String exportFile;

    public Exporter(String exportFile) {
        this.exportFile = exportFile;
    }

    /**
     * <strong>exportData()</strong>
     * <p>This methods exports all the Institution and the Report data to a file.</p>
     * @param inst Institution to export the data.
     * @param reporter The Report object to export the data.
     */
    public void exportData(Institution inst, Report reporter) {

        JSONObject instObject = new JSONObject();
        instObject.put("Name", inst.getName());
        
        JSONObject reporterObj = new JSONObject();
        reporterObj.put("Date", reporter.getDate().toString());
        reporterObj.put("UsedVehicles", reporter.getUsedVehicles());
        reporterObj.put("UnusedVehicles", reporter.getNotUsedVehicles());
        reporterObj.put("TotalDistance", reporter.getTotalDistance());
        reporterObj.put("TotalDuration", reporter.getTotalDuration());
        
        instObject.put("Report", reporterObj);

        JSONArray aidBoxArray = new JSONArray(); //Array de AidBoxs

        for (AidBox box : inst.getAidBoxes()) {
            if (box == null) {
                continue;
            }

            JSONObject aidBoxObj = new JSONObject();
            aidBoxObj.put("Code", box.getCode());
            aidBoxObj.put("Zone", box.getZone());
            aidBoxObj.put("Latitude", box.getCoordinates().getLatitude());
            aidBoxObj.put("Longitude", box.getCoordinates().getLongitude());

            JSONArray containersArray = new JSONArray(); //Array de Containers

            for (Container cntnr : box.getContainers()) {

                if (cntnr == null) {
                    continue;
                }

                JSONObject containerObj = new JSONObject();
                containerObj.put("Code", cntnr.getCode());
                containerObj.put("Capacity", cntnr.getCapacity());
                containerObj.put("Type", cntnr.getType());

                JSONArray measurementsArray = new JSONArray(); //Array de Measurements

                for (Measurement msrmnt : cntnr.getMeasurements()) {

                    if (msrmnt == null) {
                        continue;
                    }

                    JSONObject msrmntObj = new JSONObject();
                    msrmntObj.put("Date", msrmnt.getDate());
                    msrmntObj.put("Value", msrmnt.getValue());

                    measurementsArray.add(msrmntObj);
                }

                containerObj.put("Measurements", measurementsArray);

                containersArray.add(containerObj);
            }
            aidBoxObj.put("Containers", containersArray);

            aidBoxArray.add(aidBoxObj);

        }

        instObject.put("AidBoxs", aidBoxArray); //Adiciona Array de AidBox ao JSONObject principal

        JSONArray vhclArray = new JSONArray(); //Array de Vehicles

        for (Vehicle vhcl : inst.getVehicles()) {

            if (vhcl == null) {
                continue;
            }

            JSONObject vhclObj = new JSONObject();
            vhclObj.put("Plate", vhcl.getPlate());
            vhclObj.put("Type", vhcl.getSupplyType().toString());
            vhclObj.put("Capacity", vhcl.getMaxCapacity());
            vhclObj.put("State", vhcl.getState().toString());

            vhclArray.add(vhclObj);
        }

        instObject.put("Vehicles", vhclArray); //Adiciona Array de Vehicles ao JSONObject principal


        try ( FileWriter file = new FileWriter(this.exportFile)) {
            file.write(instObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
