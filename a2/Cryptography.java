package a2;

public class Cryptography {
  public static void main(String[] args) {
    System.out.println(caesarEncrypt("zzz!", 29));
    System.out.println(caesarDecrypt("aaa!", 29));
    System.out.println(caesarEncrypt("go Rangers!", 3));
    System.out.println(caesarDecrypt("jr Udqjhuv!", 3));
  }

  public static String caesarEncrypt(String originalMessage, int shift) {
    String encryptedMessage = "";
    shift = shift % 26;

    for (int i = 0; i < originalMessage.length(); i++) {
      char currentChar = originalMessage.charAt(i);
      char newChar = (char)(currentChar + shift);

      if((currentChar >= 'a' && currentChar <= 'z') || (currentChar >= 'A' && currentChar <= 'Z')){
        if ((newChar > 'z' && Character.isLowerCase(currentChar))
            || (newChar > 'Z' && Character.isUpperCase(currentChar))) {
          newChar = (char) (currentChar - (26 - shift));
        }
      }
      else{
        newChar = currentChar;
      }
      encryptedMessage += newChar;
    }
    return encryptedMessage;
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
