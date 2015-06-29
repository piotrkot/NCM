/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm.api;

import com.piokot.ncm.Sentence;

/**
 * Format of a sentence to be produced. Output is parametrized.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public interface SentenceFormat {
    /**
     * Conversion of a single sentence.
     *
     * @param sentence Sentence of text.
     * @return Converted sentence in new format.
     */
    String convert(Sentence sentence);
}
