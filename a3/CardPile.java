package a3;

import java.util.ArrayList;
import java.util.Collections;

public class CardPile {
  private ArrayList<Card> cards;

  public CardPile(){
    this.cards = new ArrayList<Card>();
  }

  public int getNumCards(){
    return this.cards.size();
  }

  public boolean isEmpty(){
    return this.cards.isEmpty();
  }

  public void addToBottom(Card c){
    this.cards.add(c);
  }

  public Card get(int i){
    return this.cards.get(i);
  }

  public Card remove(int i){
    return this.cards.remove(i);
  }

// TODO - need to override equals for Card object
  public int find(Suit s, Value v){
    for (int i = 0; i < this.getNumCards(); i++){
      if (this.cards.get(i).getSuit() == s && this.cards.get(i).getValue() == v){
        return i;
      }
    }
    return -1;
  }

  public String toString(){
    StringBuilder returnString = new StringBuilder();

    for (int i =0; i < this.getNumCards(); i++){
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

    Collections.shuffle(cardPile.cards);
    return cardPile;
  }

  public static CardPile makeFullDeck(int n){
    CardPile cardPile = new CardPile();

    for (int i = 0; i < n; i++){
      CardPile temp = makeFullDeck();
      cardPile.cards.addAll(temp.cards);
    }
    return cardPile;
  }
}
