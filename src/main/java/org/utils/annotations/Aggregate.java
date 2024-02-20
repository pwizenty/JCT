package org.utils.annotations;

public class Aggregate implements Annotation {
 @Override
 public String toString() {
  return "Aggregate{}";
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
