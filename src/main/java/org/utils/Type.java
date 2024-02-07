package org.utils;

import org.utils.annotations.Annotation;

import java.util.Set;

public class Type extends SourceElement< Type > {

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Annotation> getAnnotations() {
    return annotations;
  }

  public void setAnnotations(Set<Annotation> annotations) {
    this.annotations = annotations;
  }

  public Set<TypeWithCardinality> getSubnodes() {
    return subnodes;
  }

  public void setSubnodes(Set<TypeWithCardinality> subnodes) {
    this.subnodes = subnodes;
  }

  public boolean isPrimitive() {
    return isPrimitive;
  }

  public void setPrimitive(boolean primitive) {
    isPrimitive = primitive;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
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
