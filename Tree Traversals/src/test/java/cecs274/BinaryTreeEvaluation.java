package cecs274;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Unit test for simple App.
 */
public class BinaryTreeEvaluation {
   List<BinaryTree<Integer>> trees;

   @BeforeEach
   public void initialize() {
      trees = new ArrayList<>();
      trees.add(new BinaryTree<>().createRandomTree(103));
      trees.add(new BinaryTree<>().createRandomTree(290));
      trees.add(new BinaryTree<>().createRandomTree(201));
   }

   @Test
   public void testTraversalEmptyTree() {
      BinaryTree<Integer> empty = new BinaryTree<>();
      Assertions.assertAll(
            () -> assertEquals("", empty.toStringPreOrderIterative()),
            () -> assertEquals("", empty.toStringPostOrderIterative()),
            () -> assertEquals("", empty.toStringLevelOrder())
      );
   }

   @Test
   public void testPostOrderRandomTree() {
      List<String> expectedPostOrderTraversals = new ArrayList<>();
      List<String> actualPostOrderTraversals = new ArrayList<>();
      for (BinaryTree<Integer> tree : trees) {
         expectedPostOrderTraversals.add(tree.toStringPostOrder());         // expect postorder given by recursive method
         actualPostOrderTraversals.add(tree.toStringPostOrderIterative());  // actual is the one returned by iterative method
      }
      Assertions.assertAll(
            () -> assertEquals(expectedPostOrderTraversals.get(0), actualPostOrderTraversals.get(0)),  // expect: 92 405 463 170 399 458 132 290
            () -> assertEquals(expectedPostOrderTraversals.get(1), actualPostOrderTraversals.get(1)),  // expect: 161 119 114 424 441 245 102 59 127 89 347 248 466 486
            () -> assertEquals(expectedPostOrderTraversals.get(2), actualPostOrderTraversals.get(2))   // expect: 200 375 217 182 71 44 455
      );
   }

   @Test
   public void testPreOrderRandomTree() {
      List<String> expectedPreOrderTraversals = new ArrayList<>();
      List<String> actualPreOrderTraversals = new ArrayList<>();
      for (BinaryTree<Integer> tree : trees) {
         expectedPreOrderTraversals.add(tree.toStringPreOrder());           // expect preorder given by recursive method
         actualPreOrderTraversals.add(tree.toStringPreOrderIterative());    // actual is the one returned by iterative method
      }
      Assertions.assertAll(
            () -> assertEquals(expectedPreOrderTraversals.get(0), actualPreOrderTraversals.get(0)), // expect: 290 463 405 92 132 458 170 399
            () -> assertEquals(expectedPreOrderTraversals.get(1), actualPreOrderTraversals.get(1)), // expect: 486 245 114 161 119 441 424 466 127 102 59 248 89 347
            () -> assertEquals(expectedPreOrderTraversals.get(2), actualPreOrderTraversals.get(2))  // expect: 455 182 200 217 375 44 71
      );
   }


   @Test
   public void testLevelOrderRandomTree() {
      List<String> expectedLevelOrderTraversals = new ArrayList<>();
      List<String> actualLevelOrderTraversals = new ArrayList<>();
      for (BinaryTree<Integer> tree : trees) {
         actualLevelOrderTraversals.add(tree.toStringLevelOrder());
      }
      expectedLevelOrderTraversals.add("290 (1), 463 (2), 132 (2), 405 (3), 458 (3), 92 (4), 170 (4), 399 (4)");
      expectedLevelOrderTraversals.add("486 (1), 245 (2), 466 (2), 114 (3), 441 (3), 127 (3), 248 (3), 161 (4), 119 (4), 424 (4), 102 (4), 59 (4), 89 (4), 347 (4)");
      expectedLevelOrderTraversals.add("455 (1), 182 (2), 44 (2), 200 (3), 217 (3), 71 (3), 375 (4)");
      Assertions.assertAll(
            () -> assertEquals(expectedLevelOrderTraversals.get(0), actualLevelOrderTraversals.get(0)),
            () -> assertEquals(expectedLevelOrderTraversals.get(1), actualLevelOrderTraversals.get(1)),
            () -> assertEquals(expectedLevelOrderTraversals.get(2), actualLevelOrderTraversals.get(2))
      );
   }


   @Test
   public void testCreateFromEmptyTraversal() {
      BinaryTree<String> emptyTreeExpected = new BinaryTree<>();
      List<String> emptyList = new ArrayList<>();
      BinaryTree<String> actualTree = new BinaryTree<String>().createFromTraversals(emptyList, emptyList);
      assertEquals(emptyTreeExpected, actualTree);
   }

   @Test
   public void testCreateFromTraversals() {
      List<List<Integer>> preOrderTraversals = new ArrayList<>();
      List<List<Integer>> inOrderTraversals = new ArrayList<>();

      List<BinaryTree<Integer>> actualTrees = new ArrayList<>();

      for (int i = 0; i < trees.size(); i++) {
         BinaryTree<Integer> expectedTree = trees.get(i);
         preOrderTraversals.add(Stream.of(expectedTree.toStringPreOrder().split(" ")).map(Integer::parseInt).collect(Collectors.toList()));
         inOrderTraversals.add(Stream.of(expectedTree.toStringInOrder().split(" ")).map(Integer::parseInt).collect(Collectors.toList()));
         actualTrees.add(new BinaryTree<Integer>().createFromTraversals(preOrderTraversals.get(i), inOrderTraversals.get(i)));
      }

      Assertions.assertAll(
            () -> assertEquals(trees.get(0), actualTrees.get(0)),
            () -> assertEquals(trees.get(1), actualTrees.get(1)),
            () -> assertEquals(trees.get(2), actualTrees.get(2))
      );
   }
}
