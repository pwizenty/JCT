package org.utils;

import org.utils.annotations.Annotation;

import java.nio.file.Path;
import java.util.Set;

public class Program {

  Path sourcefile;
  Set< Type > types;
  Set< Interface > interfaces;
  Set< Annotation > annotations;

  public Program( Path sourcefile, Set< Type > types, Set< Annotation > annotations, Set< Interface > interfaces ) {
    this.sourcefile = sourcefile;
    this.types = types;
    this.annotations = annotations;
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

  public Set< Annotation > getAnnotations() {
    return annotations;
  }

  @Override
  public String toString() {
    return "Program{" +
        "\nsourcefile=" + sourcefile +
        "\n, annotations=" + annotations +
        "\n, types=" + types +
        "\n, interfaces=" + interfaces +
        "\n}";
  }
}
