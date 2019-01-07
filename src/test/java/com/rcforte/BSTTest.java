package com.rcforte;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class BSTTest {

  @Test
  public void canDoInorderTraversal() {
    BST<Integer> bst = new BST();
    bst.insert(4, 2, 1, 3, 6, 5, 7);
    assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7), bst.inorder());
  }

  @Test
  public void removesLeafNode() {
    BST<Integer> bst;

    bst = new BST<>();
    bst.insert(2, 3, 1);
    bst.remove(1);
    assertEquals(Arrays.asList(2, 3), bst.inorder());

    bst = new BST<>();
    bst.insert(2, 3, 1);
    bst.remove(3);
    assertEquals(Arrays.asList(1, 2), bst.inorder());
  }

  @Test
  public void removesNodeWithOneChild() {
    BST<Integer> bst;

    bst = new BST<>();
    bst.insert(3, 2, 1);
    bst.remove(2);
    assertEquals(Arrays.asList(1, 3), bst.inorder());

    bst = new BST<>();
    bst.insert(3, 5, 6);
    bst.remove(6);
    assertEquals(Arrays.asList(3, 5), bst.inorder());
  }

  @Test
  public void removesNodeWithTwoChildren() {
    BST<Integer> bst;
    bst = new BST<>();
    bst.insert(4, 2, 1, 3);
    bst.remove(2);
    assertEquals(Arrays.asList(1, 3, 4), bst.inorder());
  }

  @Test
  public void badAssTest() {
    // Adds random numbers to the tree
    BST<Integer> bst = new BST<>();
    Random r = new Random();
    List<Integer> lst = new LinkedList<>();
    for(int i = 0; i < 5000; i++) {
      int val = r.nextInt();
      bst.insert(val);

      // also enters random numbers to a helper list
      lst.add(val);
    }

    // Removes the root from the list
    Integer root = bst.preorder().get(0);
    lst.remove(root);

    // Removes the root from the tree
    bst.remove(root);

    lst.sort(Comparator.<Integer>naturalOrder());
    assertEquals(lst, bst.inorder());
  }
}
