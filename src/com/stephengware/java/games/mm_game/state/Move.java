package com.stephengware.java.games.mm_game.state;

/**
 * Represents an individual move in a game of Tic Tac Toe.
 * 
 * @author Stephen G. Ware
 */
public class Move {

	/** The player making the move */
	public final Player player;
	
	/** The row on the grid in which the player will place his mark */
	public final int row;
	
	/** The column in the grid in which the player will place his mark */
	public final int column;
	
	/**
	 * Constructs a move object.
	 * 
	 * @param player the player making the move
	 * @param row the row on the gird in which the player places his mark
	 * @param column the column on the grid in which the player places his mark
	 */
	public Move(Player player, int row, int column) {
		this.player = player;
		this.row = row;
		this.column = column;
	}
	
	@Override
	public String toString() {
		return player + " @ " + row + " " + column;
	}
}
