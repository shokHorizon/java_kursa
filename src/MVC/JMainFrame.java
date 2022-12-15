package MVC;

import javax.swing.*;

public class JMainFrame extends JFrame {

    JPanel currentPanel = null;
    public JMainFrame(){
        // создание окна
        super("Example LayeredTest");
        // выход при закрытии окна
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // определение многослойной панели
        JLayeredPane lp = getLayeredPane();
        setSize(280, 250);
        setVisible(true);
    }

    public void set_panel(JPanel panel){
        if (this.currentPanel != null)
            this.remove(this.currentPanel);
        this.currentPanel = panel;
    }
}
