import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Die Klasse SOUNDENGINE spielt Sounds ab.
 * @author (kinder)
 * @version (08.06.16)
 * @since (21.04.16)
 * 
 */
public class SOUNDENGINE {
    // Töne für während des Spiels
    File click, illegal;
    
    // Töne für Spielende
    File fanfare, drawer, lost;
    
    // Hauptthema
    File main;
    
    public SOUNDENGINE() {
        File click = new File("sounds/click.wav");
        File illegal = new File("sounds/illegal.wav");
        
        File fanfare = new File("sounds/fanfare.wav");
        File drawer = new File("sounds/drawer.wav");
        File lost = new File("sounds/lost.wav");
        
        File main = new File("sounds/main.wav");
        
        this.click = click;         // Click-Sound für legale Züge
        this.illegal = illegal;     // Fehler-Sound für illegale Züge
        
        this.fanfare = fanfare;     // Fanfaren-Sound für Sieg
        this.drawer = drawer;       // Sound für unentschieden
        this.lost = lost;           // Sound für verloren
        
        this.main = main;           // Haupthema
    }

    public static void playSound(File Sound) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();            
        } catch(Exception e) {
            //System.out.println("fail");
        }
    }

    public void playClick()     { playSound(click); }
    public void playIllegal()   { playSound(illegal); }
    
    public void playFanfare()   { playSound(fanfare); }
    public void playDrawer()    { playSound(drawer); }
    public void playLost()      { playSound(lost); }
    
    public void playMain()      { playSound(main); }
}