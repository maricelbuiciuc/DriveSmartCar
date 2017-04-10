package Model;

/**
 *
 * @author Marcy
 */
public class Door {

    private boolean DoorIsUnlock;

    public Door(boolean DoorIsUnlock) {
        this.DoorIsUnlock = DoorIsUnlock;
    }

    public boolean isDoorOpen() {
        return DoorIsUnlock;
    }

    public void setDoorIsUnlock(boolean DoorIsUnlock) {
        this.DoorIsUnlock = DoorIsUnlock;
    }

}
