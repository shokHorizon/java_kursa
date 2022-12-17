package Server.Tours;

import javax.swing.*;

public class ToursView extends JPanel {
    public static final ToursView INSTANCE = new ToursView();

    public ToursView(){
        super();
        JTable jTable = new JTable(10, 3);
        JButton jButton = new JButton("Суп из семи залуп");
        jButton.setBounds(10, 40, 120, 120);
        this.add(jButton);
        this.setSize(200, 200);
        this.setVisible(true);
    }


}
