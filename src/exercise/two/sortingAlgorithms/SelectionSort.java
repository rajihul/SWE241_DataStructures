import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class SelectionSort {

	//because i don't know how big the input file is, 
	//going to store elements in a linked list, get the size of the list, then transfer the 
	//contents to an array. Then sort the array. It's easier to insert in a linked list and then 
	//sort in an array becuase i have random access to it, in comparison to a linked list, which is good
	//at inserting, but not good at random access and searching.
	
	//Instance Variables
	int size;
	String[] myContent;
	
	//Constructor
	public SelectionSort()
	{
		size=0;
	}
	
	public String[] selectionSort(String[] content)
	{
		String[] myContent= content;
		String currentMin=myContent[0];
		int size = myContent.length;
		int minIndex=0;
		
		for(int i=0; i < size; i ++)
		{
			currentMin=myContent[i];
			minIndex=i;

			for(int j=i+1; j<size; j++)
			{
				if(myContent[j].compareToIgnoreCase(currentMin) <= 0)
				{
					currentMin=myContent[j];
					minIndex=j;					
				}
			}
			
			swap(myContent, i, minIndex);
		}
			return myContent;
	}
	//Helper functions

	public  void swap(String[] array, int i, int j) {
		String temp = array[i];
	    array[i] = array[j];
	    array[j] = temp;

	}
		
	
}
