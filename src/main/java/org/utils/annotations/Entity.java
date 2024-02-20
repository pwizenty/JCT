package org.utils.annotations;

public class Entity implements Annotation {
  String context;

  @Override
  public String toString() {
    return "Entity{}";
  }

  @Override
  public boolean equals(Object obj) {
    return toString().equals(obj.toString());
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }
}
