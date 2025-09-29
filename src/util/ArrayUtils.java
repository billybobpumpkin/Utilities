package util;

/**
 * A helper class that provides useful array manipulation methods.
 * <p>
 * @author Abraham Yelifari
 * @version 1.0
 * @since 2025-09-18 2:01
 */
public class ArrayUtils
{
	/**
	 * Reverses the array.
	 * @param arr the array to be reversed.
	 */
	public static void reverseArray(Object[] arr)
	{
        int start = 0;
        int end = arr.length - 1;

        while (start < end)
        {
            Object temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
	
	/**
	 * Attempts to shift the elements of the array by {@code shift} positions to the right, leaving {@code null}
	 * in the empty spaces and overwriting the overlapping positions. Does not loop around.
	 * @param arr the array to be shifted.
	 * @param shift how many positions to be shifted by.
	 */
	public static void shiftRight(Object[] arr, int shift)
	{
		if(shift <= 0 || shift >= arr.length)
			return;
		
		for (int i = arr.length - 1; i >= shift; i--)
			arr[i] = arr[i - shift];
		for (int i = 0; i < shift; i++)
			arr[i] = null;
	}
	
	/**
	 * Moves all of the elements of the array to the left until there is no more room, or the next element
	 * is not null.
	 * @param arr the array to be shifted
	 */
	public static void ramLeft(Object[] arr)
	{
		int insertPos = 0;

        for (int i = 0; i < arr.length; i++)
            if (arr[i] != null)
                arr[insertPos++] = arr[i];
        
        while (insertPos < arr.length)
            arr[insertPos++] = null;
	}
	
	/**
	 * Moves all of the elements of the array to the right until there is no more room, or the next element
	 * is not null.
	 * @param arr the array to be shifted
	 */
	public static void ramRight(Integer[] arr)
	{
        int insertPos = arr.length - 1;
        
        for (int i = arr.length - 1; i >= 0; i--)
            if (arr[i] != null)
                arr[insertPos--] = arr[i];
        
        while (insertPos >= 0)
            arr[insertPos--] = null;
    }
	
	/**
	 * Attempts to shift the elements of the array by {@code shift} positions to the left, leaving {@code null}
	 * in the empty spaces and overwriting the overlapping positions. Does not loop around.
	 * @param arr the array to be shifted.
	 * @param shift how many positions to be shifted by.
	 */
	public static void shiftLeft(Object[] arr, int shift)
	{
		ArrayUtils.reverseArray(arr);
		shiftRight(arr, shift);
		ArrayUtils.reverseArray(arr);
	}
}