
/**
 * Objekte der Klasse INDEX teilen dem ZENSOR mit, welche Phrasen zensiert werden
 * Die INDEX-Klasse befindet sich in Bearbeitung
 * 
 * @author SFr682k 
 * @version 2016-12-01
 */
public class INDEX {
    // deklariert Variablen für die Größe des Arrays
    private int width = 15;  // 15 Felder in x-Richtung, nicht ändern!
    private int length;      //  y Felder in y-Richtung
    
    // deklariert Variablen für die Anzahl der Wörter im Array
    private int size;
    
    // deklariert ein Array für den Index
    String[][] index;

    // Konstruktor
    public INDEX() {
        // HIER manuell die Anzahl der indizierten Wörter einfügen
        size = 1;
        
        // Hier wird demnächst die benötigte Anzahl der Felder in y-Richtung berechnet
        length = 1;
        
        // Initialisiert das Array für die indizierten Wörter
        index = new String[width][length];
        
        // Weiterer Quellcode folgt
    }

    // Methode, die zu zensierende Wörter auf den Index setzt
    public void getIndex() {
        // @hobbyduck, eine Vorbereitung für Ihre Liste kommt noch
    }
}
