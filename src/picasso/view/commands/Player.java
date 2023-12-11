package picasso.view.commands;

import java.time.Duration;
import java.time.Instant;

import picasso.model.Pixmap;
import picasso.parser.IdentifierAnalyzer;
import picasso.parser.language.expressions.Constant;
import picasso.util.Command;

/**
 * Repeatedly evaluate the expression while updating the time variable.
 * 
 * @author Reese Nelson
 */
public class Player implements Command<Pixmap> {
	
	private static Player ourInstance;
	private final Evaluator evaluator;
	private Duration playTime;
	private Instant startTime;
//threaded command here not in frame
	private Player(Evaluator evaluator) {
		this.evaluator = evaluator;
		this.playTime = Duration.ZERO;
		this.startTime = Instant.now();
	}

	@Override
	public void execute(Pixmap target) {
			updatePlayTime();
			evaluator.execute(target);
	}
	
	public Duration getPlayTime() {
		return playTime;
	}
	
	private void updatePlayTime() {
		playTime = Duration.between(startTime, Instant.now());
		double t = ((double) playTime.toMillis() / 10000);
	    double l = (Math.round((t % 2) * 100) / 100.0) - 1;
	    double b = Math.round(Math.cos(t * Math.PI) * 100) / 100.0;
		//System.out.println(t);
		//System.out.println(l);
		//System.out.println(b);
		//IdentifierAnalyzer.idToExpression.put("t", new Constant(t));
		IdentifierAnalyzer.idToExpression.put("r", new Constant(l));
		IdentifierAnalyzer.idToExpression.put("b", new Constant(b));
	}
	
	/**
	 * Make sure that there is only one player for the application.
	 * 
	 * @return the player
	 */
	public static Player getInstance(Evaluator evaluator) {
		if (ourInstance == null) {
			ourInstance = new Player(evaluator);
		}
		return ourInstance;
	}
}
