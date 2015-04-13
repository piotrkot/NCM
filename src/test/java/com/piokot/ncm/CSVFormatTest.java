/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.google.common.collect.Iterables;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link CSVFormat} class.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class CSVFormatTest {
    /**
     * Can form CSV sentence.
     *
     * @throws Exception If fails.
     */
    @Test
    public void formCSVSentence() throws Exception {
        Assert.assertTrue(
            "not sorted",
            Iterables.elementsEqual(
                Collections.singletonList("Sentence 2, abba, Big, zero"),
                new CSVFormat(0)
                    .sentence(Arrays.asList("zero", "abba", "Big"), 1)
            )
        );
    }
    /**
     * Can form CSV header.
     *
     * @throws Exception If fails.
     */
    @Test
    public void formCSVHeader() throws Exception {
        Assert.assertTrue(
            "invalid header",
            Iterables.elementsEqual(
                Collections.singletonList(", Word 1, Word 2"),
                new CSVFormat(2).header()
            )
        );
    }
}
