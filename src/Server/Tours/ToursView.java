package Server.Tours;

import javax.swing.*;

public class ToursView extends JPanel {
    public static final ToursView INSTANCE = new ToursView();

    public ToursView(){
        super();
        this.add(new JButton("Суп из семи залуп"));
        this.setSize(200, 200);
        this.setVisible(true);
    }


}
