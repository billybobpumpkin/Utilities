package util;

import java.lang.reflect.Array;

public class SearchUtil
{
	public static int binarySearch(int a[], int searchKey, int low, int high)
	{
		int middle;
		
		while(low <= high)
		{
			middle = (low + high)/2;
			
			if (a[middle] == searchKey)
			{
				return a[middle]; 
			}
			
			if (searchKey > a[middle])
			{
				high = middle - 1;
			}
			else
			{
				low = middle + 1;
			}
		}
		return -1;
	}
	
	public static <T extends Comparable<? super T>> void mergeSort(T[] arr)
	{
        if (arr.length <= 1) return;
        mergeSort(arr, 0, arr.length - 1);
    }
	
	
	

    private static <T extends Comparable<? super T>> void mergeSort(T[] arr, int left, int right)
    {
        if (left >= right) return;

        int mid = (left + right) / 2;

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        merge(arr, left, mid, right);
    }

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