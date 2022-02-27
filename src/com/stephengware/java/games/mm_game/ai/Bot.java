package com.stephengware.java.games.mm_game.ai;

import com.stephengware.java.games.mm_game.state.State;

/**
 * A bot is an artificially intelligent agent which, given the state of game,
 * decides which move to make next.
 * 
 * @author Stephen G. Ware
 */
public interface Bot {
		
	public Decision chooseMove(State state);
}
