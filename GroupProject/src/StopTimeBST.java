import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.ArrayList;
import java.util.List;


public class StopTimeBST<Key extends Comparable<Key>, Value> {
    private Node root;            

    public class Node {
    	private Key key;         
    	private List<Value> vals;         
    	private Node left, right;  
    	private int N;             

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

    public boolean isEmpty() { return size() == 0; }

    public int size() { return size(root); }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public List<Value> get(Key key) { return get(root, key); }

    private List<Value> get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.vals;
    }


    public void put(Key key, Value val) {
        if (val == null) { delete(key); return; }
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