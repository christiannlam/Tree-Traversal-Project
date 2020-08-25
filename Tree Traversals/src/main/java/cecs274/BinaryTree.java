package cecs274;

import java.util.*;

/**
 * Parameterized binary tree class.
 * @author Alvaro Monge alvaro.monge@csulb.edu
 * @author Gideon Essel gideon.essel@student.csulb.edu
 * @author Christian Lam christian.lam@csulb.edu
 */
public class BinaryTree<E> {
   /** maximum height for the random tree */
   public static final int MIN_RANDOM_HEIGHT = 2;

   /** minimum height for the random tree */
   public static final int MAX_RANDOM_HEIGHT = 4;

   /** minimum value for the random int value to use as data */
   public static final int MIN_INT_VALUE = 1;

   /** maximum value for the random int value to use as data */
   public static final int MAX_INT_VALUE = 500;


   private BinaryTreeNode<E> root; // the only instance variable, do not add more!

   // A BinaryTreeNode object is one node in a binary tree.
   private static class BinaryTreeNode<E> {
      private E data;            // data stored at this node
      private BinaryTreeNode left;    // reference to left subtree
      private BinaryTreeNode right;   // reference to right subtree

      // Constructs a leaf node with the given data.
      public BinaryTreeNode(E data) {
         this(data, null, null);  // calls constructor with three parameters
      }

      // Constructs a branch node with the given data and links.
      public BinaryTreeNode(E data, BinaryTreeNode left, BinaryTreeNode right) {
         this.data = data;
         this.left = left;
         this.right = right;
      }
   } // end of inner class defining BinaryTreeNode


   // Now for BinaryTree constructors and methods
   public BinaryTree() {
      root = null;
   }

   // You can use this method to create random binary trees of Integer
   public BinaryTree<Integer> createRandomTree() {
      Random randomGenerator = new Random(103);  // Using seed to recreate the same tree each time, remove for random
      BinaryTree<Integer> tree = new BinaryTree<>();
      tree.root = createRandomTree(1, randomGenerator);
      return tree;
   }

   // You can use this method to create random binary trees
   private BinaryTreeNode<Integer> createRandomTree(int height, Random randomGenerator) {

      int randomData = MIN_INT_VALUE + randomGenerator.nextInt(MAX_INT_VALUE - MIN_INT_VALUE + 1);
      BinaryTreeNode<Integer> theRoot = new BinaryTreeNode<>(randomData);

      if ( (height < MIN_RANDOM_HEIGHT || randomGenerator.nextBoolean()) && (height < MAX_RANDOM_HEIGHT)) {
         theRoot.left = createRandomTree(height + 1, randomGenerator);
      }
      if ( (height < MIN_RANDOM_HEIGHT || randomGenerator.nextBoolean()) && (height < MAX_RANDOM_HEIGHT)) {
         theRoot.right = createRandomTree(height + 1, randomGenerator);
      }

      return theRoot;
   }

   /**
    * Helper method to create nodes for the Binary Tree.
    * @param preOrder the list of the preOrder traversal of a tree
    * @param inOrder  the list of the inOrder traversal of a tree
    * @return nodes of the given tree
    */
   public BinaryTreeNode<E> createFromTraversalsHelper(List<E> preOrder, List<E> inOrder)
   {
      // First Index Of preOrder is the Root
      int preOrderRoot = 0;
      // Create New Node
      BinaryTreeNode<E> node;
      if ( preOrder.size() > inOrder.size() )
      {
         node = null;
      }
      else if (preOrder.isEmpty() && inOrder.isEmpty() )
      {
         node = null;
      }
      else
      {
         // Create root node
         node = new BinaryTreeNode<E>(preOrder.get(preOrderRoot));
         // Gets Index of Root in inOrder Traversal
         int inOrderIndexRoot = inOrder.indexOf(node.data);
         node.left = createFromTraversalsHelper(preOrder.subList(preOrderRoot + 1, inOrderIndexRoot + 1),inOrder.subList(preOrderRoot,inOrderIndexRoot));
         node.right = createFromTraversalsHelper(preOrder.subList(inOrderIndexRoot + 1, preOrder.size()), inOrder.subList(inOrderIndexRoot + 1, inOrder.size()) );
      }
      return node;
   }

   /**
    * Method that creates a tree given the preOrder and inOrder traversal of a given tree
    * @param preOrder the list of preOrder traversal of a tree
    * @param inOrder  the list of inOrder traversal of a tree
    * @return a binary tree that represents the given preOrder and inOrder lists
    */
   public BinaryTree<E> createFromTraversals(List<E> preOrder, List<E> inOrder)
   {
     BinaryTree<E> binaryTree = new BinaryTree<>();
     binaryTree.root = createFromTraversalsHelper(preOrder,inOrder);
     return binaryTree;
   }

