/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.piokot.ncm.api.SentenceFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Sentence produced in CSV format.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class CSVSentence implements SentenceFormat {
    /**
     * Word compare function.
     */
    private final transient Comparator<? super String> compare;

    /**
     * Class constructor.
     *
     * @param comparator Word compare function.
     */
    public CSVSentence(final Comparator<? super String> comparator) {
        this.compare = comparator;
    }

    @Override
    public String convert(final Sentence sentence) {
        final List<String> words = Lists.newArrayList(sentence.words());
        Collections.sort(words, this.compare);
        final String comma = ", ";
        return Joiner.on(comma).join(
            Joiner.on(" ").join("Sentence", sentence.counter()),
            Joiner.on(comma).join(words)
        );
    }
}
