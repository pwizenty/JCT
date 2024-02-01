package org.utils;

import org.utils.annotations.Annotation;

import java.util.Set;

public class Operation {

  String name;
  Set< Annotation > annotations;
  Type inputType;
  Type outputType;

  public Operation( String name, Set< Annotation > annotations, Type inputType, Type outputType ) {
    this.name = name;
    this.annotations = annotations;
    this.inputType = inputType;
    this.outputType = outputType;
  }

}
