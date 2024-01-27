package snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public  class Board extends JPanel implements ActionListener {

    private Image apple;
    private Image dot;
    private Image head;

    private final int ALL_DOTS = 900;
    private final int DOT_SIZE = 10;
    private final int RANDOM_POSITION = 29;

    private int apple_x;
    private int apple_y;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;

    private boolean inGame = true;

    private int dots;
    private int score = 0;
    private int speed = 300;
    //private BufferedImage bufferImage;

    private int currentLevel = 1;
    private Timer timer;
    private List<Point> obstacles = new ArrayList<>();

    Board() {

        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(600, 600));
        setFocusable(true);
        
        timer = new Timer(speed, this);
        timer.start();
        loadImages();
        initGame();
       
//        loadImages();
//        initGame();

//        timer = new Timer(speed, this);
//        timer.start();

        
    }
  


    public void loadImages() {
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/apple.png"));
        apple = i1.getImage();

        ImageIcon i2 = new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/dot.png"));
        dot = i2.getImage();

        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/head.png"));
        head = i3.getImage();
    }

    public void initGame() {
        dots = 3;

        for (int i = 0; i < dots; i++) {
            y[i] = 50;
            x[i] = 50 - i * DOT_SIZE;
        }

        locateApple();

        // Initializing the obstacles
        obstacles.add(new Point(150, 150));
        obstacles.add(new Point(300, 300));
        obstacles.add(new Point(450, 450));

        timer = new Timer(speed, this);
        timer.start();
    }

    public void locateApple() {
        Random random = new Random();
        apple_x = random.nextInt(RANDOM_POSITION) * DOT_SIZE;
        apple_y = random.nextInt(RANDOM_POSITION) * DOT_SIZE;
    }

    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        draw(g);
    }
    

    public void draw(Graphics g) {
        if (inGame) {
            g.drawImage(apple, apple_x, apple_y, DOT_SIZE * 2, DOT_SIZE * 2, this);

            for (int i = 0; i < dots; i++) {
                if (i == 0) {
                    g.drawImage(head, x[i], y[i], DOT_SIZE * 2, DOT_SIZE * 2, this);
                } else {
                    g.drawImage(dot, x[i], y[i], DOT_SIZE*2, DOT_SIZE * 2, this);
                }
            }

            // Draw obstacles
            g.setColor(Color.RED);
            for (Point obstacle : obstacles) {
                g.fillRect(obstacle.x, obstacle.y, DOT_SIZE * 2, DOT_SIZE * 2);
            }

            // Display the score
            g.setColor(Color.WHITE);
            g.drawString("Score: " + score, 10, 10);

            Toolkit.getDefaultToolkit().sync();
        } else {
            gameOver(g);
        }
    }

    public void gameOver(Graphics g) {
        String msg = "Game Over!";
        Font font = new Font("SAN_SERIF", Font.BOLD, 14);
        FontMetrics metrics = getFontMetrics(font);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(msg, (300 - metrics.stringWidth(msg)) / 2, 300 / 2);
    }

    public void move() {
        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        if (leftDirection) {
            x[0] = x[0] - DOT_SIZE;
        }
        if (rightDirection) {
            x[0] = x[0] + DOT_SIZE;
        }
        if (upDirection) {
            y[0] = y[0] - DOT_SIZE;
        }
        if (downDirection) {
            y[0] = y[0] + DOT_SIZE;
        }
    }

    public void checkApple() {
        if ((x[0] == apple_x) && (y[0] == apple_y)) {
            dots++;
            score++;
            locateApple();
        }
    }

    public void checkCollision() {
        for (int i = dots; i > 0; i--) {
            if ((i > 4) && (x[0] == x[i]) && (y[0] == y[i])) {
                inGame = false;
            }
        }

        // Checking for collision with obstructions
        for (Point obstacle : obstacles) {
            if (x[0] == obstacle.x && y[0] == obstacle.y) {
                inGame = false;
            }
        }
        
        if (!inGame) {
            timer.stop();
            // Reset the game and increase the level
            dots = 3;
            score = 0;
            inGame = true;
            currentLevel++;
            initGame();
            
            // Start a new timer with the updated speed based on the level
            timer.stop();
            speed = calculateSpeed(currentLevel);
            timer = new Timer(speed, this);
            timer.start();
        }
        if (y[0] >= 600) {
            inGame = false;
        }
        if (x[0] >= 600) {
            inGame = false;
        }
        if (y[0] < 0) {
            inGame = false;
        }
        if (x[0] < 0) {
            inGame = false;
        }

        if (!inGame) {
            timer.stop();
            resetGame();
        }
    }
    private int calculateSpeed(int level) {
        
        return Math.max(50, 300 - (level - 1) * 20);
    }
    
    private void resetGame(){
        dots =3;
        score = 0;
        speed = 140;
        inGame = true;
        for(int i=0;i<dots;i++)
        {
        y[i] = 50;
        x[i] = 50 - i * DOT_SIZE;
        }
        locateApple();
        timer.start();
    }
    

    public void actionPerformed(ActionEvent ae) {
        if (inGame) {
            checkApple();
            checkCollision();
            move();
            timer.setDelay(speed); 
        }

        repaint();
    }

    public class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if (key == KeyEvent.VK_RIGHT && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if (key == KeyEvent.VK_UP && (!downDirection)) {
                upDirection = true;
                leftDirection = false;
                rightDirection = false;
            }

            if (key == KeyEvent.VK_DOWN && (!upDirection)) {
                downDirection = true;
                leftDirection = false;
                rightDirection = false;
            }
        }
    }
}
