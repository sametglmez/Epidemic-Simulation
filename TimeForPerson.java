import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer for collision and death
 */
public class TimeForPerson {
    Timer timer;
    Person person;
    int seconds;
    int typeOfTimerFlag;
    int leftTime;
    boolean isItStart;

    /**
     * Constructor
     * @param person
     * @param typeOfTimerFlag
     */
    public TimeForPerson(Person person, int typeOfTimerFlag) {

        //this.seconds = seconds;
        this.person = person;
        this.typeOfTimerFlag = typeOfTimerFlag;
        leftTime = 0;
        isItStart = false;
    }

    /**
     * When this method called
     * Timer is starting
     * @param seconds
     */
    public void startTimer(int seconds){
        this.seconds = seconds;
        isItStart = true;
        timer = new Timer();
        timer.schedule(new RemindTask(),0,1000);
    }


    class RemindTask extends TimerTask {
        int i = 0;

        /**
         * This method notify finish time
         * WE can take left Time
         */
        public void run() {
            i++;
            if(i % seconds == 0){
                if(typeOfTimerFlag == 1){
                    person.collusionFlag = false;
                    person.collusionFlag2 = false;
                    person.timeFinishCollison();

                    timer.cancel();

                }else if (typeOfTimerFlag == 2){
                    person.timeFinishDead();
                    timer.cancel();
                }
                timer.cancel();
            }else{
                leftTime = seconds - (i % seconds);
            }
        }
    }
}
