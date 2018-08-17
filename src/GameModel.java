import javafx.scene.control.Tab;

/**
 * Model of the entire FreeCell Game
 * @author Brian Legarth
 * @author Tommy Thetford
 * @author Chris Randall
 * @author Mark Donohue
 */
public class GameModel {
	private AbstractCell[] freeCells;
	private AbstractCell[] tableaux;
	private AbstractCell[] homeCells;
	private Deck deck;
	
	/**
	 * Constructs the Game Model Class
	 */
	public GameModel() {
		deck = new Deck();
		deck.shuffle();
		freeCells = new FreeCell[4];
		homeCells = new HomeCell[4];
		tableaux = new Tableau[8];
		for (int i = 0; i < 4; i++) {
			freeCells[i] = new FreeCell();
			homeCells[i] = new HomeCell();
		}
		
		for (int j = 0; j < 8; j++) {
			tableaux[j] = new Tableau();
		}
		for (int k = 0; k < 6; k++) {
			for (int l = 0; l<8; l++) {
				Card c = deck.deal();
				c.turn();
				tableaux[l].add(c);
			}	
		}
		for (int m = 0; m < 4; m++) {
			Card c = deck.deal();
			c.turn();
			tableaux[m].add(c);
		}
	}
	/**
	 * Checks to see if a game is lost.
	 * @return true if game is lost.
	 */
	
	public boolean gameLost() {
		//Check to see if free cells are empty
		for (int i = 0;i < 4; i++) {
			if (freeCells[i].isEmpty())
				return false;
		}
		for (AbstractCell tab : tableaux) {
			for (AbstractCell free : freeCells)
				if (tab.canAddFrom(free)) return false;
			for (AbstractCell home : homeCells)
				if (home.canAddFrom(tab)) return false;
		}
		int count = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++)
				if (i != j && tableaux[i].canAddFrom(tableaux[j])) {
					count+=1;
				}
			if (count > 1)
				return false;
		}
		return true;
	}
	public boolean gameWon() {
		for (int i = 0; i < 8; i++) {
			if (!(tableaux[i].inOrder(tableaux[i].getCardStack())))
				return false;
			}
		return true; 
	}
	
	
	/**
	 * Checks to see if the object can move a card form one cell to the other
	 * @param cell1 cell being removed from
	 * @param cell2 cell getting a card
	 * @param card card that will be removed
	 */
	public boolean move(AbstractCell cell1, AbstractCell cell2) {
		System.out.println(cell1.toString());
		System.out.println(cell2.toString());
		return cell1.addFrom(cell2);
	}
	/**
	 * Converts the model to a string
	 * @return string representation
	 */
	public String toString() {
		String result = "Freecells: 								Homecells:";
		for (int i = 0; i < 4; i++) {
			result += freeCells[i].toString() + "								" + homeCells[i].toString() + "\n";
		}
		result += "Tableau Board:";
		for (int i = 0; i < 8; i++) {
			result += tableaux[i].toString() + "\n";
		}
		return result;
	}	
	
	
	
	public void reset() {
		deck = new Deck();
		deck.shuffle();
		for (int i = 0; i < 4; i++) {
			freeCells[i] = new FreeCell();
			homeCells[i] = new HomeCell();
		}
		
		for (int j = 0; j < 8; j++) {
			tableaux[j] = new Tableau();
		}
		for (int k = 0; k < 6; k++) {
			for (int l = 0; l<8; l++) {
				Card c = deck.deal();
				c.turn();
				tableaux[l].add(c);
			}	
		}
		for (int m = 0; m < 4; m++) {
			Card c = deck.deal();
			c.turn();
			tableaux[m].add(c);
		}
	}
	
	public AbstractCell[] getFreeCells() {
		return freeCells;
	}
	
	public AbstractCell[] getTableauCells() {
		return tableaux;
	}
	
	public AbstractCell[] getHomeCells() {
		return homeCells;
	}
}