import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String args[])
    {
        JFrame obj = new JFrame("Snake Game By Borneel");
        GamePlay gameplay = new GamePlay();
        obj.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //obj.setBounds(10, 10, 905, 700);
        obj.setBackground(Color.DARK_GRAY);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameplay);
    }
}
