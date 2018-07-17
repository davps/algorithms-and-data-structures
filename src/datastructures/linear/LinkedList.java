package datastructures.linear;

public class LinkedList <T> {
	
	public static void main(String[] args) {
		
		/**
		 * Aceptance tests
		 */
		LinkedList<String> list = new LinkedList<String>();
		
		expectTrue(list.size() == 0, "List is empty");

		String hi = "Hello, world!";
		list.add(hi);
		
		expectTrue(list.peek().equals(hi) && list.size() == 1, 
				"Root to be " + hi);
		
		String item = "one more item";
		list.add("test 2");
		list.add(item);
		expectTrue(list.size() == 3, "List have 3 items");
		
		expectTrue(list.remove(0).equals(hi), "remove root");
		
		expectTrue(list.remove(list.size() - 1).equals(item) && list.size()==1, 
				"remove last item");
		
	}

	private static void expectTrue(boolean condition, String message) {
		if(condition) {
			System.out.println("Success: " + message);
		}else {
			System.err.println("Error: " + message);
		}
	}

}
