# Algorithms and data structures in java

- [Minimum Spanning Tree](https://github.com/davps/algorithms-and-data-structures-in-java/blob/master/src/algorithms/SpanningTree.java) 

- [Breadth-first search](https://github.com/davps/algorithms-and-data-structures-in-java/blob/master/src/datastructures/graph/BFS.java)

- [Depth-first search](https://github.com/davps/algorithms-and-data-structures-in-java/blob/master/src/datastructures/graph/DFS.java)

- [R-way Trie](https://github.com/davps/algorithms-and-data-structures-in-java/blob/master/src/datastructures/sorting/bydigit/TrieST.java)

- [(Max) Heap](https://github.com/davps/algorithms-and-data-structures-in-java/blob/master/src/datastructures/tree/MaxHeap.java)

- [Linked List](https://github.com/davps/algorithms-and-data-structures-in-java/blob/master/src/datastructures/linear/LinkedList.java)

- [Stack](https://github.com/davps/algorithms-and-data-structures-in-java/blob/master/src/datastructures/linear/Stack.java)

- [Queue](https://github.com/davps/algorithms-and-data-structures-in-java/blob/master/src/datastructures/linear/Queue.java)

- [Merge sort](https://github.com/davps/algorithms-and-data-structures-in-java/blob/master/src/datastructures/sorting/bycomparison/Merge.java)

- [Bubble sort](https://github.com/davps/algorithms-and-data-structures-in-java/blob/master/src/datastructures/sorting/bycomparison/Bubble.java)

- [Selection sort](https://github.com/davps/algorithms-and-data-structures-in-java/blob/master/src/datastructures/sorting/bycomparison/Selection.java)

- [Tree Pre-order, In-order and Post-order traversal](https://github.com/davps/algorithms-and-data-structures-in-java/blob/master/src/datastructures/tree/Traversal.java)



## Code organization

Each algorithm is implemented on a single file (I didn't split the classes on separate files) so you can run each algorithm from a single file, which make it easy to run it on [CoderPad](https://coderpad.io), for example, or by just copy/pasting any file in your java environment (independently from each other) without any external dependency.

## Tools 

### Drawing a graph on the browser

[GraphvizOnline](https://dreampuf.github.io/GraphvizOnline/) is a tool for graph drawing. There are many tools like this online, all based on Graphviz.

You can specify a tree like this:

```
digraph {
    C;
    B -> C;
    B -> D;
 }
```
which outputs this image:

![Demo Digraph](./assets/graph1.png?raw=true)

Also, a linked list:

```
digraph {
    node [shape=record];
    node0[label="<data> 12 | <pointer> next"]
    node2[label="<data>  1 | <pointer> next"]
    node0:pointer -> node2:data
}
```
![Demo Digraph 2](./assets/graph2.png?raw=true)


and you can even draw a fancier version, like this:

```
digraph g {
    rankdir=LR;
    node [shape=record];
    a [label="{ <data> 12 | <pointer>  }", width=1.2]
    b [label="{ <data> 1 | <pointer>  }"];
    c [label="{ <data> 7 | <pointer>  }"];
    d [shape=box];
    a:pointer:c -> b:data [arrowhead=vee, arrowtail=dot, dir=both, tailclip=false, arrowsize=1.2];
    b:pointer:c -> c:data [arrowhead=vee, arrowtail=dot, dir=both, tailclip=false];
    c:pointer:c -> d      [arrowhead=vee, arrowtail=dot, dir=both, tailclip=false];
}

```
![Demo Digraph 3](./assets/graph3.png?raw=true)


[Draw.io](https://www.draw.io/) is more manual, for general purpose drawing, but could be useful in some cases.


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
 - Everything is on this single file (to make it easy to reproduce in codepad, for example)
 - I use an online graph renderer called [digraph dot (graphviz)](http://www.samsarin.com/project/dagre-d3/latest/demo/interactive-demo.html) to represent the graph.
	 
	
