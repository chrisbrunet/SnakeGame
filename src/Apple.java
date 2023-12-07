import java.util.Random;

public class Apple {
	
	private int x_coord;
	private int y_coord;
	
	Random r;

	public Apple(int width, int height, int blockSize) {
		r = new Random();
		x_coord = r.nextInt((int)(width / blockSize)) * blockSize;
		y_coord = r.nextInt((int)(height / blockSize)) * blockSize;
	}

	public int getY_coord() {
		return y_coord;
	}

	public int getX_coord() {
		return x_coord;
	}

	
}
