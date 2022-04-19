package ru.gb.java_core1.l8_gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Example {
    private static final int WINDOW_WIDTH = 640;
    private static final int WINDOW_HEIGHT = 480;

    public static void main(String[] args) {
        new ExampleWindow();
    }

    public static class ExampleWindow extends JFrame {
        private int counter;

        public ExampleWindow() {
            setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
//            setLocation(500, 500);
            setLocationRelativeTo(null);
            setTitle("My GUI example");
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());
            JButton button = new JButton("BUTTON");
            panel.add(button);

            JButton button2 = new JButton("222222");
            panel.add(button2);
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

            JLabel label = new JLabel("Press the button");
            label.setFont(new Font("Times New Roman", Font.BOLD, 50));
            add(label);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    counter++;
                    label.setText("COUNTER: " + counter);
                }
            });

            button2.addActionListener((e) -> {
                counter--;
                label.setText("COUNTER: " + counter);
            });
            setVisible(true);
        }

    }
}
