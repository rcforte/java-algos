package com.rcforte;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class BSTTest {

  private final BST<Integer> bst = new BST<>();

  @Test
  public void isCreatedEmpty() {
    assertTrue(bst.isEmpty());
  }

  @Test
  public void isEmptyReturnsTrueWhenTreePopulated() {
    bst.insert(4);
    assertFalse(bst.isEmpty());
  }

  @Test
  public void traversesInorder() {
    bst.insert(4, 2, 1, 3, 6, 5, 7);
    assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7), bst.inorder());
  }

  @Test
  public void traversesPreorder() {
    bst.insert(4, 2, 1, 3, 6, 5, 7);
    assertEquals(Arrays.asList(4, 2, 1, 3, 6, 5, 7), bst.preorder());
  }

  @Test
  public void traversesPostorder() {
    BST<String> bst = new BST<>();
    bst.insert("F", "B", "A", "D", "C", "E", "G", "I", "H");
    assertEquals(Arrays.asList("A", "C", "E", "D", "B", "H", "I", "G", "F"), bst.postorder());
  }

  @Test
  public void findsExistingElement() {
    bst.insert(4, 2, 1, 3, 6, 5, 7);
    assertEquals(new Integer(1), bst.find(1));
  }

  @Test
  public void findReturnsNullForUnexistingElement() {
    bst.insert(4, 2, 1, 3, 6, 5, 7);
    assertNull(bst.find(8));
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
  public void removesRootFromBigTree() {
    // Adds random numbers to the tree
    BST<Integer> bst = new BST<>();
    Random r = new Random();
    List<Integer> lst = new LinkedList<>();
    for(int i = 0; i < 5000; i++) {
      int val = r.nextInt();
      bst.insert(val);
      lst.add(val); // also enters random number to a list for testing
    }

    // Removes the root from the list
    Integer root = bst.preorder().get(0);
    lst.remove(root);

    // Removes the root from the tree
    bst.remove(root);

    lst.sort(Comparator.<Integer>naturalOrder());
    assertEquals(lst, bst.inorder());
  }

  @Test
  public void refcountKeepsTrackOfNumberOfTimesElementInserted() {
    bst.insert(4,4);
    assertEquals(2, bst.refcount(4));
  }

  @Test
  public void refcountIsMinusOneIfElementNotPresent() {
    assertEquals(-1, bst.refcount(4));
  }

  @Test
  public void refcountIsOneIfElementJustInserted() {
    bst.insert(4);
    assertEquals(1, bst.refcount(4));
  }
}
