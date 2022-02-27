/**
 * This package provides the game of Tic Tac Toe and the tools for building AI
 * players.
 * 
 * <p>You should start by running {@link Main}.  This program plays some number
 * of games of Tic Tac Toe against two different kinds of bot.  Initially, both
 * bots simply choose moves at random.</p>
 * 
 * <p>Your task is to build a more intelligent bot using adversarial game tree
 * search techniques.</p>
 * 
 * <p>To save time, reduce {@link Main#NUMBER_OF_GAMES} to 10.  Then fill in
 * the missing code from
 * {@link com.stephengware.java.games.mm_game.ai.MinMaxBot MinMaxBot}.  MinMax
 * bot will expand the entire game tree before making its decision.  This is
 * possible for a game as small as Tic Tac Toe, but it would not be possible
 * for larger games.</p>
 * 
 * <p>When you are done, change {@link Main#getXBot()} to return an instance
 * of your new MinMax bot.  Now, player X should win or tie every game, but the
 * amount of work required to make decisions is incredibly high.</p>
 * 
 * <p>Now fill in the missing code from
 * {@link com.stephengware.java.games.mm_game.ai.AlphaBetaBot AlphaBetaBot}.
 * This bot uses a modified version of min max search that can prune large
 * portions of the game tree which are irrelevant to making the best decision.
 * </p>
 * 
 * <p>When you are done, change {@link Main#getXBot()} to return an instance
 * of your new AlphaBeta bot.  Player X should still win or tie every game, but
 * the amount of work required to make decisions will be much lower.  You
 * should now be able to change {@link Main#NUMBER_OF_GAMES} back to 100.</p>
 */
package com.stephengware.java.games.mm_game;