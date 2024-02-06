package org.utils;

import org.utils.annotations.Annotation;

import java.util.Set;

public class TypeWithCardinality extends Type {

   // we encode * as Integer.MAX_VALUE
   int min;
   int max;

  public TypeWithCardinality( String name, Set< Annotation > annotations, Set< TypeWithCardinality > subnodes, boolean isPrimitive, String value, int min, int max ) {
    super( name, annotations, subnodes, isPrimitive, value );
    this.min = min;
    this.max = max;
  }

 @Override
 public String toString() {
  return "TypeWithCardinality{" +
    "\nmin=" + min +
    "\n, max=" + max +
    "\n, name='" + name + '\'' +
    "\n, annotations=" + annotations +
    "\n, subnodes=" + subnodes +
    "\n, isPrimitive=" + isPrimitive +
    "\n, value='" + value + '\'' +
    "\n}";
 }
}
