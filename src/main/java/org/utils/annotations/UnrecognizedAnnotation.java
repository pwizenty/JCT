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

 @Override
 public boolean equals(Object obj) {
  return toString().equals(obj.toString());
 }

 @Override
 public int hashCode() {
  return toString().hashCode();
 }
}
