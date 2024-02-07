package org.utils;

import org.utils.annotations.Annotation;

import java.util.Set;

public class Interface extends SourceElement< Interface > {

  String name;
  Set< Annotation > annotations;
  Set< Operation > operations;

  public Interface( String name, Set< Annotation > annotations, Set< Operation > operations ) {
    this.name = name;
    this.annotations = annotations;
    this.operations = operations;
  }

  @Override
  public String toString() {
    return "Interface{" +
      "\nname='" + name + '\'' +
      "\n, annotations=" + annotations +
      "\n, operations=" + operations +
      "\n}";
  }
}
