/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.Word;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link Sentence} class.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@lhsystems.com)
 * @version $Id$
 * @since 1.0
 */
public final class SentenceTest {
    /**
     * Empty sentence can be handled.
     *
     * @throws Exception If fails
     */
    @Test
    public void handlesEmptySentence() throws Exception {
        final Sentence sentence =
            new Sentence(new ArrayList<HasWord>(0), 0, Pattern.compile(""));
        Assert.assertEquals("Count incorrect", 0L, (long) sentence.counter());
        Assert.assertFalse("No words", sentence.words().iterator().hasNext());
    }

    /**
     * Regular characters can be handled.
     *
     * @throws Exception If fails
     */
    @Test
    public void handlesRegularSentence() throws Exception {
        final String token = "hello";
        final Sentence sentence = new Sentence(
            Collections.<HasWord>singletonList(new Word(token)),
            0,
            Pattern.compile("")
        );
        Assert.assertEquals("Word", token, sentence.words().iterator().next());
    }

    /**
     * Special characters can be skipped.
     *
     * @throws Exception If fails
     */
    @Test
    public void skipsSpecialCharsInSentence() throws Exception {
        final List<String> tokens = Arrays.asList("first", ".", "etc.");
        final Iterator<String> words = new Sentence(
            Arrays.<HasWord>asList(
                new Word(tokens.get(0)),
                new Word(tokens.get(1)),
                new Word(tokens.get(2))
            ),
            0,
            Pattern.compile("\\.")
        ).words().iterator();
        Assert.assertEquals(
            "word regular",
            tokens.get(0),
            words.next()
        );
        Assert.assertEquals(
            "word irregular",
            tokens.get(2),
            words.next()
        );
    }
}
