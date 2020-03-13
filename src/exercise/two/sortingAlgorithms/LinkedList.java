
//LinkedList customized for SelectionSort
public class LinkedList {
	Node head; // head of list
	Node tail; // keep track of the end of the list
	int currentSize = 0; 
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
	       currentSize ++; //increase size by 1
	    }
	
	public LinkedList() {
		//Constructor with no input.
	}

	//Methods of the Linked List
	  
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
		
		
		//check last node = will be head in size 1 list, or last node in size > 1 node.
		//For the word in question, check each node to see if it occurs at all.
		if (last.data.equalsIgnoreCase(word)){
			//The word is contained in the List and is not unique 
			return true;
		}
		
		//If the word is unique then return false to contains
		return false;
	}
	
	//Returns current size of the List stored in the currentSize variable. 
	public int size()
	{

		return currentSize;
	}
	
	//A complimentary method used for testing purposes.
	public void displayList()
	{
		Node last = this.head;
		while (last.next !=null){
			//count ++;
			System.out.println(last.data);
			last = last.next;
		}
		System.out.println(last.data);

	}
	
	public Node getHead()
	{
		return head;
	}
	
	public String[] listToArray()
	{
		int arraySize = size();
		int index = 0;
		String[] list;
		
		 // No need to sort, if just 1 element
        if (arraySize > 1) 
        { 
        	list = new String[arraySize];
        
        	//Start with the head node of the linked list.
        	Node last = this.head;
		
        	//check other nodes if LinkedList size is greater than 1
        	//Traverse all the way to the end of the node
        	while (last.next !=null)
        	{
        		list[index] = last.data;
        		index++;
        		last = last.next;
			}
        	list[arraySize-1]=last.data;
        }
        else
        {
        	//return an empty string with 0 content;
        	list = new String[1];
        	list[0]="";
        }
		return list;
	}


}