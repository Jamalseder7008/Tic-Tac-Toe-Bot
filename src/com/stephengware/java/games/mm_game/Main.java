package com.stephengware.java.games.mm_game;

import java.util.Random;

import com.stephengware.java.games.mm_game.ai.*;
import com.stephengware.java.games.mm_game.state.*;

/**
 * Plays {@link #NUMBER_OF_GAMES} games of Tic Tac Toe and reports the results.
 * 
 * @author Stephen G. Ware
 */
public class Main {

	/** Number of games to play */
	public static final int NUMBER_OF_GAMES = 100;
	
	/**
	 * Runs the application.
	 * 
	 * @param args ignored
	 */
	public static void main(String[] args) {
		System.out.println("Playing " + NUMBER_OF_GAMES + " games...");
		int xWins = 0;
		int oWins = 0;
		double xWork = 0;
		int oWork = 0;
		for(int i = 0; i < NUMBER_OF_GAMES; i++) {
			Game game = new Game(getXBot(), getOBot());
			if(game.winner == Player.X)
				xWins++;
			else if(game.winner == Player.O)
				oWins++;
			xWork += game.xWork;
			oWork += game.oWork;
		}
		System.out.println("Results:");
		System.out.println("X won " + xWins + "/" + NUMBER_OF_GAMES + " games and considered about " + (xWork / NUMBER_OF_GAMES) + " moves per game.");
		System.out.println("O won " + oWins + "/" + NUMBER_OF_GAMES + " games and considered about " + (oWork / NUMBER_OF_GAMES) + " moves per game.");
		System.out.println(NUMBER_OF_GAMES - (xWins + oWins) + " games tied.");
	}
	
	/** A random number generator */
	private static final Random random = new Random(9);
	
	/**
	 * Returns the {@link com.stephengware.java.games.mm_game.ai.Bot Bot} to
	 * play X.
	 * 
	 * @return a bot to play X
	 */
	private static Bot getXBot() {
		return new RandomBot(random);
		
	}
	
	/**
	 * Returns the {@link com.stephengware.java.games.mm_game.ai.Bot Bot} to
	 * play O.
	 * 
	 * @return a bot to play O
	 */
	private static Bot getOBot() {
		return new AlphaBetaBot();
	}
}
