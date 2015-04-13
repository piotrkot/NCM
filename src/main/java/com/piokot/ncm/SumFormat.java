/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.piokot.ncm.api.TextFormat;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * Format of sum of words in sentences.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@Slf4j
public final class SumFormat implements TextFormat<Integer> {
    @Override
    public Iterable<Integer> header() {
        return Collections.emptyList();
    }

    @Override
    public Iterable<Integer> footer() {
        return Collections.emptyList();
    }

    @Override
    public Iterable<Integer> sentence(final List<String> words, final int num) {
        return Collections.singletonList(words.size());
    }
}
