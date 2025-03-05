public class MakingChange {
  public static void main(String[] args) {
    int money = Integer.parseInt(args[0]);

    int[] possible_coins = {200, 100, 25, 10, 5, 1};
    String[] coin_names = {"toonies", "loonies", "quarters", "dimes", "nickels", "pennies"};

    for (int i = 0; i < possible_coins.length; i++) {
      int num_coins = money / possible_coins[i];
      System.out.printf("Number of %s is: %d%n", coin_names[i], num_coins);
      money = money - (num_coins * possible_coins[i]);
    }
  }
}
