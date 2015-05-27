/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.piokot.ncm.api.DataOutStream;
import com.piokot.ncm.api.StreamTransform;
import com.piokot.ncm.api.TextFormat;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

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
        this.strm = stream;
    }
    @Override
    public void convert(final InputStream istream) {
        this.strm.write(this.frmt.header());
        final Scanner scanner = new Scanner(istream, Charsets.UTF_8.name())
            .useDelimiter("\\.");
        int index = 0;
        while (scanner.hasNext()) {
            final List<String> words = Lists.newArrayList(
                Splitter.on(Pattern.compile("[\\s,]")).trimResults()
                    .omitEmptyStrings().split(scanner.next())
            );
            if (!words.isEmpty()) {
                this.strm.write(this.frmt.sentence(words, index));
                index += 1;
            }
        }
        scanner.close();
        this.strm.write(this.frmt.footer());
    }
}
