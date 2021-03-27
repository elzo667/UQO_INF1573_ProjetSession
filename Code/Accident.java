/* Nom: Accident
 * Version: 1.0
 * Date: 03/26/2021
 * Auteur: Marc-Antoine Ricard
 * Copyright 2021 Équipe 4
 * */
package Code;

import java.util.Random;

/* Cette classe permet de definir si un accident a lieu sur une route */
public class Accident {
    
    private static int probabilite = 5; //Probabilite d'avoir un accident en %
    
    /* Permet de générer si un accident est causé
     * 
     * @return  un booléan si un accident a lieu
     * */
    public static boolean causeAccident() {
        Random random = new Random();
        
        if(random.nextInt( 100 ) < probabilite) {
            return true;
        }
        return false;
    }
    
}
