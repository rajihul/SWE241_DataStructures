package exercise.one.setImplementations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;



public class BinaryTree {

	//Variables of the Binary Tree Class.
	Node root;
	int currentSize = 0; 
	
	class Node {
		
		//Variables of the node object. Describes what the node object is.
		String value;
		Node left;
		Node right; 
		
		//Constructor of the Node Class
		Node(String value)
		{
			this.value = value;
			right = null;
			left = null;
		}
	}
	
	//Binary Tree Constructor
	
	public BinaryTree()
	{
		//Empty Constructor
	}
	
	//Methods of the Binary Tree
	  
	//Add new Node to Binary Tree
	public boolean add(String word)
	{
		//Building the new node with word and setting the next to null.
		Node new_node = new Node(word);
		new_node.left=null;
		new_node.right=null;
		
		 // If the Binary Tree is empty, 
        // then make the new node as the root 
        if (this.root == null) { 
            this.root = new_node; 
            //System.out.println("Root: " + new_node.value);
            currentSize ++;
            return true;
        }
        else
        {
         		//Start with the head node of the Binary Tree.
        		Node last = this.root;
        		
            	while (true)
            	{
            		if(last.value.compareToIgnoreCase(word) >= 0)
                	{
                		//go to the left
                		if(last.left == null)
                		{
                			//set the new node
                			last.left=new_node;
                			currentSize ++;
                			//System.out.println("Parent: " + last.value + " Left: " + last.left.value);
                			return true;
                		}
                		else
                		{
                			//keep traversing down
                			last  = last.left;
                		}
                		
                	}
                	else if (last.value.compareToIgnoreCase(word) < 0)
                	{
                		//go to the right
                		if(last.right == null)
                		{
                			//set the new node
                			last.right=new_node;
                			currentSize ++;
                			//System.out.println("Parent: " + last.value + " Right: " + last.right.value);
                			return true;
                		}
                		else
                		{
                			//keep traversing down
                			last=last.right;
                		}
                	}
            	}
            	
        }
  
	}
	
	public boolean contains(String word)
	{
		 // If the Binary Tree is empty, 
        // then the tree contains no data. 
        if (this.root == null) { 
            return false; //tree dosen't contain
        }
        else if (this.root.value.equalsIgnoreCase(word))
        {
        	return true; //tree contains
        }
        else
        {
         		//Start with the head node of the Binary Tree.
        		Node last = this.root;
        		
            	do
            	{
            		if(last.value.compareToIgnoreCase(word) >= 0)
                	{
                		//go to the left
            			if(last.left == null)
            			{
            				return false; //not there
            			}
            			else if(last.left.value.equalsIgnoreCase(word))
                		{
                			return true;
                		}
                		else
                		{
                			//keep traversing down
                			last  = last.left;
                		}
                		
                	}
                	else if (last.value.compareToIgnoreCase(word) < 0)
                	{
                		//go to the right
                		if(last.right == null)
            			{
            				return false; //not there
            			}
                		else if(last.right.value.equalsIgnoreCase(word))
                		{
                			return true;
                		}
                		else
                		{
                			//keep traversing down
                			last=last.right;
                		}
                	}
            	} while(last.right != null || last.left !=null);
            	
        }
        
		return false;
	}
	
	//Returns current size of the List stored in the currentSize variable. 
	public int size()
	{

		return currentSize;
	}
	
	/* HELPER FUNCTIONS */
	public void testTree()
	{
		/*TESTING*/
		add("hello");
		add("not");
		add("blue");
		add("nasty");
		add("baloon");
		add("bmes");
		add("nro");
		System.out.println("done...total size of Binary Tree: " + size());
		
		if(contains("balloon"))
			System.out.println("Word exists");
		else
			System.out.println("The word doesn't exist");
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
	/*
	//Main Method
	public static void main (String[] args) throws IOException
	{
	

		BinaryTree tree = new BinaryTree();
		
		//BinaryTree
		System.out.println("BINARY TREE: ");
		tree.insertFromFile();
		tree.searchComplexity();
	}
	*/
}