   /**
    * Creates Pre Order Traversal Of Tree.
    * @return the preorder traversal of the tree with given root
    */
   public String toStringPreOrderIterative() {
      Stack<BinaryTreeNode> preOrderElements = new Stack<>(); //Initial stack that will contain the root
      String preTraversed = ""; //Stores the pre order traversal of the elements

      if(root != null)
      {
         preOrderElements.push(root);
         while(!preOrderElements.empty())
         {
            BinaryTreeNode currentElement = preOrderElements.pop();
            preTraversed += currentElement.data +" "; //Top is popped and then immediately stored as a String
            //The right child of the Top node is then pushed to the stack
            if(currentElement.right != null)
            {
               preOrderElements.push(currentElement.right);
            }
            //The left child of the Top node is then pushed to the stack
            if (currentElement.left != null)
            {
               preOrderElements.push(currentElement.left);
            }
         }
      }
      return preTraversed.trim();
   }
   /**
    * Creates a post order traversal of the tree
    * @return a post order traversal tree with given root
    * Citation: https://www.geeksforgeeks.org/iterative-postorder-traversal/
    * Gideon found this to help his idea of using two stacks, thus he was able to create a while loop to store all the elements
    */
   public String toStringPostOrderIterative() {
      Stack<BinaryTreeNode> postOrderElements = new Stack<>(); //Initial stack that contains the root
      Stack<Integer> currentOrderedElements = new Stack<>(); //Stores the post-order traversal

      String postTraversed = "";//An empty string that will contain the traversed elements

      if(root!= null)
      {
         postOrderElements.push(root);

         while(!postOrderElements.empty()) //Iterates while the stack is not empty
         {
            BinaryTreeNode currentElement = postOrderElements.pop(); //An element is popped from the stack and pushed into the outgoing stack
            currentOrderedElements.push((Integer) currentElement.data);
            //The left child of the popped element are pushed into the stack
           if(currentElement.left != null )
           {
              postOrderElements.push(currentElement.left);
           }
            //The right child of the popped element is pushed into the stack
           if(currentElement.right != null)
           {
              postOrderElements.push(currentElement.right);
           }
         }
         //All elements of the outgoing stack are stored as string
         while(!currentOrderedElements.empty())
         {
            postTraversed += currentOrderedElements.pop() + " ";
         }
      }
      return postTraversed.trim();
   }

   /**
    * Creates level order traversal of tree
    * @return level order traversal of tree with a given root
    */
   public String toStringLevelOrder() {
      Queue<BinaryTreeNode> levelOrderElements = new LinkedList<>();
      Queue<Integer> currentLevel = new LinkedList<>();
      String levelTraversed = "";
      int counter = 1;
      if(root != null)
      {
         levelOrderElements.add(root);
         currentLevel.add(counter);
         while(!levelOrderElements.isEmpty())
         {
            BinaryTreeNode currentElement = levelOrderElements.remove();
            Integer currentLevelValue = currentLevel.remove();
            levelTraversed += currentElement.data + "(" + currentLevelValue + ")," + " " ; //Current head is stored into a String variable along with level count
            if(currentElement.left != null && currentElement.right != null) //Checks the children of current element which indicates the # to increment level
            {
               currentLevel.add(currentLevelValue + 1);
               currentLevel.add(currentLevelValue + 1);
            }
            else //If there is only one child of the parent node then the level value is incremented once
            {
               currentLevel.add(currentLevelValue+1);
            }

            if(currentElement.left!= null)//The left child of the current element are pushed into the stack
            {
               levelOrderElements.add(currentElement.left);
            }

            if(currentElement.right!= null)//The left child of the current element are pushed into the stack
            {
               levelOrderElements.add(currentElement.right);
            }
         }
      }
      levelTraversed = levelTraversed.substring(0,levelTraversed.length()-2); //Removes final comma at the end of the string
      return levelTraversed;
   }

   public String toString() {
      return toStringInOrder();
   }

   // post: prints the tree contents using a preorder traversal
   public String toStringPreOrder() {
      return toStringPreOrder(root).stripTrailing();
   }

   // post: prints in preorder the tree with given root
   private String toStringPreOrder(BinaryTreeNode root) {
      StringBuffer preOrderTraversal = new StringBuffer();
      if (root != null) {
         preOrderTraversal.append(root.data).append(" ");
         preOrderTraversal.append(toStringPreOrder(root.left));
         preOrderTraversal.append(toStringPreOrder(root.right));
      }
      return preOrderTraversal.toString();
   }

   // post: prints the tree contents using an inorder traversal
   public String toStringInOrder() {
      return toStringInOrder(root).stripTrailing();
   }

   // post: inorder traversal of the tree with given root
   private String toStringInOrder(BinaryTreeNode root) {
      StringBuffer inOrderTraversal = new StringBuffer();
      if (root != null) {
         inOrderTraversal.append(toStringInOrder(root.left));
         inOrderTraversal.append(root.data).append(" ");
         inOrderTraversal.append(toStringInOrder(root.right));
      }
      return inOrderTraversal.toString();
   }

   // post:  traversal of the tree contents using a postorder traversal
   public String toStringPostOrder() {
      return toStringPostOrder(root).stripTrailing();
   }

   // post: postorder traversal the tree with given root
   private String toStringPostOrder(BinaryTreeNode root) {
      StringBuffer postOrderTraversal = new StringBuffer();
      if (root != null) {
         postOrderTraversal.append(toStringPostOrder(root.left));
         postOrderTraversal.append(toStringPostOrder(root.right));
         postOrderTraversal.append(root.data).append(" ");
      }
      return postOrderTraversal.toString();
   }
}
