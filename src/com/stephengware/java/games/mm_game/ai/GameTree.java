package com.stephengware.java.games.mm_game.ai;

import java.util.ArrayList;
import java.util.Iterator;

import com.stephengware.java.games.mm_game.state.Move;
import com.stephengware.java.games.mm_game.state.State;

/**
 * A game tree is a representation of all the possible states that could occur
 * during the play of game and how one state is reached from another.
 * 
 * @author Stephen G. Ware
 */
public class GameTree {

	/** The move to resulted in the current state */
	public final Move move;
	
	/** The current state of the game */
	public final State state;
	
	/** The parent node of this tree (i.e. the state before this state) */
	public final GameTree parent;
	
	/** This node's children nodes (i.e. all possible next states) */
	public final ArrayList<GameTree> children = new ArrayList<>();
	
	/** An iterator of the next possible moves to make */
	private final Iterator<Move> nextMoves;
	
	/** The utility value of this state (i.e. how desirable it is for the player) */
	public double value = 0;
	
	/**
	 * Constructs a new game tree with some initial state as the root.
	 * 
	 * @param initial the initial state of the game
	 */
	public GameTree(State initial) {
		this(null, initial, null);
	}
	
	/**
	 * Constructs a new game tree with a current state that resulted from
	 * taking a given move.
	 * 
	 * @param move the move that resulted in this state
	 * @param state the state of the game after making that move
	 * @param parent the parent node (i.e. the previous state)
	 */
	protected GameTree(Move move, State state, GameTree parent) {
		this.move = move;
		this.state = state;
		this.parent = parent;
		this.nextMoves = state.getAvailableMoves().iterator();
	}
	
	/**
	 * Returns the number of nodes in this tree.
	 * 
	 * @return the number of nodes
	 */
	public int size() {
		int size = 1;
		for(GameTree child : children)
			size += child.size();
		return size;
	}
	
	/**
	 * Returns true if this node has more children nodes which have not yet
	 * been expanded.
	 * 
	 * @return true if there are more children nodes to add, false otherwise
	 */
	public boolean hasNextChild() {
		return nextMoves.hasNext();
	}
	
	/**
	 * Constructs and returns the next child node of this node.
	 * 
	 * @return the next child node
	 */
	public GameTree getNextChild() {
		Move move = nextMoves.next();
		GameTree child = new GameTree(move, state.transition(move), this);
		children.add(child);
		return child;
	}
}
