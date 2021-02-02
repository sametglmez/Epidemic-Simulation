import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

/**
 * Backend of simulation
 * @personDatas  Personnel information is updated here.
 * @Thread Simulation thread is run
 * @stateObservers for notfiy state information
 * @mediator find instersection between persons
 */
public class Model implements Observable,Runnable{
    ArrayList<Person> persons;
    ArrayList<PersonData> personDatas;
    ArrayList<Observers> observers;
    ArrayList<stateObservers> stateObservers;
    Thread thread;
    boolean threadFlag;
    Mediator mediator;
    ArrayList<Integer> pauseTimer;
    stateNumbers numberOfState;
    double Z;

    boolean puaseFlag;

    /**
     * Constructor of Model
     */
    public Model() {
        thread = new Thread(this);
        persons = new ArrayList<>();
        observers = new ArrayList<>();
        stateObservers = new ArrayList<>();
        personDatas = new ArrayList<>();
        mediator = new Mediator();
        puaseFlag = false;
        pauseTimer = new ArrayList<>();
        numberOfState = new stateNumbers(0,0,0,0);
    }

    @Override
    public void registerObserver(Observers temp) {
        observers.add(temp);
    }

    @Override
    public void registerStateObserver(stateObservers temp) {
        stateObservers.add(temp);
    }

    @Override
    public void removeObserver(Observers temp) {
        observers.remove(temp);
    }

    @Override
    public void removeStateObservers(stateObservers temp) {
        stateObservers.remove(temp);
    }

    @Override
    public void notifyObserver() {
        for (int i = 0 ; i < observers.size(); i++){
            observers.get(i).update(personDatas);
        }
        for(int i = 0 ; i < stateObservers.size(); i++){
            stateObservers.get(i).update(numberOfState);
        }
    }

    /**
     * coordinates are updated.
     * The interface is informed.
     * section control is done.
     */
    @Override
    public void run() {
        while (true) {
            if(puaseFlag == false){
                for( int i = 0 ; i < persons.size(); i++){
                    int mediatorResult = this.mediator.findInterSection(persons.get(i),i);
                    persons.get(i).collusionFlag = false;
                }
                notifyObserver();
                updatePersonCoordinate();

            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @personDatas updated
     * @numberOfState to count situations
     */
    public void updatePersonCoordinate(){
        personDatas.clear();
        numberOfState.restart();
        for (int i = 0 ; i < persons.size();i++){
            if(persons.get(i).collusionFlag2 == false)
                persons.get(i).updateWay();
            if(persons.get(i).state == persons.get(i).getInjectedState()){
                personDatas.add(new PersonData(persons.get(i).coordinateX,persons.get(i).coordinateY,persons.get(i).width,persons.get(i).height, Color.RED));
                numberOfState.injectedState++;
            }else if (persons.get(i).state == persons.get(i).getHealthyState()){
                personDatas.add(new PersonData(persons.get(i).coordinateX,persons.get(i).coordinateY,persons.get(i).width,persons.get(i).height,Color.BLUE));
                numberOfState.healtyState++;
            }else if ((persons.get(i).state == persons.get(i).getDeadState())){
                numberOfState.deadState++;
            }else{

            }
        }
    }

    public void stopSimulation(){
        Thread.interrupted();
    }

    /**
     * When press button START
     */
    public void startGame(){
        thread.start();
        threadFlag = true;
        persons.add(new Person(5,400,300,5,5,10,true,3,mediator));
        persons.get(0).setState(persons.get(0).getInjectedState());

        for(int i = 0 ; i < persons.size() ; i++){
            mediator.persons.add(persons.get(i));
        }
    }

    /**
     * When press button PAUSE
     */
    public void pauseSimulation() {
        this.puaseFlag = true;
        for (int i = 0 ; i < persons.size(); i++){
            if(persons.get(i).timerCollison.isItStart == true){
                persons.get(i).timerCollison.timer.cancel();
            }
            if(persons.get(i).timerDead.isItStart == true){
                persons.get(i).timerDead.timer.cancel();
            }
        }
    }

    /**
     * When press button CONTINUE
     */
    public void continueSimulation(){
        this.puaseFlag = false;
        for (int i = 0 ; i < persons.size(); i++){
            if(persons.get(i).timerCollison.isItStart == true){
                persons.get(i).timerCollison.timer = new Timer();
                persons.get(i).timerCollison.startTimer(persons.get(i).timerCollison.leftTime);
            }
            if(persons.get(i).timerDead.isItStart == true){
                persons.get(i).timerDead.timer = new Timer();
                persons.get(i).timerDead.startTimer(persons.get(i).timerDead.leftTime);
            }
        }
    }

    /**
     * Add one person to simulation
     */
    public void addOnePerson(){
        Random rand = new Random();
        int x = rand.nextInt(990);
        int y = rand.nextInt(590);
        int c = rand.nextInt(5) + 1;
        int mask = rand.nextInt(3) + 1;
        int speed = rand.nextInt(5) + 1;
        int socialDistance = rand.nextInt(10) + 1;
        if(mask == 1){
            persons.add(new Person(speed,x,y,5,5,socialDistance,true,c,mediator));
        }else{
            persons.add(new Person(speed,x,y,5,5,socialDistance,false,c,mediator));
        }

    }

    /**
     * Add person bulk
     * @param size
     */
    public void addBulkPerson(int size){
        for (int i = 0 ; i < size ; i++){
            addOnePerson();
        }

    }
}
