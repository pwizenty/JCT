package org.utils.annotations;

public class BoundedContext implements Annotation {

  String context;

  public BoundedContext( String context ) {
    this.context = context;
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
