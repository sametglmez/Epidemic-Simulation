/**
 * Hold state counters
 */
public class stateNumbers {
    int healtyState;
    int hospitalState;
    int injectedState;
    int deadState;

    /**
     * Constructor
     * @param healtyState
     * @param hospitalState
     * @param deadState
     * @param injectedState
     */
    public stateNumbers(int healtyState, int hospitalState, int deadState,int injectedState) {
        this.healtyState = healtyState;
        this.hospitalState = hospitalState;
        this.deadState = deadState;
        this.injectedState = injectedState;
    }

    /**
     * Restart all datas
     */
    public void restart(){
        this.healtyState = 0;
        this.hospitalState = 0;
        this.deadState = 0;
        this.injectedState = 0;
    }
}
