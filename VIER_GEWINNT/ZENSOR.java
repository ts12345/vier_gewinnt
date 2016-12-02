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
        
        // Legt fest, wie viele Zeichen einer anstößigen Phrase stehen bleiben
        // zensiereAbZeichen = 1  bewirkt:   Zensur  -> Z*****
        int zensiereAbZeichen = 1;
        
        // Legt einen Zwischenspeicher für die Zensurprozedur an:
        String zensurPruefen;
        
        // holt sich die Größe des Index:
        int indexSize = schindlersListe.getIndexSize();
                
        // Durchforstet mit Hilfe von gezählten Wiederholungen die Zeichenkette
        // i: Anfang der Auswahl, j: Ende der Auswahl
        for(int i = 0; i < inputLength; i++) {
            for(int j = inputLength; j > i; j--) {
                // leert Zwischenspeicher
                zensurPruefen = "";
                
                // schreibt die Auswahl in den Zwischenspeicher
                for(int k = i; k < j; k++) {
                    zensurPruefen = zensurPruefen + input.charAt(k);
                }
                
                // gleicht ab, ob die Auswahl indiziert ist und zensiert wenn nötig
                for(int k = 0; k < indexSize; k++) {
                    if(zensurPruefen.equalsIgnoreCase(schindlersListe.getIndexValue(k))) {
                        for(int l = i + zensiereAbZeichen; l < j; l++) {
                            stelleZensieren[l] = true;
                        }
                    }
                }
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
        
        // Gibt die bereinigte Version der Eingabe zurück
        return output;
    }
}