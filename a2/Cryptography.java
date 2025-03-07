package a2;

import java.util.Random;

public class Cryptography {
  public static void main(String[] args) {

    System.out.println(crackCipher("jr Udqjhuv!", 10));
    System.out.println(permuteEncrypt("hello world!"));
    System.out.println(permuteEncrypt("HEllo World!"));

  }

  public static void shuffle(char[] letters){
    Random generator = new Random(12345);
    double number_of_swaps = Math.pow(letters.length,4);

    for (int i = 0; i < number_of_swaps; i++) {
      int index_1 = generator.nextInt(letters.length);
      int index_2 = generator.nextInt(letters.length);

      char helper_swap = letters[index_1];
      letters[index_1] = letters[index_2];
      letters[index_2] = helper_swap;
    }
  }

  public static char[] generatePermutation(){
    char [] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    shuffle(alphabet);

    return alphabet;
  }

  public static String permuteEncrypt(String input){
    char [] encryption_array = generatePermutation();

    StringBuilder encryptedOutput = new StringBuilder();

    for (int i = 0; i < input.length(); i++){
      char currentChar = input.charAt(i);
      char encrypted_char;

      if(Character.toUpperCase(currentChar) >= 'A' && Character.toUpperCase(currentChar) <= 'Z'){
        int encrypted_index = (int)Character.toUpperCase(currentChar) - (int)'A';
        encrypted_char = encryption_array[encrypted_index];

        if(Character.isLowerCase(currentChar)){
          encrypted_char = Character.toLowerCase(encrypted_char);
        }

      }
      else {
        encrypted_char = currentChar;
      }

      encryptedOutput.append(encrypted_char);

    }

    return encryptedOutput.toString();
  }

  public static String caesarEncrypt(String originalMessage, int shift) {
    StringBuilder encryptedMessage = new StringBuilder();
    shift = shift % 26;

    for (int i = 0; i < originalMessage.length(); i++) {
      char currentChar = originalMessage.charAt(i);
      char newChar = (char)(currentChar + shift);

      if((currentChar >= 'a' && currentChar <= 'z') || (currentChar >= 'A' && currentChar <= 'Z')){
        if ((newChar > 'z' && Character.isLowerCase(currentChar))
            || (newChar > 'Z' && Character.isUpperCase(currentChar))) {
          newChar = (char) (currentChar - (26 - shift));
        }
      } else {
        newChar = currentChar;
      }
      encryptedMessage.append(newChar);
    }
    return encryptedMessage.toString();
  }

  public static String caesarDecrypt(String encryptedMessage, int shift) {
    int decrypt_shift = 26 - (shift % 26);
    return caesarEncrypt(encryptedMessage, decrypt_shift);
  }

  public static String crackCipher(String encoded, int numberLetters){
    String return_string = "";
    int max_english_words = 0;

    for (int i = 0; i < numberLetters; i++) {
      String candidate_string = caesarDecrypt(encoded, i);
      int current_english_words = SentenceChecker.countEnglishWords(candidate_string);

      if (current_english_words > max_english_words) {
        return_string = candidate_string;
        max_english_words = current_english_words;
      }

    }

    return return_string;
  }
}
