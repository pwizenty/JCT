package org.utils.annotations;

public class Part implements Annotation {

 @Override
 public String toString() {
  return "Part{}";
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
