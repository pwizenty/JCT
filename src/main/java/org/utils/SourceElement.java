package org.utils;

public class SourceElement< T > {
  int line;

  public T setLine( int line ) {
    this.line = line;
    return ( T ) this;
  }

  public int getLine() {
    return line;
  }
}
