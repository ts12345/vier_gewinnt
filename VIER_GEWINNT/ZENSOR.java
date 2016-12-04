import java.util.List;
/**
 * Objekte der Klasse ZENSOR zensieren unangebrachte Wörter in Zeichenketten
 * 
 * @author SFr682k and the one and only hobbyduck
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
        // Legt fest, wie viele Zeichen einer anstößigen Phrase stehen bleiben
        // zensiereAbZeichen = 1  bewirkt:   Zensur  -> Z*****
        int zensiereAbZeichen = 1;

        // deklariert das Zensurzeichen:
        String zensurzeichen = "*";
        List<String> test = schindlersListe.getList();
        while(!test.isEmpty()){
            String totest = test.get(0); //gibt des erste Element der Zensurliste
            String replacement=zenword(totest, zensiereAbZeichen, zensurzeichen); //gibt die zensierte version des zu zensierenden worts aus
            //input=input.replaceAll("(?i)affe","Arsch");
            input=input.replaceAll("(?i)"+totest,replacement);//ersetzt alle instanzen des zu zensierenden wortes
            test.remove(0); // schmeisst das getestete Element weg (da Liste, rutscht nach)
        }
        return input;
    }

    private String zenword(String input, int ab, String ersetzen){
        String output="";
        for (int i = 0; i<ab; i++){
            output += input.charAt(i);
        }
        for (int i = ab; i<input.length(); i++){
            output += ersetzen;
        }
        return output;
    }
}