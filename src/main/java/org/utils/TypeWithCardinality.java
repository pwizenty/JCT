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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TypeWithCardinality that = (TypeWithCardinality) o;

        if (min != that.min) return false;
        return max == that.max;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + min;
        result = 31 * result + max;
        return result;
    }
}
