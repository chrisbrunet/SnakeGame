
public class Snake {
	
	private int blockSize;
	private int bodyParts;
	private int x[];
	private int y[];
	
	public Snake(int numBlocks, int blockSize) {
		bodyParts = 3;
		x = new int[numBlocks];
		y = new int[numBlocks];
		this.blockSize = blockSize;
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
