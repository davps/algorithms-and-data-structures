# My coding interview solutions and resources
Solutions I implemented for typical coding interview programming questions


## Tools 

#Drawing a graph on the browser

This is a specialized tool for graph drawing 

	http://www.samsarin.com/project/dagre-d3/latest/demo/interactive-demo.html

You can do, for example:

	```
	digraph {
	    C;
	    B -> C;
	    B -> D;
	 }
	```	

Draw.io is more manual but useful in some cases
	https://www.draw.io/


## Interactive construction of data structures
I've used this interactive tool:

	https://visualgo.net/en/heap

also, this one:
	
	https://www.cs.usfca.edu/~galles/visualization/Heap.html

## Tests 
	 * I implemented the test cases the main function of each class.
	 * There is always a full test coverage, without any external dependencies
	 * -Algorithm implemented using the TDD methodology (write tests first, then
	 *  implement the code that passes the tests). TDD is suitable for this case because
	 *  the requirements are well known.
	 * -Everything is on this single file (no make it easy to reproduce in codepad, for example)
	 * -I use digraph - dot (graphviz) language to represent the graph, an online 
	 *  renderer can be found here 
	 	 http://www.samsarin.com/project/dagre-d3/latest/demo/interactive-demo.html
	
