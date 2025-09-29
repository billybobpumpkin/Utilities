package util;


/**
 * Utility class for timing the execution duration of code blocks.
 * <p>
 * Each method accepts a {@link Runnable} representing the code to time
 * and returns the elapsed time in the specified unit.
 * <p>
 * @author Abraham Yelifari
 * @version 1.0
 * @since 2025-09-18 2:34
 */
public class BigO
{
	/**
	 * Returns the elapsed time in seconds for the function's execution.
	 * @param lambda function to time.
	 * @return The time it took in seconds.
	 */
	public static double nTimerSeconds(Runnable action)
	{
		return (nTimerNanoseconds(action) / 1_000_000_000.0);
	}
	
	/**
	 * Returns the elapsed time in milliseconds for the function's execution.
	 * @param lambda function to time.
	 * @return The time it took in milliseconds.
	 */
	public static double nTimerMilliseconds(Runnable action)
	{
		return (nTimerNanoseconds(action) / 1_000_000.0);
	}
	
	/**
	 * Returns the elapsed time in microseconds for the function's execution.
	 * @param lambda function to time.
	 * @return The time it took in microseconds.
	 */
	public static double nTimerMicroseconds(Runnable action)
	{
		return (nTimerNanoseconds(action) / 1_000.0);
	}
	
	/**
	 * Returns the elapsed time in nanoseconds for the function's execution.
	 * @param lambda function to time.
	 * @return The time it took in nanoseconds.
	 */
	public static double nTimerNanoseconds(Runnable action)
	{
		long start = System.nanoTime();
		action.run();
		long end = System.nanoTime();
		
		return (end - start);
	}
}