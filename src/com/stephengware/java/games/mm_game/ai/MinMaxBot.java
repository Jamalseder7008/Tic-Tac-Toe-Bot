package com.stephengware.java.games.mm_game.ai;
import com.stephengware.java.games.mm_game.state.Player;
import com.stephengware.java.games.mm_game.state.State;

/**
 * This bot expands the entire game tree and makes its decision based on Min
 * Max search.
 * 
 * @author Stephen G. Ware
 */
 public class MinMaxBot implements Bot {

	
	@Override
	public Decision chooseMove(State state) {
		GameTree root = new GameTree(state);
		double value;
		if(state.getCurrentPlayer() == Player.X)
			value = findMax(root);
		else
			value = findMin(root);
		for(GameTree child : root.children){
			if(child.value == value)
				return new Decision(child.move, root.size());
		}
		return null;
	}
	
	/**
	 * Given a {@link GameTree} node, expand its children (if any) to find the
	 * node with the highest minimum utility value.
	 * 
	 * @param tree the node whose children need to be expanded
	 * @return the utility value of the node with the highest minimum utility
	 */
	private double findMax(GameTree tree) {
		// First, check if this node is a leaf node (i.e. the game is over)
		// using Tree#state#isTerminal().  If so, simply return the utility of
		// this state.
		if(tree.state.isTerminal()) {
			return Utility.evaluate(tree.state);
		}
		// If this node is not a leaf, then we need to expand all of its
		// children and find the one with the highest minimum utility value.
		// Start with the lowest possible number, Double#NEGATIVE_INFINITY and
		// work your way up from there.
		double max = Double.NEGATIVE_INFINITY;
		// You can check if a node has more children that have not yet been
		// explored using GameTree#hasNextChild().
		while (tree.hasNextChild()){
			// You can get the next unexplored child node with GameTree#getNextChild().
			GameTree child = tree.getNextChild();

			
			// Find the lowest possible utility value the child node can have.
			child.value = findMin(child);
			
			// Update 'max' based on this new information.  'max' should always hold the
			// largest value we have discovered so far.
			
			max = max(max, child.value);
		}
		// Return the highest utility value of all the children nodes.
		return max;
	}
	
	private double max(double max, double child_value) {
		if (max > child_value)
			return max;
		else{ 
			return child_value;
		}
	}

	/**
	 * Given a {@link GameTree} node, expand its children (if any) to find the
	 * node with the lowest maximum utility value.
	 * 
	 * @param tree the node whose children need to be expanded
	 * @return the utility value of the node with the lowest maximum utility
	 */
	private double findMin(GameTree tree) {
		// This method is simply the opposite of #findMax.
		if(tree.state.isTerminal()) {
			return Utility.evaluate(tree.state);
		}
		double min = Double.POSITIVE_INFINITY;

		while (tree.hasNextChild()){
			// You can get the next unexplored child node with GameTree#getNextChild().
			GameTree child = tree.getNextChild();

			// Find the lowest possible utility value the child node can have.
			child.value = findMax(child);			
			// Update 'min' based on this new information.  'min' should always hold the
			// smallest value we have discovered so far.
			
			min = min(min, child.value);
		}
		return min;
	}

	private double min(double min, double child_value) {
		if (min < child_value)
			return min;
		else{ 
			return child_value;
		}
	}
}
