package org.utils.annotations;

public class ValueObject implements Annotation {
    @Override
    public String toString() {
        return "Value_Object{}";
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
