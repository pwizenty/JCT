package org.utils.annotations;

public class Identifier implements Annotation {

 @Override
 public String toString() {
  return "Identifier{}";
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
