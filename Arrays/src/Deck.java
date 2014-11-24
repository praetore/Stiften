import java.util.Arrays;

/**
 * Een deck met Cards
 * 
 */
public class Deck {

	Card[] cardArray;

	/**
	 * Constructor. Maakt een deck met lengte 0.
	 */
	Deck() {
		cardArray = new Card[0];
	}

	/**
	 * Vult de array met 52 kaarten: 2,3 ... ,10,V,B,K,A van klaveren, schoppen,
	 * harten en ruiten.
	 */
	public void fill() {
		Suit[] suits = Suit.values();
		Number[] numbers = Number.values();
		for (int i = 0; i < suits.length; i++) {
			Suit suit = suits[i];
			for (int j = 0; j < numbers.length; j++) {
				Number number = numbers[j];
				cardArray = Arrays.copyOf(cardArray, cardArray.length + 1);
				cardArray[cardArray.length-1] = new Card(number, suit);
			}
		}
	}

	/**
	 * Zoals gezegd is dit spel een beetje vreemd. Bijvoorbeeld: spelers kunnen
	 * kaarten toevoegen aan het deck. Hierdoor kan het aantal kaarten groter
	 * worden dan 52.
	 * 
	 * @param card
	 *            een Kaart
	 * @param index
	 *            Op positie
	 */
	public void insertAt(Card card, int index) {
		cardArray = Arrays.copyOf(cardArray, cardArray.length + 1);
		for (int i = cardArray.length - 1; i > index; i--) {
			cardArray[i] = cardArray[i-1];
		}
		cardArray[index] = card;
	}

	/**
	 * Kaarten kunnen ook verwijderd worden uit het deck. delete Haalt de kaart
	 * met een bepaalde index er uit.
	 * 
	 * Merk op: na delete is de array zo groot als het aantal elementen dat er
	 * in zit.
	 * 
	 * @param index
	 */
	public void delete(int index) {
		for (int i = index; i < cardArray.length - 1; i++) {
			cardArray[i] = cardArray[i + 1];
		}
		cardArray = Arrays.copyOf(cardArray, cardArray.length - 1);
	}

	/**
	 * Schud alle kaarten zodat de volgorde 'willekeurig' is. Hiervoor is het
	 * toegestaan de Java Random generator te gebruiken.
	 * 
	 */
	public void shuffle() {

	}

	/**
	 * Utillity method for swapping cards in given indices
	 * 
	 * @param indexA
	 * @param indexB
	 */
	private void cardSwap(int indexA, int indexB) {
	}

	/**
	 * Een gegeven kaart moet worden opgezocht in de array, en de index ervan
	 * moet als return worden teruggegeven. Zie [Hubbard p.30]
	 * 
	 * @param card
	 *            de kaart die gezocht wordt
	 * @return De index van de gevonden kaart
	 */
	public int sequentialSearch(Card card) {
		int result = -1;
		return result;
	}

	/**
	 * Legt de kaarten op volgorde. We nemen aan dat een deck op volgorde ligt,
	 * als de volgorde hetzelfde is als na {@link #fill()}
	 */
	public void sort() {
	}

	/**
	 * Vertelt of het deck gesorteerd is.
	 * @return
	 */
	public boolean isSorted(){
		boolean sorted = true;
		//...
		return sorted;
	}

	/**
	 * Een bepaalde kaart moet worden opgezocht in de gesorteerde array op de
	 * binary search manier zoals besproken in [Hubbard p.31].
	 * 
	 * @param card
	 *            de kaart die gezocht wordt
	 * @return De index van de gevonden kaart
	 */
	public int binarySearch(Card card) {
		int result = -1;
		return result;
	}


	/**
	 * Pretty-print het deck.
	 */
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < cardArray.length; i++) {
			Card card = cardArray[i];
			res.append(card.toString() + '\n');
		}
		return res.toString() + '\n';
	}
	
	public int compareTo(Deck d){
		return 0;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Deck deck = (Deck) o;

		if (!Arrays.equals(cardArray, deck.cardArray)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return cardArray != null ? Arrays.hashCode(cardArray) : 0;
	}
}
