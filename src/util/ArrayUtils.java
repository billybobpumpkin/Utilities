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
	
	//Assuming that there is enough space in the array!!!
	public static void shiftRight(Object[] arr, int shift)
	{
		if(shift <= 0 || shift >= arr.length)
			return;
		
		for (int i = arr.length - 1; i >= shift; i--)
			arr[i] = arr[i - shift];
		for (int i = 0; i < shift; i++)
			arr[i] = null;
	}
	
	public static void ramLeft(Object[] arr)
	{
		int insertPos = 0;

        for (int i = 0; i < arr.length; i++)
            if (arr[i] != null)
                arr[insertPos++] = arr[i];
        
        while (insertPos < arr.length)
            arr[insertPos++] = null;
	}
	
	public static void ramRight(Integer[] arr)
	{
        int insertPos = arr.length - 1;
        
        for (int i = arr.length - 1; i >= 0; i--)
            if (arr[i] != null)
                arr[insertPos--] = arr[i];
        
        while (insertPos >= 0)
            arr[insertPos--] = null;
    }
	
	//Assuming that there is enough space in the array!!!
	public static void shiftLeft(Object[] arr, int shift)
	{
		ArrayUtils.reverseArray(arr);
		shiftRight(arr, shift);
		ArrayUtils.reverseArray(arr);
	}
}