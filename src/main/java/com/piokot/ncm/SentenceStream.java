/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.google.common.base.Charsets;
import com.piokot.ncm.api.DataOutStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import lombok.SneakyThrows;

/**
 * Output stream that handles string sentenced.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class SentenceStream extends DataOutputStream
    implements DataOutStream<String> {
    /**
     * Class constructor.
     *
     * @param stream Output stream.
     */
    public SentenceStream(final OutputStream stream) {
        super(stream);
    }

    @Override
    @SneakyThrows
    public void write(final Iterable<String> data) {
        for (final String line : data) {
            this.write(line.getBytes(Charsets.UTF_8));
            this.writeBytes(System.getProperty("line.separator"));
        }
    }
}
