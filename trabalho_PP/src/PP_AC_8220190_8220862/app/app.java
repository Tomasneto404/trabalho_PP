/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PP_AC_8220190_8220862.app;

import PP_AC_8220190_8220862.core.Institution;
import PP_AC_8220190_8220862.io.Exporter;
import com.estg.io.HTTPProvider;
import PP_AC_8220190_8220862.io.Importer;
import PP_AC_8220190_8220862.pickingManagement.RefrigeratedVehicle;
import PP_AC_8220190_8220862.pickingManagement.Vehicle;
import com.estg.core.ItemType;
import com.estg.core.exceptions.InstitutionException;
import com.estg.core.exceptions.VehicleException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public final class app {

    private final String AIDBOXES_FILE = "src/Files/aidboxes.json";

    private final String READINGS_FILE = "src/Files/containersReadings.json";

    private final String INST_FILE = "src/Files/instData.json";

    private HTTPProvider provider;

    private Institution institution;

    private BufferedReader reader;

    private Importer importer;

    private Exporter exporter;

    {
        this.provider = new HTTPProvider();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.importer = new Importer(AIDBOXES_FILE, READINGS_FILE);
        this.exporter = new Exporter(INST_FILE);
    }

    public void start() throws FileNotFoundException, InstitutionException {
        this.saveDataFromAPI("https://data.mongodb-api.com/app/data-docuz/endpoint/aidboxes", AIDBOXES_FILE);
        this.saveDataFromAPI("https://data.mongodb-api.com/app/data-docuz/endpoint/readings", READINGS_FILE);

        System.out.print("Institution name: \n> ");
        try {

            this.institution = new Institution(reader.readLine());
            this.importer.importData(this.institution);

            this.MainMenu();

        } catch (IOException e) {
            System.out.println("Invalid Input");
        }

    }

    private void MainMenu() throws IOException {
        boolean flag = true;

        while (flag == true) {

            System.out.print("<Main Menu>\n" + "1 - Manage Institution\n" + "2 - Manage Aid Boxs\n" + "3 - Get Reports\n" + "4 - Save Institution Data To File\n" + "0 - Quit\n" + ">");

            String option = this.reader.readLine();

            switch (option) {
                case "1":
                    this.institutionMenu();
                    break;

                case "2":
                    System.out.println("2");
                    break;

                case "4":
                    this.exporter.exportData(this.institution);
                    break;

                default:
                    flag = false;
            }
        }
    }

    private void institutionMenu() throws IOException {
        boolean flag = true;

        while (flag == true) {
            System.out.print("<Institution Menu>\n" + "1 - Vehicles\n" + "2 - Aid Boxs\n" + "3 - Routes\n" + "4 - Picking Maps\n" + "0 - Quit\n" + ">");

            String option = this.reader.readLine();

            switch (option) {
                case "1":
                    this.vehicleMenu();
                    break;
                default:
                    flag = false;
            }
        }
    }

    private void vehicleMenu() throws IOException {
        boolean flag = true;

        while (flag == true) {
            System.out.print("***Vehicle Menu***\n" + "1 - Add new vehicle\n" + "2 - Show Vehicles\n" + "3 - Delete Vehicle\n" + "4 - Enable Vehicle\n" + "5 - Disable Vehicle\n" + "0 - Quit\n" + ">");

            String option = this.reader.readLine();

            switch (option) {
                case "1":
                    this.insertVehicle();
                    break;

                case "2":
                    this.showVehicles();
                    break;
                
                case "3":
                    this.removeVehicle();
                    break;

                default:
                    flag = false;
            }
        }
    }

    private void updateVehicle(){
        
    }
    
    private void removeVehicle(){
        if (this.institution.getVehicles() != null) {
            
        }
    }
    
    private void showVehicles() {

        for (Vehicle vhcl : this.institution.getVehicles()) {

            if (vhcl != null) {
                System.out.println("Plate: " + vhcl.getPlate());
                System.out.println("Supply type: " + vhcl.getSupplyType());
                System.out.println("Max Capacity: " + vhcl.getMaxCapacity());
                System.out.println("State: " + vhcl.getState());
                System.out.println("--------------------------------\n");
            }

        }
    }

    private void insertVehicle() throws IOException {
        System.out.print("<Plate>\n> ");
        String plate = this.reader.readLine();

        System.out.print("<Max Capacity>\n> ");
        int capacity = Integer.parseInt(reader.readLine());

        System.out.print("<Type>:\n" + "1 - PERISHABLE_FOOD\n" + "2 - NON_PERISHABLE_FOOD\n" + "3 - CLOTHING\n" + "4 - MEDICINE\n" + "\n" + ">");
        String typeOption = this.reader.readLine();

        ItemType type;
        switch (typeOption) {
            case "1":
                type = ItemType.PERISHABLE_FOOD;
                break;

            case "2":
                type = ItemType.NON_PERISHABLE_FOOD;
                break;

            case "3":
                type = ItemType.CLOTHING;
                break;

            case "4":
                type = ItemType.MEDICINE;
                break;

            default:
                type = null;
                break;
        }

        try {            
            this.institution.addVehicle(new Vehicle(plate, (double) capacity, type));
        } catch (VehicleException e) {
            e.getMessage();
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InstitutionException e) {
            e.printStackTrace();
        }

    }

}
