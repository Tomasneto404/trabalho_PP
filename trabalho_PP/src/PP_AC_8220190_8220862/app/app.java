/*
* Nome: Tomás Leonardo Leão Sousa Neto
* Número: 8220862
* Turma: LSIRC12T1
*
* Nome: Tânia Sofia da Silva Morais
* Número: 8220190
* Turma: LSIRC12T1
 */
package PP_AC_8220190_8220862.app;

import PP_AC_8220190_8220862.core.AidBox;
import PP_AC_8220190_8220862.core.Container;
import PP_AC_8220190_8220862.core.Institution;
import PP_AC_8220190_8220862.enums.VehicleState;
import PP_AC_8220190_8220862.io.Exporter;
import com.estg.io.HTTPProvider;
import PP_AC_8220190_8220862.io.Importer;
import PP_AC_8220190_8220862.pickingManagement.RefrigeratedVehicle;
import PP_AC_8220190_8220862.pickingManagement.Vehicle;
import com.estg.core.ItemType;
import com.estg.core.Measurement;
import com.estg.core.exceptions.InstitutionException;
import com.estg.core.exceptions.VehicleException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <strong>app</strong>
 * <p>This class represents the application itself.</p>
 */
public final class App {

    private final String AIDBOXES_FILE = "src/Files/aidboxes.json";

    private final String READINGS_FILE = "src/Files/containersReadings.json";

    private final String INST_FILE = "src/Files/instData.json";

    private HTTPProvider provider;

    private Institution institution;

    private BufferedReader reader;

    private Importer importer;

    private Exporter exporter;

    /**
     * <strong>App()</strong>
     * <p>Default constructor method's definitions.</p>
     */
    {
        this.provider = new HTTPProvider();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.importer = new Importer(AIDBOXES_FILE, READINGS_FILE);
        this.exporter = new Exporter(INST_FILE);
    }

