package exercise.three.Graphs;

import java.util.LinkedList;

/*
 * Author: Rahul Jain
 * Date: 10/22/19
 * SWE 241P LAB A: Applied Data Structures and Algorithms 
 * Exercise 3: Graph Data Structures
 * 
 */

public class Exercise3 {

	//Instance Variables
	//LinkedList adjacencyList;
	
	//Constructor
		public Exercise3()
		{
			//empty constructor
		}
		
	
	/***************************************************************/
	/************ GRAPH'S EDGE OBJECT REPRESENTATION	 ********************/
	/***************************************************************/

	//edge object to keep track of edges
	public class EdgeNode {
		//instance variables
		int reference;
		int referencee;
		
		//constructor, create edgeNode object
		public EdgeNode (int ref, int refee)
		{
			reference = ref;
			referencee = refee;
		}
		
		public int getRef()
		{
			return this.reference;
		}
		
		public int getRefee()
		{
			return this.referencee;
		}
		
		//check whether the edge exist in the set. return index of where it exists
		public int edgeExists(int a, int b, EdgeNode[] nodes)
		{
			//traverse the set of edges
			for(int x=0; x < nodes.length; x ++)
			{
				if(nodes[x].getRef()==a)
				{
					if(nodes[x].getRefee()==b)
					{
						return x;
					}
				}
						
				else if(nodes[x].getRef()==b) 
				{
					if(nodes[x].getRefee()==a)
					{
						return x;
					}
					
				}
			}
			
			return -1;
		}
		

	}
	
	/***************************************************************/
	/***************************************************************/
	

	/**********************************************************************************************/
	/**********************************************************************************************/
	//**********              ADJACENCY MATRIX TO ADJACENCY LIST				*******************/
	/**********************************************************************************************/
	/**********************************************************************************************/
	
	//Method to convert from adjacency matrix to adjacency list
	public void amToAL(int[][] ad_matrix)
	{
		int rowLength = ad_matrix.length;
		int colLength = ad_matrix[0].length;
		
		//Create an array fo linked lists
		LinkedList<Integer> list[] = new LinkedList[rowLength];

		//System.out.println("row: " + rowLength + ", col: " + colLength);
		//For each row (vertex)
		for (int i=0; i < rowLength; i++)
		{
			list[i] = new LinkedList<>();
			
			//add the head node corresponding to the vertex
			list[i].add(i);
			//Traverse the columns, representing edges for current i
			for (int j=0; j < colLength; j++)
			{
				 
				//Check if the value is 1 where i and j meet
				//This would represent an edge
				if (ad_matrix[i][j]==1)
				{
					//Build the adjacency list for given vertex ,i, by adding the adjacent vertex to list
					 list[i].add(j);
				}
			}
		}
		
		System.out.println("1. Convert from adjacency matrix to adjacency list: \n");
		
		
		//print list for testing purposes.
		for(int y=0; y < list.length; y++)
		{
			System.out.print("\tVertex " + y + ": ");
			for(int z=0; z < list[y].size(); z ++)
			{
				System.out.print(list[y].get(z) + " ");
			}
			System.out.println();
		}
		
		System.out.println("\n\tTime Complexity: O(N^2) becuase you have to linearly");
		System.out.println("\tscan all elements of a NxN 2D adjacency matrix, ");
		System.out.println("\twhere N = number of vertices, and insert values ");
		System.out.println("\tinto Linked List in O(1) time.");
		
	}
	
	/***************************************************************************/
	/*********     <END> ADJACENCY MATRIX TO ADJACENCY LIST <END>  *************************/
	/***************************************************************************/
	

	/**********************************************************************************************/
	/**********************************************************************************************/
	//**********              ADJACENCY LIST TO INCIDENCE MATRIX				*******************/
	/**********************************************************************************************/
	/**********************************************************************************************/
	
