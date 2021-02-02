/**
 * Injected state of Person
 */
public class InjectedState implements State {
    Person person;

    /**
     *
     * @param person
     */
    public InjectedState(Person person) {
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
        person.setState(person.getHospitallyState());
        System.out.println("InjectedState durumdan inHospital durumuna geçildi  !!!");
    }

    @Override
    public void beDead() {
        person.setState(person.getDeadState());
        System.out.println("InjectedState durumdan beDead durumuna geçildi  !!!");
    }
}
