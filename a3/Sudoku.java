package a3;

public class Sudoku {
  public static void main(String[] args) {
    int [][] board = {
        {5, 3, 4, 6, 7, 8, 9, 1, 2},
        {6, 3, 2, 1, 9, 5, 3, 4, 8},
        {1, 9, 8, 3, 4, 2, 5, 6, 7},
        {8, 5, 9, 7, 6, 1, 4, 2, 3},
        {4, 2, 6, 8, 5, 3, 7, 9, 1},
        {7, 1, 3, 9, 2, 4, 8, 5, 6},
        {9, 6, 1, 5, 3, 7, 2, 8, 4},
        {2, 8, 7, 4, 1, 9, 6, 3, 5},
        {3, 4, 5, 2, 8, 6, 1, 7, 9}};

    System.out.println(isSudoku(board));

  }

  public static boolean isSudoku(int[][] solutionCandidate){
    if (!checkBoardDims(solutionCandidate)){
      return false;
    }

    for (int row = 0; row < solutionCandidate.length; row++) {
      if(!checkValidArray(solutionCandidate[row])){
        return false;
      }
    }

    for (int col = 0; col < solutionCandidate[0].length; col++) {
      int [] candidateCol = getColumn(solutionCandidate, col);
      if (!checkValidArray(candidateCol)){
        return false;
      }
    }

    for (int i = 0; i < 9; i+=3){
      for (int j = 0; j < 9; j+=3){
        int [] flattenedSubGrid = flatten(subGrid(solutionCandidate, i, j, 3));
        if (!checkValidArray(flattenedSubGrid)){
          return false;
        }
      }
    }

    return true;
  }

  public static boolean checkBoardDims(int[][] board){
    if (board.length == board[0].length && board.length == 9) {
      return true;
    }
    return false;
  }

  public static boolean checkValidArray(int [] a){
    if (a.length != 9) {
      return false;
    }

    int[] sorted = sort(a);

    if (sorted[0] != 1 || sorted[a.length - 1] != 9) {
      return false;
    }

    if (!uniqueEntries(sorted)){
      return false;
    }

    return true;
  }

  public static int[] sort(int [] input){
    int [] output = input.clone();

    for (int i = 1; i < input.length; i++) {
      for (int j = 0; j < i; j++) {

        if(output[i] < output[j]){
          int temp = output[i];
          output[i] = output[j];
          output[j] = temp;
        }
      }
    }
    return output;
  }

  public static boolean uniqueEntries(int [] input){
    int [] sorted_input = sort(input);

    for (int i = 0; i < sorted_input.length - 1; i++) {
      if (sorted_input[i] == sorted_input[i + 1]){
        return false;
      }
    }
    return true;
  }

  public static int[] getColumn(int[][] board, int j){
    int [] jColumn = new int[board.length];

    for (int i = 0; i < board.length; i++) {
      jColumn[i] = board[i][j];
    }
    return jColumn;
  }

  public static int[] flatten(int[][] board){
    int [] flattened = new int[board.length * board[0].length];
    int currIndex = 0;

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        flattened[currIndex] = board[i][j];
        currIndex++;
      }
    }
    return flattened;
  }

  public static int[][] subGrid(int[][] board, int index1, int index2, int size){
    int[][] returnGrid = new int[size][size];

    int pos1 = 0;
    int pos2 = 0;

    for (int i = index1; i < index1 + size; i++){
      for (int j = index2; j < index2 + size; j++){
        returnGrid[pos1][pos2] = board[i][j];
        pos2++;
      }
      pos1++;
      pos2 = 0;
    }
    return returnGrid;
  }
}
