import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class TestSort {
	
	public static void main(String[] args) throws IOException
	{
		// Using a LinkedList to serve as intermediate storage to an array to be sent to
		// sorting algorithm. 
		LinkedList list = new LinkedList();
		
		//TOKENIZE WORDS
		//LOAD PRIDE AND PREJUIDICE TEXT
		File inputFile = new File("pride-and-prejudice.txt");
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		
		String currLine;
		StringTokenizer st;
		String currWord;
		long startTime, endTime, duration, selectionTime, insertionTime, mergeTime, qsTime, heapTime;
		boolean result;
		
		//Read through the file and Tokenize to alpha-numeric characters. 
		while ((currLine = br.readLine()) != null) {
			st = new StringTokenizer(currLine, " -.");
			
			while(st.hasMoreTokens()) {
				
				currWord = st.nextToken().replaceAll("[^A-Za-z0-9]", "");
				
				//want to make sure each set is unique, so don't want to add
				//the same word to thet set. 
				if (!list.contains(currWord))
				{
					list.add(currWord);
				}
			}
			
		}
		
		
		//Instantiate the different sorting algorithms
		SelectionSort select = new SelectionSort();
		InsertionSort insertion = new InsertionSort();
		MergeSort merge = new MergeSort();
		HeapSort heaps = new HeapSort();
		QuickSort qsort = new QuickSort();
		
		//Create a holder for the sorted list
		String[] sortedList;
		
		String[] unsortedList;
		
		//Selection Sort
		unsortedList = list.listToArray(); // refresh the unsorted list
		
		startTime = System.nanoTime();
		sortedList = select.selectionSort(unsortedList);
		endTime = System.nanoTime();
		selectionTime = endTime - startTime;
		
		System.out.println("Selection Sort Time: " + selectionTime);
		
		//Insertion Sort
		unsortedList = list.listToArray(); // refresh the unsorted list
		
		startTime = System.nanoTime();
		sortedList = insertion.sort(unsortedList);
		endTime = System.nanoTime();
		insertionTime = endTime - startTime;
		
		System.out.println("Insertion Sort Time: " + insertionTime);
		
		heaps.sort(unsortedList); 
		
		//HeapSort
		unsortedList = list.listToArray(); // refresh the unsorted list
		
		startTime = System.nanoTime();
		heaps.sort(unsortedList);
		endTime = System.nanoTime();
		heapTime = endTime - startTime;
		

		System.out.println("Heap Sort Time: " + heapTime);
		//Merge Sort
		unsortedList = list.listToArray(); // refresh the unsorted list
		
		startTime = System.nanoTime();
		merge.mergeSort(unsortedList, unsortedList.length);
		endTime = System.nanoTime();
		mergeTime = endTime - startTime;
		
		System.out.println("Merge Sort Time: " + mergeTime);
	
		
	
		// QuickSort
		unsortedList = list.listToArray(); // refresh the unsorted list
		
		startTime = System.nanoTime();
		qsort.quickSort(unsortedList, 0, unsortedList.length - 1);
		endTime = System.nanoTime();
		qsTime = endTime - startTime;
		
		System.out.println("Quick Sort Time: " + qsTime);
	

		System.out.println("\n# Items Sorted: " + list.size());
		
		// Uncomment the 4 lines below if you want to Display the sorted list
		
		//for(int j=0; j < sortedList.length; j++)
		//{
		//	System.out.println(unsortedList[j]);
		//}
		

	}
}
