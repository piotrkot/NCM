/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.google.common.base.Charsets;
import com.piokot.ncm.api.DataOutStream;
import com.piokot.ncm.api.StreamTransform;
import com.piokot.ncm.api.TextFormat;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;

/**
 * Stream transform based on sentences.
 * <p/>
 * Input stream is tokenized into words. Dot ('.') character is a separator
 * for sentences. Each sentence is transformed independently.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class SentenceTransform<T> implements StreamTransform {
    /**
     * Character of sentence end.
     */
    private static final String SENTENCE_END = ".";
    /**
     * Format of output handling.
     */
    private final transient TextFormat<T> frmt;
    /**
     * Output stream.
     */
    private final transient DataOutStream<T> strm;

    /**
     * Class constructor.
     *
     * @param format Text format.
     * @param stream Output stream.
     */
    public SentenceTransform(final TextFormat<T> format,
        final DataOutStream<T> stream) {
        this.frmt = format;
        this.strm  = stream;
    }

    @Override
    @SneakyThrows
    public void convert(final InputStream istream) {
        this.strm.write(this.frmt.header());
        try (final BufferedReader bufr = new BufferedReader(
            new InputStreamReader(istream, Charsets.UTF_8)
        )) {
            final StreamTokenizer stok = new StreamTokenizer(bufr);
            stok.eolIsSignificant(true);
            stok.ordinaryChar(SENTENCE_END.charAt(0));
            final List<String> words = new ArrayList<>(0);
            int index = 0;
            while (stok.nextToken() != StreamTokenizer.TT_EOF) {
                if (stok.ttype == SENTENCE_END.charAt(0)
                    && !words.isEmpty()) {
                    this.strm.write(this.frmt.sentence(words, index));
                    index += 1;
                    words.clear();
                } else {
                    if (stok.ttype == StreamTokenizer.TT_WORD) {
                        words.add(stok.sval);
                    } else if (stok.ttype == StreamTokenizer.TT_NUMBER) {
                        words.add(Double.toString(stok.nval));
                    }
                }
            }
            if (!words.isEmpty()) {
                this.strm.write(this.frmt.sentence(words, index));
                words.clear();
            }
        }
        this.strm.write(this.frmt.footer());
    }
}
