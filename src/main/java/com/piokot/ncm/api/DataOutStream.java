/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm.api;

/**
 * Parametrized Data Output Stream.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public interface DataOutStream<T> {
    /**
     * Write parametrized data to the output stream.
     *
     * @param data Parametrized data.
     */
    void write(Iterable<T> data);
}
