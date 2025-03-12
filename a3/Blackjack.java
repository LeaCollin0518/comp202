package a3;

import static a3.CardPile.makeFullDeck;
import java.util.Scanner;

public class Blackjack {

  public enum Results{
    DEALER_WINS,
    PLAYER_WINS,
    TIE,
    BLACKJACK
  }

  public static int getScore(Card c){
    return c.getValue().getValue();
  }

  public static int countValues(CardPile p){
    int totalScore = 0;
    boolean acePresent = false;

    for (int i = 0; i < p.getNumCards(); i++){
      totalScore += getScore(p.get(i));

      if (p.get(i).getValue() == Value.ACE){
        acePresent = true;
      }
    }
    if (acePresent && totalScore > 21){
      totalScore -= 10;
    }

    return totalScore;
  }

  public static Results playRound(CardPile gamePile){
    CardPile player = new CardPile();
    CardPile dealer = new CardPile();

    // TODO - what's a better way to do this?
    player.addToBottom(gamePile.remove(gamePile.getNumCards() - 1));
    player.addToBottom(gamePile.remove(gamePile.getNumCards() - 1));

    dealer.addToBottom(gamePile.remove(gamePile.getNumCards() - 1));
    dealer.addToBottom(gamePile.remove(gamePile.getNumCards() - 1));

    int playerScore = countValues(player);
    int dealerScore = countValues(dealer);
    int dealerValue = getScore(dealer.get(1));

    System.out.println("Your score is: " + playerScore);
    System.out.println("The dealer's second card is: " + dealerValue);

    if (playerScore==21 && dealerScore!=21){
      return Results.BLACKJACK;
    }

    if (playerScore==21 && dealerScore==21){
      return Results.TIE;
    }

    // player plays
    while (playerScore < 21){
      Scanner sc = new Scanner(System.in);  // Create a Scanner object
      System.out.println("Do you want to hit or stay?");

      String userInput = sc.next();

      if(userInput.equals("hit")){
        player.addToBottom(gamePile.remove(gamePile.getNumCards() - 1));
        playerScore = countValues(player);
        System.out.println("Your score is now: " + playerScore);
      }
      else if (userInput.equals("stay")){
        break;
      }
    }

    System.out.println("Your final score is: " + playerScore);
    System.out.println(player);

    if(playerScore == 21){
      return Results.PLAYER_WINS;
    }

    if(playerScore > 21){
      return Results.DEALER_WINS;
    }

    System.out.println();
    System.out.println("The dealer's current score is: " + dealerScore);
    System.out.println("The dealer will now play.");

    // dealer plays
    while (dealerScore < 18){
      dealer.addToBottom(gamePile.remove(gamePile.getNumCards() - 1));
      dealerScore = countValues(dealer);
    }

    System.out.println("The dealer final score is: " + dealerScore);
    System.out.println(dealer);
    System.out.println("A reminder that your final score is: " + playerScore);

    if (dealerScore == 21){
      return Results.DEALER_WINS;
    }

    if (dealerScore > 21){
      return Results.PLAYER_WINS;
    }

    if (dealerScore > playerScore){
      return Results.DEALER_WINS;
    }

    if (dealerScore < playerScore){
      return Results.PLAYER_WINS;
    }

    return Results.TIE;

  }

  public static void main(String[] args) {
    int numChips = Integer.parseInt(args[0]);
    CardPile blackJack = makeFullDeck(4);

    while (numChips > 0){
      Scanner sc1 = new Scanner(System.in);
      System.out.println("How much do you want to bet? You have " + numChips + " chips.");
      int bet = sc1.nextInt();

      while (bet > numChips){
        System.out.println("That bet is too high. Please enter a bet between 1 and " + numChips);
        bet = sc1.nextInt();
      }

      Results result = playRound(blackJack);
      System.out.println(result);
      System.out.println();

      if (result.equals(Results.BLACKJACK)){
        numChips += ((int) (bet * 1.5));
      }
      else if (result.equals(Results.PLAYER_WINS)){
        numChips += bet;
      }
      else if (result.equals(Results.DEALER_WINS)){
        numChips -= bet;
      }
    }
  }
}
