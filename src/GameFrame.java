import javax.swing.JFrame;

public class GameFrame extends JFrame {
    
    private static final long serialVersionUID = 1L;
    MenuPanel menu;
    GamePanel game;
    
    public GameFrame() {
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
    
    public void showMenuPanel() {
    	if(game != null) {
    		this.remove(game);
    		game = null;
    	}
    	
    	menu = new MenuPanel(this);
    	this.add(menu);
    	menu.requestFocusInWindow();
    	this.validate();
        this.repaint(); 
    	this.pack();
    	this.setVisible(true);
    }
    
    public void showGamePanel(int delay) {
    	if(game != null) {
    		this.remove(game);
    		game = null;
    	}
    	
    	if(menu != null) {
    		this.remove(menu);
    		menu = null;
    	}
    	
    	game = new GamePanel(this);
        this.add(game);
        game.requestFocusInWindow();
        this.validate();
        this.repaint(); 
        this.pack();
    	this.setVisible(true);
        game.startGame(delay);
    }
	
}
