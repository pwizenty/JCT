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

  @Override
  public String toString() {
    return "Program{" +
      "\nsourcefile=" + sourcefile +
      "\n, types=" + types +
      "\n, interfaces=" + interfaces +
      "\n}";
  }
}
