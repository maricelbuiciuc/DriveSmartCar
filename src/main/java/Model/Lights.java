package Model;

/**
 *
 * @author Marcy
 */
public class Lights {

    private boolean LightsAreON;

    public Lights(boolean LightsAreON) {
        this.LightsAreON = LightsAreON;
    }

    public boolean isFrontLighted() {
        return LightsAreON;
    }

    public void setEngineIsON(boolean LightsAreON) {
        this.LightsAreON = LightsAreON;
    }
}
