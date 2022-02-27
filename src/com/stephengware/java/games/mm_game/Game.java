package com.stephengware.java.games.mm_game;

import com.stephengware.java.games.mm_game.ai.Bot;
import com.stephengware.java.games.mm_game.ai.Decision;
import com.stephengware.java.games.mm_game.state.Player;
import com.stephengware.java.games.mm_game.state.State;

/**
 * Plays a game of Tic Tac Toe and measures how many moves each player
 * considers during the game.
 * 
 * @author Stephen G. Ware
 */
public class Game {

	/** THe X player */
	public final Bot x;
	
	/** The O player */
	public final Bot o;
	
	/** The final state of the game after it is over */
	public final State state;
	
	/** The winning player */
	public final Player winner;
	
	/** The number of moves considered by player X during the game */
	public final double xWork;
	
	/** The number of moves considered by player O during the game */
	public final double oWork;
	
	/**
	 * Constructs and plays a game of Tic Tac Toe.
	 * 
	 * @param x the X player
	 * @param o the O player
	 */
	public Game(Bot x, Bot o) {
		this.x = x;
		this.o = o;
		State current = new State();
		double xw = 0;
		double ow = 0;
		while(!current.isTerminal()) {
			Decision decision;
			if(current.getCurrentPlayer() == Player.X) {
				decision = x.chooseMove(current);
				xw += decision.work;
			}
			else {
				decision = o.chooseMove(current);
				ow += decision.work;
			}
			current = current.transition(decision.move);
		}
		this.state = current;
		this.winner = current.getWinner();
		this.xWork = xw;
		this.oWork = ow;
	}
}
