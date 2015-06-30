/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.google.common.io.ByteStreams;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import lombok.SneakyThrows;

/**
 * Temporary cache of input stream so that it can be re-read.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class InputStreamCache {
    /**
     * Temporal file name.
     */
    private static final String TEMP_NAME = "temp";
    /**
     * Temporal file suffix.
     */
    private static final String TEMP_SUFF = ".txt";
    /**
     * Temporal file.
     */
    private final transient File temp;
    /**
     * Class constructor.
     *
     * @param stream Character stream to be temporarily stored.
     */
    @SneakyThrows
    public InputStreamCache(final InputStream stream) {
        this.temp = File.createTempFile(TEMP_NAME, TEMP_SUFF);
        ByteStreams.copy(stream, new FileOutputStream(this.temp));
    }
    /**
     * Copy of original character stream.
     *
     * @return New instance of character stream reader.
     */
    @SneakyThrows
    public Reader reader() {
        return new FileReader(this.temp);
    }
}
