public class DnaUtilities {
  public static boolean isValidBase(char base){
    return (base == 'A') || (base == 'C') || (base == 'G') || (base == 'T');
  }

  public static char watsonCrickComplement(char base){
    if (!isValidBase(base)){
      return base;
    }
    else if(base == 'A'){
      return 'T';
    }
    else if(base == 'T'){
      return 'A';
    }
    else if(base == 'C'){
      return 'G';
    }
    else if(base == 'G'){
      return 'C';
    }
    else{
      return base;
    }
  }

  public static String watsonCrickTripletComplement(String dnaSequence){
    if (dnaSequence.length() != 3){
      return "";
    }

    String return_string = "";

    for (int i = 0; i < dnaSequence.length(); i++){
      char complement_char = watsonCrickComplement(dnaSequence.charAt(i));
      if (isValidBase(complement_char)){
        return_string += complement_char ;
      }
      else{
        return "";
      }
    }
    return return_string;
  }

  public static void main(String[] args) {
    System.out.println(watsonCrickTripletComplement("ATA"));
  }
}
