public class LeapYearCalculator {
  public static void main(String[] args) {
//    printIsLeapYear(1000);
//    printIsLeapYear(2000);
//    printIsLeapYear(1994);
    System.out.println(subsequentLeapYear(2006));
  }

  public static boolean isLeapYear(int year) {
    return year % 4 == 0 & year % 100 != 0 || year % 400 == 0;
  }

  public static void printIsLeapYear(int year) {
    if (isLeapYear(year))
      System.out.println(year + " is a leap year");
    else
      System.out.println(year + " is not a leap year");
  }

  public static int subsequentLeapYear(int year) {
    int current_year = year;
    if (isLeapYear(current_year))
      current_year += 4;
    else
      current_year += 1;

    while (true)
      if (isLeapYear(current_year))
        return current_year;
      else
        current_year += 1;
  }
}
