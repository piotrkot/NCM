/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import edu.stanford.nlp.ling.HasWord;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Single sentence of text.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class Sentence {
    /**
     * Sentence's number.
     */
    private final transient int count;
    /**
     * Sentence word tokens as of Stanford Natural Language Processing.
     */
    private final transient List<HasWord> chars;
    /**
     * Pattern for Strings that are not valid words.
     */
    private final transient Pattern nwords;

    /**
     * Class constructor.
     *
     * @param characters All characters forming the sentence.
     * @param number Sentence's number in text.
     * @param nonwords Characters that should not be treated as word tokens.
     */
    public Sentence(final List<HasWord> characters, final int number,
        final Pattern nonwords) {
        this.chars = new ArrayList<>(characters);
        this.count = number;
        this.nwords = nonwords;
    }

    /**
     * Tokens that form a single sentence.
     *
     * @return Unordered list of allowed tokens.
     */
    public Iterable<String> words() {
        final Collection<String> words = new ArrayList<>(0);
        for (final HasWord token : this.chars) {
            if (!token.word().matches(this.nwords.pattern())) {
                words.add(token.word());
            }
        }
        return words;
    }

    /**
     * Sentence count in text.
     *
     * @return Positive integer.
     */
    public int counter() {
        return this.count;
    }
}
