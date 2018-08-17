import java.util.*;
/**
 * Tableau cell in a game of free cell
 * @author Brian Legarth
 * @author Tommy Thetford
 * @author Chris Randall
 * @author Mark Donohue
 */

public class Tableau extends AbstractCell implements Cell {
	private ArrayList<Card> addable;
    /**
     * Checks to see if a card can be added and does it if possible
     * @param card card being checked
     * @return if the card was added
     */

    public boolean canAddCard(Card card){
    		int cardOrder = card.getSuit().getOrder();
    		Card topCard = this.peek();
    		if ((this.isEmpty())&&(card.getRank()==13)) {	
    			return true;
    		}
    		else if ((topCard.getRank()==card.getRank()+1)&&!(this.isEmpty())) {
    			int topCardOrder = topCard.getSuit().getOrder();
    			if (((topCardOrder==1)||(topCardOrder==4)) && ((cardOrder==2)||(cardOrder==3))) {
    				return true;
    			}
    			else if (((topCardOrder==2)||(topCardOrder==3)) && ((cardOrder==1)||(cardOrder==4))) {
    				return true;
    			}
    			else
    			{
    				return false;
    			}
    		}
    		else {
    			return false;
    		}   	
    }

    public boolean inOrder(ArrayList<Card> cardStack) {
        for (int i = 0; i < cardStack.size() - 1; i++) {
        		Card topCard = cardStack.get(i);
        		Card card = cardStack.get(i+1);
        		if (topCard.getRank()==card.getRank()+1) {
        			int topCardOrder = topCard.getSuit().getOrder();
        			int cardOrder = card.getSuit().getOrder();
        			if (!((((topCardOrder==1)||(topCardOrder==4)) && ((cardOrder==2)||(cardOrder==3)))||(((topCardOrder==2)||(topCardOrder==3)) && ((cardOrder==1)||(cardOrder==4))))) {
        				return false;
        			}
        		}
        		else
        			return false;
        		}
        return true;
    }
    public boolean canAddFrom(AbstractCell cell) {
    		if (cell.getCardStack().size() == 0) {
    			System.out.println("not sensing cards");
    			return false;
    		}
    		else
    		{
	        ArrayList<Card> cardStack = new ArrayList<Card>(cell.getCardStack());
	        System.out.println(cardStack.toString());
	        int size = cardStack.size();
	        addable = new ArrayList<Card>();
	        for (int i = 0; i < size;i++) {
	        		if (this.inOrder(cardStack) && this.canAddCard(cardStack.get(0))) {
	        			addable = cardStack;
	        			return true;
	        		}
	        		else
	        			cardStack.remove(0);
	        		}
	        return false;
    		}
    }   
    public boolean addFrom(AbstractCell cell) {
    		if (this.canAddFrom(cell)) {
    			for (Card x : addable) {
    				cell.remove();
    				this.add(x);
    			}
    			return true;
    		}
    		return false;
    }
}
    

