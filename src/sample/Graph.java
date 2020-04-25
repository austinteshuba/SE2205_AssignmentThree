// Graph.java
// Austin Teshuba
// This is a Graph class that includes methods to traverse the graph (BFS and DFS)

package sample;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack; 

public class Graph {

    private HashMap<Node, LinkedList<Node>> adjacencyMap;
    public Graph() {
        adjacencyMap = new HashMap<>();
    }

    public void addEdgeHelper(Node a, Node b) {
        LinkedList<Node> tmp = adjacencyMap.get(a);

        if (tmp != null) {
            tmp.remove(b);
        }
        else tmp = new LinkedList<>();
        tmp.add(b);
        adjacencyMap.put(a,tmp);
    }

    public void addEdge(Node source, Node destination) {

        if (!adjacencyMap.keySet().contains(source))
            adjacencyMap.put(source, null);

        if (!adjacencyMap.keySet().contains(destination))
            adjacencyMap.put(destination, null);

        addEdgeHelper(source, destination);
        addEdgeHelper(destination, source);
        
    }
    public boolean hasEdge(Node source, Node destination) {
        return adjacencyMap.containsKey(source) && adjacencyMap.get(source).contains(destination);
    }
    
    public void resetNodesVisited()
    {
    	 for (Node n : adjacencyMap.keySet()) {
             n.unvisit();
         }
    
    }
    
    void BFS(Node node) {
    	//implement the BFS code
    	resetNodesVisited();
    	// This is going to use a queue
    	LinkedList<Node> nodes = new LinkedList<Node>();
    	Node currentNode = null;
    	nodes.addLast(node); // Always add to the end, pull from the front
    	node.visit();
    	do {
    		currentNode = nodes.pop(); // this will pop from the front of the list.
    		LinkedList<Node> neighbours = adjacencyMap.get(currentNode);
    		
    		for (Node n : neighbours) {
    			if (!n.isVisited()) {
    				nodes.addLast(n);
    				n.visit();
    			}
    		}
    		System.out.print(currentNode.name + " ");
    	} while(!nodes.isEmpty());
    	System.out.println("");
    }
    
    String BFSText(Node node) {
    	//implement the BFS code
    	resetNodesVisited();
    	String out = "";
    	// This is going to use a queue
    	LinkedList<Node> nodes = new LinkedList<Node>();
    	Node currentNode = null;
    	nodes.addLast(node); // Always add to the end, pull from the front
    	node.visit();
    	do {
    		currentNode = nodes.pop(); // this will pop from the front of the list.
    		LinkedList<Node> neighbours = adjacencyMap.get(currentNode);
    		
    		for (Node n : neighbours) {
    			if (!(n==null) && !n.isVisited()) {
    				nodes.addLast(n);
    				n.visit();
    			}
    		}
    		out += currentNode.name + " ";
    	} while(!nodes.isEmpty());
    	out += "\n";
    	return out;
    }
    
   
   public void DFS(Node node) {
	   // Helper function to print output with new line.
	   DFSRecurse(node);
	   System.out.println("");
   }
   
   public void DFSRecurse(Node node) {
	   node.visit(); // visit the node we are currently at and print it out. 
	   System.out.print(node.name + " ");
	   LinkedList<Node> neighbours = adjacencyMap.get(node); // get its neighbours and iterate through them
	   for (Node n: neighbours) {
		   if (!(n==null) && !n.isVisited()) { // if the neighbour has not been visited, run DFS on it.
			   DFSRecurse(n);
		   }
			   
	   }
   }
   
   public String DFSText(Node node) {
//     //Implement DFS
//	   // Start by resetting the node list
//	   resetNodesVisited();
//	   // DFS is going to use a stack
//	   Stack<Node> nodes = new Stack();
//	   // We must ensure we don't revisit visited nodes
//	   String output = ""; // We will start with an empty output string for the DFS/BFS
//	   Node currentNode = null; // we will start by considering the current node
//	   nodes.add(node);
//	   node.visit();
//	   do {
//		   currentNode = nodes.pop(); // Get the next current node from the stack
//		   // Note: this is going to be a unique node since we don't add nodes to the stack that 
//		   // have been visited. mark as visited when added to the stack
//		   LinkedList<Node> neighbours = adjacencyMap.get(currentNode);
//		   
//		   for (Node n : neighbours) {
//			   if (!n.isVisited()) {
//				   nodes.add(n);
//				   n.visit();
//			   }
//		   }
//		   output = output + " " + currentNode.name;
//		   
//	   } while (!nodes.isEmpty());
//	   
//	   return output;
	   node.visit(); // visit the node we are currently at and print it out.
	   String current = "";
	   current+= node.name + " ";
	   LinkedList<Node> neighbours = adjacencyMap.get(node); // get its neighbours and iterate through them
	   for (Node n: neighbours) {
		   if (!(n==null) && !n.isVisited()) { // if the neighbour has not been visited, run DFS on it.
			   current += DFSText(n);
		   }
	   }
	   return current;
   }
   
   public int numberOfNodes() {
	   return adjacencyMap.size();
   }
   
   public void printEdges() {
       //implement printEdges
	   for (Node n : adjacencyMap.keySet()) {
		   System.out.print("The " + n.name + " has an edge towards: ");
		   for (Node b : adjacencyMap.get(n)) {
			   System.out.print(" " + b.name);
		   }
		   System.out.println("");
	   }
   }
   
   public String edgesText() {
	   String out = "";
	   for (Node n : adjacencyMap.keySet()) {
		   out += "The " + n.name + " has an edge towards: ";
		   for (Node b : adjacencyMap.get(n)) {
			   out += " " + b.name;
		   }
		   out += "\n";
	   }
	   return out;
   }
}
