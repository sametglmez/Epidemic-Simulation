import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observer;

/**
 * Panel to show state counters
 */
public class showStatePanel extends JPanel implements stateObservers{
    JLabel label;
    Model model;
    String outputText;
    int healty;
    int hospital;
    int injected;
    int dead;

    /**
     * Constructor
     * @param model
     */
    public showStatePanel(Model model) {
        this.model = model;
        this.model.registerStateObserver(this);

        outputText = "";
        this.healty = 0;
        this.hospital = 0;
        this.dead = 0;
        this.injected = 0;
        this.setPreferredSize(new Dimension(1000,50));
        label = new JLabel(outputText);
        label.setFont(new Font("Verdana",1,20));
        this.add(label); // Components Added using Flow Layout
    }


    /**
     * Update state counter data came from Model
     * @param stateNumber
     */
    @Override
    public void update(stateNumbers stateNumber) {
        outputText = "Healty Person : " + stateNumber.healtyState + "   Injected Person : " + stateNumber.injectedState + "   Hospital Person : " + stateNumber.hospitalState
                + "   Dead Person : " + stateNumber.deadState;
        label.setText(outputText);

    }
}
