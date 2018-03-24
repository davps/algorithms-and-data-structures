package datastructures.graph;

public class BFS {
	
	public static void main(String[] args) {
		/*		
			digraph{
			 0->"1 visited=true"
			 0->"2 visited=false"
			 "2 visited=false"->"1 visited=true"
			 "1 visited=true"->null;
		}		
		*/		
		final Node n0 = new Node(0);
		final Node n1 = new Node(1);
		final Node n2 = new Node(2);
		n0.addAdjacents(n1);
		n0.addAdjacents(n2);
		n2.addAdjacents(n1);
		
		final BFS bfs = new BFS();
		final String result = bfs.search(n0);
		final String msg1 = "0->1->2->";
		assertEqual(result, msg1, "BFS " + msg1);
		
		/*
			digraph{
				 0;1;2;3;4;5;
				//visit first children of root node (0)
				0->1 
				//visiting first child (1) of (0)
				1->3
				//visiting first child (3) of (1)
				3->2
				2->1 //skip the visited child (1) 
				3->4 //node (4) have no links, so, end and go up (to parent)
				1->4 //skip visited child (4)
				0->4 //skip visited child (4)
				0->5
			}	
		 */
		final Node x0 = new Node(0);
		final Node x1 = new Node(1);
		final Node x2 = new Node(2);
		final Node x3 = new Node(3);
		final Node x4 = new Node(4);
		final Node x5 = new Node(5);
		x0.addAdjacents(x1, x4, x5);
		x1.addAdjacents(x3, x4);
		x2.addAdjacents(x1);
		x3.addAdjacents(x2);
		final String result2 = bfs.search(x0);
		final String msg2 = "0->1->4->5->3->2->";
		assertEqual(result2, msg1, "BFS " + msg2);
	}

	private static void assertEqual(String result, String expect, String msg) {
		if(result.equals(expect)) {
			System.out.println("Success: "+msg);
		}else {
			System.err.println("Failure: "+msg+". Expected "+expect+" but got "+ result);
		}
	}

}
