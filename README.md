# Algorithms and data structures in java

- [Minimum Spanning Tree](https://github.com/davps/algorithms-and-data-structures-in-java/blob/master/src/algorithms/SpanningTree.java) 

- [Breadth-first search](https://github.com/davps/algorithms-and-data-structures-in-java/blob/master/src/datastructures/graph/BFS.java)

- [Depth-first search](https://github.com/davps/algorithms-and-data-structures-in-java/blob/master/src/datastructures/graph/DFS.java)

- [R-way Trie](https://github.com/davps/algorithms-and-data-structures-in-java/blob/master/src/datastructures/sorting/bydigit/TrieST.java)

- [(Max) Heap](https://github.com/davps/algorithms-and-data-structures-in-java/blob/master/src/datastructures/tree/MaxHeap.java)

## Code organization

Each algorithm is implemented on a single file (I didn't split the classes on separate files) so you can run each algorithm from a single file. Just copy/paste any file in your java environment (independently from each other) and run it, without external dependencies.

## Tools 

### Drawing a graph on the browser

[This is a specialized tool](http://www.samsarin.com/project/dagre-d3/latest/demo/interactive-demo.html) for graph drawing 
	
You can do, for example:

```
digraph {
    C;
    B -> C;
    B -> D;
 }
```

[Draw.io](https://www.draw.io/) is more manual but useful in some cases


### Interactive construction of data structures
I've used this interactive tool:

https://visualgo.net/en/heap

also, this one:
	
https://www.cs.usfca.edu/~galles/visualization/Heap.html

## Tests 
 - I implemented the test cases the main function of each class.
 - There is always a full test coverage, without any external dependencies
 - The algorithms are implemented using the TDD methodology (write tests first, then
   implement the code that passes the tests). TDD is suitable for this case because
   the requirements are well known.
 - Everything is on this single file (no make it easy to reproduce in codepad, for example)
 - I use an online graph renderer called [digraph dot (graphviz)](http://www.samsarin.com/project/dagre-d3/latest/demo/interactive-demo.html) to represent the graph.
	 
	
