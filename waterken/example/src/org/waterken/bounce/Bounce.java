// Copyright 2007 Waterken Inc. under the terms of the MIT X license
// found at http://www.opensource.org/licenses/mit-license.html
package org.waterken.bounce;

import static org.ref_send.promise.Eventual.ref;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.joe_e.Struct;
import org.joe_e.array.BooleanArray;
import org.joe_e.array.ByteArray;
import org.joe_e.array.CharArray;
import org.joe_e.array.ConstArray;
import org.joe_e.array.DoubleArray;
import org.joe_e.array.FloatArray;
import org.joe_e.array.ImmutableArray;
import org.joe_e.array.IntArray;
import org.joe_e.array.LongArray;
import org.joe_e.array.PowerlessArray;
import org.joe_e.array.ShortArray;
import org.ref_send.promise.Deferred;
import org.ref_send.promise.Eventual;
import org.ref_send.promise.Promise;
import org.ref_send.promise.Receiver;
import org.ref_send.promise.Vat;

/**
 * A {@link Wall} implementation.
 */
public final class
Bounce {
    private Bounce() {}

    /**
     * Constructs an instance.
     * @param _ eventual operator
     */
    static public Wall
    make(final Eventual _) {
        final Deferred<Boolean> d = _.defer();
        final Receiver<Boolean> normal = d.resolver;
        final Promise<Boolean> p = ref(false);
        class WallX extends Struct implements Wall, Serializable {
            static private final long serialVersionUID = 1L;

            public Promise<ConstArray<AllTypes>>
            getAll() {
                return ref(ConstArray.array(new AllTypes(
                    BooleanArray.array(true, false),
                    CharArray.array('a', '\"', '\\', '<', '>', '/', '\b',
                                    '\f', '\n', '\r', '\t', '\u0085'),
                    FloatArray.array(0.0F,
                          Float.MAX_VALUE, Float.MIN_VALUE,
                          -Float.MAX_VALUE, -Float.MIN_VALUE,
                          Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY,
                          Float.NaN),
                    DoubleArray.array(0.0,
                          Double.MAX_VALUE, Double.MIN_VALUE,
                          -Double.MAX_VALUE, -Double.MIN_VALUE,
                          Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY,
                          Double.NaN),
                    ByteArray.array((byte)0, Byte.MAX_VALUE, Byte.MIN_VALUE),
                    ShortArray.array((short)0, Short.MAX_VALUE,Short.MIN_VALUE),
                    IntArray.array(0, Integer.MAX_VALUE, Integer.MIN_VALUE),
                    LongArray.array(0L, 1L << 53, -(1L << 53)),
                    "a \" \\ / </ < > \b \f \n \r \t \u0085",
                    ConstArray.array(normal, null),
                    ConstArray.array(d.promise, p),
                    ConstArray.array(new Vat<Deferred<?>>(d, normal)),
                    ConstArray.array(
                        ImmutableArray.array(PowerlessArray.array(true)),
                        10,
                        BigInteger.TEN,
                        new BigDecimal("3.14")
                    ))));
            }

            public <A> Promise<A>
            bounce(final A a) { return ref(a); }

            public Promise<Integer>
            sum(int... num) {
                int total = 0;
                for (int a : num) {
                    total += a;
                }
                return ref(total);
            }
        }
        return new WallX();
    }
}
