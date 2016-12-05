import java.util.List;

/**
 * Objekte der Klasse ZENSOR zensieren unangebrachte Wörter in Zeichenketten
 * 
 * @author SFr682k and the one and only hobbyduck
 * @version 2016-12-04
 */
public class ZENSOR {
    // deklariert ein Referenzattribut für den INDEX
    INDEX schindlersListe;

    public ZENSOR() {
        // Erstellt Referenz auf den INDEX
        schindlersListe = new INDEX();
    }

    // Methode, die eine Eingabezeichenkette bereinigt zurückgibt
    public String zensiereText(String input) {
        String output = "";
        int inputLength = input.length();
        String zensurPruefen;
        
        // Deklarieren, Initialisieren und Vorbelegen des Zensurarrays
        boolean[] stelleZensieren;
        stelleZensieren = new boolean[inputLength];
        for(int i = 0; i < inputLength; i++) {
            stelleZensieren[i] = false;
        }
        
        /* /
        int zensiereAbZeichen = 1; // zensiereAbZeichen = 1  bewirkt:  Zensur -> Z*****
        String zensurzeichen = "*"; // Das bzw. die Zeichen, mit denen zensiert wird
        boolean zensurlueckenSchliessen = true; // wenn true: S********** statt S******w***
        
        // Lädt Zensurliste
        List<String> index = schindlersListe.getList();
        
        // Schaut, welche Zeichen zensiert werden müssen
        while(!index.isEmpty()) {
            String indiziert = index.get(0); // Holt sich das erste Element der Zensurliste
            
            for(int i = 0; i < inputLength; i++) {
                for(int j = inputLength; j > i; j--) {
                    zensurPruefen = ""; // leert Zwischenspeicher
                    
                    // schreibt die Auswahl in den Zwischenspeicher
                    for(int k = i; k < j; k++) {
                        zensurPruefen = zensurPruefen + input.charAt(k);
                    }
                    
                    // gleicht ab, ob die Auswahl indiziert ist und zensiert wenn nötig
                    if(zensurPruefen.equalsIgnoreCase(indiziert)) {
                        for(int l = i + zensiereAbZeichen; l < j; l++) {
                            stelleZensieren[l] = true;
                        }
                    }
                }
            }
                        
            index.remove(0); // schmeisst das getestete Element weg (da Liste, rutscht nach)
        }
        
        // Schließt Lücken, die bei der Zensur einer "Schimpfwortkomposita" entstehen:
        // Zensurmechanismus -> Z*****m********** -> Z****************
        int lueckengroesse = 0;
        boolean zensurluecke = false;
        if(zensurlueckenSchliessen == true) {
            for(int i = 1; i < inputLength; i++) {
                if(stelleZensieren[i] == true) {
                    // Prüft, ob vor Zeichen i eine Zensurlücke ist
                    if(lueckengroesse != zensiereAbZeichen) {
                        zensurluecke = false;
                    } else {
                        for(int j = 1; j == lueckengroesse; j++) {
                            zensurluecke = true;
                            
                            if(input.charAt(i-j) == ' ') {
                                zensurluecke = false;
                            }
                        }
                    }
                    
                    if(zensurluecke == true) {
                        for(int j = 1; j == lueckengroesse; j++) {
                            stelleZensieren[i-j] = true;
                        }
                        zensurluecke = false;
                    }
                    
                    lueckengroesse = 0;
                } else {
                    lueckengroesse = lueckengroesse + 1;
                }
            }
        }
        
        // Bereinigt die Eingabe
        for(int i = 0; i < inputLength; i++) {
            if(stelleZensieren[i] == true) {
                output = output + zensurzeichen;
            } else {
                output = output + input.charAt(i);
            }
        }
                
        return output;
    }
}