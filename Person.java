import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;

/**
 * represet person in simulation
 *
 */
public class Person{
    TimeForPerson timerCollison;
    TimeForPerson timerDead;
    Thread thread;

    int Z;
    double angle = 0;
    boolean dr=false;
    double frX = 0;
    double frY = 0;
    double frEndX = 970;
    double frEndY = 560;
    double dirX = 0;
    double dirY = 0;
    double radius = 2.5;
    Mediator mediator;

    private int speed;
    double coordinateX;
    double coordinateY;
    int width;
    int height;
    int socialDistance;
    int C;
    boolean collusionFlag = false;
    boolean collusionFlag2 = false;
    boolean mask;
    boolean deadFlag;
    double maskStatus;

    State healthyState;
    State injectedState;

    State hospitallyState;
    State deadState;

    State state;

    Random ra = new Random();

    /**
     * Constructor
     * @param speed
     * @param coordinateX
     * @param coordinateY
     * @param width
     * @param height
     * @param socialDistance
     * @param mask
     * @param C
     * @param mediator
     */
    public Person(int speed, int coordinateX, int coordinateY, int width, int height, int socialDistance,boolean mask,int C,Mediator mediator) {
        timerCollison = new TimeForPerson(this,1);
        timerDead = new TimeForPerson(this,2);

        this.speed = speed;
        angle = (2 * Math.PI * Math.random());
        dirX = Math.cos(angle) * this.speed;
        dirY = Math.sin(angle) * this.speed;

        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.width = width;
        this.height = height;
        this.socialDistance = socialDistance;
        this.mask = mask;
        this.C = C;

        if(this.mask == true)
            maskStatus = 1;
        else
            maskStatus = 0.2;

        collusionFlag = false;
        deadFlag = false;

        Random rand =new Random();
        this.Z = rand.nextInt(10) + 1;

        this.healthyState = new HealthyState(this);
        this.injectedState = new InjectedState(this);
        this.hospitallyState = new HospitallyState(this);
        this.deadState = new DeadState(this);

        this.state = healthyState;
        this.mediator = mediator;
        mediator.persons.add(this);
    }

    /**
     * Getter
     * @return
     */
    public State getState() {
        return state;
    }

    /**
     * Setter
     * @param state
     */
    public void setState(State state) {
        this.state = state;
    }

    public State getHealthyState() { return healthyState; }
    public State getInjectedState() { return injectedState; }
    public State getHospitallyState() { return hospitallyState; }
    public State getDeadState() { return deadState; }

    /**
     * State being healty
     */
    public void healthyStateOccurence(){state.beHealty();}
    public void injectedStateOccurance(){state.beInfected();}
    public void hospitallyStateOccurance(){state.inHospital();}
    public void deadStateOccurance(){state.beDead();}

    /**
     * İnsanıın gideceği yolu belirlemektedir.
     */
    public void updateWay(){

        if (this.coordinateX - radius < this.frX)
            this.coordinateX = (this.frX + radius);
        else if (this.coordinateX + radius > this.frEndX)
            this.coordinateX = (this.frEndX - radius);
        if (coordinateY - radius < this.frY)
            coordinateY = (this.frY + radius);
        else if (coordinateY + radius > frEndY)
            coordinateY = (frEndY - radius);

        double newx = coordinateX + dirX * 1;
        double newy = coordinateY +  dirY * 1;

        if (newy < frY + radius) {
            newy = 2*(frY+radius) - newy;
            dirY = Math.abs(dirY);
        }
        else if (newy > frEndY - radius) {
            newy = 2*(frEndY-radius) - newy;
            dirY = -Math.abs(dirY);
        }
        if (newx < frX + radius) {
            newx = 2*(frX+radius) - newx;
            dirX = Math.abs(dirX);
        }
        else if (newx > frEndX - radius) {
            newx = 2*(frEndX-radius) - newx;
            dirX = -Math.abs(dirX);
        }

        coordinateX = newx;
        coordinateY = newy;
    }

    public int getSpeed() {
        return speed;
    }

    /**
     * When collison timer is finish :
     * This method called
     * Start Dead timer after collison and infected
     */
    public void timeFinishCollison(){
        this.collusionFlag = false;
        timerCollison = new TimeForPerson(this,1);
        updateAfterCollison();
        if(deadFlag == false && this.state == getInjectedState()){
            int time = Z * 100 / 10;
            this.timerDead.startTimer(time);
            deadFlag = true;
        }
    }

    /**
     * After stop in collison person have to start different location
     */
    public void updateAfterCollison(){
        this.coordinateX += dirX;
        this.coordinateY += dirY;
    }

    /**
     * When dead time is finish
     */
    public void timeFinishDead(){
        this.deadStateOccurance();

    }

}
