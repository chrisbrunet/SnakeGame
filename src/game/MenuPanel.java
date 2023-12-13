package game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;
	
	private JButton startGameButton, exitButton;
	private JRadioButton easy, medium, hard;
	private ButtonGroup buttons;
	private ImageIcon icon;
	
	private int delay;

	public MenuPanel(GameFrame frame) {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.DARK_GRAY);
        this.setFocusable(true);
        
        icon = new ImageIcon("snakeIcon.png", "Snake Logo");
               
        easy = new JRadioButton("Easy");
        this.add(easy);
        
        medium = new JRadioButton("Medium");
        this.add(medium);
        
        hard = new JRadioButton("Hard");
        this.add(hard);
        
        buttons = new ButtonGroup();
        buttons.add(easy);
        buttons.add(medium);
        buttons.add(hard);
        
        easy.setSelected(true);

        startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(easy.isSelected()) {
            		delay = 150;
            	} else if(medium.isSelected()) {
            		delay = 100;
            	} else {
            		delay = 50;
            	}
            	
            	frame.showGamePanel(delay);
            }
        });
        this.add(startGameButton);
        
        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.exit(0);
            }
        });
        this.add(exitButton);
        
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
		
		g.drawImage(icon.getImage(), WIDTH / 2 - 50, HEIGHT / 2 + 50, 100, 100, this);
	}
	
	public void draw(Graphics g) {
		String title = "SNAKE GAME";
		g.setFont(new Font("Comic Sans MS", 30, 50));
		g.setColor(Color.MAGENTA);
		int titleWidth = g.getFontMetrics().stringWidth(title);
		g.drawString(title, WIDTH / 2 - titleWidth / 2, 150);
		
		startGameButton.setSize(250, 50);
	    startGameButton.setLocation(WIDTH / 2 - startGameButton.getWidth() / 2, HEIGHT / 3);
	    startGameButton.setFont(new Font("Comic Sans MS", 30, 30));
	    
	    easy.setSize(75, 20);
	    easy.setLocation(WIDTH / 2 - startGameButton.getWidth() / 2, HEIGHT / 3 + 60);
	    easy.setFont(new Font("Comic Sans MS", 15, 15));
	    easy.setForeground(Color.GREEN);
	    
	    medium.setSize(100, 20);
	    medium.setLocation(WIDTH / 2 - startGameButton.getWidth() / 2 + easy.getWidth(), HEIGHT / 3 + 60);
	    medium.setFont(new Font("Comic Sans MS", 15, 15));
	    medium.setForeground(Color.ORANGE);
	    
	    hard.setSize(100, 20);
	    hard.setLocation(WIDTH / 2 - startGameButton.getWidth() / 2 + easy.getWidth() + medium.getWidth(), 
	    		HEIGHT / 3 + 60);
	    hard.setFont(new Font("Comic Sans MS", 15, 15));
	    hard.setForeground(Color.RED);
	    
	    exitButton.setSize(150, 40);
	    exitButton.setLocation(WIDTH - exitButton.getWidth() - 20, HEIGHT - 20 - exitButton.getHeight());
	    exitButton.setFont(new Font("Comic Sans MS", 15, 15));
	    
	    String author = "Created by Chris Brunet";
		g.setFont(new Font("Comic Sans MS", 30, 15));
		g.setColor(Color.WHITE);
		g.drawString(author, 20, HEIGHT - 20);
	}

}
