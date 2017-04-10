package Model;

/**
 *
 * @author Marcy
 */
public class Engine {

    private boolean EngineIsON;

    public Engine(boolean EngineIsON) {
        this.EngineIsON = EngineIsON;
    }

    public boolean isEngineRunning() {
        return EngineIsON;
    }

    public void setEngineIsON(boolean EngineIsON) {
        this.EngineIsON = EngineIsON;
    }

}
