package cecs274;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Unit test for simple App.
 * @author Gideon Essel gideon.essel@student.csulb.edu
 * @author Christian Lam christian.lam@csulb.edu
 */
public class BinaryTreeTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testCreateTree()
    {
        BinaryTree<Integer> tree = new BinaryTree<>().createRandomTree();
        assertEquals("463 405 92 290 132 170 458 399", tree.toString());
    }

    @Test
    public void preOrder()
    {
        BinaryTree<Integer> tree = new BinaryTree<>().createRandomTree();
        assertEquals(tree.toStringPreOrderIterative(), tree.toStringPreOrder());
    }

    @Test
    public void postOrder()
    {
        BinaryTree<Integer> tree = new BinaryTree<>().createRandomTree();
        assertEquals(tree.toStringPostOrderIterative(), tree.toStringPostOrder());
    }

    @Test
    public void levelOrder()
    {
        BinaryTree<Integer> tree = new BinaryTree<>().createRandomTree();
        assertEquals("290(1), 463(2), 132(2), 405(3), 458(3), 92(4), 170(4), 399(4)", tree.toStringLevelOrder());

    }

    @Test
    public void createBinaryTree()
    {
        BinaryTree<Integer> tree = new BinaryTree<>().createRandomTree();
        BinaryTree method = new BinaryTree<>();
        List preOrder = Arrays.asList(tree.toStringPreOrder().split(" "));
        List inOrder = Arrays.asList(tree.toStringInOrder().split(" "));
        BinaryTree<Integer> createTree = method.createFromTraversals(preOrder,inOrder);
        assertEquals(tree.toString(),createTree.toString());
    }
}
