/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm.api;

import com.github.piotrkot.cli.CommandLineArgs;

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
    boolean canRun(CommandLineArgs cline);

    /**
     * Run command.
     */
    void run();
}
