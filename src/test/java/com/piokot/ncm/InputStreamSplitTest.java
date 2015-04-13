/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link InputStreamSplit} class.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class InputStreamSplitTest {
    /**
     * Can return stream copy.
     *
     * @throws Exception If fails
     */
    @Test
    public void returnStreamCopy() throws Exception {
        final String content = "hello world";
        final InputStreamSplit split = new InputStreamSplit(
            new ByteArrayInputStream(content.getBytes(Charsets.UTF_8))
        );
        Assert.assertEquals(
            "first copy same",
            content,
            CharStreams.toString(new InputStreamReader(split.streamCopy()))
        );
        Assert.assertEquals(
            "second copy same",
            content,
            CharStreams.toString(new InputStreamReader(split.streamCopy()))
        );
    }
}
