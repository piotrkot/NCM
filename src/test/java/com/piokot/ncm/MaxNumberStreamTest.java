/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link MaxNumberStream} class.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class MaxNumberStreamTest {
    /**
     * Can return maximum value.
     *
     * @throws Exception If fails
     */
    @Test
    public void returnMax() throws Exception {
        final MaxNumberStream stream = new MaxNumberStream();
        stream.write(ImmutableList.of(1, 2, 0));
        Assert.assertEquals("incorrect max value", 2, stream.maximum());
    }
}
