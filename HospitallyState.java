/**
 * Hospital state of person
 */
public class HospitallyState implements State {
    Person person;

    /**
     *
     * @param person
     */
    public HospitallyState(Person person) {
        this.person = person;
    }

    @Override
    public void beHealty() {
        person.setState(person.getHealthyState());
        System.out.println("Hospitally durumdan beHealty durumuna geçildi  !!!");
    }

    @Override
    public void beInfected(){
    }

    @Override
    public void inHospital() {

    }

    @Override
    public void beDead() {

    }
}
