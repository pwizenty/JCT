package org.utils.annotations;

public class Repository implements Annotation {
    @Override
    public String toString() {
        return "Repository{}";
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
