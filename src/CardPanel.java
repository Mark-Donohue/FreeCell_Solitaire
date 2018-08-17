import javax.swing.*;
import java.awt.*;


public class CardPanel extends JPanel {
	private AbstractCell cards;
	public ViewInformer view;
	
    /**
     * Constructor for an empty panel, displays a wire frame. 
     */
    public CardPanel(ViewInformer v){
    		setBackground(Color.GREEN);
    		view = v;
    }
    
    /**
     * Paints the card's face image if a card is present, otherwise, paints the back side image.
     */

    public void paintComponent(Graphics g){
	    	super.paintComponent(g);
	    	Icon image;
	    	if (cards.isEmpty()){
	    		image = Card.getBack();
	    		g.setColor(Color.yellow);
	    		int x = (getWidth() - image.getIconWidth()) / 2;
	    		g.drawRect(x, 0, image.getIconWidth(), image.getIconHeight());
	    	}
	    	else{
	    		int count = 0;
	    		for (Card c : cards) {
	    			c.turn();
	    			image = c.getImage();
	    			int x = (getWidth() - image.getIconWidth()) / 2;
	    			int y = count * 30;
	    			image.paintIcon(this, g, x, y);
	    			count++;
	    		}
	    	}
    	}
    public void setCells(AbstractCell cell) {
		cards = cell;
    }
    public AbstractCell getCells() {
    		return cards;
    }
    public boolean isEmpty() {
    		return cards.isEmpty();
    }
}

