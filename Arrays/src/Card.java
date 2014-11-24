public class Card implements Comparable<Card> {
	private final Number num;
	private final Suit suit;

	/**
	 * Constructor
	 *
	 * @param num Waarde van kaart
	 * @param suit Kleur van kaart
	 */
	Card(Number num, Suit suit) {
		this.num = num;
		this.suit = suit;
	}


	/**
	 * Pretty-print deze Card als string
	 */
	public String toString() {
		return num + " van " + suit;
	}

	/**
	 * Vergelijk twee kaarten.
	 */
	public int compareTo(Card card) {
		if (getIndex(this.num) > getIndex(card.getNum())) {
			return 1;
		} else if (getIndex(this.num) < getIndex(card.getNum())) {
			return -1;
		}
		return 0;
	}

	private int getIndex(Number num) {
		Number[] values = Number.values();
		int index = 0;
		while (values[index] != num) {
			index++;
		}
		return index;
	}

	public Number getNum() {
		return num;
	}
}
