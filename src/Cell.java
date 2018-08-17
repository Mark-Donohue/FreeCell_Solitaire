import java.util.Iterator;
import java.util.*;

/**
 * Interface that makes all the different cells available
 * @author Brian
 * @author Chris Randall
 * @author Mark Donohue
 * @author Tommy Thetford
 */
public interface Cell extends Iterable<Card> {
	
	/**
	 * Checks to see if a card can be added to the cell
	 * @param card being added
	 * @return if it was added
	 */
	public void add(Card c);
	
	/**
	 * Removes the top card from the cell
	 */
	public void remove();
	
	/**
	 * Checks to see if the cell is empty
	 * @return if the card stack is empty
	 */
	public boolean isEmpty();
	
	
	/**
	 * Returns the card in the top of the cell
	 * @return top card of cell
	 */
	public Card peek();
	
	/**
	 * Returns an iterator on the cell object between cards
	 * @return an iterator through cards in cell
	 */
	public Iterator<Card> iterator();
	
	/**
	 * Returns a string representation of the cell
	 * @return string representation
	 */
	public String toString();
	/**
	* Returns the cell's card stack
	* @return the list containing the cards
	*/
	public ArrayList<Card> getCardStack();
	/**
	* Determines if a list's cards are in order
	* @param cardStack - the list to be checkedd
	* @return a boolean value representing whether or not the cards are in order
	*/
	public boolean inOrder(ArrayList<Card> cardStack);
	
}