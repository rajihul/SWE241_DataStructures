package exercise.one.setImplementations;

import java.io.IOException;

public class TestContainers {
	public static void main(String[] args) throws IOException
	{
		//Instantiate structures
		LinkedList list = new LinkedList();
		BinaryTree tree = new BinaryTree();
		HashTables table = new HashTables();
		
		System.out.println("*********** LINKED LIST: ************");
		list.insertFromFile();
		list.searchComplexity();
		

		
		//BinaryTree
		System.out.println("\n*************** BINARY TREE: *************");
		tree.insertFromFile();
		tree.searchComplexity();
		
		//Hash Tables
		System.out.println("\n**************** HASH TABLES: *******************");
		table.insertFromFile();
		table.searchComplexity();
	}
}
