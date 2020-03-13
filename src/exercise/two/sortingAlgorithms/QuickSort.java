
public class QuickSort {
	
	//Algorithm adopted by Skiena book.
	
	public void quickSort(String[] list, int l, int h)
	{
		int p;			/* index of partition */
		
		if((h-l) > 0)
		{
			p = partition(list, l, h);
			//System.out.println("Partition: " + p);
			quickSort(list, l, p-1);
			quickSort(list, p+1, h);
		}
	}
	
	public int partition(String[] list, int l, int h)
	{
		int i;
		int p;
		int firsthigh;
		
		p = h; //Choosing the right most, high element to be the pivot
		firsthigh = l;
		
		for(i = l; i < h; i++)
		{
			if(list[i].compareToIgnoreCase(list[p]) < 0)
			{
				swap(list, i, firsthigh);
				firsthigh ++;
			}
		}
		
		swap(list, p, firsthigh);
		
		return firsthigh;
	}
	
	//helper functions
	public void swap(String[] list, int a, int b)
	{
		//switch values of elements a and b
		String temp = list[a];
		list[a] = list[b];
		list[b] = temp;
	}
	
	public void testSort()
	{
		String[] test = {"hello", "beautiful", "zebra", "apple", "car", "banana", "Jirafe",
				"mother", "father", "lala", "mmh", "this", "great"};
		quickSort(test, 0, (test.length-1));
		
		for(int i = 0; i < test.length; i ++)
		{
			System.out.println(test[i]);
		}
	}
}
