package exercise.one.setImplementations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class HashTables {
	
	//instance variable declaration 
	int tableSize;
	int size;
	LinkedList[] HTList; //custom linked list that I made	

	//constructor
	public HashTables()
	{
		size = 0;
		tableSize = 6690;
		HTList = new LinkedList[tableSize];
		
		//initialize HT list
		for(int i = 0; i < tableSize; i ++)
		{
			HTList[i]=null;
		}
	}
	
	//Add new entry to Hash Table
	public boolean add(String word)
	{
		word = word.toLowerCase(); //let's filter case sensitivity out
		
		//the word is the key
		int hashCode = word.hashCode();
		
		//mapping string to an array index
		//taking the modulus restricts the index size between 1 to (tableSize - 1)
		int arrayIndex = Math.abs(hashCode) % tableSize;
		
		//check the hashed index
		if(HTList[arrayIndex] == null)
		{
			//create new linked list to the bucket
			HTList[arrayIndex] = new LinkedList();
		}
		
		HTList[arrayIndex].add(word);
		size ++;
		
		return true;
	}
	
	public boolean contains(String word)
	{
		word = word.toLowerCase(); //let's filter case sensitivity out
		
		int hashCode = word.hashCode(); //hash function: relationship between word and index
		int arrayIndex = Math.abs(hashCode) % tableSize;
		if(HTList[arrayIndex] == null)
		{
			//list doesn't exist
			return false;
		}
		
		if(HTList[arrayIndex].contains(word))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public int size()
	{
		return size;
	}
	
	/* HELPER FUNCTIONS*/
	
	// Display contents of table
	public void display()
	{
		for(int x = 0; x < HTList.length; x ++)
		{
			if(!(HTList[x] == null))
			{
				System.out.println("List " + x + ": " + HTList[x].size());
				HTList[x].displayList();
			}
			else
			{
				System.out.println("List " + x + ": Unused");
			}
		}
	}
	
	// Test Table
	public void testTable()
	{
		/* Used for verifying HashTable Works */
		add("hello");
		add("nice");
		add("great");
		add("yikes");
		add("him");
		add("her");
		
		display();
		System.out.println("The index of great is: " + contains("him"));
		
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
	
		
	//Main Method
	/*public static void main (String[] args) throws IOException
	{
		HashTables table = new HashTables();
		
		//Hash Tables
		System.out.println("HASH TABLES: ");
		table.insertFromFile();
		table.searchComplexity();
		
		
	}
	*/
}
