package lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExampleGui {

    public static class ExampleWindow extends JFrame{
    private static final int WINDOW_WIDTH = 640;
    private static final int WINDOWS_HEIGHT = 480;
    private int counter;
        public ExampleWindow(){
            setVisible(true);
            setSize(WINDOW_WIDTH,WINDOWS_HEIGHT);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            setTitle("My GuI!");
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());

            JButton button = new JButton("Button");
            panel.add(button, BorderLayout.NORTH);

            JButton button2 = new JButton("but 2");
            panel.add(button2, BorderLayout.SOUTH);
            add(panel, BorderLayout.SOUTH);

            JMenuBar bar = new JMenuBar();
            JMenu fileMenu = new JMenu("File");
            JMenuItem createItem = new JMenuItem("Create");
            JMenuItem exitItem = new JMenuItem("Exit");
            JMenu editMenu = new JMenu("Edit");
            JMenu helpMenu = new JMenu("Help");


            bar.add(fileMenu);
            bar.add(editMenu);
            bar.add(helpMenu);
            fileMenu.add(createItem);
            fileMenu.add(exitItem);

            add(bar, BorderLayout.NORTH);

            exitItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(1);
                }
            });

            JLabel label = new JLabel("Press button");
            label.setFont(new Font("Times New Roman", Font.BOLD,50));
            add(label);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    counter++;
                    label.setText("COUNTER : " + counter);
                }
            });

            button2.addActionListener((e) ->{
                counter--;
                label.setText("COUNTER : " + counter);
            });
        }
    }
}
