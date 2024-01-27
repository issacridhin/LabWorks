package snakegame;

import java.awt.Dimension;
import javax.swing.*;

public class SnakeGame extends JFrame {

    private Board board;
    private StartScreen startScreen;
    
    SnakeGame() {
        super("Snake Game");

        initializeComponents();

        setLocationRelativeTo(null);
        setResizable(false);
        setMinimumSize(new Dimension(600, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initializeComponents() {
        startScreen = new StartScreen(this::startGame);
        board = new Board();

        add(startScreen);
    }

    private void startGame() {
        
        getContentPane().remove(startScreen);
        getContentPane().add(board);

        pack();
        setLocationRelativeTo(null); 
        revalidate();
        repaint();

        board.requestFocusInWindow(); 
        board.initGame(); 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SnakeGame().setVisible(true));
    }
}
