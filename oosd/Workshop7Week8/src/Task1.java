import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class Task1 {
	public static final int SCORE_COUNT = 7;
	public static final int MAX_SCORE = 10;
	public static final int MIN_SCORE = 0;
	public static final double MAX_DIFFICULTY = 3.8;
	public static final double MIN_DIFFICULTY = 1.2;
	public static final double MULTIPLIER = 0.6;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double total_score = 0;
		double difficulty = 0;
		Boolean valid_difficulty = false;
		Boolean valid_scores = false;
		
		List<Double>scores = new ArrayList<Double>();
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Enter 7 scores:");
		while (keyboard.hasNextDouble() && scores.size() < SCORE_COUNT) {
			scores.add(keyboard.nextDouble());
			System.out.println(scores.size());
		}
		
		
		
		Collections.sort(scores);
		scores.remove(0); //remove the highest score
		scores.remove(scores.size()-1); //remove the lowest score
		// compute sum of scores
		for (double score : scores) {
			total_score += score;
		}
		//compute average
		total_score /= scores.size();
		
		System.out.flush();
		System.out.println("Degree of difficulty?");
		while(true) {
			try {
				difficulty = keyboard.nextDouble();
				if (difficulty < MIN_DIFFICULTY || difficulty > MAX_DIFFICULTY) throw new difficultyRangeException(difficulty);
				else break; 
			} catch(difficultyRangeException e) {
				System.out.println(e.getMessage());
				System.out.println("Try again");
				
			}
		}
		
		// compute final score
		total_score *= (difficulty * MULTIPLIER);
		
		System.out.println("Final score: " + total_score);
		
		
		
	}
	
	static class difficultyRangeException extends Exception {
		public difficultyRangeException(double difficulty) {
			super("Invalid range: " + difficulty + " is not in range: " +
					MIN_DIFFICULTY + " <-> " + MAX_DIFFICULTY + ".");
		}
		
	}

}
