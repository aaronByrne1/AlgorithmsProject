import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.ArrayList;
import java.util.List;


public class StopTimeBST<Key extends Comparable<Key>, Value> {
    private Node root;             // root of BST

    public class Node {
    	private Key key;           // sorted by key
    	private List<Value> vals;         // associated data
    	private Node left, right;  // left and right subtrees
    	private int N;             // number of nodes in subtree

        public Node(Key key, Value val, int N) {
        	vals = new ArrayList<Value>();
            this.key = key;
            vals.add(val);
            this.N = N;
        }
        
        public List<Value> getVals() {return vals;}
        
        public Node getLeft() { return left;}
        
        public Node getRight() { return right;}
        
        public Key getKey() { return key; }
    }
    public Node getRoot() { return root; }
    
    public Key getKey(Node x) { return x.getKey(); } 

    // is the symbol table empty?
    public boolean isEmpty() { return size() == 0; }

    // return number of key-value pairs in BST
    public int size() { return size(root); }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    /**
     *  Search BST for given key.
     *  Does there exist a key-value pair with given key?
     *
     *  @param key the search key
     *  @return true if key is found and false otherwise
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     *  Search BST for given key.
     *  What is the value associated with given key?
     *
     *  @param key the search key
     *  @return value associated with the given key if found, or null if no such key exists.
     */
    public List<Value> get(Key key) { return get(root, key); }

    private List<Value> get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.vals;
    }

    /**
     *  Insert key-value pair into BST.
     *  If key already exists, add to list.
     *
     *  @param key the key to insert
     *  @param val the value associated with key
     */
    public void put(Key key, Value val) {
        if (val == null) { delete(key); return; } //?
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.vals.add(val);
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * Tree height.
     *
     * Asymptotic worst-case running time using Theta notation: Theta(N)
     * 
     * The method runs to every leaf in a similar way to a while loop, returning
     *  when it hits null. This means the height function is called on every elem
     *  only once and height has a base constant running time 
     * 
     *
     * @return the number of links from the root to the deepest leaf.
     *
     * Example 1: for an empty tree this should return -1.
     * Example 2: for a tree with only one node it should return 0.
     * Example 3: for the following tree it should return 2.
     *   B
     *  / \
     * A   C
     *      \
     *       D
     */
    public int height() 
    {
    	if(root == null)
    		return -1;
    	return height(root);
    }
    
    private int height(Node x)
    {
    	
    	if(x.left == null && x.right == null)
    		return 0;
    	
    	if(x.left != null && x.right == null)
    		return height(x.left) + 1;
    	
    	if(x.right != null && x.left == null)
    		return height(x.right) + 1;
    	
    	if(height(x.right) > height(x.left))
    		return height(x.right) + 1;
    	else
    		return height(x.left) + 1;
    		
    }

    /**
     * Median key.
     * If the tree has N keys k1 < k2 < k3 < ... < kN, then their median key 
     * is the element at position (N+1)/2 (where "/" here is integer division)
     *
     * @return the median key, or null if the tree is empty.
     */
    public Key median() {
      if (isEmpty()) return null;
      //TODO fill in the correct implementation. The running time should be Theta(h), where h is the height of the tree.
      return median(root, (root.N-1)/2);
    }

    private Key median(Node x, int median)
    {
    	int size = size(x.left);
    	if (size > median) return median(x.left, median);
    	else if (size < median) return median(x.right, median-size-1);
    	else return x.key;
    }

    /**
     * Print all keys of the tree in a sequence, in-order.
     * That is, for each node, the keys in the left subtree should appear before the key in the node.
     * Also, for each node, the keys in the right subtree should appear before the key in the node.
     * For each subtree, its keys should appear within a parenthesis.
     *
     * Example 1: Empty tree -- output: "()"
     * Example 2: Tree containing only "A" -- output: "(()A())"
     * Example 3: Tree:
     *   B
     *  / \
     * A   C
     *      \
     *       D
     *
     * output: "((()A())B(()C(()D())))"
     *
     * output of example in the assignment: (((()A(()C()))E((()H(()M()))R()))S(()X()))
     *
     * @return a String with all keys in the tree, in order, parenthesized.
     */
    public String printKeysInOrder() {
      if (isEmpty()) return "()";
      return printKeysInOrder(root);
    }
    
    private String printKeysInOrder(Node x)
    {
    	String currentString = "(";
    	if(x.left == null && x.right == null)
    	{
    		currentString+= "()" + x.key.toString() + "()";
    		return currentString + ")";
    	}
    	
    	if(x.left != null)
    	{
    		
    		currentString+= printKeysInOrder(x.left);
    		currentString+= x.key.toString();
    		if(x.right == null)
    			currentString+= "()";
    	}
    	if(x.right != null)
    	{
    		if(x.left == null)
    		currentString+= "()" + x.key.toString();
    		currentString+= printKeysInOrder(x.right);
    	}
    	
    	return currentString + ")";
    }
    /**
     * Pretty Printing the tree. Each node is on one line -- see assignment for details.
     *
     * @return a multi-line string with the pretty ascii picture of the tree.
     */
    public String prettyPrintKeys() {
      //TODO fill in the correct implementation.
    	 if (isEmpty()) return "-null\n";
    	 return prettyPrintKeys(root, "");
    }
    
    private String prettyPrintKeys(Node x, String prefix)
    {
    	String finalString = "";
    	finalString += prefix;
    	finalString += "-" + x.key.toString() + "\n";
    	
    	if(x.left != null)
    	finalString+=prettyPrintKeys(x.left, prefix+" |");
    	
    	if(x.left == null)
    		finalString+=prefix+" |-null\n";
    	
    	if(x.right == null)
    		finalString+=prefix+ "  -null\n";
    	
    	else
    		finalString+=prettyPrintKeys(x.right, prefix+"  ");
    	
    	return finalString;
    }
    
    /**
     * Deletes a key from a tree (if the key is in the tree).
     * Note that this method works symmetrically from the Hibbard deletion:
     * If the node to be deleted has two child nodes, then it needs to be
     * replaced with its predecessor (not its successor) node.
     *
     * @param key the key to delete
     */
    public void delete(Key key) 
    {
      root = delete(root, key);
    }
    
    private Node delete(Node x, Key key)
    {
    	if(x == null) return null;
    	int cmp = key.compareTo(x.key);
    	if (cmp < 0) x.left = delete(x.left, key);
    	else if (cmp > 0) x.right = delete(x.right, key);
    	else {
    		if (x.right == null) return x.left;
    		if (x.left == null) return x.right;
    		
    		Node t = x;
    		x = max(t.left);
    		x.left = delete(t.left, max(x).key);
    		x.right = t.right;
    	}
    	x.N = size(x.left) + size(x.right) + 1;
    	return x;
    }
	
	public Node max()
	{
		return max(root);
	}
	
	private Node max(Node x)
	{
		if(x == null)
			return null;
		if(x.right == null)
			return x;
		else
			return max(x.right);
	}
	

}