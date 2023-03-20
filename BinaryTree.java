import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class that represents a full binary tree
 * @see Node
 */
public class BinaryTree<T>{
    private Node<T> root = null;

    /**
     * Default constructor, creates an empty binary tree
     */
    public BinaryTree(){

    }

    /**
     * Creates a binary tree with the provided nodes
     * @param nodes A set of nodes to add to the binary tree
     */
    public BinaryTree(Node<T>[] nodes){
        for(Node<T> n: nodes){
            add(n.getData());
        }
    }

    /**
     * Creates a binary tree with the provided items
     * @param items The items to add to the tree
     */
    public BinaryTree(T[] items){
        for(T item: items){
            add(item);
        }
    }

    /**
     * Method to add an item to the tree
     * @param value The value to add the the tree
     */
    public void add(T value){
        root = recursiveAdd(root, value);
    }

    /**
     * Method to create a new root when adding to a tree
     * @param current The node to add to
     * @param value The value to add
     * @return A node with value added
     */
    private Node<T> recursiveAdd(Node<T> current, T value){
        if(current == null){
            return new Node<T>(value);
        }
        if((new Node<T>(value)).compareTo(current) < 0){
            current.left = recursiveAdd(current.left, value);
        }else if((new Node<T>(value)).compareTo(current) > 0){
            current.right = recursiveAdd(current.right, value);
        }else{
            return current;
        }

        return current;
    }

    /**
     * Method to test if a node is in the binary tree
     * @param n The node to check
     * @return A boolean representing if the node is in the binary tree
     */
    public boolean in(Node<T> n){
        if(root == null){
            return false;
        }
        Node<T> current = root;
        while(!current.equals(n)){
            if(n.compareTo(current) <= 0){
                current = current.left;
            }else{
                current = current.right;
            }
            if(current == null){
                return false;
            }
        }
        return true;
    }

    /**
     * A method that finds the lowest value node in the tree
     * @return The node that has the lowest value
     */
    public Node<T> min(){
        if(root == null){
            return null;
        }
        Node<T> current = root;
        while(current.left != null){
            current = current.left;
        }
        return current;
    }

    /**
     * A method that finds the highest value node in the tree
     * @return The node that has the highest value
     */
    public Node<T> max(){
        if(root == null){
            return null;
        }
        Node<T> current = root;
        while(current.right != null){
            current = current.right;
        }
        return current;
    }

    /**
     * A method that preordered traverses the tree
     * @return The array of all the data in the tree preordered
     */
    public Node<T>[] preorder(){
        return preorder(root);
    }

    /**
     * A method that preordered traverses a subtree
     * @param n The subtree to return the data of
     * @return The array of all the data in the sub-tree
     */
    public Node<T>[] preorder(Node<T> n){
        if(n == null){
            return null;
        }
        ArrayList<Node<T>> result = new ArrayList<Node<T>>();
        result.add(n);
        if(n.left != null){
            for(Node<T> i: preorder(n.left)){
                result.add(i);
            }
        }
        if(n.right != null){
            for(Node<T> i: preorder(n.right)){
                result.add(i);
            }
        }
        
        return Arrays.copyOf(result.toArray(), result.size(), Node[].class);
    }

    /**
     * A method that traverses the tree in order
     * @return The array of all the data in the tree
     */
    public Node<T>[] inorder(){
        return inorder(root);
    }

    /**
     * A method that traverses a subtree in order
     * @param n The sub-tree to traverse
     * @return The array of all the data in the subtree
     */
    public Node<T>[] inorder(Node<T> n){
        if(n == null){
            return null;
        }
        ArrayList<Node<T>> result = new ArrayList<Node<T>>();
        if(n.left != null){
            for(Node<T> i: inorder(n.left)){
                result.add(i);
            }
        }
        result.add(n);
        if(n.right != null){
            for(Node<T> i: inorder(n.right)){
                result.add(i);
            }
        }
        
        return Arrays.copyOf(result.toArray(), result.size(), Node[].class);
    }

    /**
     * A method that postorder traverses the tree
     * @return The array of all the data in the tree
     */
    public Node<T>[] postorder(){
        return postorder(root);
    }

    /**
     * A method that postorder traverses a subtree
     * @param n The subtree to traverse
     * @return The array of all the data in the subtree
     */
    public Node<T>[] postorder(Node<T> n){
        if(n == null){
            return null;
        }
        ArrayList<Node<T>> result = new ArrayList<Node<T>>();
        if(n.left != null){
            for(Node<T> i: postorder(n.left)){
                result.add(i);
            }
        }
        if(n.right != null){
            for(Node<T> i: postorder(n.right)){
                result.add(i);
            }
        }
        result.add(n);
       
        return Arrays.copyOf(result.toArray(), result.size(), Node[].class);
    }
}
