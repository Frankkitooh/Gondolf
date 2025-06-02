package juego;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sonido {
	private Clip clip;

	
	
public Sonido(String ubicacion){
	try {
		File efectoSonido = new File(ubicacion);
		AudioInputStream sonidito = AudioSystem.getAudioInputStream(efectoSonido);
		clip = AudioSystem.getClip();
		clip.open(sonidito);
	} catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
	}
	
}

public void reproducir() {
	if(clip != null) {
		
		clip.start();
	}
	
	
}

public void stop() {
    if (clip != null && clip.isRunning()) {
        clip.stop();
    }
}



public Clip getClip() {
	return clip;
}

public void setClip(Clip clip) {
	this.clip = clip;
}
	

}

