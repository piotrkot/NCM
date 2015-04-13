/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import java.io.ByteArrayOutputStream;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link SentenceStream} class.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class SentenceStreamTest {
    /**
     * Can properly write sentences.
     *
     * @throws Exception If fails
     */
    @Test
    public void writeSentences() throws Exception {
        final String worda = "word1";
        final String wordb = "word2";
        final String nline = System.getProperty("line.separator");
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        new SentenceStream(stream).write(ImmutableList.of(worda, wordb));
        Assert.assertEquals(
            "wrong content",
            Joiner.on("").join(worda, nline, wordb, nline),
            stream.toString()
        );
    }
}
