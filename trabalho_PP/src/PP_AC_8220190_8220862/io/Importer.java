/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PP_AC_8220190_8220862.io;

import com.estg.core.Institution;
import com.estg.core.exceptions.InstitutionException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;

/**
 *
 * @author tomas
 */
public class Importer implements com.estg.io.Importer {

    private final String AIDBOXES_FILE = "src/Files/aidboxes.json";

    private final String READINGS_FILE = "src/Files/containersReadings.json";

    @Override
    public void importData(Institution instn) throws FileNotFoundException, IOException, InstitutionException {

        try {
            
            JSONParser parser = new JSONParser();
            
            Reader readerAidBoxs = new FileReader(AIDBOXES_FILE);
            Reader readerReadings = new FileReader(READINGS_FILE);
            
            Object aidBoxJsonObj = parser.parse(readerAidBoxs);
            Object readingsJsonObj = parser.parse(readerAidBoxs);
            
            JSONObject aidBoxJsonObject = (JSONObject) aidBoxJsonObj;
            JSONObject readingJsonObject = (JSONObject) readingsJsonObj;
            
            
            
        } catch (IOException e) {

        } catch (Exception e) {
            
        }

    }

}
