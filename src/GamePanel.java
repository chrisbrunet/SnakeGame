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
	
	static final int WIDTH = 600;
	static final int HEIGHT = 600;
	static final int DELAY = 150;
	static final int BLOCK_SIZE = 25;
	static final int NUM_BLOCKS = (WIDTH * HEIGHT) / BLOCK_SIZE;
	boolean running = false;
	char direction;
	int  score = 0;
	
	Timer timer;
	Snake snake;
	Apple apple;
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.DARK_GRAY);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	
	public void startGame() {
		running = true;
		apple = new Apple(WIDTH, HEIGHT, BLOCK_SIZE);
		snake = new Snake(WIDTH, HEIGHT, NUM_BLOCKS, BLOCK_SIZE);
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		if(running) {
			
			g.setFont(new Font("Comic Sans MS", 1, 20));
			String scoreText = "Score: " + score;
			int scoreWidth = g.getFontMetrics().stringWidth(scoreText);
			
			g.setColor(Color.RED);
			g.drawString(scoreText, WIDTH/2 - scoreWidth/2, HEIGHT - 2 * BLOCK_SIZE);
			
			g.fillOval(apple.getX_coord(), apple.getY_coord(), BLOCK_SIZE, BLOCK_SIZE);
			
			for(int i = 0; i < snake.getBodyParts(); i++) {
				if(i == 0) {
					g.setColor(Color.ORANGE);
					g.fillRect(snake.getX()[i], snake.getY()[i], BLOCK_SIZE, BLOCK_SIZE);
				} else {
					g.setColor(Color.GREEN);
					g.fillRect(snake.getX()[i], snake.getY()[i], BLOCK_SIZE, BLOCK_SIZE);
				}
			}
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
