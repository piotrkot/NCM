/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.google.common.collect.Iterables;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link XMLFormat} class.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class XMLFormatTest {
    /**
     * Can form XML sentence.
     *
     * @throws Exception If fails.
     */
    @Test
    public void formXMLSentence() throws Exception {
        Assert.assertTrue(
            "not sorted",
            Iterables.elementsEqual(
                Arrays.asList(
                    "<sentence>",
                    "<word>abba</word>",
                    "<word>Big</word>",
                    "<word>zero</word>",
                    "</sentence>"
                ),
                new XMLFormat()
                    .sentence(Arrays.asList("zero", "abba", "Big"), 0)
            )
        );
    }
}
