/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import java.io.StringReader;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link Text} class.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class TextTest {
    /**
     * Sentences from text should not be removed.
     *
     * @throws Exception If fails
     */
    @Test(expected = UnsupportedOperationException.class)
    public void removeSentence() throws Exception {
        new Text(new StringReader("to remove")).remove();
    }

    /**
     * Iterates over all sentences.
     *
     * @throws Exception If fails
     */
    @Test(expected = NoSuchElementException.class)
    public void iterateOverSentences() throws Exception {
        final Iterator<Sentence> text =
            new Text(new StringReader("One Sentence."));
        Assert.assertTrue("Has one sentence", text.hasNext());
        Assert.assertNotNull("Not null sentence", text.next());
        Assert.assertFalse("Has no more", text.hasNext());
        text.next();
        Assert.fail("Found sentence while there're no more");
    }
}
