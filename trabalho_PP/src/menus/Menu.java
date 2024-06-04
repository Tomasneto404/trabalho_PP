/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Menu {
   
    
    
    public void InstitutionMenu() throws IOException{
        System.out.println("""
                  <Institution Menu>
                  1 - Manage Coisa
                  2 - Manage outra coisa
                           """);
        
        System.out.print("> ");
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        String option = reader.readLine();
        
        switch (option) {
            case "1": System.out.println("1");
            break;
            case "2": System.out.println("2");
            break;
            default: System.out.println("Essa merda não dá");
        } 

        
        
    }
    
}
