/**
 * Objekte der Klasse INDEX teilen dem ZENSOR mit, welche Phrasen zensiert werden
 * Die INDEX-Klasse befindet sich in Bearbeitung
 * 
 * @author SFr682k 
 * @version 2016-12-01
 */
public class INDEX {
    // deklariert Variable für die Größe des Arrays
    private int size;
    
    // deklariert ein Array für den Index
    String[] index;

    // Konstruktor
    public INDEX() {
        // HIER manuell die Anzahl der indizierten Wörter einfügen
        // WICHTIG:   Letztes Wort = Position in Array + 1   !!!!
        size = 3;
                
        // Initialisiert das Array für die indizierten Wörter
        index = new String[size];
        
        // Lädt die auf dem Index vorhandenen Wörter
        loadIndex();
    }

    // Methode, die zu zensierende Wörter auf den Index setzt
    public void loadIndex() {
        // Schreibt die Liste der zu zensierenden Wörter in den Index
        // Bitte bei "0" beginnen!!
        index[0] = "Wort 1";
        index[1] = "Wort 2";
        index[2] = "Wort 3";
    }
    
    // Methode, die die Größe des Index zurückgibt
    public int getIndexSize() {
        return size;
    }
    
    // Methode, die das Wort aus Stelle x des Index zurückgibt
    public String getIndexValue(int position) {
        // Deklariert einen Zwischenspeicher
        String wort;
        
        // Prüft die Eingabe; gibt bei fehlerhaften Werten das erste/letzte Wort zurück
        if (position < 0) {
            position = 0;
        } else if (position >= size) {
            position = size - 1;
        }
        
        // Lädt das Wort an Position x in den Zwischenspeicher
        wort = index[position];
        
        // Gibt den Inhalt des Zwischenspeichers zurück
        return wort;
    }
}