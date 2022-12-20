package MVC;

import javax.swing.*;
import java.awt.*;

public class JMainFrame extends JFrame {

    JPanel currentPanel = null;
    public JMainFrame(){
        // создание окна
        super("SERVERSIDE");
        // выход при закрытии окна
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(600, 800));
        this.setResizable(false);
        pack();
        setVisible(true);
    }

    public void set_panel(JPanel panel){
        if (this.currentPanel != null)
            this.remove(this.currentPanel);
        this.currentPanel = panel;
        setContentPane(panel);
    }
}
