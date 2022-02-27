package com.stephengware.java.games.mm_game.ai;

import java.util.Random;

import com.stephengware.java.games.mm_game.state.State;

/**
 * A random Tic Tac Toe bot simply chooses its next move at random from all the
 * available next moves.
 * 
 * @author Stephen G. Ware
 */
public class RandomBot implements Bot {

	/** A random number generator */
	private Random random;
	
	/**
	 * Constructs a new random bot with a given random number generator seed.
	 * 
	 * @param seed the random number generator seed
	 */
	public RandomBot(long seed) {
		this.random = new Random(seed);
	}
	
	@Override
	public Decision chooseMove(State state) {
		GameTree root = new GameTree(state);
		while(root.hasNextChild())
			root.getNextChild();
		GameTree child = root.children.get(random.nextInt(root.children.size()));
		System.out.println(child.value);
		return new Decision(child.move, root.size());
	}
}
