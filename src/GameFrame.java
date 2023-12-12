import javax.swing.JFrame;

public class GameFrame extends JFrame {
    
    private static final long serialVersionUID = 1L;
    MenuPanel menu;
    GamePanel game;
    
    public GameFrame() {
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        
        menu = new MenuPanel(this);
        this.add(menu);
        
        game = new GamePanel(this);
        
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    
    public void startGame() {
        this.remove(menu);
        this.add(game);
        game.requestFocusInWindow();
        this.pack();
        game.startGame();
    }
    
    public void newGame() {
    	 this.remove(game);
         game = new GamePanel(this);
         this.add(game);
         game.requestFocusInWindow();
         this.pack();
         game.startGame();
         game.setVisible(true);
    }
	
}
