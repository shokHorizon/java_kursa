package Server.Tours;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class ToursView extends JPanel {
    public static final ToursView INSTANCE = new ToursView();
/*
    public ToursView(){
        JTableHeader jTableHeader = new JTableHeader();
        List<Object> data =
        JTable jTable = new JTable(new Object[][]{
                {1, "Berlin"},
                {2, "Paris"}
        }, new String[]{"ID", "City"});
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        model.addRow();
        jTable.setForeground(Color.GRAY);
        JButton jButton = new JButton("Суп куры");
        jTable.setBounds(0, 0, 200, 200);
        jTable.setRowHeight(30);
        this.add(new JScrollPane(jTable));
        add(jButton);
        add(new JButton("Reunion"));
        this.setMinimumSize(new Dimension(600, 400));
        setForeground(Color.BLUE);
        this.setVisible(true);
    }

*/
}
