/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class Menu {

    public void MainMenu() throws IOException {

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

}
