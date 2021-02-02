import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Simulation Panel
 */
public class personAreaPanel extends JPanel implements Observers {
    Model model;
    ArrayList<PersonData> personDatas;

    /**
     * Constructor
     * @param model
     */
    public personAreaPanel(Model model) {
        this.model = model;
        this.model.registerObserver(this);
        //this.persons = new ArrayList<>();
        this.personDatas = new ArrayList<>();
        this.setBackground(Color.YELLOW);
        this.setVisible(true);
        this.setSize(new Dimension(1000,600));

        //mediator = new Mediator(persons);

    }

    /**
     * paint method to show persons
     * @param g
     */
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        for (int i = 0 ; i < personDatas.size();i++){
            personDatas.get(i);
            Rectangle recPerson = new Rectangle();
            recPerson.x = (int) personDatas.get(i).getCoordinateX();
            recPerson.y = (int) personDatas.get(i).getCoordinateY();
            recPerson.width = personDatas.get(i).getWidth();
            recPerson.height =personDatas.get(i).getHeight();

            g.setPaintMode();
            g.setColor(personDatas.get(i).color);
            //g.setColor(Color.RED);
            g.fillRect(recPerson.x,recPerson.y,recPerson.width,recPerson.height);
        }
    }

    /**
     * Update person datas came from @Model
     * @param personDatas
     */
    @Override
    public void update(ArrayList<PersonData> personDatas) {
        this.personDatas.clear();
        for (int i = 0 ; i < personDatas.size(); i++)
            this.personDatas.add(personDatas.get(i));

        repaint();
    }
}
