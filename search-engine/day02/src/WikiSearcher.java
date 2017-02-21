/**
 * Created by carl on 2/20/17.
 */
import javax.swing.*;
public class WikiSearcher {
    public static void main(String[] args){

        Gui q = new Gui();

        JFrame frame = new JFrame("WikiSearcher");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(q);
        frame.pack();
        frame.setVisible(true);
    }
}
