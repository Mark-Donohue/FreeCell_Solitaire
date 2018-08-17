import javax.swing.*;
import java.awt.*;

/**
 * Represents the GUI component for painting an image of a playing card.
 * @author lambertk
 * @author Brian Legarth
 * @author Tommy Thetford
 * @author Chris Randall
 * @author Mark Donohue
 */

public class TableauPanel extends AbstractPanel{

    /**
     * Constructor for an empty panel, displays a wire frame. 
     */
    public TableauPanel(ViewInformer view){
        super(view);
    }


    /**
     * Paints the card's face image if a card is present, otherwise, paints the back side image.
     */
    public void paintComponent(Graphics g){
	    	super.paintComponent(g);
	    	AbstractCell cards = this.getCells();
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
	    			image = c.getImage();
	    			int x = (getWidth() - image.getIconWidth()) / 2;
	    			int y = count * 30;
	    			image.paintIcon(this, g, x, y);
	    			count++;
	    		}
	    	}
	}
    


}
