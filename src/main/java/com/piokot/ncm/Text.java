/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.process.DocumentPreprocessor;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

/**
 * Text as an iterator over its sentences. As a real iterator it can iterate
 * only once. Sentences and words are extracted with Stanford Natural Language
 * Processing library. Punctuations are not treated as valid words.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@lhsystems.com)
 * @version $Id$
 * @since 1.0
 */
public final class Text implements Iterator<Sentence> {
    /**
     * Sentence count in text.
     */
    private transient int sentence;
    /**
     * Stanford Natural Language Processing document.
     */
    private final transient Iterator<List<HasWord>> document;
    /**
     * Pattern for characters not to be recognized as words.
     */
    private final transient Pattern nwords;

    /**
     * Class constructor.
     *
     * @param reader Input reader.
     */
    public Text(final Reader reader) {
        this.document = new DocumentPreprocessor(reader).iterator();
        this.nwords = Pattern.compile("\\p{Punct}");
    }

    @Override
    public boolean hasNext() {
        return this.document.hasNext();
    }

    @Override
    public Sentence next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        this.sentence += 1;
        return new Sentence(this.document.next(), this.sentence, this.nwords);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
