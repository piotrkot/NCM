/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

/**
 * Conversion application.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class Application {
    /**
     * Utility class.
     */
    private Application() {
        // intentionally empty
    }
    /**
     * Main application point.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        new Commands(
            new XMLCommand(System.in, System.out),
            new CSVCommand(System.in, System.out),
            new HELPCommand()
        ).dispatch(args);
    }
}
