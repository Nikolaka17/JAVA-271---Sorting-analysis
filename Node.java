/**
 * A class that holds a specific node of a binary tree
 * @see BinaryTree
 */
public class Node<T> implements Comparable<Node<T>>{
    private T data;
    public Node<T> left = null;
    public Node<T> right = null;

    /**
     * Constructor that sets the stored data
     * @param s The data to store in the node
     */
    public Node(T s){
        data = s;
    }

    /**
     * Accessor for the instance variable data
     * @return The value stored in 'data'
     */
    public T getData(){
        return data;
    }

    /**
     * Overriden method to compare different nodes
     * @param n The node to compare to
     * @return An integer, 1 if n is greater, 0 if n is equal, -1 if n is lesser
     */
    @Override
    public int compareTo(Node<T> n){
        return getData().hashCode() - n.getData().hashCode();
    }

    /**
     * overriden method to check if nodes are equal
     * @param o A node to compare to
     * @return A boolean representing if the data of the two nodes is equal
     */
    @Override
    public boolean equals(Object o){
        return data.equals(((Node<T>) o).getData());
    }
}
