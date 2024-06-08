/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PP_AC_8220190_8220862.menus;

import PP_AC_8220190_8220862.core.Institution;
import com.estg.io.HTTPProvider;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public final class Menu {

    private HTTPProvider provider;

    private Institution institution;
    
    private BufferedReader reader;

    {
        this.provider = new HTTPProvider();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void start() {
        this.saveDataFromAPI("https://data.mongodb-api.com/app/data-docuz/endpoint/aidboxes", "src/Files/aidboxes.json");
        this.saveDataFromAPI("https://data.mongodb-api.com/app/data-docuz/endpoint/readings", "src/Files/containersReadings.json");
        
        System.out.print("Institution name: \n> ");
        try {
            
           this.institution = new Institution(reader.readLine());
           
           
           
        } catch(IOException e) {
            System.out.println("Invalid Input");
        }
        
        
    }
    
    private void MainMenu() throws IOException {

        

        boolean flag = true;

        while (flag == true) {
            System.out.println("<Main Menu>\n" + "1 - Manage Institution\n" + "2 - Manage outra coisa\n" + "\n" + "0 - Quit\n");

            System.out.print("> ");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String option = reader.readLine();

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
            System.out.println("<Institution Menu>\n" + "1 - Manage vehicles\n" + "2 - Manage Aid Box\n" + "3 - Manage Picking Maps\n" + "4 -\n" + "\n" + "0 - Quit\n");

            System.out.print("> ");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String option = reader.readLine();

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
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.start();

    }

}
