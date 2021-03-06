// Copyright 2007 Waterken Inc. under the terms of the MIT X license
// found at http://www.opensource.org/licenses/mit-license.html
package org.waterken.bounce;

import org.joe_e.array.ConstArray;
import org.ref_send.promise.Promise;

/**
 * An object to bounce requests off of.
 */
public interface
Wall {
    
    /**
     * Creates a record of all types.
     */
    Promise<ConstArray<AllTypes>> getAll();

    /**
     * Returns the given argument.
     * @param a value to return
     * @return <code>a</code>
     */
    <A> Promise<A> bounce(A a);
    
    /**
     * Sends a variable argument list.
     * @param num   each vararg
     */
    Promise<Integer> sum(int... num);
}