    /**
     * <strong>start()</strong>
     * <p>Main method to run the application.</p>
     * @throws InstitutionException if the Institution was not defined;
     */
    public void start() throws InstitutionException {
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

    /**
     * <strong>MainMenu()</strong>
     * <p>This method prints in the console the main menu of the application</p>
     * @throws IOException If couldn´t read the user input.
     */
    private void MainMenu() throws IOException {
        boolean flag = true;

        while (flag == true) {

            System.out.print("""
                             <Main Menu>
                             1 - Manage Institution
                             3 - Get Reports (Não funciona)
                             4 - Save Institution Data To File
                             0 - Quit
                             > """);

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

    /**
     * <strong>institutionMenu()</strong>
     * <p>This method prints in the console the institution manage menu of the application.</p>
     * @throws IOException If couldn´t read the user input.
     */
    private void institutionMenu() throws IOException {
        boolean flag = true;

        while (flag == true) {

            System.out.print("""
                             <Institution Menu>
                             1 - Vehicles
                             2 - Aid Boxs
                             3 - Routes (Não funciona)
                             4 - Picking Maps (Não funciona)
                             0 - Quit
                             > """);

            String option = this.reader.readLine();

            switch (option) {
                case "1":
                    this.vehicleMenu();
                    break;

                case "2":
                    this.aidBoxMenu();
                    break;
                default:
                    flag = false;
            }
        }
    }

    /**
     * <strong>aidBoxMenu()</strong>
     * <p>This method prints in the console the aid box manage menu of the application.</p>
     * @throws IOException If couldn´t read the user input.
     */
    private void aidBoxMenu() throws IOException {
        boolean flag = true;

        while (flag == true) {

            System.out.print("""
                             ***Aid Box Menu***
                             1 - Show Aid Boxs
                             0 - Quit
                             > """);

            String option = this.reader.readLine();

            switch (option) {
                case "1":
                    this.showAidBoxes();
                    break;
                default:
                    flag = false;
            }
        }
    }

    /**
     * <strong>showAidBoxes()</strong>
     * <p>prints in the console the informations of each aid box inside the institution.</p>
     */
    private void showAidBoxes() {

        if (this.institution.getAidBoxes().length == 0) {
            System.out.println("No AidBoxs available.");
        }

        for (AidBox adbx : this.institution.getAidBoxes()) {

            if (adbx != null) {
                System.out.println("Code: " + adbx.getCode());
                System.out.println("Zone: " + adbx.getZone());
                System.out.println("Ref. Local: " + adbx.getRefLocal());
                System.out.println("Coordinates: ");
                System.out.println("    Latitude: " + adbx.getCoordinates().getLatitude());
                System.out.println("    Longitude: " + adbx.getCoordinates().getLongitude());
                System.out.println("Containers: ");

                for (Container cntnr : adbx.getContainers()) {

                    if (cntnr != null) {

                        System.out.println("    Code: " + cntnr.getCode());
                        System.out.println("    Capacity: " + cntnr.getCapacity());
                        System.out.println("    Type: " + cntnr.getType());
                        System.out.println("    Measurements: ");

                        for (Measurement msrmnt : cntnr.getMeasurements()) {

                            if (msrmnt != null) {
                                System.out.println("        Date: " + msrmnt.getDate());
                                System.out.println("        Value: " + msrmnt.getValue());
                                System.out.println("        ----------------");
                            }

                        }

                        System.out.println("    *********************");
                    }

                }

                System.out.println("--------------------------------\n");
            }

        }
    }

    /**
     * <strong>aidBoxMenu()</strong>
     * <p>This method prints in the console the vehicle manage menu of the application.</p>
     * @throws IOException If couldn´t read the user input.
     */
    private void vehicleMenu() throws IOException {
        boolean flag = true;

        while (flag == true) {

            System.out.print("""
                             ***Vehicle Menu***
                             1 - Add new vehicle
                             2 - Show Vehicles
                             3 - Enable Vehicle
                             4 - Disable Vehicle
                             0 - Quit
                             > """);

            String option = this.reader.readLine();

            switch (option) {
                case "1":
                    this.insertVehicle();
                    break;

                case "2":
                    this.showVehicles();
                    break;

                case "3":
                    if (this.enableVehicleOption()) {
                        System.out.println("Vehicle Enabled");
                    } else {
                        System.out.println("Vehicle not found.");
                    }

                    break;

                case "4":
                    if (this.disableVehicleOption()) {
                        System.out.println("Vehicle Disabled");
                    } else {
                        System.out.println("Vehicle not found.");
                    }
                    break;

                default:
                    flag = false;
            }
        }
    }

    private boolean enableVehicleOption() throws IOException {

        if (this.institution.getVehicles() != null) {

            System.out.println("Vehicle plate: ");
            String vhclPlate = this.reader.readLine();

            for (Vehicle vhcl : this.institution.getVehicles()) {
                if ((vhcl != null) && (vhcl.getPlate() == vhclPlate)) {
                    try {
                        this.institution.enableVehicle(vhcl);
                        return true;
                    } catch (VehicleException e) {
                        e.getMessage();
                    }
                }
            }
        }
        return false;
    }

    private boolean disableVehicleOption() throws IOException {

        if (this.institution.getVehicles() != null) {

            System.out.println("Vehicle plate: ");
            String vhclPlate = this.reader.readLine();

            for (Vehicle vhcl : this.institution.getVehicles()) {
                if ((vhcl != null) && (vhcl.getPlate() == vhclPlate)) {
                    try {
                        this.institution.disableVehicle(vhcl);
                        return true;
                    } catch (VehicleException e) {
                        e.getMessage();
                    }
                }
            }
        }
        return false;
    }

    private void showVehicles() {

        if (this.institution.getVehicles().length == 0) {
            System.out.println("No Vehicles available.");
        }

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
        App menu = new App();
        try {
            menu.start();
        } catch (InstitutionException e) {
            e.printStackTrace();
        }

    }

}
