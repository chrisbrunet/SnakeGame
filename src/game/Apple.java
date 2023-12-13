package game;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Apple {
	
	private int x_coord;
	private int y_coord;
	private int blockSize;
	
	Random r;

	public Apple(int width, int height, int blockSize) {
		this.blockSize = blockSize;
		r = new Random();
		x_coord = r.nextInt((int)(width / blockSize)) * blockSize;
		y_coord = r.nextInt((int)(height / blockSize)) * blockSize;
	}
	
	public void drawApple(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(x_coord, y_coord, blockSize, blockSize);
	}

	public int getY_coord() {
		return y_coord;
	}

	public int getX_coord() {
		return x_coord;
	}

	
}
