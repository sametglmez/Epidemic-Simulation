/**
 * Healthy Status of person
 */
public class HealthyState implements State {
    Person person;

    /**
     * Constructor
     * @param person
     */
    public HealthyState(Person person) {
        this.person = person;
    }

    @Override
    public void beHealty() {
    }

    @Override
    public void beInfected(){
        person.setState(person.getInjectedState());
    }

    @Override
    public void inHospital() {
    }

    @Override
    public void beDead() {

    }
}
