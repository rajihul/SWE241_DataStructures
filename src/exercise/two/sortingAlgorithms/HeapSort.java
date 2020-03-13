
public class HeapSort {

	public void sort(String[] list)
	{
		int leng = list.length;
		
		//Create the initial Max Heap
		//Because of the nature of a binary tree
		//only have to start at the middle of the tree
		for(int i = leng/2-1; i >= 0; i--)
		{
			heapify(list, leng, i); 
		}
		
		//Now that the Max Heap has been created.
		//swap the elements and heapify again to bubble down changes.
		for(int i = leng-1; i >= 0; i--)
		{
			String temp = list[0];
			//swamp the 1st index (containing largest value) with the last index
			list[0] = list[i];
			list[i] = temp;
			
			//swaping breaks the heap so bubble down changes from the top most, to re-stablish heap.
			heapify(list, i, 0);
		}
	}
	
	//create maxHeap
	public void heapify(String[] list, int n, int i)
	{
		int largest = i; // parent node index position
		int li = 2*i + 1; // left child node index position
		int ri = 2*i + 2; // right child node index position
		
		//Compare left child with largest, which is index of parent 
		if(li < n && list[li].compareToIgnoreCase(list[largest]) > 0)
		{
			largest = li;
		}
		
		//Compare right child with largest index (which could be parent or left child)
		//depending on frist if statement.
		if(ri < n && list[ri].compareToIgnoreCase(list[largest]) > 0)
		{
			largest = ri;
		}
		
		//Base case is when the parent has no child, otherwise recursively
		//extract the largest element in the subtree and make it the parent. 
		if(largest != i)
		{
			String temp = list[i];
			list[i] = list[largest];
			list[largest] = temp;
			
			heapify(list, n, largest);
		}

	}
	
	//unit test
	public void heapTest(String[] arr)
	{
		//String[] test = arr;// {"hello", "burrito", "glad", "good", "nothign", "doing", "Rahul"};
		sort(arr);
		
		for(int i = 0; i < arr.length; i ++)
		{
			System.out.println(arr[i]);
		}
	}
	
	
}
