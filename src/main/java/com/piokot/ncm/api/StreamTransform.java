/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm.api;

import java.io.InputStream;

/**
 * Class transforming input stream into output stream.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public interface StreamTransform {
    /**
     * Converts input stream into output stream.
     *
     * @param istream Input stream.
     */
    void convert(InputStream istream);
}
