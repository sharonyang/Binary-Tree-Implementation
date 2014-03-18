// Sharon Yang
// BinaryTree implementation by Sharon Yang
// This BTree has nodes that assocaites a key (int) to a name (String).
// Notice that it is a Binary SEARCH Tree, hence the nodes are sorted.
// Callable functions:
//  - addNode(key, name): add a node to the tree
//  - findNode(key): return a node that associates with the key
//  - printInOrder(): print the BTree in order.
//  - printReverse(): print the BTree in reverse.
//  - printPreOrder(): print the BTree by first node first printed basis.
//  - Size(node): return an int of the size of the tree starting with the node.
//  - printLevel(target_level, current_level, node): return all nodes on target level inputting
//    our current level and current node.
//  - findLongHeight(node): find the longest distance from node entered to a bottom "leaf."
//  - findShortHeight(node): find the shortest distance from node entered to a bottom "leaf."
//  - deleteNode(key, node): delete the node with specified key, starting the search from the node
//    inputted.

class Node {
  int value;
  String name;
  Node left;
  Node right;
  
  Node() {
    this.value = 0;
    this.left = null;
    this.right = null;
    this.name = "";
  }
  
  Node(int data, String name) {
    this.value = data;
    this.name = name;
  }
}

public class BinaryTree {
  Node head;
  BinaryTree() {
    this.head = null;
  }
  
  public void addNode(int data, String name) {
    if (head == null) {
      Node newNode;
      newNode = new Node(data, name); // now you have the node
      head = newNode;
    }
    else {
      add(data, name, head);
      return;
    }
  }
  
  public void add(int data, String name, Node curr) {
    if (curr.value == data) {
      return;
    }
    if (curr.value < data) {
      if (curr.right == null) {
        Node newNode;
        newNode = new Node(data, name);
        curr.right = newNode;
      }
      else {
        add(data, name, curr.right);
      }
    }
    else {
      if (curr.left == null) {
        Node newNode;
        newNode = new Node(data, name);
        curr.left = newNode;
      }
      else {
        add(data, name, curr.left);
      }
    }
  }
  
  public Node findNode(int key) {
    if (head == null) {
      return null;
    }
    else {
      return find(head, key);
    }
  }
  
  public Node find(Node curr_node, int key) {
    if (curr_node == null) {
      return null;
    }
    else if (curr_node.value == key) {
      return curr_node;
    }
    else if (key > curr_node.value) {
      return find(curr_node.right, key);
    }
    else {
      return find(curr_node.left, key);
    }
  }
  
  public void printInOrder(Node curr_node) {
    if (curr_node == null) {
      return;
    }
    printInOrder(curr_node.left);
    System.out.print(curr_node.value + " ");
    printInOrder(curr_node.right);
  }
  
  public void printReverse(Node curr_node) {
    if (curr_node == null) {
      return;
    }
    printReverse(curr_node.right);
    System.out.print(curr_node.value + " ");
    printReverse(curr_node.left);
  }
  
  public void printPreOrder(Node curr_node) {
    if (curr_node == null) {
      return;
    }
    //System.out.print(curr_node.value + " ");
    printPreOrder(curr_node.left);
    printPreOrder(curr_node.right);
  }
  
  public int Size(Node curr) {
    if (curr == null) {
      return 0;
    }
    return 1 + Size(curr.left) + Size(curr.right);
  }
  
  public void printLevel(int level, int curr_level, Node start) {
    if (start == null) {
      return;
    }
    if (level == curr_level) {
      System.out.print(start.value + " ");
    }
    else {
      printLevel(level, curr_level + 1, start.left);
      printLevel(level, curr_level + 1, start.right);
    }
  }
  
  public int findLongHeight(Node start) {
    if (start == null) {
      return 0;
    }
    else {
      return 1 + Math.max( findLongHeight(start.right), findLongHeight(start.left) );
    }
  }
  
  public int findShortHeight( Node start ) {
    if (start == null) {
      return 0;
    }
    else {
      return 1 + Math.min( findShortHeight(start.left), findShortHeight(start.right));
    }
  }
  
  public boolean deleteNode(int key, Node curr) {
    if (curr == null) {
      return false;
    }
    
    else if (curr.left == null && curr.right == null && curr.value == key) {
      curr = null;
      return true;
    }
    
    else if ( (curr.left == null) != (curr.right == null) && curr.value == key) {
      if (curr.left != null) {
        curr = curr.left;
      }
      else if (curr.right != null) {
        curr = curr.right;
      }
      return true;
    }
    
    else if ( curr.left != null && curr.right != null && curr.value == key ) {
      Node found = returnWithMin( curr );
      curr.value = found.value;
      curr.name = found.name;
      
      return true;
    }
    
    else {
      if (curr.value > key) {
        return deleteNode(key, curr.left);
      }
      else {
        return deleteNode(key, curr.right);
      }
    }
  }
  
  public Node returnWithMin(Node curr) {
    Node prev = curr;
    curr = curr.right;
    if (curr.left == null && curr.right == null) {
      prev.right = null;
      return curr;
    }
    else if (curr.left == null && curr.right != null) {
      prev.right = curr.right;
      return curr;
    }
    while (true) {
      if (curr.left != null) {
        prev = curr;
        curr = curr.left;
      }
      else {
        prev.left = null;
        return curr;
      }
    }
  }
  
  public static void main (String [] args) {
  } 
}