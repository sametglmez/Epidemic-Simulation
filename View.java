//Usually you will require both swing and awt packages
// even if you are working with just swings.
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * simulation window
 */
class View{
    ControllerInterface controller;

    JFrame frame;
    personAreaPanel personArea;
    showStatePanel showPanel;
    JPanel panelForButton;
    JButton addOne;
    JButton start;
    JButton stop;
    JButton addBulk;
    JButton pause;
    JButton Continue;
    JPanel panel;
    JLabel label;

    /**
     * Constructors
     * @param personArea
     * @param showPanel
     * @param controller
     */
    public View(personAreaPanel personArea,showStatePanel showPanel,ControllerInterface controller) {
        this.controller = controller;
        frame = new JFrame("Epidemic Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1000,750));

        //Creating the MenuBar and adding components
        panelForButton = new JPanel(); // the panel is not visible in output
        this.showPanel = showPanel;
        this.personArea = personArea;
        stop = new JButton("END");
        start = new JButton("START");
        addOne = new JButton("ADD ONE");
        addBulk = new JButton("ADD BULK");
        pause = new JButton("PAUSE");
        Continue = new JButton("CONTINUE");
        panelForButton.setPreferredSize(new Dimension(1000,100));
        panelForButton.add(stop);
        panelForButton.add(start);
        panelForButton.add(addOne);
        panelForButton.add(addBulk);
        panelForButton.add(pause);
        panelForButton.add(Continue);
        frame.addWindowListener(new WindowAdapter() { public void windowClosing(WindowEvent we) { System. exit(1); } });


        stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.stop();
                System. exit(1);


            }
        });
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.start();
            }
        });
        addOne.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.addOnePerson();
            }
        });
        addBulk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.addBulkPerson();
            }
        });
        pause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.pauseButton();
            }
        });
        Continue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.continueButton();
            }
        });


        //Adding Components to the frame.
        frame.setLayout(new BorderLayout());
        frame.add(BorderLayout.SOUTH, showPanel);
        frame.add(BorderLayout.NORTH, panelForButton);
        frame.add(BorderLayout.CENTER, personArea);
        frame.setVisible(true);
    }


}