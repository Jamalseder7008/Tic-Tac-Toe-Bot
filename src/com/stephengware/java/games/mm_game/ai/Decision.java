package com.stephengware.java.games.mm_game.ai;

import com.stephengware.java.games.mm_game.state.Move;

/**
 * Represents a decision made by a {@link Bot} about which
 * {@link com.stephengware.java.games.mm_game.state.Move Move} to make next
 * and how many possible moves the bot considered while making its decision.
 * 
 * @author Stephen G. Ware
 */
public class Decision {

	/** The move to make */
	public final Move move;
	
	/** The number of nodes in the game tree that the bot expanded before making this decision */
	public final int work;
	
	/**
	 * Constructs a new decision object.
	 * 
	 * @param move the move to make
	 * @param work the number of nodes in the game tree that the bot expanded before making this decision
	 */
	Decision(Move move, int work) {
		this.move = move;
		this.work = work;
	}
	
	@Override
	public String toString() {
		return move.toString();
	}
}
