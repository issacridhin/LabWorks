/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author ridhi
 */
public class StartScreen extends JPanel {
    private Runnable onStart;

    public StartScreen(Runnable onStart) {
        this.onStart = onStart;
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Snake Game", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        JTextArea instructions = new JTextArea(
                "Use arrow keys to control the snake.\n" +
                        "Eat the apples to grow longer.\n" +
                        "Avoid colliding with the walls and yourself.\n\n" +
                        "Press 'Start' to begin the game!");
        instructions.setEditable(false);
        instructions.setOpaque(false);
        instructions.setFont(new Font("Arial", Font.PLAIN, 16));
        instructions.setWrapStyleWord(true);
        instructions.setLineWrap(true);
        add(instructions, BorderLayout.CENTER);

        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.BOLD, 18));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                onStart.run();
            }
        });
        add(startButton, BorderLayout.SOUTH);
    }
    
}
