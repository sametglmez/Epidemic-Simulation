/**
 * Person death status
 */
public class DeadState implements State {
    Person person;

    /**
     * Constructor
     * @param person
     */
    public DeadState(Person person) {
        this.person = person;
    }

    @Override
    public void beHealty() {

    }

    @Override
    public void beInfected() {

    }

    @Override
    public void inHospital() {

    }

    @Override
    public void beDead() {
        ;
    }
}
