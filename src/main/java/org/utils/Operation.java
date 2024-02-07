package org.utils;

import org.utils.annotations.Annotation;

import java.util.Set;

public class Operation extends SourceElement< Operation > {

  String name;
  Set< Annotation > annotations;
  String inputType;
  String outputType;

  public Operation( String name, Set< Annotation > annotations, String inputType ) {
    this.name = name;
    this.annotations = annotations;
    this.inputType = inputType;
    this.outputType = null;
  }

  public Operation( String name, Set< Annotation > annotations, String inputType, String outputType ) {
    this.name = name;
    this.annotations = annotations;
    this.inputType = inputType;
    this.outputType = outputType;
  }

  @Override
  public String toString() {
    return "Operation{" +
      "\nname='" + name + '\'' +
      "\n, annotations=" + annotations +
      "\n, inputType='" + inputType + '\'' +
      "\n, outputType='" + outputType + '\'' +
      "\n}";
  }
}
