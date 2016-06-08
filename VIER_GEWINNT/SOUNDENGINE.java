import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 * Die Klasse SOUNDENGINE spielt die Sounds Click und Fanfare ab.
 * @author (kinder)
 * @version (08.06.16)
 * @since (21.04.16)
 * 
 */
public class SOUNDENGINE
{
    File click;
    File fanfare;
    public SOUNDENGINE()
    {
        File click = new File("sounds/click.wav"); 
        File fanfare = new File("sounds/fanfare.wav"); 
        this.click = click;
        this.fanfare = fanfare;

    }

    public static void PlaySound(File Sound){
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();            
        }catch(Exception e)
        {
            //System.out.println("fail");
        }

    }

    public void playClick(){
        PlaySound(click);
    }
    
    public void playFanfare(){
        PlaySound(fanfare);
    }
}