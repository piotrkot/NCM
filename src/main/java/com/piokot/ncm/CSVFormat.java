/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.google.common.base.Joiner;
import com.piokot.ncm.api.TextFormat;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * Format produced as CSV.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@Slf4j
public final class CSVFormat implements TextFormat<String> {
    /**
     * Maximum words in sentence.
     */
    @SuppressWarnings("PMD.BeanMembersShouldSerialize")
    private final int maxwords;

    /**
     * Class constructor.
     *
     * @param words Number of maximum words in sentence.
     */
    public CSVFormat(final int words) {
        this.maxwords = words;
    }

    @Override
    public Iterable<String> header() {
        final StringBuilder head = new StringBuilder();
        //@checkstyle IllegalTokenCheck (1 line)
        for (int ind = 0; ind < this.maxwords; ind++) {
            head.append(", Word ").append(ind + 1);
        }
        return Collections.singletonList(head.toString());
    }

    @Override
    public Iterable<String> footer() {
        return Collections.emptyList();
    }

    @Override
    public Iterable<String> sentence(final List<String> words, final int num) {
        Collections.sort(words, String.CASE_INSENSITIVE_ORDER);
        final String comma = ", ";
        return Collections.singletonList(
            Joiner.on(comma).join(
                Joiner.on(" ").join("Sentence", num + 1),
                Joiner.on(comma).join(words)
            )
        );
    }
}
