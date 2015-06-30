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
    public static void main(final String... args) {
        final InputStreamCache input = new InputStreamCache(System.in);
        new Commands(
            new XMLCommand(input, System.out),
            new CSVCommand(input, System.out),
            new HELPCommand()
        ).dispatch(args);
    }
}
