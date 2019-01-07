package com.rcforte;

public class Node<T> {

  public T value;
  public Node<T> parent, left, right;
  public boolean visited, visitedLeft, visitedRight;
  public int refcount;

  public Node(T value) {
    this.value = value;
  }

  public void resetVisits() {
    visited = visitedLeft = visitedRight = false;
  }

  public void remove() {
    this.value = null;
    this.parent = this.left = this.right = null;
    this.refcount = 0;
  }
}
