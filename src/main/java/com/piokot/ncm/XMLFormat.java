/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.piokot.ncm.api.TextFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Format produced as XML.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class XMLFormat implements TextFormat<String> {
    @Override
    public Iterable<String> header() {
        return Arrays.asList(
            "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>",
            "<text>"
        );
    }

    @Override
    public Iterable<String> footer() {
        return Collections.singletonList("</text>");
    }

    @Override
    public Iterable<String> sentence(final List<String> words, final int num) {
        Collections.sort(words, String.CASE_INSENSITIVE_ORDER);
        return Iterables.concat(
            Collections.singletonList("<sentence>"),
            Iterables.transform(
                words,
                new Function<String, String>() {
                    @Override
                    public String apply(final String word) {
                        return Joiner.on("").join("<word>", word, "</word>");
                    }
                }
            ),
            Collections.singletonList("</sentence>")
        );
    }
}
