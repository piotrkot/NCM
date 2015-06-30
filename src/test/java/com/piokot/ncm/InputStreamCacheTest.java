/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import java.io.ByteArrayInputStream;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link InputStreamCache} class.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class InputStreamCacheTest {
    /**
     * Can return stream reader.
     *
     * @throws Exception If fails
     */
    @Test
    public void returnStreamCopy() throws Exception {
        final String content = "hello world";
        final InputStreamCache split = new InputStreamCache(
            new ByteArrayInputStream(content.getBytes(Charsets.UTF_8))
        );
        Assert.assertEquals(
            "first reader same",
            content,
            CharStreams.toString(split.reader())
        );
        Assert.assertEquals(
            "second reader same",
            content,
            CharStreams.toString(split.reader())
        );
    }
}
