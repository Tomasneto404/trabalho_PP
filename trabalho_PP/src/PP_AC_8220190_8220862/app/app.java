/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PP_AC_8220190_8220862.app;

import PP_AC_8220190_8220862.core.Institution;
import com.estg.io.HTTPProvider;
import PP_AC_8220190_8220862.io.Importer;
import com.estg.core.exceptions.InstitutionException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public final class app {

    private final String AIDBOXES_FILE = "src/Files/aidboxes.json";
    
    private final String READINGS_FILE = "src/Files/containersReadings.json";
    
    private HTTPProvider provider;

    private Institution institution;
    
    private BufferedReader reader;
    
    private Importer importer;

    {
        this.provider = new HTTPProvider();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.importer = new Importer();
    }

    public void start() throws FileNotFoundException, InstitutionException {
        this.saveDataFromAPI("https://data.mongodb-api.com/app/data-docuz/endpoint/aidboxes", AIDBOXES_FILE);
        this.saveDataFromAPI("https://data.mongodb-api.com/app/data-docuz/endpoint/readings", READINGS_FILE);
        
        System.out.print("Institution name: \n> ");
        try {
            
           this.institution = new Institution(reader.readLine());
           this.importer.importData(this.institution);
           
           //this.MainMenu();
           
           
        } catch(IOException e) {
            System.out.println("Invalid Input");
        }
        
        
    }
    
    private void MainMenu() throws IOException {
        boolean flag = true;

        while (flag == true) {

            System.out.print("""
                             <Main Menu>
                             1 - Manage Institution
                             2 - Manage outra coisa
                             3 - Get Reports
                             4 - Save Data To File
                             0 - Quit
                             > """);

            System.out.print("<Main Menu>\n" + "1 - Manage Institution\n" + "2 - Manage outra coisa\n" + "3 - Get Reports\n" + "0 - Quit\n" + ">");


            String option = this.reader.readLine();

            switch (option) {
                case "1":
                try {
                    institutionMenu();
                } catch (IOException e) {
                    System.out.println("Invalid input.");
                }

                break;
                case "2":
                    System.out.println("2");
                    break;
                default:
                    flag = false;
            }
        }
    }

    private void institutionMenu() throws IOException {
        boolean flag = true;

        while (flag == true) {
            System.out.print("""
                             <Institution Menu>
                             1 - Coisa
                             0 - Quit
                             > """);

            String option = this.reader.readLine();

            switch (option) {
                case "1":
                    
                    break;
                default:
                    flag = false;
            }
        }
    }

    private boolean saveDataFromAPI(String url, String filePath) {

        String data = this.provider.getFromURL(url);

        try ( FileWriter writer = new FileWriter(filePath)) {
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    
    public static void main(String[] args) {
        app menu = new app();
        try {
             menu.start();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (InstitutionException e) {
            e.printStackTrace();
        }
       

    }

}
