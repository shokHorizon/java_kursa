import MVC.JMainFrame;
import Server.Tours.ToursView;

public class Main {
    public static void main(String[] args) {
        JMainFrame jMainFrame = new JMainFrame();
        jMainFrame.set_panel(ToursView.INSTANCE);
    }
}