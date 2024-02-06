package org.utils.annotations;

public class UnrecognizedAnnotation implements Annotation {
 String annotation;
 public UnrecognizedAnnotation( String annotation ) {
  this.annotation = annotation;
 }

 @Override
 public String toString() {
  return "UnrecognizedAnnotation{" +
    "annotation='" + annotation + '\'' +
    '}';
 }
}
