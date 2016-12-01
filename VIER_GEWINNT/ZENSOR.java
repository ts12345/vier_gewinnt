/**
 * Objekte der Klasse ZENSOR zensieren unangebrachte Wörter in Zeichenketten
 * 
 * @author SFr682k
 * @version 2016-12-01
 */
public class ZENSOR {
    // deklariert ein Referenzattribut für den INDEX
    INDEX schindlersListe;

    // Konstruktor
    public ZENSOR() {
        // Erstellt Referenz auf den INDEX
        schindlersListe = new INDEX();
    }

    // Methode, die eine Eingabezeichenkette bereinigt zurückgibt
    public String zensiereText(String input) {
        // Deklariert eine Variable für den Rückgabetext:
        String output = "";
        
        // Deklariert ein Array, das sagt, welche Zeichen ersetzt werden müssen
        boolean[] stelleZensieren;
        
        // Ermittelt die Länge des eingegeben Textes
        // NOTIZ: Zählung beginnt bei 1
        int inputLength = input.length();
        
        // Initialisiert das Zensurarray
        stelleZensieren = new boolean[inputLength];
        
        // Standardmäßig werden alle Zeichen nicht zensiert
        // Entsprechende Vorbelegung des Zensurarrays
        for(int i = 0; i < inputLength; i++) {
            stelleZensieren[i] = false;
        }
        
        // Die folgende Zensurpozedur muss noch (üb-)erarbeitet werden:
            // Durchforstet mit Hilfe von gezählten Wiederholungen die Zeichenkette
            for(int i = 0; i < inputLength; i++) {
                for (int j = inputLength; j > i; j--) {
                    // hier muss ich noch Code hinzufügen ...
                }
            }
        
            // deklariert das Zensurzeichen:
            String zensurzeichen = "*";
            
            // geht den Eingabetext Zeichen für Zeichen durch
            // Dabei werden zu zensierende Zeichen durch das Zensurzeichen ersetzt
            for(int i = 0; i < inputLength; i++) {
                if(stelleZensieren[i] == true) {
                    output = output + zensurzeichen;
                } else {
                    output = output + input.charAt(i);
                }
            }
            
        // Da obige Zensurprozedur noch nicht funktioniert, Rückgabe des Eingabewerts
        output = input;
        
        // Gibt die bereinigte Version der Eingabe zurück
        return output;
    }
}