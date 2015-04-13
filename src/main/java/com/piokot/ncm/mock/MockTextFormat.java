/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm.mock;

import com.google.common.base.Joiner;
import com.piokot.ncm.api.TextFormat;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * Text format mock class with dummy behaviour.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@Slf4j
public final class MockTextFormat implements TextFormat<String> {
    /**
     * Header.
     */
    private final transient String hdr;
    /**
     * Footer.
     */
    private final transient String ftr;

    /**
     * Class constructor.
     *
     * @param head Format header.
     * @param foot Format footer.
     */
    public MockTextFormat(final String head, final String foot) {
        this.hdr = head;
        this.ftr = foot;
    }

    @Override
    public Iterable<String> header() {
        return Collections.singletonList(this.hdr);
    }

    @Override
    public Iterable<String> footer() {
        return Collections.singletonList(this.ftr);
    }

    @Override
    public Iterable<String> sentence(final List<String> words, final int num) {
        return Collections.singletonList(
            Joiner.on(" ").join(words)
        );
    }
}
