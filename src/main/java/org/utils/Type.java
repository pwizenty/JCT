package org.utils;

import org.utils.annotations.Annotation;

import java.util.Set;

public class Type {

  String name;
  Set< Annotation > annotations;
  Set< TypeWithCardinality > subnodes;
  boolean isPrimitive;
  String value;

  public Type( String name, Set< Annotation > annotations, Set< TypeWithCardinality > subnodes, boolean isPrimitive, String value ) {
    this.name = name;
    this.annotations = annotations;
    this.subnodes = subnodes;
    this.isPrimitive = isPrimitive;
    this.value = value;
  }

  @Override
  public String toString() {
    return "Type{" +
      "\nname='" + name + '\'' +
      "\n, annotations=" + annotations +
      "\n, subnodes=" + subnodes +
      "\n, isPrimitive=" + isPrimitive +
      "\n, value='" + value + '\'' +
      "\n}";
  }
}