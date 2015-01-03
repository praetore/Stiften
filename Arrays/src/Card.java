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
		if (getNumberIndex(this.num) > getNumberIndex(card.getNum())) {
			return 1;
		} else if (getNumberIndex(this.num) < getNumberIndex(card.getNum())) {
			return -1;
		}
		return 0;
	}

	protected int getIndex(Card card) {
		return getNumberIndex(num) + (getSuitIndex(suit) * (
				Number.values().length * Suit.values().length));
	}

	private int getNumberIndex(Number num) {
		Number[] values = Number.values();
		int index = 0;
		while (values[index] != num) {
			index++;
		}
		return index;
	}

	private int getSuitIndex(Suit suit) {
		Suit[] values = Suit.values();
		int index = 0;
		while (values[index] != suit) {
			index++;
		}
		return index;
	}

	public Number getNum() {
		return num;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Card card = (Card) o;

		return num == card.num && suit == card.suit;
	}

	@Override
	public int hashCode() {
		int result = num != null ? num.hashCode() : 0;
		result = 31 * result + (suit != null ? suit.hashCode() : 0);
		return result;
	}
}
