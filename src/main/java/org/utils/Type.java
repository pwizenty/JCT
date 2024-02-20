package org.utils;

import org.utils.annotations.Annotation;

import java.util.Objects;
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

  public TypeWithCardinality toTypeWithCardinality( int min, int max ){
    return new TypeWithCardinality( this.name, this.annotations, this.subnodes, this.isPrimitive, this.value, min, max );
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Type type = (Type) o;

    if (isPrimitive != type.isPrimitive) return false;
    if (!Objects.equals(name, type.name)) return false;
    if (!Objects.equals(annotations, type.annotations)) return false;
    if (!Objects.equals(subnodes, type.subnodes)) return false;
    return Objects.equals(value, type.value);
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (annotations != null ? annotations.hashCode() : 0);
    result = 31 * result + (subnodes != null ? subnodes.hashCode() : 0);
    result = 31 * result + (isPrimitive ? 1 : 0);
    result = 31 * result + (value != null ? value.hashCode() : 0);
    return result;
  }
}
