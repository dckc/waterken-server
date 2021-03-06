// Copyright 2011 Waterken Inc. under the terms of the MIT X license found at
// http://www.opensource.org/licenses/mit-license.html
package org.waterken.syntax;

import org.joe_e.Powerless;
import org.ref_send.deserializer;
import org.ref_send.name;

/**
 * Signals parsed data did not contain an expected token at a required position.
 */
public class WrongToken extends RuntimeException implements Powerless {
    static private final long serialVersionUID = 1L;

    /**
     * expected token
     */
    public final String expected;
    
    /**
     * Constructs an instance.
     * @param expected  {@link #expected}
     */
    public @deserializer
    WrongToken(@name("expected") final String expected) {
        super(expected);
        this.expected = expected;
    }
    
    /**
     * Requires a token to have an expected value.
     * @param expected  expected token
     * @param actual    actual token
     * @throws WrongToken   {@code actual} is not {@code expected}
     */
    static public void
    require(final String expected, final String actual) throws WrongToken {
        if (!expected.equals(actual)) { throw new WrongToken(expected); }
    }
}
