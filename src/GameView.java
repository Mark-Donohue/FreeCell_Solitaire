import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameView extends JFrame implements ActionListener{
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	
    private GameModel game;
    private AbstractPanel[] freePanels;
    private AbstractPanel[] homePanels;
    private AbstractPanel[] tableauPanels;
    private AbstractPanel fromPanel;
    private ViewInformer view;
    private AbstractCell[] freeCells;
    private AbstractCell[] homeCells;
    private AbstractCell[] tableauCells;
    private GridBagLayout grid;
    private GridBagConstraints con;
    private Container c = getContentPane();
    private JButton newGame;
    

    public GameView(){
    		view = new GameView.AppViewInformer();
    		fromPanel = null;
        grid = new GridBagLayout();
        con = new GridBagConstraints();
        con.fill = GridBagConstraints.HORIZONTAL;
        c = getContentPane();
        c.setLayout(grid);
        con.gridheight = 2;
        con.gridwidth = 8;
		
		game = new GameModel();
        
		freePanels = new FreePanel[4];
	    homePanels = new HomePanel[4];
	    tableauPanels = new TableauPanel[8];
	    
	    freeCells = game.getFreeCells();
	    homeCells = game.getHomeCells();
	    tableauCells = game.getTableauCells();
	   
	    for (int i = 0; i < 4; i++){
	    		freePanels[i] = new FreePanel(view);
	    		freePanels[i].setSize(100, 200);
	    		freePanels[i].setCells(freeCells[i]);
	    		con.anchor = con.PAGE_START;
	    		addToGrid(freePanels[i], i, 0, 125, 1, 0.5, 0.0);
	    		
	    		homePanels[i] = new HomePanel(view);
	    		homePanels[i].setSize(100,200);
	    		homePanels[i].setCells(homeCells[i]);
	    }
	    for (int j = 0; j < 4; j++){
	    		con.anchor = con.PAGE_START;
	    		addToGrid(homePanels[j], 4 + j, 0, 125, 1, 0.5, 0.0);
	    }
	    for (int k = 0; k < 8; k++) {
			tableauPanels[k] = new TableauPanel(view);
			tableauPanels[k].setSize(100,400);
			tableauPanels[k].setCells(tableauCells[k]);
			//con.anchor = con.CENTER;
			addToGrid(tableauPanels[k], k, 2, 500, 1, 1.0, 2.0);
	    }
		
        newGame = new JButton("New Game");
        newGame.setSize(200,50);
        newGame.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			reset();
        		}
        });
        con.anchor = con.PAGE_END;
        addToGrid(newGame, 3, 2, 25, 2, .5, 0.0);
        
        menuBar = new JMenuBar();
        menu = new JMenu("Colors");
        menuBar.add(menu);
        menuItem = new JMenuItem("BLUE");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("RED");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("GREEN");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("CYAN");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("PINK");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("WHITE");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("ORANGE");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("MAGENTA");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("YELLOW");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("GRAY");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        this.setJMenuBar(menuBar);
        
    }
    
    public void reset() {
    		game.reset();
    		freeCells = game.getFreeCells();
    	    homeCells = game.getHomeCells();
    	    tableauCells = game.getTableauCells();
    	    for (int i = 0; i < 4; i++){
	    		freePanels[i].setCells(freeCells[i]);
	    		homePanels[i].setCells(homeCells[i]);
	    }
    	    for (int j = 0; j < 8; j++) {
	    		tableauPanels[j].setCells(tableauCells[j]);
	    }
    	    repaint();
    }
    public void addToGrid(Component panel, int x, int y, int pady, int width, double wx, double wy) {
        con.ipady = pady;   
        con.ipadx = 100;
        con.gridx = x;    
        con.gridy = y;  
        con.weightx = wx;
        con.weighty = wy;
        con.gridwidth = width;  
        grid.setConstraints(panel, con);
        c.setLayout(grid);
        c.add(panel);
    }
    public void actionPerformed(ActionEvent e) {
    		JMenuItem source = (JMenuItem)e.getSource();
    		Color c;
    		if (source.getText() == "BLUE") c = Color.BLUE;
    		else if (source.getText() == "RED") c = Color.RED;
    		else if (source.getText() == "GREEN") c = Color.GREEN;
    		else if (source.getText() == "CYAN") c = Color.CYAN;
    		else if (source.getText() == "PINK") c = Color.PINK;
    		else if (source.getText() == "WHITE") c = Color.WHITE;
    		else if (source.getText() == "MAGENTA") c = Color.MAGENTA;
    		else if (source.getText() == "YELLOW") c = Color.YELLOW;
    		else if (source.getText() == "GRAY") c = Color.DARK_GRAY;
    		else c = Color.ORANGE;
    		for (int i = 0; i < 8; i++) {
    			if (i < 4) {
    				freePanels[i].setBackground(c);
    				homePanels[i].setBackground(c);
    			}
    			tableauPanels[i].setBackground(c);
    		}
    		repaint();
    }
    private class AppViewInformer implements ViewInformer {
    		private AppViewInformer() {}
		public void panelPressed(AbstractPanel c) {
			if (game.gameLost()) {
				JOptionPane.showMessageDialog(GameView.this, "No more moves");
			}
			else if (game.gameWon()) {
				JOptionPane.showMessageDialog(GameView.this, "You win");
			}
			else if (fromPanel == null){
				fromPanel = c;
				System.out.println("FromPanel is reset");
			}
			else if (c == fromPanel) {
				fromPanel = null;
				
				System.out.println("FromPanel was selected again");
			}
			else if (game.move(c.getCells(), fromPanel.getCells())) {
				c.repaint();
				fromPanel.repaint();
				fromPanel = null;
			}
			else {
				fromPanel = null;
				
				JOptionPane.showMessageDialog(GameView.this, "Illegal Move");
			}
			
		}
		
	}
}
        