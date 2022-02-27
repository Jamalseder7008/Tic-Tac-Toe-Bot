package com.stephengware.java.games.mm_game.ai;

import com.stephengware.java.games.mm_game.state.Player;
import com.stephengware.java.games.mm_game.state.State;

/**
 * A utility function measures how desirable a given state is for some agent.
 * Because we are assuming a two player zero-sum game, we can say that player
 * X is trying to maximize utility and player O is trying to minimize utility.
 * 
 * @author Stephen G. Ware
 */
public class Utility {

	/**
	 * Returns the desirability of the current state for player X.
	 * 
	 * @param state the current state of the game
	 * @return a positive or negative number or zero
	 */
	public static double evaluate(State state) {
		if(state.getWinner() == Player.X)
			return 1;
		else if(state.getWinner() == Player.O)
			return -1;
		else
			return 0;
	}
}
