import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;
	JButton startGameButton;

    private GameFrame frame;

	public MenuPanel(GameFrame frame) {
		this.frame = frame;
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.DARK_GRAY);
        this.setFocusable(true);
        
		startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.startGame();
            }
        });
        this.add(startGameButton);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		startGameButton.setSize(100, 20);
	    startGameButton.setLocation(WIDTH / 2 - startGameButton.getWidth() / 2, HEIGHT / 2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startGameButton) {
			System.out.println("button clicked");
		}		
	}

}
