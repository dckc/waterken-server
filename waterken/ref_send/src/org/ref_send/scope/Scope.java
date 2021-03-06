// Copyright 2008 Waterken Inc. under the terms of the MIT X license found at
// http://www.opensource.org/licenses/mit-license.html
package org.ref_send.scope;

import java.io.Serializable;

import org.joe_e.Selfless;
import org.joe_e.array.ConstArray;

/**
 * A <code>{ name : value }</code> record.
 * @param <T> nominal type
 */
public class
Scope<T> implements Selfless, Serializable {
    static private final long serialVersionUID = 1L;

    /**
     * structural type
     */
    public final Layout<T> meta;

    /**
     * each corresponding value
     */
    public final ConstArray<?> values;

    Scope(final Layout<T> meta, final ConstArray<?> values) {
        if (meta.names.length()!=values.length()){throw new RuntimeException();}

        this.meta = meta;
        this.values = values;
    }
    
    /**
     * Constructs an instance.
     * @param meta      {@link #meta}
     * @param values    {@link #values}
     */
    static public <T> Scope<T>
    make(final Layout<T> meta, final ConstArray<?> values) {
        return new Scope<T>(meta, values);
    }

    // java.lang.Object interface

    /**
     * Is the given object equal to this one?
     * @param o compared to object
     * @return {@code true} if equal, else {@code false}
     */
    public boolean
    equals(final Object o) {
        return o instanceof Scope &&
               values.equals(((Scope<?>)o).values) &&
               meta.names.equals(((Scope<?>)o).meta.names);
    }

    /**
     * Calculates the hash code.
     */
    public int
    hashCode() { return 0x4EF5C09E + meta.hashCode() + values.hashCode(); }

    // org.ref_send.scope.Scope interface

    /**
     * Gets the corresponding value.
     * @param name  name to lookup
     * @return corresponding value, or {@code null} if none
     */
    public <R> R
    get(final String name) {
        final int i = meta.find(name);
        if (-1 == i) { return null; }
        final @SuppressWarnings("unchecked") R r = (R)values.get(i);
        return r;
    }
}
