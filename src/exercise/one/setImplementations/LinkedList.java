package exercise.one.setImplementations;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class LinkedList {
	Node head; // head of list
	Node tail; // keep track of end of the list
	int currentSize; 
	//Linked list Node.
	
	
	// This inner class is made static
	// so that main () can access it.
	static class Node {
		String data;
		Node next;
		
		//Constructor
		Node (String d)
		{
			data=d;
			next=null;
		}
	}
	
	//LinkedList Constructors
	  public LinkedList(String item) { 
		  //Constructor with input paramater
	       this.head = new Node(item);

	       currentSize = 0;
	       currentSize ++; //increase size by 1
	    }
	
	public LinkedList() {
		//Constructor with no input.
		currentSize = 0;
	}

	//Methods of the Linked List
	
	// Insert new Node to end of LinkedList with O(1) complexity.
		public boolean add(String word)
		{
			
			//Building the new nodw with word and setting the next to null.
			Node new_node = new Node(word);
			new_node.next=null;
			
			 // If the Linked List is empty, 
	        // then make the new node as head 
	        if (this.head == null) { 
	            this.head = new_node; 
	            tail = this.head; // set the last node to be the head node.
	        }
	        else{
	        	
	        	tail.next = new_node;
	        	tail = new_node;
	        }

			currentSize ++;
			return true;
		}
		
	// Insert new Node to end of LinkedList with O(n) complexity.
	public boolean addEnd(String word)
	{
		//Building the new nodw with word and setting the next to null.
		Node new_node = new Node(word);
		new_node.next=null;
		
		 // If the Linked List is empty, 
        // then make the new node as head 
        if (this.head == null) { 
            this.head = new_node; 
        }
        else{
        	//Start with the head node of the linked list.
        	Node last = this.head;
		
			//Traverse all the way to the end of the node and add new node with new word.
			while (last.next !=null){
				//System.out.println(last.data);
				last = last.next;
			}
			last.next=new_node;
        }

		currentSize ++;
		return true;
	}
	
	// insert at the beginning of the list
	public boolean addBefore(String word)
	{
		//Insert Node at the beginning
		Node new_Node = new Node(word);
		new_Node.next=null;
		
		 // If the Linked List is empty, 
        // then make the new node as head 
        if (this.head == null) { 
            this.head = new_Node; 
        }
        else{
        	new_Node.next=head;
        	head = new_Node;
        }
        
        currentSize ++;
		return true;
	}
	
	public boolean contains(String word)
	{
		//Go through the linked list and see if the string is contained
		//so that we can ensure a unique set read from the text file.
		//Building the new nodw with word and setting the next to null.
		
		 // If the Linked List is empty, 
        // then List contains no data. 
        if (this.head == null) { 
            return false;
        }
        
		//Start with the head node of the linked list.
		Node last = this.head;
		
		//check other nodes if LinkedList size is greater than 1
		//Traverse all the way to the end of the node
		while (last.next !=null){
			
			//For the word in question, check each node to see if it occurs at all.
			if (last.data.equalsIgnoreCase(word)){
				//The word is contained in the List and is not unique
				return true;
			}
			
			last = last.next;
			
		}
		
		// If List Size == 1 case:
		//check last node = will be head in size 1 list, or last node in size > 1 node.
		//For the word in question, check each node to see if it occurs at all.
		if (last.data.equalsIgnoreCase(word)){
			//The word is contained in the List and is not unique 
			return true;
		}
		
		//If the word is unique then return false to contains
		return false;
	}
	
	//Returns current size of the List in O(n) time.
	public int size()
	{
		Node last = this.head;
		int counter = 0;
		
		if (this.head != null)
		{
			counter ++;
		}
		
		while (last.next !=null){
			counter ++;
			last = last.next;
		}
		
		return counter;
	}
	
	//Returns current size of the List stored in the currentSize variable in O(1) time
	public int size2()
	{

		return currentSize;
	}
	
	
	
	
	/************** HELPER FUNCTIONS (BEGIN) ***********/
	//A complimentary method used for testing purposes.
	public void displayList()
	{
		Node last = this.head;
		while (last.next !=null){
			System.out.println(last.data);
			last = last.next;
		}
		System.out.println(last.data);

	}
	
	public void testList()
	{
		/*    TESTING   */

		/* TESTING ADD METHOD */
		add("blue");
		add("green");
		System.out.println("success, count: " + size());
		displayList();
		
		/* TESTING CONTAINS METHOD */
		if(contains("greedn")){
			System.out.println("The word exists in the list");
		}
		else{
			System.out.println("The word doesn't exist in the list");
		}
		
	}
	
	public void insertFromFile() throws IOException
	{
		/* TEST INSERT COMPLEXITY */
		
		//LOADING AND TOKENIZING FILE
			//Read the file
			//For each word, check whether linked list 
			//Insert the values
		
		//LOAD PRIDE AND PREJUIDICE TEXT
		File inputFile = new File("src/exercise/one/setImplementations/pride-and-prejudice.txt");
		
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		
		long insertStart, insertEnd, insertDuration;
		
		String currLine;
		StringTokenizer st;
		String currWord;
		
		insertDuration = 0;
		
		//Read through the file and Tokenize to alpha-numeric characters. 
		while ((currLine = br.readLine()) != null) {
			st = new StringTokenizer(currLine, " -.");
			
			while(st.hasMoreTokens()) {
				
				currWord = st.nextToken().replaceAll("[^A-Za-z0-9]", "");
				
				//want to make sure each set is unique, so don't want to add
				//the same word to thet set. 
				if (!contains(currWord))
				{
					insertStart = System.nanoTime();
					add(currWord);
					insertEnd = System.nanoTime();
					
					//print cumulative searchTime
					insertDuration += (insertEnd - insertStart);
					
					//Print cumulative insertTime
					//System.out.println(insertDuration);
					
					
				}
			}
			
		}
		
		//table.display();
		System.out.println("Total number of unique words inserted: " + size());
		System.out.println("Total Insert Time: " + insertDuration + " ns\n");
	}
	
	public void searchComplexity() throws IOException
	{
		
		/* TEST SEARCH COMPLEXITY*/
		
		//Read through the file and Tokenize to alpha-numeric characters. 
		File searchFile = new File("src/exercise/one/setImplementations/words-shuffled.txt");
		
		BufferedReader sr = new BufferedReader(new FileReader(searchFile));
		

		String currLine;
		StringTokenizer st;
		String currWord;
		
		//set the counter
		int wordsNotExist = 0;
		
		boolean searchResult;
		long searchStart, searchEnd, searchDuration;
		searchDuration = 0;
		
		while ((currLine = sr.readLine()) != null) {
			st = new StringTokenizer(currLine, " -.");
			
			while(st.hasMoreTokens()) {
				
				currWord = st.nextToken().replaceAll("[^A-Za-z0-9]", "");
				
				searchStart = System.nanoTime();
				searchResult = contains(currWord);
				searchEnd = System.nanoTime();
				
				//calculate cumulative searchTime
				searchDuration += (searchEnd - searchStart);
				
				//print cumulative searchTime
				//System.out.println(searchDuration);
				
				//want to make sure each set is unique, so don't want to add
				//the same word to the set. 
				if (!searchResult)
				{
					wordsNotExist ++;
				}
			}
			
		}
		
		System.out.println("Number of words that do not exist from 2nd file: " + wordsNotExist);
		System.out.println("Total Search Time: " + searchDuration + " ns");
		
	}
	/************** HELPER FUNCTIONS (END) ***********/
	
	/******** MAIN METHOD ************/
	/*public static void main (String[] args) throws IOException
	{
		
		//Start with empty list
		LinkedList list = new LinkedList();
		
		System.out.println("LINKED LIST: ");
		list.insertFromFile();
		list.searchComplexity();
		

	}
*/
}