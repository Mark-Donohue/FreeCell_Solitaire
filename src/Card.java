import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Represents a playing card with a suit, rank, image, and face up status.
 * @author lambertk
 * @author Brian Legarth
 * @author Tommy Thetford
 * @author Chris Randall
 * @author Mark Donohue
 */

public class Card implements Comparable<Card>{

    private Suit suit;
    private int rank;
    private boolean faceUp;
    private Icon image;
    private static Icon CARD_BACK;

    /**
     * Constructor.
     * @param suit the card's suit
     * @param rank the card's rank
     */
    public Card(Suit suit, int rank){
    	this.suit = suit;
    	this.rank = rank;
    	faceUp = false;
    	image = getImageFromFile(rank, suit);
    	if (CARD_BACK == null)
    		CARD_BACK = getBackFromFile();
    }

    /**
     * Returns the card's face image if its face is up or its back side image otherwise.
     * @return the card's face image or the back side image
     */
    public Icon getImage(){
    	if (faceUp)
    	    return image;
    	else
    	    return CARD_BACK;
    }

    /**
     * Returns the back side image of a card.
     * @return the back side image of a card
     */
    public static Icon getBack(){
    	if (CARD_BACK == null)
    	    new Card(Suit.spade, 1);
    	return CARD_BACK;
    }

    /**
     * Turns the card over, negating its face up status.
     */
    public void turn(){
    	faceUp = ! faceUp;
    }

    /**
     * Obtains an image of a card from DECK folder
     * @param rank the card's rank
     * @param suit the card's suit
     * @return ImageIcon the image of a card
     */
    private Icon getImageFromFile(int rank, Suit suit){
	    	String fileName = "DECK/";
	    	fileName += rank;
	    	fileName += Character.toUpperCase(suit.toString().charAt(0));
	    	fileName += ".GIF";
	    	return new ImageIcon(getClass().getResource(fileName));
    }

    /**
     * Obtains image of back of a card from DECK folder
     * @return ImageIcon back of a card
     */
    private Icon getBackFromFile(){
	    	String fileName = "DECK/CARDBACK.GIF";
	    	return new ImageIcon(getClass().getResource(fileName));
    }

    /**
     * Returns the card's face up status.
     * @return true if face up or false otherwise
     */
    public boolean isFaceUp(){
       return faceUp;
    }

    /**
     * Returns the card's suit.
     * @return the card's suit
     */
    public Suit getSuit(){
        return suit;
    }

    /**
     * Returns the card's rank
     * @return the card's rank
     */
    public int getRank(){
        return rank;
    }

    /**
     * Compares two cards with respect to rank
     * @return 0 if equal, less than 0 if less, greater than 0 if greater
     */
    public int compareTo(Card other){
        return this.rank - other.rank;
    }

    /**
     * Returns the string representation of the card (rank of suit)
     * @return the string representation of the card
     */
    public String toString(){
        return rankToString(rank) + " of " + suit;
    }

    /**
     * Determines face card by value of rank 
     * @param rank
     * @return string name of a face card
     */
    static private String rankToString(int rank){
        if (rank >= 2 && rank <= 10) return rank + "";
        else if (rank == 11) return "Jack";
        else if (rank == 12) return "Queen";
        else if (rank == 13) return "King";
        else return "Ace";
    }
}

