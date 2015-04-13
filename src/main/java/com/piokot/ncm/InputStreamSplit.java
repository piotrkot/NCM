/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.google.common.io.ByteStreams;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import lombok.SneakyThrows;

/**
 * Split of the input stream.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class InputStreamSplit {
    /**
     * Temporal file name.
     */
    private static final String TEMP_NAME = "temp";
    /**
     * Temporal file suffix.
     */
    private static final String TEMP_SUFF = "txt";
    /**
     * Temporal file.
     */
    private final transient File temp;

    /**
     * Class constructor.
     *
     * @param stream Input stream to be split (copied).
     */
    @SneakyThrows
    public InputStreamSplit(final InputStream stream) {
        this.temp = File.createTempFile(TEMP_NAME, TEMP_SUFF);
        ByteStreams.copy(stream, new FileOutputStream(this.temp));
    }

    /**
     * Copy of input stream.
     *
     * @return New instance of a copy of input stream.
     */
    @SneakyThrows
    public InputStream streamCopy() {
        return new FileInputStream(this.temp);
    }
}
