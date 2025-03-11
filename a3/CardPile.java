package a3;

public class CardPile {
  private Card[] cards;
  private int numCards;
  public CardPile(){
    this.cards = new Card[52];
    this.numCards = 0;
  }

  public int getNumCards(){
    return this.numCards;
  }

  public void addToBottom(Card c){
    this.cards[this.numCards] = c;
    this.numCards++;
  }

  public boolean isEmpty(){
    return this.numCards == 0;
  }

  public Card get(int i){
    if (i > this.numCards && i < this.cards.length){
      return null;
    }

    return this.cards[i];
  }

  public Card remove(int i){
    if (this.isEmpty()){
      return null;
    }

    Card c = this.get(i);

    for (int j = i+1; j < this.numCards; j++){
      this.cards[j-1] = this.get(j);
    }

    this.numCards--;
    return c;
  }

  public int find(Suit s, Value v){
    for (int i = 0; i < this.numCards; i++){
      if (this.cards[i].getSuit() == s && this.cards[i].getValue() == v){
        return i;
      }
    }
    return -1;
  }

  public String toString(){
    StringBuilder returnString = new StringBuilder();

    for (int i =0; i < this.numCards; i++){
      String cardString = this.get(i).toString();
      returnString.append(i).append(".").append(cardString).append(" ");
    }

    return returnString.toString();
  }

  public static CardPile makeFullDeck(){
    CardPile cardPile = new CardPile();

    for (Suit s : Suit.values()){
      for (Value v : Value.values()){
        Card c = new Card(s, v);
        cardPile.addToBottom(c);
      }
    }
    UtilityCode.shuffle(cardPile.cards, cardPile.numCards);

    return cardPile;
  }
}
