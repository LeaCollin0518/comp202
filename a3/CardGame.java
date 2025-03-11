package a3;

import static a3.CardPile.makeFullDeck;

public class CardGame {
  public static void main(String[] args) {
    CardPile cards = makeFullDeck();

    int numPlayers = Integer.parseInt(args[0]);

    CardPile[] cardPiles = new CardPile[numPlayers];
    for (int i = 0; i < numPlayers; i++) {
      cardPiles[i] = new CardPile();
    }

    int playerTurn = 0;

    while (!cards.isEmpty()) {
      if (playerTurn == numPlayers) {
        playerTurn = 0;
      }
      Card c = cards.remove(cards.getNumCards() - 1);
      cardPiles[playerTurn].addToBottom(c);

      playerTurn++;
    }

    for (int i = 0; i < numPlayers; i++) {
      System.out.println(cardPiles[i].toString());
      int aceOfSpades = cardPiles[i].find(Suit.SPADES, Value.ACE);
      if (aceOfSpades != -1) {
        System.out.println("Player " + i + " is the winner!");
      }
      System.out.println();
    }
  }
}
