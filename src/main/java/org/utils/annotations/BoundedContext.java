package org.utils.annotations;

public class BoundedContext implements Annotation {

  String context;

  public BoundedContext( String context ) {
    this.context = context;
  }

}
