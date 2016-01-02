import java.util.ArrayList;

public interface DuckInterface
{
	/**
	 * Returns a list of names in the order in which the people will be standing
	 * for the "game". Although fewer people may be playing, you should still
	 * return 20 names here. Do not provide duplicate names.
	 * 
	 * @return a list of 20 names in order
	 */
	public ArrayList<String> getNames();

	/**
	 * Plays a "game" with n people counting down every m. An explanation for
	 * how the "game" works can be found in the lab specs. This method should
	 * return a list of names in the order in which they were chosen (including
	 * the survivor, who should be last).
	 * 
	 * @param n
	 *            the number of people playing the "game"; 10 <= n <= 20
	 * @param m
	 *            the number of people to count past before the goose gets
	 *            cooked; m > 0
	 * @return a list of names in the order in which they were chosen
	 */
	public ArrayList<String> playGame(int n, int m);

	/**
	 * Returns the "safe index", the last index/location in the circle that
	 * would be chosen were the "game" to be played. The point of this method is
	 * for someone to cheat the system and find the last safe location ahead of
	 * time.
	 * 
	 * Ex: For 12 people and a count of 10, the "safe index" is 4.
	 * 
	 * @param n
	 *            the number of people playing the "game"; 10 <= n <= 20
	 * @param m
	 *            the number of people to count past before the goose gets
	 *            cooked; m > 0
	 * @return the safe index
	 */
	public int reportSafeIndex(int n, int m);
}
