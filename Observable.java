import java.util.ArrayList;

/**
 * Observable Class
 * Send new changes to other class
 */
public interface Observable {

    /**
     *
     * @param temp
     */
    public void registerObserver(Observers temp);
    public void registerStateObserver(stateObservers temp);

    /**
     *
     * @param temp
     */
    public void removeObserver(Observers temp);
    public void removeStateObservers(stateObservers temp);

    /**
     * notify the other object
     */
    public void notifyObserver();



}
