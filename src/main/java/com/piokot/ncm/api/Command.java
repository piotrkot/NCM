/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm.api;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;

/**
 * Command that can be run.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public interface Command {
    /**
     * Test if command line allows it to be run.
     *
     * @param cline Command line.
     * @return True, if can be run. False, otherwise.
     */
    boolean canRun(CommandLine cline);

    /**
     * Run command.
     */
    void run();

    /**
     * Command line option identifying that command.
     *
     * @return Option object.
     */
    Option option();
}
