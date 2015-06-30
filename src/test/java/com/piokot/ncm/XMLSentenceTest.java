/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.google.common.base.Joiner;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.Word;
import java.text.Collator;
import java.util.Arrays;
import java.util.regex.Pattern;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link XMLSentence} class.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class XMLSentenceTest {
    /**
     * Can form XML sentence.
     *
     * @throws Exception If fails.
     */
    @Test
    public void formXMLSentence() throws Exception {
        Assert.assertEquals(
            "not sorted",
            Joiner.on(System.lineSeparator()).join(
                "<sentence>",
                "<word>abba</word>",
                "<word>Big</word>",
                "<word>zero</word>",
                "</sentence>"
            ),
            new XMLSentence(Collator.getInstance()).convert(
                new Sentence(
                    Arrays.<HasWord>asList(
                        new Word("zero"),
                        new Word("abba"),
                        new Word("Big")
                    ),
                    0,
                    Pattern.compile("")
                )
            )
        );
    }
}
