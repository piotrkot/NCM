/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.Word;
import java.util.Arrays;
import java.util.regex.Pattern;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link CSVSentence} class.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class CSVSentenceTest {
    /**
     * Can form CSV sentence.
     *
     * @throws Exception If fails.
     */
    @Test
    public void formCSVSentence() throws Exception {
        Assert.assertEquals(
            "not sorted",
            "Sentence 1, abba, Big, zero",
            new CSVSentence().convert(
                new Sentence(
                    Arrays.<HasWord>asList(
                        new Word("zero"),
                        new Word("abba"),
                        new Word("Big")
                    ),
                    1,
                    Pattern.compile("")
                )
            )
        );
    }
}
