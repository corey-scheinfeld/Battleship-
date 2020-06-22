/*
Corey Scheinfeld
CS201 Problem Set Two
Battleship

This program creates a game board with dimensions specified by the user. 5 ships of length 5, 4, 3, 3, and 2, are randomly placed throughout
the board. The user can then input different coordinates to a player board of the same dimensions. If the coordinates match those of a ship placement on
the game board, both boards update to include a hit. If the player misses, both boards update to include a miss. The boards are compared after each guess,
and if at anypoint they are identical, the game is over and the user has won.
*/

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

class Battleship{

  public String[][]game_board;
  public int attempts;
  public String [][]player_board;

  public Battleship(int m, int n){
    //Constructor
    player_board  = new String[m+1][n+1];
    create_player();
    game_board = new String[m+1][n+1];
    create_game();
    }

  public void create_game(){
    // Creates a game_board with 5 ships randomly placed throughout a board with dimensions specified by the user.
    String boats = " PSBC";
    Random rand = new Random();
    for(int i = 0; i<game_board.length; i++){
      for (int z = 0; z<game_board[0].length; z++){
          game_board[i][z]=  " - ";
      }}
    Boolean destroyer = true;
    for(int k = 5; k>1; k--){
      int direction = rand.nextInt(2);
      if(direction == 0){
      Boolean space_x = true;
      while(space_x){
      int x = rand.nextInt( (game_board.length-1))+1;
      int y = rand.nextInt( (game_board.length-k-1))+1;
      Boolean clear = true;
      for(int check = y; check < (y+k); check++){
        if(!(game_board[x][check].equals(" - "))){
          clear = false;
        }
      }
      if(clear == true){
        space_x= false;
        for (int ship = k; ship>0; ship--){
          game_board[x][y]= " "+boats.charAt(k-1)+" ";
          y++;
        }
        if(k==3 && destroyer== true){
          k++;
          boats = " PDBC";
        }
        destroyer = !destroyer;
      }}}
      else {
      Boolean space_y = true;
      while(space_y){
      int x2 = rand.nextInt((game_board.length-k-1))+1;
      int y2 = rand.nextInt((game_board.length-1))+1;
      Boolean clear = true;
      for(int check = x2; check < (x2+k); check++){
        if(!(game_board[check][y2].equals(" - "))){
          clear = false;
        }
      }
      if(clear == true){
        space_y = false;
        for (int ship = k; ship>0; ship--){
          game_board[x2][y2]= " "+boats.charAt(k-1)+" ";
          x2++;
        }
        if(k==3 && destroyer== true){
          k++;
          boats = " PDBC";
        }
        destroyer = !destroyer;
      }
    }
    }}}

public void create_player(){
  // Creates a blank player board with dimensions specified by the user.
  for(int i = 0; i<player_board.length; i++){
    for (int k = 0; k<player_board[0].length; k++){
        player_board[i][k]= " - ";
    }}
}
  public void board_return(){
    //Prints the player board.
    for(int i =0; i<player_board.length; i++){
      for (int k = 0; k<player_board[0].length; k++){
        if(k ==0 && i ==0){
          System.out.print("  ");
        }
        else if(k ==0 && i<10){
          System.out.print(String.valueOf(i)+" ");
        }
        else if(k == 0 && i >=10){
          System.out.print(String.valueOf(i));
        }
        else if(i ==0 && k<10){
          System.out.print(" "+String.valueOf(k)+" ");
        }
        else if(i == 0 && k >=10){
          System.out.print(" "+String.valueOf(k));
        }
        else {
          System.out.print(player_board[i][k]);

        }}
      System.out.println("");
    }}

  public void game_return(){
    //Prints the game board.
    for(int i =0; i<game_board.length; i++){
      for (int k = 0; k<game_board[0].length; k++){
        if(k ==0 && i ==0){
          System.out.print("  ");
        }
        else if(k ==0 && (i<=10)){
          System.out.print(String.valueOf(i)+" ");
        }
        else if(k == 0 && i >=10){
          System.out.print(String.valueOf(i));
        }
        else if(i ==0 && k<10){
          System.out.print(" "+String.valueOf(k)+" ");
        }
        else if(i == 0 && k >=10){
          System.out.print(" "+String.valueOf(k));
        }
        else {
          System.out.print(game_board[i][k]);

        }}
      System.out.println("");
    }}
  public Boolean game_over(){
    //Checks to see if the game board and the player board are identical. If so, returns false. If not, returns true.
    for(int i =0; i<game_board.length; i++){
      for (int k = 0; k<game_board[0].length; k++){
        if(!game_board[i][k].equals(" O ")){
          if(!game_board[i][k].equals(" - ")){
            if(!game_board[i][k].equals(" X ")){
              return true;
            }
          }
        }
      }
    }
    return false;
  }
  public void ship_ID(String ship_name){
    //Identifies which ship has been sunk.
    if(ship_name.equals(" C ")){
      System.out.println("Carrier Sunk!");
    }
    if(ship_name.equals(" B ")){
      System.out.println("Battleship Sunk!");
    }
    if(ship_name.equals(" D ")){
      System.out.println("Destroyer Sunk!");
    }
    if(ship_name.equals(" S ")){
      System.out.println("Submarine Sunk!");
    }
    if(ship_name.equals(" P ")){
      System.out.println("Patrol Boat Sunk!");
    }
  }
  public void board_comparison(int x, int y){
    //Compars scans the game board to see if the coordinates entered by the user contain a ship, and updates both boards accordingly.
    if(game_board[x][y] != " - " && game_board[x][y] != " O " ){
      player_board[x][y] = " X ";
      String check =   game_board[x][y];
      game_board[x][y] = " X ";
      System.out.println("Hit!");
      if(!check_for_sink(check).equals(" - ")){
        ship_ID(check_for_sink(check));
      }
      board_return();

      attempts++;
    }
    else{
      System.out.println("Miss.");
      player_board[x][y] = " O ";
      game_board[x][y] = " O ";
      board_return();
      attempts++;
    }
  }
  public String check_for_sink(String boat_name){
    //Checks to see if a boat has been sunk by searching for its letter value on the game board. Returns the string " - " if the boat has not been sunk, and the boat name if it has.
    for(int i =0; i<game_board.length; i++){
      for (int k = 0; k<game_board[0].length; k++){
        if(game_board[i][k].equals(boat_name)){
          return " - ";
        }
      }
    }
    return boat_name;
  }
  public static void main (String[] args){
    //Main
    System.out.print("Let's play Battleship! Enter board dimension (>10): ");
    Scanner sc = new Scanner(System.in);
    String value = sc.next();
    Battleship game = new Battleship(Integer.valueOf(value), Integer.valueOf(value));
    game.board_return();
    Boolean end_value = true;
    while(end_value){
    System.out.println("Enter a coordinate: ");
    System.out.print("X: ");
    int y_coordinate = sc.nextInt();
    System.out.print("Y: ");
    int x_coordinate = sc.nextInt();
    game.board_comparison(x_coordinate, y_coordinate);
    end_value = game.game_over();
  }
  System.out.println("Congratulations! All battleships have been destoryed in "+ String.valueOf(game.attempts)+ " tries!");

  }}