	//Method to convert from adjacency list to an incidence matrix.
	public void alToIM(LinkedList<Integer>[] adjacencyList)
	{
		int numVertices = adjacencyList.length;
		EdgeNode[] graphEdges = readEdges(adjacencyList);
		int edgeCount = numEdges(adjacencyList);
		int[][] incidenceMatrix = new int[numVertices][edgeCount];
		int edgeindex = 0;
		
		//System.out.println("Edge Count: " + edgeCount);
		for(int i=0; i < numVertices; i++)
		{
			//for each vertex, check if each element  adjacency list.
			for(int j=1; j < adjacencyList[i].size(); j ++)
			{
				edgeindex = graphEdges[j].edgeExists(adjacencyList[i].get(0), adjacencyList[i].get(j), graphEdges);
				if (edgeindex != -1) 
				{
					incidenceMatrix[i][edgeindex] = 1;
				}
			}
		}
		
		System.out.println("2. Convert from adjacency list to an incidence matrix:\n");
		
		//Print Matrix
		for(int p=0; p < numVertices; p++)
		{	
			for(int m=0; m < edgeCount; m++)
			{
				System.out.print("\t" + incidenceMatrix[p][m] + " ");
			}
			System.out.println();
		}
		
		System.out.println("\n\tTime Complexity: O(NxMxO) where N = # of vertices,");
		System.out.println("\tM = max adjacent nodes and O = total # of edges");
		//System.out.println("\tand insert values into Linked List in O(1) time.");
	}
	
	//helper functions
	
	//count edges
	public int numEdges(LinkedList<Integer>[] list)
	{
		int count = 0;
		for(int y=0; y < list.length; y++)
		{
			//for each vertex count how many adjacent vertexes.
			//start from 1 as to not include head. you don't want to loop (count iteslf).
			for(int z=1; z < list[y].size(); z ++)
			{
				//increment the count
				count ++;
			}
			
		}
		return (count/2);
		
	}
	
	//returns a set of edges unique to the graph (ie. set of vertices).
	public EdgeNode[] readEdges(LinkedList<Integer>[] list)
	{
		EdgeNode edge; 
		EdgeNode[]  listEdges = new EdgeNode[numEdges(list)]; 
		int edgeCounter = 0;
		
		//initialize edges array
		for(int i=0; i < listEdges.length; i ++)
		{
			listEdges[i] = new EdgeNode(0,0);
		}
		
		for(int y=0; y < list.length; y++)
		{
			//for each vertex count how many adjacent vertexes.
			//System.out.println("Size " + list.length + " : " + list[y].size());
			for(int z=1; z < list[y].size(); z ++)
			{
				
				//create a new edge instance
				edge = new EdgeNode(list[y].get(0), list[y].get(z));
				
				//add the edge if it doesn't exist
				if(edge.edgeExists(list[y].get(0), list[y].get(z), listEdges) == -1)
				{
					listEdges[edgeCounter]=edge;  
					//System.out.println("set (" + list[y].get(0)+ ", " + list[y].get(z) + ")");
					edgeCounter ++;
				}
			}
		}
		
		return listEdges;
	}
	
	/***************************************************************************/
	/*********     <END> ADJACENCY LIST TO INCIDENCE MATRIX <END>  *************/
	/***************************************************************************/
		
	/**********************************************************************************************/
	/**********************************************************************************************/
	/**********************************************************************************************/
	//**********              INCIDENCE MATRIX TO ADJACENCY LIST				*******************/
	/**********************************************************************************************/
	/**********************************************************************************************/
	/**********************************************************************************************/
	
	//Method to convert from an incidence matrix to adjacency list 
	public void imToAL(int[][] incidentMatrix)
	{
		//Row of the incident list tells us the # of vertices
		int numVertices = incidentMatrix.length;
		
		//length of an element tells us the column length
		int edges = incidentMatrix[0].length;
		
		//Test it
		//System.out.println("row: " + numVertices + " col: " + edges);
		
		LinkedList<Integer> list[] = new LinkedList[numVertices];
		
		int edgeNode1 = 0, edgeNode2= 0, edgeCounter=0;
		
		//inatialize linkedlist
		for(int p=0; p < numVertices; p++)
		{
			list[p] = new LinkedList();
			
			////add the head node representing the vertex number
			list[p].add(p);
		}
		//Each row represents vertex, so read the ones in the columns to tell you which are adjacent vertices
		for(int i = 0; i < edges; i++)
		{
			for(int j=0; j < numVertices; j ++)
			{
				if(incidentMatrix[j][i] == 1)
				{
					if(edgeCounter == 0)
					{
						edgeNode1=j;
					}
					else 
					{
						edgeNode2=j;
					}
					edgeCounter++;
				}
				
			}
			list[edgeNode1].add(edgeNode2);
			list[edgeNode2].add(edgeNode1);
			edgeCounter = 0;
		}
		
		System.out.println("3. Convert from an incidence matrix to adjacency list \n");
		
		//print list
		for(int y=0; y < list.length; y++)
		{
			System.out.print("\tVertex " + y + ": ");
			for(int z=0; z < list[y].size(); z ++)
			{
				System.out.print(list[y].get(z) + " ");
			}
			System.out.println();
		}
		
		//Time Complexity Output
		System.out.println("\n\tTime Complexity: O(NxM) becuase you have to linearly");
		System.out.println("\tscan all elements of a NxM 2D incidence matrix, where");
		System.out.println("\tN = # of vertices and M= # of edges in addition");
		System.out.println("\tto inserting values into Linked List in O(1) time.");
		
	}
	
