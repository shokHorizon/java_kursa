package Server.Tours;

import javax.swing.*;

public class ToursView extends JPanel {
    public static final ToursView INSTANCE = new ToursView();

    public ToursView(){
        JTable jTable = new JTable(10, 3);
        JButton jButton = new JButton("Суп из семи залуп");
        jTable.setBounds(0, 0, 600, 800);
        jTable.setRowHeight(75);
        for (int i = 0; i < jTable.getColumnCount(); i++){
            jTable.getColumnModel().getColumn(i).setPreferredWidth(200);
        }
        this.add(jTable);
        this.setSize(600, 800);
        this.setVisible(true);
    }


}
