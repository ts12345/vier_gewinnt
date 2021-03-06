import java.util.List;
import java.util.LinkedList;
import java.io.*;

/**
 * Objekte der Klasse INDEX teilen dem ZENSOR mit, welche Phrasen zensiert werden
 * 
 * @author SFr682k and the one and only hobbyduck
 * @version 2016-12-07
 */
public class INDEX {
    // deklariert Variable für die Größe des Arrays
    private List<String> index;

    // Konstruktor
    public INDEX() {
        index=new LinkedList<String>();
        loadIndex();
    }

    List<String> getList() {
        return new LinkedList(index);
    }

    // Methode, die zu zensierende Wörter auf den Index setzt
    public void loadIndex() {
        try {
            FileReader fr = new FileReader("INDEX/INDEX.txt");
            BufferedReader br = new BufferedReader(fr);
            String zeile;
            
            do {
                zeile = br.readLine();
                index.add(0,zeile);
            } while(!zeile.startsWith("#END#"));
            
            index.remove(0);
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }
}