	/***************************************************************************/
	/*********     <END> INCIDENCE MATRIX TO ADJACENCY LIST <END>  *************/
	/***************************************************************************/
		
	
	/**********************************************************************/
	//**********              MAIN METHOD				*******************/
	/**********************************************************************/
	
	public static void main (String[] args)
	{
		//System.out.println("This is the main method");
		Exercise3 test = new Exercise3();
		//build the matrix representing
		/*
		 * 
		 * 0------1\	-
		 * |    / | \
		 * |   /  |  2
		 * |  /   | /
		 * 4------3/
		 *  OUTPUT:
		 *  Vertex 0: 1 4 
			Vertex 1: 0 2 3 4 
			Vertex 2: 1 3 
			Vertex 3: 1 2 4 
			Vertex 4: 0 1 3 
		 * 
		 * 
		 */
		
		//The Test problem
		 System.out.println("The Test Case: ");
		 System.out.println("0------1\\	-");
		 System.out.println("|    / | \\");
		 System.out.println("|   /  |  2");
		 System.out.println("|  /   | /");
		 System.out.println("4------3/");
		 System.out.println();
		 
		//TESTING  ALGORITHM: Adjacency Matrix to Adjacency List
		int[][] graph = new int[][]{
			  { 0, 1, 0, 0, 1 },
			  { 1, 0, 1, 1, 1 },
			  { 0, 1, 0, 1, 0 },
			  { 0, 1, 1, 0, 1 },
			  { 1, 1, 0, 1, 0 }
			};
			
		test.amToAL(graph);
		
		System.out.println();
		
		//TESTING ALGORITHM: Adjacency List to Incidence Matrix
		LinkedList<Integer>[] inputList = new LinkedList[5];
		LinkedList sublist;
		
		sublist = new LinkedList();
		sublist.add(0);
		sublist.add(1);
		sublist.add(4);
		inputList[0] = sublist;
		
		sublist = new LinkedList();
		
		sublist.add(1);
		sublist.add(0);
		sublist.add(2);
		sublist.add(3);
		sublist.add(4);
		inputList[1] = sublist;
		
		sublist = new LinkedList();
		
		sublist.add(2);
		sublist.add(1);
		sublist.add(3);
		inputList[2] = sublist;
		
		sublist = new LinkedList();
		
		sublist.add(3);
		sublist.add(1);
		sublist.add(2);
		sublist.add(4);
		inputList[3] = sublist;
		
		sublist = new LinkedList();
		
		sublist.add(4);
		sublist.add(0);
		sublist.add(1);
		sublist.add(3);
		inputList[4] = sublist;
		
		test.alToIM(inputList);
		
		System.out.println();
		
		//TESTING ALGORITHM: Incidence Matrix to Adjacency List 
				int[][] incidenceList = new int[][]{
					  { 1, 1, 0, 0, 0, 0, 0 },
					  { 1, 0, 1, 1, 1, 0, 0 },
					  { 0, 0, 1, 0, 0, 1, 0 },
					  { 0, 0, 0, 1, 0, 1, 1 },
					  { 0, 1, 0, 0, 1, 0, 1 }
					};
		
			test.imToAL(incidenceList);
	}
		
	
}
