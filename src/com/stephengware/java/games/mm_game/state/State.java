package com.stephengware.java.games.mm_game.state;

import java.util.ArrayList;

/**
 * Represents the current state of a Tic Tac Toe game.
 * 
 * @author Stephen G. Ware
 */
public class State {
	
	/** The 9 square grid */
	private final Player[][] grid;
	
	/** The player who will move next */
	private final Player current;
	
	/** The player who has won (if any) */
	private final Player winner;

	/**
	 * Constructs the new state that would result from taking a given
	 * {@link Move} in some given previous state.
	 * 
	 * @param previous the state of the game before the given move is taken
	 * @param move the move to be taken
	 */
	private State(State previous, Move move) {
		grid = previous.grid.clone();
		for(int i=0; i<grid.length; i++)
			grid[i] = grid[i].clone();
		if(previous.winner != null)
			throw new IllegalStateException("The game has ended; no more moves can be taken.");
		if(grid[move.row][move.column] != null)
			throw new IllegalStateException("Position (" + move.row + "," + move.column + ") is already occupied.");
		if(move.player != previous.current)
			throw new IllegalStateException("It is not player " + move.player + "'s turn.");
		grid[move.row][move.column] = move.player;
		if(move.player == Player.X)
			current = Player.O;
		else
			current = Player.X;
		if(checkWin(move.player))
			winner = move.player;
		else
			winner = null;
	}
	
	/**
	 * Checks if a given player has won.
	 * 
	 * @param player the candidate player
	 * @return true if the player has won, false otherwise
	 */
	private final boolean checkWin(Player player) {
		for(int i = 0; i < 3; i++)
			if(checkRow(i, player) || checkColumn(i, player))
				return true;
		if(getSquare(1, 1) == player) {
			if(getSquare(0, 0) == player && getSquare(2, 2) == player)
				return true;
			else if(getSquare(0, 2) == player && getSquare(2, 0) == player)
				return true;
		}
		return false;
	}
	
	/**
	 * Checks if a given player has placed 3 marks on the given row.
	 * 
	 * @param row the row of the grid to check
	 * @param player the player
	 * @return true if the player has placed 3 marks on that row
	 */
	private final boolean checkRow(int row, Player player) {
		for(int column = 0; column < 3; column++)
			if(getSquare(row, column) != player)
				return false;
		return true;
	}
	
	/**
	 * Checks if a given player has placed 3 marks on the given column.
	 * 
	 * @param column the column of the grid to check
	 * @param player the player
	 * @return true if the player has placed 3 marks on that column
	 */
	private final boolean checkColumn(int column, Player player) {
		for(int row = 0; row < 3; row++)
			if(getSquare(row, column) != player)
				return false;
		return true;
	}
	
	/**
	 * Constructs the initial state of all Tic Tac Toe games, in which the grid
	 * is empty and it is X's turn to move.
	 */
	public State() {
		this.grid = new Player[3][3];
		this.current = Player.X;
		this.winner = null;
	}
	
	/**
	 * Returns the player whose turn it is to move next.
	 * 
	 * @return the current player
	 */
	public Player getCurrentPlayer() {
		return current;
	}
	
	/**
	 * Returns the mark at the given row and column of the grid.
	 * 
	 * @param row the row number (0 to 2)
	 * @param column the column number (0 to 2)
	 * @return the player who has placed a mark at that location, or null if no
	 * player has placed a mark in that location
	 */
	public Player getSquare(int row, int column) {
		if(row == -1)
			row = 2;
		if(column == -1)
			column = 2;
		return grid[row % 3][column % 3];
	}
	
	/**
	 * Returns a set of all the next moves that can be made in the game.
	 * 
	 * @return all possible next moves
	 */
	public Iterable<Move> getAvailableMoves() {
		ArrayList<Move> moves = new ArrayList<>();
		if(winner != null)
			return moves;
		for(int r = 0; r < 3; r++)
			for(int c = 0; c < 3; c++)
				if(grid[r][c] == null)
					moves.add(new Move(current, r, c));
		return moves;
	}

	/**
	 * Returns the state that would result from taking the given move.
	 * Note that this object remains unchanged.
	 * 
	 * @param move the move to take
	 * @return the resulting state
	 */
	public State transition(Move move) {
		return new State(this, move);
	}
	
	/**
	 * Returns true if a winner has been decided or if the game is a tie.
	 * 
	 * @return true if the game is over, false otherwise
	 */
	public boolean isTerminal() {
		if(winner != null)
			return true;
		for(int r = 0; r < 3; r ++)
			for(int c = 0; c < 3; c++)
				if(grid[r][c] == null)
					return false;
		return true;
	}
	
	/**
	 * Returns the winner, if one has been determined.
	 * 
	 * @return the winning player, or null if no player has won or the game ended in a tie
	 */
	public Player getWinner() {
		return winner;
	}
	
	@Override
	public String toString() {
		String str = "";
		for(int r = 0; r < 5; r++) {
			for(int c = 0; c < 5; c++) {
				if(r % 2 == 1 && c % 2 == 1)
					str += "+";
				else if(r % 2 == 1)
					str += "-";
				else if(c % 2 == 1)
					str += "|";
				else {
					Player p = getSquare(r / 2, c / 2);
					if(p == null)
						str += " ";
					else
						str += p;
				}
			}
			str += "\n";
		}
		return str;
	}
}
