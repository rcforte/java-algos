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
}
