package com.rcforte;

@FunctionalInterface
public interface Visitor<T> {

  void visit(Node<T> node);
}
