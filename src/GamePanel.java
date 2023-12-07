import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
		g.setColor(Color.RED);
		g.fillOval(apple.getX_coord(), apple.getY_coord(), BLOCK_SIZE, BLOCK_SIZE);
		
		for(int i = 0; i < snake.getBodyParts(); i++) {
			if(i == 0) {
				g.setColor(Color.GREEN);
				g.fillRect(snake.getX()[i], snake.getY()[i], BLOCK_SIZE, BLOCK_SIZE);
			} else {
				g.setColor(Color.MAGENTA);
				g.fillRect(snake.getX()[i], snake.getY()[i], BLOCK_SIZE, BLOCK_SIZE);
			}
		}
	}
	
	public void checkApple() {
		if(snake.getX()[0] == apple.getX_coord() && snake.getY()[0] == apple.getY_coord()){
			apple = new Apple(WIDTH, HEIGHT, BLOCK_SIZE);
			snake.eatApple();
		}
	}
	
	public void checkFault() {
		if(snake.fault()) {
			running = false;
		}
	}
	
	public void gameOver(Graphics g) {
		
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
