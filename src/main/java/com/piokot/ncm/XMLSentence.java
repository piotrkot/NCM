/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.piokot.ncm.api.SentenceFormat;
import java.util.Collections;
import java.util.List;

/**
 * Sentence produced in XML format.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class XMLSentence implements SentenceFormat {
    // @todo: customize with Collator
    @Override
    public String convert(final Sentence sentence) {
        final List<String> words = Lists.newArrayList(sentence.words());
        Collections.sort(words, String.CASE_INSENSITIVE_ORDER);
        return Joiner.on(System.lineSeparator()).join(
            Iterables.concat(
                Collections.singletonList("<sentence>"),
                Iterables.transform(
                    words,
                    new Function<String, String>() {
                        @Override
                        public String apply(final String word) {
                            return String.format("<word>%s</word>", word);
                        }
                    }
                ),
                Collections.singletonList("</sentence>")
            )
        );
    }
}
