package Model;

/**
 *
 * @author Marcy
 */
public class Radio {

    private boolean RadioIsON;

    public Radio(boolean RadioIsON) {
        this.RadioIsON = RadioIsON;
    }

    public boolean isMusicPlaying() {
        return RadioIsON;
    }

    public void setMusicPlaying(boolean RadioIsON) {
        this.RadioIsON = RadioIsON;
    }
}
