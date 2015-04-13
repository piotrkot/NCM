/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.piokot.ncm.api.DataOutStream;
import lombok.SneakyThrows;

/**
 * Stream calculating maximum number from numbers written to it.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class MaxNumberStream
    implements DataOutStream<Integer> {
    /**
     * Maximum value among values written to the stream.
     */
    @SuppressWarnings("PMD.BeanMembersShouldSerialize")
    private int value;

    /**
     * Class constructor.
     */
    public MaxNumberStream() {
        this.value = 0;
    }

    @Override
    @SneakyThrows
    public void write(final Iterable<Integer> data) {
        for (final Integer val : data) {
            if (val > this.value) {
                this.value = val;
            }
        }
    }

    /**
     * Maximum value among values written to it.
     *
     * @return Positive integer.
     */
    public int maximum() {
        return this.value;
    }
}
