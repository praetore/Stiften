import java.util.Arrays;
import java.util.Random;

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
		final Suit[] suits = Suit.values();
		final Number[] numbers = Number.values();
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
		final Card[] shuffled = new Card[cardArray.length];
		int[] unshuffled = new int[cardArray.length];
		for (int i = 0; i < unshuffled.length; i++) {
			unshuffled[i] = i;
		}
		final Random random = new Random();
		for (int i = 0; i < cardArray.length; i++) {
			int idx = random.nextInt(unshuffled.length);
			shuffled[i] = cardArray[unshuffled[idx]];
			if (unshuffled[idx] != unshuffled[unshuffled.length - 1]) {
				for (int j = idx; j < unshuffled.length; j++) {
					unshuffled[idx] = unshuffled[idx + 1];
				}
			}
			unshuffled = Arrays.copyOf(unshuffled, unshuffled.length - 1);
		}
		cardArray = shuffled;
	}

	/**
	 * Utility method for swapping cards in given indices
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
		int idx = 0;
		try {
			while (!cardArray[idx].equals(card)) {
				idx++;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			idx = -1;
		}
		return idx;
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
		final Suit[] suits = Suit.values();
		final Number[] numbers = Number.values();
		for (int i = 0; i < suits.length; i++) {
			Suit suit = suits[i];
			for (int j = 0; j < numbers.length; j++) {
				Number number = numbers[j];
				Card check = new Card(number, suit);
				Card currentCard = cardArray[(j + (i * (numbers.length)))];
				int resCompare = currentCard.compareTo(check);
				if (resCompare != 0) {
					return false;
				}
			}
		}
		return true;
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
		int idx = 0;
		try {
			while (!cardArray[idx].equals(card)) {
				idx++;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return -1;
		}
		return binarySearch(card, cardArray);
	}

	private int binarySearch(Card card, Card[] partialArray) {
		int idx = 0;
		int mid = partialArray.length / 2;
		while (!partialArray[idx].equals(card)) {
			idx++;
		}

		if (partialArray.length > 1) {
			if (idx < mid) {
				return binarySearch(card, Arrays.copyOfRange(partialArray, 0, mid));
			} else if (idx > mid) {
				return binarySearch(card, Arrays.copyOfRange(partialArray, mid, partialArray.length));
			} else if (idx == mid) {
				return 0;
			}
		}
		if (idx == mid) {
			return 0;
		}
		return -1;
	}


	/**
	 * Pretty-print het deck.
	 */
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		for (Card card : cardArray) {
			res.append(card.toString()).append('\n');
		}
		return res.toString() + '\n';
	}
	
	public int compareTo(Deck d) {
		if (cardArray.length > d.cardArray.length) {
			return 1;
		} else if (cardArray.length < d.cardArray.length) {
			return -1;
		}

		int total = 0;
		for (int i = 0; i < cardArray.length; i++) {
			Card card = cardArray[i];
			total += card.compareTo(d.cardArray[i]);
		}
		return total;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Deck deck = (Deck) o;

		return Arrays.equals(cardArray, deck.cardArray);
	}

	@Override
	public int hashCode() {
		return cardArray != null ? Arrays.hashCode(cardArray) : 0;
	}
}