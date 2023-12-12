import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;
	private static final int BLOCK_SIZE = 25;
	private static final int NUM_BLOCKS = (WIDTH * HEIGHT) / BLOCK_SIZE;
	private int delay = 150;
	private boolean running = false;
	private char direction;
	private int score;
	
	private Timer timer;
	private Snake snake;
	private Apple apple;
	
	private JButton newGameButton;
	private JButton menuButton;
	
	
	public GamePanel(GameFrame frame) {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.DARK_GRAY);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		
		newGameButton = new JButton("Play Again");
		newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.showGamePanel(delay);
            }
        });
        this.add(newGameButton);
        newGameButton.setVisible(false);
        
        menuButton = new JButton("Main Menu");
        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.showMenuPanel();
            }
        });
        this.add(menuButton);
        menuButton.setVisible(false);
	}
	
	public void startGame(int delay) {
		this.delay = delay;
		direction = 'O';
        newGameButton.setVisible(false);
		score = 0;
        running = true;		
        apple = new Apple(WIDTH, HEIGHT, BLOCK_SIZE);
		snake = new Snake(WIDTH, HEIGHT, NUM_BLOCKS, BLOCK_SIZE);
		if (timer != null) {
            timer.stop();
            timer = null;
        }
        timer = new Timer(this.delay, this);
        timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		if(running) {
			String scoreText = "Score: " + score;

			g.setFont(new Font("Comic Sans MS", 1, 20));
			int scoreWidth = g.getFontMetrics().stringWidth(scoreText);
			g.setColor(Color.RED);
			g.drawString(scoreText, WIDTH/2 - scoreWidth/2, HEIGHT - 2 * BLOCK_SIZE);
			
			apple.drawApple(g);			
			snake.drawSnake(g);
		} else {
			gameOver(g); 
		}
	}
	
	public void checkApple() {
		if(snake.getX()[0] == apple.getX_coord() && snake.getY()[0] == apple.getY_coord()){
			apple = new Apple(WIDTH, HEIGHT, BLOCK_SIZE);
			snake.eatApple();
			score++;
		}
	}
	
	public void checkFault() {
		if(snake.fault()) {
			running = false;
		}
	}
	
	public void gameOver(Graphics g) {
		newGameButton.setSize(100, 20);
		newGameButton.setLocation(WIDTH / 2 - newGameButton.getWidth() / 2, HEIGHT / 2);
		newGameButton.setVisible(true);
		
		menuButton.setSize(100, 20);
		menuButton.setLocation(WIDTH / 2 - newGameButton.getWidth() / 2, HEIGHT / 2 + 30);
		menuButton.setVisible(true);
		
		if (timer != null) {
            timer.stop();
        }
		
		String gameOverText = "Game Over";
		String finalScoreText = "Final Score: " + score;
		
		g.setFont(new Font("Comic Sans MS", 1, 20));
		int fontHeight = g.getFontMetrics().getHeight();
		int gameOverWidth = g.getFontMetrics().stringWidth(gameOverText);
		int finalScoreWidth = g.getFontMetrics().stringWidth(finalScoreText);
		
		int rectW = Math.max(gameOverWidth, finalScoreWidth) + 50;
		int rectH = fontHeight * 2 + 50;
		int rectX = WIDTH/2 - rectW/2;
		int rectY = 100;
		
    	g.setColor(Color.ORANGE);
		g.drawRoundRect(rectX, rectY, rectW, rectH, 10, 10);
    	g.setColor(Color.RED);
		g.drawString(gameOverText, rectX + (rectW - gameOverWidth) / 2, 
				rectY + rectH / 2 - fontHeight / 2);
    	g.setColor(Color.GREEN);
		g.drawString(finalScoreText, rectX + (rectW - finalScoreWidth) / 2, 
				rectY + rectH / 2 + fontHeight);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(running) {
			snake.move(direction);
			checkApple();
			checkFault();
		}
		repaint();
	}


	public class MyKeyAdapter extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
					break;
				}
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
					break;
				}
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
					break;
				}
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
					break;
				}
			}
	
		}
	}
}
