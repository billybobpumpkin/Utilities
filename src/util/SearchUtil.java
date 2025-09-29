package util;

import java.lang.reflect.Array;

/**
 * A class the possesses methods for sorting arrays and searching in arrays.
 * <p>
 * @author Abraham Yelifari
 * @version 1.0
 * @since 2025-9-25 12:08
 */
public class SearchUtil
{
	/**
	 * Uses the binary search algorithm to search through a sorted array. Takes
	 * {@code O(log n)} time.
	 * @param a the array to be searched in.
	 * @param searchKey the value to find.
	 * @param low the lowest value to be considered.
	 * @param high the highest value to be considered.
	 * @return the found value, or null.
	 */
	public static <T extends Comparable<? super T>> T binarySearch(T a[], T searchKey, int low, int high)
	{
		int middle;
		
		while(low <= high)
		{
			middle = (low + high) / 2;
		    int cmp = searchKey.compareTo(a[middle]); // reversed

		    if (cmp == 0) // searchKey == a[middle]
		    {
		        return a[middle];
		    }

		    if (cmp > 0) // searchKey > a[middle]
		    {
		        low = middle + 1;
		    }
		    else // searchKey < a[middle]
		    {
		        high = middle - 1;
		    }
		}
		return null;
	}
	
	/**
	 * Utilizes the merge sort algorithm. Time complexity is {@code O(log n)}.
	 * @param <T>
	 * @param arr the array to be sorted.
	 */
	public static <T extends Comparable<? super T>> void mergeSort(T[] arr)
	{
        if (arr.length <= 1) return;
        mergeSort(arr, 0, arr.length - 1);
    }
	
	
	
	/*
	 * Private method to help sort using recursion.
	 */
    private static <T extends Comparable<? super T>> void mergeSort(T[] arr, int left, int right)
    {
        if (left >= right) return;

        int mid = (left + right) / 2;

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        merge(arr, left, mid, right);
    }

    /*
	 * Private method to help sort using recursion.
	 */
    private static <T extends Comparable<? super T>> void merge(T[] arr, int left, int mid, int right)
    {
    	T[] temp = (T[])Array.newInstance(arr.getClass().getComponentType(), arr.length);

        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right)
        {
        	if (arr[i] == null && arr[j] == null)
                temp[k++] = arr[i++]; // or arr[j++], both null
            else if (arr[i] == null)
                temp[k++] = arr[j++];
            else if (arr[j] == null)
                temp[k++] = arr[i++];
            else
            {
            	int cmp = arr[i].compareTo(arr[j]);
            	
                if (cmp <= 0)
                	temp[k++] = arr[i++];
                else
                	temp[k++] = arr[j++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        System.arraycopy(temp, 0, arr, left, k);
    }
}