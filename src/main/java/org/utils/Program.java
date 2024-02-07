package org.utils;

import java.nio.file.Path;
import java.util.Set;

public class Program {

  Path sourcefile;
  Set< Type > types;
  Set< Interface > interfaces;

  public Program( Path sourcefile, Set< Type > types, Set< Interface > interfaces ) {
    this.sourcefile = sourcefile;
    this.types = types;
    this.interfaces = interfaces;
  }

  public Path getSourcefile() {
    return sourcefile;
  }

  public void setSourcefile( Path sourcefile ) {
    this.sourcefile = sourcefile;
  }

  public Set< Type > getTypes() {
    return types;
  }

  public void setTypes( Set< Type > types ) {
    this.types = types;
  }

  public Set< Interface > getInterfaces() {
    return interfaces;
  }

  public void setInterfaces( Set< Interface > interfaces ) {
    this.interfaces = interfaces;
  }

  @Override
  public String toString() {
    return "Program{" +
        "\nsourcefile=" + sourcefile +
        "\n, types=" + types +
        "\n, interfaces=" + interfaces +
        "\n}";
  }
}
