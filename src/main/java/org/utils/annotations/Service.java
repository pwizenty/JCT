package org.utils.annotations;

public class Service implements Annotation {
    @Override
    public String toString() {
        return "Service{}";
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
