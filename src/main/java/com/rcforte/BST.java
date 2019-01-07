package com.rcforte;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BST<T extends Comparable<? super T>> {

  private Node<T> root;

  @SafeVarargs
  public final BST<T> insert(T... values) {
    for(T value : values)
      this.root = insert(this.root, value);
    return this;
  }

  public boolean isEmpty() {
    return root == null;
  }

  private Node<T> insert(Node<T> node, T value) {
    if(node == null) {
      node = new Node<>(value);
    } else {
      int cmp = node.value.compareTo(value);
      if(cmp == 0) {
        node.refcount++;
      } else if(cmp < 0) {
        node.right = insert(node.right, value);
        node.right.parent = node;
      } else {
        node.left = insert(node.left, value);
        node.left.parent = node;
      }
    }

    return node;
  }

  public T find(T value) {
    Node<T> n = find(this.root, value);
    if(n != null)
      return n.value;
    return null;
  }

  public Node<T> find(Node<T> node, T value) {
    if(node == null)
      return null;

    int cmp = node.value.compareTo(value);
    if(cmp == 0)
      return node;
    else if(cmp < 0)
      return find(node.right, value);
    else
      return find(node.left, value);
  }

  public List<T> preorder() {
    List<T> ret = new ArrayList<>();
    preorder(this.root, node -> ret.add(node.value));
    return ret;
  }

  public List<T> inorder() {
    List<T> ret = new ArrayList<>();
    inorder(this.root, node -> ret.add(node.value));
    return ret;
  }

  public List<T> postorder() {
    List<T> ret = new ArrayList<>();
    postorder(this.root, node -> ret.add(node.value));
    return ret;
  }

  public void remove(T value) {
    Node<T> node = find(this.root, value);
    if(node != null)
      remove(node);
  }

  private void remove(Node<T> node) {
    // Removes a leaf node
    if(node.left == null && node.right == null) {
      if(node.parent.left == node) node.parent.left = null;
      if(node.parent.right == node) node.parent.right = null;
      node.remove();
    }

    // Removes a node with one child
    if((node.left != null && node.right == null) || (node.left == null && node.right != null)) {
      Node<T> nn = node.left != null ? node.left : node.right;
      if(node.parent.left == node) node.parent.left = nn;
      if(node.parent.right == node) node.parent.right = nn;
      node.remove();
    }

    // Removes a node with two children
    if(node.left != null && node.right != null) {
      // Finds the successor the node being removed
      Node<T> suc = node.right;
      while(suc.left != null)
        suc = suc.left;

      // Removes the node successor either by case 1 or 2
      node.value = suc.value;
      remove(suc);
    }
  }

  public void preorder(Node<T> node, Visitor<T> visitor) {
    Stack<Node<T>> stack = new Stack<>();
    stack.push(node);

    while(!stack.isEmpty()) {
      Node<T> n = stack.peek();
      if(n != null) {
        if(!n.visited) {
          n.visited = true;
          visitor.visit(n);
        } else if(!n.visitedLeft) {
          n.visitedLeft = true;
          stack.push(n.left);
        } else if(!n.visitedRight) {
          n.visitedRight = true;
          stack.push(n.right);
        } else {
          Node<T> nn = stack.pop();
          if(nn != null)
            nn.resetVisits();
        }
      } else {
        Node<T> nn = stack.pop();
        if(nn != null)
          nn.resetVisits();
      }
    }
  }

  public void inorder(Node<T> node, Visitor<T> visitor) {
    Stack<Node<T>> stack = new Stack<>();
    stack.push(node);

    while(!stack.isEmpty()) {
      Node<T> n = stack.peek();
      if(n != null) {
        if(!n.visitedLeft) {
          n.visitedLeft = true;
          stack.push(n.left);
        } else if(!n.visited) {
          n.visited = true;
          visitor.visit(n);
        } else if(!n.visitedRight) {
          n.visitedRight = true;
          stack.push(n.right);
        } else {
          Node<T> nn = stack.pop();
          if(nn != null)
            nn.resetVisits();
        }
      } else {
        Node<T> nn = stack.pop();
        if(nn != null)
          nn.resetVisits();
      }
    }
  }

  public void postorder(Node<T> node, Visitor<T> visitor) {
    Stack<Node<T>> stack = new Stack<>();
    stack.push(node);

    while(!stack.isEmpty()) {
      Node<T> n = stack.peek();
      if(n != null) {
        if(!n.visitedLeft) {
          n.visitedLeft = true;
          stack.push(n.left);
        } else if(!n.visitedRight) {
          n.visitedRight = true;
          stack.push(n.right);
        } else if(!n.visited) {
          n.visited = true;
          visitor.visit(n);
        } else {
          Node<T> nn = stack.pop();
          if(nn != null)
            nn.resetVisits();
        }
      } else {
        Node<T> nn = stack.pop();
        if(nn != null)
          nn.resetVisits();
      }
    }
  }

  public int refcount(T value) {
    Node<T> n = find(this.root, value);
    if(n != null)
      return n.refcount;
    return -1;
  }
}
