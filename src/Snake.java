import java.awt.Color;
import java.awt.Graphics;

public class Snake {
	
	private int width;
	private int height;
	private int blockSize;
	private int bodyParts;
	private int x[];
	private int y[];
	
	public Snake(int width, int height, int numBlocks, int blockSize) {
		this.width = width;
		this.height = height;
		this.blockSize = blockSize;
		bodyParts = 1;
		x = new int[numBlocks];
		y = new int[numBlocks];
		x[0] = width / 2;
		y[0] = height / 2;
	}
	
	public void drawSnake(Graphics g) {
		for(int i = 0; i < bodyParts; i++) {
			if(i == 0) {
				g.setColor(Color.ORANGE);
				g.fillRect(x[i], y[i], blockSize, blockSize);
			} else {
				g.setColor(Color.GREEN);
				g.fillRect(x[i], y[i], blockSize, blockSize);
			}
		}
	}
	
	public void move(char direction) {
		for(int i = bodyParts; i > 0; i--) {
			x[i] = x[i - 1];
			y[i] = y[i - 1];
		}
		
		switch(direction) {
		case 'U':
			y[0] = y[0] - blockSize;
			break;
		case 'D':
			y[0] = y[0] + blockSize;
			break;
		case 'L':
			x[0] = x[0] - blockSize;
			break;
		case 'R':
			x[0] = x[0] + blockSize;
			break;
		}
	}
	
	public boolean fault() {
		if(x[0] == width || x[0] < 0) {
			return true;
		} 
		
		if(y[0] == height || y[0] < 0) {
			return true;
		}
		
		for(int i = 1; i < bodyParts; i++) {
			if(x[i] == x[0] && y[i] == y[0]) {
				return true;
			}
		}
		
		return false;
	}
	
	public void eatApple() {
		bodyParts++;
	}
	
	public int getBodyParts() {
		return bodyParts;
	}
	
	public int[] getX() {
		return x;
	}
	
	public int[] getY() {
		return y;
	}
}
