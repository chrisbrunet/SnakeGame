import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {
	
	static final int WIDTH = 600;
	static final int HEIGHT = 600;
	static final int DELAY = 75;
	static final int BLOCK_SIZE = 25;
	static final int NUM_BLOCKS = (WIDTH * HEIGHT) / BLOCK_SIZE;
	boolean running = false;
	
	Timer timer;
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
	}
		
	public void move() {
		
	}
	
	public void checkTarget() {
		
	}
	
	public void checkFault() {
		
	}
	
	public void gameOver(Graphics g) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
