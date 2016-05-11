import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class SOUNDENGINE
{
    File click;
    File fanfare;
    public SOUNDENGINE()
    {
        File click = new File("click.wav"); 
        File fanfare = new File("fanfare.wav"); 
        this.click = click;
        this.fanfare = fanfare;

    }

    public static void PlaySound(File Sound){
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();

            Thread.sleep(clip.getMicrosecondLength()/1000);
        }catch(Exception e)
        {
            System.out.println("fail");
        }

    }

    public void playClick(){
        PlaySound(click);
    }
    
    public void playFanfare(){
        PlaySound(fanfare);
    }
}