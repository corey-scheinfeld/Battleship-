/*
Corey Scheinfeld
CS201 Problem Set Three
Connect Four

This program creates a game board with dimensions specified by the user. The interface then alternates between two players. Each player has an individual name and symbol.
The player chooses a column on the board, and a symbol drops to the bottom of that column. Each valid turn is registered to an individual player Vector as a coordinate array.
After a player has made at least 4 moves, a method iterates through the vector to check if any point has three other points next to it on the left, the right, vertically, or diagonally.
If so, that player wins. The function then asks if the user wants to play again, presenting the score as of the last game.
*/


import java.util.Scanner;
import java.util.Vector;

class ConnectFour{

  public String [][]player_board;
  public Player player_one;
  public Player player_two;
  public Player player;

  public ConnectFour(int m, int n){
    //Constructor
    player_board  = new String[m][n+1];
    create_player();
    player_one = new Player(player_board, " X ");
    player_two = new Player(player_board, " O ");
    }

  public void create_player(){
    // Creates a blank player board with dimensions specified by the user.
    for(int i = 0; i<player_board.length; i++){
      for (int k = 0; k<player_board[0].length; k++){
          if(i == player_board.length-1){
            player_board[i][k]= " "+String.valueOf(k)+" ";
          }
          else{
          player_board[i][k]= " - ";
        }
      }}
  }

  public boolean isWinner(Vector <int[]> previous_moves){
  //Iterates through player vector previous_moves to determine if any point has three other vector points next to it on the left, the right, vertically, or diagonally. Returns a boolean value.
  if(previous_moves.size()>=4){
  for(int i = 0; i<previous_moves.size(); i++ ){
    int replace = i;
    int counter_1 = 1; int counter_2 =1; int counter_3 = 1; int counter_4 = 1; int counter_5 = 1;
    for (int k = 0; k<previous_moves.size(); k++){
      for(int j = 1; j<4; j++){
      if(previous_moves.get(k)[0]==previous_moves.get(i)[0]){
        if(previous_moves.get(k)[1]==previous_moves.get(i)[1]+j){
          counter_1++;
        }}
      if(previous_moves.get(k)[0]==previous_moves.get(i)[0]){
        if( previous_moves.get(k)[1]==previous_moves.get(i)[1]-j){
            counter_2++;          }}
      if(previous_moves.get(k)[1]==previous_moves.get(i)[1]){
        if(previous_moves.get(k)[0]==previous_moves.get(i)[0]-j){
          counter_3++;
        }}
      if(previous_moves.get(k)[1]==previous_moves.get(i)[1]+j){
        if(previous_moves.get(k)[0]==previous_moves.get(i)[0]-j){
          counter_4++;
          }}
      if(previous_moves.get(k)[1]==previous_moves.get(i)[1]-j){
        if( previous_moves.get(k)[0]==previous_moves.get(i)[0]-j){
          counter_5++;
        }
      }
      if(counter_1 >=4 || counter_2 >=4 || counter_3 >=4 || counter_4 >=4 || counter_5 >=4){
        return false;
      }}}
}
  return true;
}
  else{
    return true;
  }}

  public void board_return(){
    //Prints the player board.
    for(int i =0; i<player_board.length; i++){
      for (int k = 0; k<player_board[0].length; k++){
          System.out.print(player_board[i][k]);

    }System.out.println("");}
  }

  public static void main(String[] args) {
    //main
    System.out.print("Lets play Connect Four! Enter the width of the board (>=7): ");
    Scanner sc = new Scanner(System.in);
    int width = sc.nextInt();
    System.out.print("Enter the height of the board (>=6): ");
    int height = sc.nextInt();
    System.out.print("Enter player one name: ");
    String player1 = sc.next();
    System.out.print("Enter player two name: ");
    String player2 = sc.next();
    boolean again = true;
    int pl1W = 0; int pl1L = 0; int pl2W = 0; int pl2L = 0;
    while(again){
    ConnectFour game = new ConnectFour(width, height);
    game.board_return();
    boolean game_over = true;
    boolean turn = true;
    while(game_over){
      if(turn == true){
        game.player_board = game.player_one.new_move(player1);
        game.board_return();
        game_over = game.isWinner(game.player_one.prev_moves);
        if(!game_over){
          System.out.print(player1);
          pl1W++;
          pl2L++;
        }
      }
      if(turn == false){
        game.player_board = game.player_two.new_move(player2);
        game.board_return();
        game_over = game.isWinner(game.player_two.prev_moves);
        if(!game_over){
          System.out.print(player2);
          pl2W++;
          pl1L++;
        }
      }
      turn = !turn;

    }
    System.out.printf(" wins! %s is %d-%d. %s is %d-%d. Would you like to play again? (Y/N): ", player1, pl1W, pl1L, player2, pl2W, pl2L);
    String round = sc.next();
    if(round.toLowerCase().equals("n")){
      again = false;
    }
  }
  }
}

class Player{

public String[][] player_board;
public Vector <int[]> prev_moves;
public String name;

public Player(String[][] board, String letter){
  //Constructor
  prev_moves = new Vector<int[]>();
  player_board = board;
  name = letter;
}

public String[][] new_move(String player_name){
  // Takes a two-dimensional array as an argument and inserts a piece based off of the column entered by the user. Returns the updated array.
  int col;
  while(true){
  System.out.print(player_name+"\'s turn! Enter a column number: ");
  Scanner sc = new Scanner(System.in);
  String input = sc.next();
  char character = input.charAt(0);
  if(input.length()==1 && Character.isDigit(character)){
    col = Integer.valueOf(input);
    break;
  }
  else{
    System.out.println("Invalid Input. ");
  }}

    for (int k = (Integer.valueOf(player_board.length)-1); k>=0; k--){
      if(player_board[k][col]==" - "){
        player_board[k][col]= name;
        int[] location = new int[2];
        location[0] = Integer.valueOf(k);
        location[1] = Integer.valueOf(col);
        prev_moves.add(location);
        return player_board;
      }

    }
    System.out.println("Invalid Input. ");
    return player_board;
}}
