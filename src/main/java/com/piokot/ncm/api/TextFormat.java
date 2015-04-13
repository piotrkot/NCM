/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm.api;

import java.util.List;

/**
 * Format of text to be produced. Text consists of:
 * <ul>
 * <li>header - constant part of content put at the beginning.</li>
 * <li>footer - constant part of content put at the end.</li>
 * <li>sentences - varying part of content each built of words.</li>
 * </ul>
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public interface TextFormat<T> {
    /**
     * Format of header.
     *
     * @return Content put at the beginning.
     */
    Iterable<T> header();

    /**
     * Format of footer.
     *
     * @return Content put at the end.
     */
    Iterable<T> footer();

    /**
     * Format of single sentence.
     *
     * @param words Tokens that form a single sentence.
     * @param num Sentence number.
     * @return Content built with words.
     */
    Iterable<T> sentence(List<String> words, int num);
}
