import java.util.Timer;
import java.util.TimerTask;

public class TimeForCollison {
    Timer timer;
    Person person;
    int seconds;
    int typeOfTimerFlag;
    int leftTime;
    boolean isItStart;

    public TimeForCollison() {
        timer = new Timer();
        isItStart = false;
    }

    public void startTimer(int seconds,Person person){
        this.person = person;
        this.seconds = seconds;
        timer.schedule(new RemindTask(),seconds*1000);
    }

    class RemindTask extends TimerTask {
        public void run() {
            person.collusionFlag = false;
            timer.cancel();
        }
    }
}
