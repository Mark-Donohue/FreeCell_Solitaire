import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class AbstractPanel extends JPanel {
	private AbstractCell cards;
	private ViewInformer view;
	
    /**
     * Constructor for an empty panel, displays a wire frame. 
     */
    public AbstractPanel(ViewInformer v){
    		addMouseListener(new PanelListener(this));
    		view = v;
    		setBackground(Color.BLUE);
    }
    
    public void paintComponent(Graphics g){
    		super.paintComponent(g);
    }
    
    private class PanelListener extends MouseAdapter {
    		AbstractPanel panel;
    		private PanelListener (AbstractPanel p) {
    			panel = p;
    		}
		public void mousePressed(MouseEvent e) {
    			view.panelPressed(panel); 
		}
    }
    /**
     * Paints the card's face image if a card is present, otherwise, paints the back side image.
     */
    public void setCells(AbstractCell cell) {
		cards = cell;
    }
    
    /**
     * Returns cards collection
     * @return cards collection
     */
    public AbstractCell getCells() {
    		return cards;
    }
    
    /**
     * Checks if cards is empty
     * @return if cards is empty
     */
    public boolean isEmpty() {
    		return cards.isEmpty();
    }
    
    /**
     * Returns the top card
     * @return the top card 
     */
    public Card topCard() {
    		return cards.peek();
    }
}