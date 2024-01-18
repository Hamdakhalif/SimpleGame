import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SimpleGame extends JFrame implements ActionListener, KeyListener {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;
    private static final int BALL_SIZE = 20;

    private int paddleX, paddleY;
    private int ballX, ballY, ballSpeedX, ballSpeedY;

    public SimpleGame() {
        setTitle("Simple Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        paddleX = WIDTH / 2 - PADDLE_WIDTH / 2;
        paddleY = HEIGHT - 30;

        ballX = WIDTH / 2 - BALL_SIZE / 2;
        ballY = HEIGHT / 2 - BALL_SIZE / 2;
        ballSpeedX = 3;
        ballSpeedY = 3;

        Timer timer = new Timer(10, this);
        timer.start();

        addKeyListener(this);
        setFocusable(true);
    }

    public void actionPerformed(ActionEvent e) {
        moveBall();
        repaint();
    }

    private void moveBall() {
        ballX += ballSpeedX;
        ballY += ballSpeedY;

        // Bounce off the walls
        if (ballX <= 0 || ballX >= WIDTH - BALL_SIZE) {
            ballSpeedX = -ballSpeedX;
        }
        if (ballY <= 0 || ballY >= HEIGHT - BALL_SIZE) {
            ballSpeedY = -ballSpeedY;
        }

        // Check collision with the paddle
        if (ballY + BALL_SIZE >= paddleY && ballY <= paddleY + PADDLE_HEIGHT &&
                ballX + BALL_SIZE >= paddleX && ballX <= paddleX + PADDLE_WIDTH) {
            ballSpeedY = -ballSpeedY;
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT && paddleX > 0) {
            paddleX -= 10;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && paddleX < WIDTH - PADDLE_WIDTH) {
            paddleX += 10;
        }
    }

    public void keyReleased(KeyEvent e) {
        // Handle key releases if needed
    }

    public void keyTyped(KeyEvent e) {
        // Handle key typed events if needed
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(paddleX, paddleY, PADDLE_WIDTH, PADDLE_HEIGHT);
        g.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleGame game = new SimpleGame();
            game.setVisible(true);
        });
    }
}
