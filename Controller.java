/**
 * model and view controls
 */
public class Controller implements ControllerInterface {
    Model model;
    personAreaPanel personPanel ;
    showStatePanel showPanel;
    View view;

    /**
     * Constructor
     * @param model
     */
    public Controller(Model model) {
        this.model = model;
        personPanel = new personAreaPanel(this.model);
        showPanel = new showStatePanel(this.model);
        view = new View(personPanel,showPanel,this);
    }

    @Override
    public void addOnePerson() {
        model.addOnePerson();
    }

    @Override
    public void addBulkPerson() {
        model.addBulkPerson(10);
    }

    @Override
    public void start() {
        model.startGame();
    }

    @Override
    public void stop() {
        try {
            model.stopSimulation();
        }catch (Exception e){
            System.out.println("Exepiton " + e);
        }
    }

    public void pauseButton(){
        model.pauseSimulation();
    }

    public void continueButton(){
        model.continueSimulation();
    }
}
